package com.mdjin.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author jinmaodong
 * @date 2021/6/13
 * @since 1.0.0
 **/
@SpringBootApplication
@EnableEurekaClient
public class Order82Main {
    /**
     * main method
     */
    public static void main(String[] args) {
        SpringApplication.run(Order82Main.class, args);
    }

}
