package com.mdjin.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author jinmaodong
 * @date 2021/6/13
 * @since 1.0.0
 **/
@SpringBootApplication
@EnableEurekaServer
public class EurekaServer7002Main {

    public static void main(String args[]){
        SpringApplication.run(EurekaServer7002Main.class, args);
    }

}
