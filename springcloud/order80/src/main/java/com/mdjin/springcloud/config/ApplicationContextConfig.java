package com.mdjin.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.security.PublicKey;

/**
 * @author jinmaodong
 * @date 2021/6/12
 * @since 1.0.0
 **/
@Configuration
public class ApplicationContextConfig {

    @Bean(name = "restTemplate")
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean(name = "noBalanceRestTemplate")
    public RestTemplate getRestTemplateNoBalance(){
        return new RestTemplate();
    }
}
