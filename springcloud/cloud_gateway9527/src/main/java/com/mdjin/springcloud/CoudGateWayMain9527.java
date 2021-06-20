package com.mdjin.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author jinmaodong
 * @date 2021/6/20
 * @since 1.0.0
 **/
@SpringBootApplication
@EnableEurekaClient
public class CoudGateWayMain9527 {
    /**
     * main method
     */
    public static void main(String[] args) {
        SpringApplication.run(CoudGateWayMain9527.class,args);
    }

}
