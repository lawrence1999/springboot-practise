package org.example;


import org.example.chain.AbstractChainContext;
import org.example.dto.Input;
import org.example.service.MarkEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * Date: 2024/4/17
 * Author: lawrence
 */
@RestController
public class HelloController {

    @Autowired
    private AbstractChainContext<Input> abstractChainContext;

    @GetMapping("/hello")
    public void hello() {
        Input input = new Input("这是测试参数1","这是测试参数2");
        abstractChainContext.handler(MarkEnum.USER_REGISTER_FILTER.name(), input);
    }

}
