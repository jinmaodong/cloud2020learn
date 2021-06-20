package com.mdjin.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * @author jinmaodong
 * @date 2021/6/14
 * @since 1.0.0
 **/
@SpringBootApplication
@EnableHystrix
public class HystrixProvider8003Main {
    /**
     * main method
     */
    public static void main(String[] args) {
        SpringApplication.run(HystrixProvider8003Main.class, args);
    }

}
