package com.mdjin.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自己实现负载均衡
 */
public interface MyLoadBalancer {

    public ServiceInstance chooseServer(List<ServiceInstance> list);
}
