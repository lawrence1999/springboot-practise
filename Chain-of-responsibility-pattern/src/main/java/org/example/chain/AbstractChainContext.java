package org.example.chain;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Description: 抽象责任链上下文
 * Date: 2024/4/17
 * Author: lawrence
 */
@Component
public class AbstractChainContext<T> implements CommandLineRunner {

    /**
     * 保存责任链的上下文信息，key为对应的业务mark，value为责任链List
     */
    private final Map<String, List<AbstractChainHandler>> abstractChainHandlerContainer = new HashMap<>();

    /**
     * 执行责任链中的组件
     * @param mark   责任链标识
     * @param requestParam 责任链入参
     */
    public void handler(String mark, T requestParam) {
        List<AbstractChainHandler> abstractChainHandlers = abstractChainHandlerContainer.get(mark);
        if (CollectionUtils.isEmpty(abstractChainHandlers)) {
            throw new RuntimeException(String.format("[%s] Chain of Responsibility ID is undefined.", mark));
        }
        //注意我这里的责任链是对每个handler的输入都是相同的
        //如果责任链中下一位置的输入依赖上一位置的输出，可以在这里修改责任链的执行逻辑
        abstractChainHandlers.forEach(each -> each.handler(requestParam));
    }

    @Override
    public void run(String... args) throws Exception {
        //获取IOC容器中所有的责任链组件,通过实现的接口类AbstractChainHandler来查找
        Map<String, AbstractChainHandler> chainFilterMap = ApplicationContextHolder.getBeansOfType(AbstractChainHandler.class);

        //根据mark标记分类，以及Order的执行顺序依次放入到abstractChainHandlerContainer中
        chainFilterMap.forEach((beanName, bean) -> {
            List<AbstractChainHandler> abstractChainHandlers = abstractChainHandlerContainer.get(bean.mark());
            if (CollectionUtils.isEmpty(abstractChainHandlers)) {
                abstractChainHandlers = new ArrayList();
            }
            abstractChainHandlers.add(bean);
            List<AbstractChainHandler> actualAbstractChainHandlers = abstractChainHandlers.stream()
                    .sorted(Comparator.comparing(Ordered::getOrder))
                    .collect(Collectors.toList());
            abstractChainHandlerContainer.put(bean.mark(), actualAbstractChainHandlers);
        });
    }
}
