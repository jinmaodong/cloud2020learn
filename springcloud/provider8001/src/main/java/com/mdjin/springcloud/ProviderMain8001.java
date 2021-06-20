package com.mdjin.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author jinmaodong
 * @date 2021/6/12
 * @since 1.0.0
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class ProviderMain8001 {
    /**
     * main method
     */
    public static void main(String[] args) {
        SpringApplication.run(ProviderMain8001.class, args);
    }

}
