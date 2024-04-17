package org.example;

import org.example.chain.AbstractChainContext;
import org.example.dto.Input;
import org.example.service.MarkEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description: ${DESCRIPTION}
 * Date: 2024/4/17
 * Author: lawrence
 */
@SpringBootApplication
public class ChainMain {
    public static void main(String[] args) {
        SpringApplication.run(ChainMain.class, args);
    }
}