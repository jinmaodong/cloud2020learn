package com.mdjin.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author jinmaodong
 * @date 2021/6/13
 * @since 1.0.0
 **/
@SpringBootApplication
@EnableFeignClients
public class OpenFeignOrder81Main {
    /**
     * main method
     */
    public static void main(String[] args) {
        SpringApplication.run(OpenFeignOrder81Main.class, args);
    }

}
