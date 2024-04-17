package org.example.chain;

import org.springframework.core.Ordered;

/**
 * Description: 抽象责任链接口
 * 继承Ordered接口，可以指定具体责任链的执行顺序
 * Date: 2024/4/17
 * Author: lawrence
 */
public interface AbstractChainHandler<T> extends Ordered {

    /**
     * 责任链的执行逻辑
     * @param requestParam  责任链入参
     */
    void handler(T requestParam);

    /**
     *
     * @return 标识不同的责任链，处理不同的业务
     */
    String mark();
}
