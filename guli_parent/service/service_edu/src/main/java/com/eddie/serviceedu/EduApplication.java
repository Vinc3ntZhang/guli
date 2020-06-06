package com.eddie.serviceedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author eddie
 * @Date 2020-06-05 14:57
 * @Version 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.eddie"})
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
