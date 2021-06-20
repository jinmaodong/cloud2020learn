package com.mdjin.springcloud.lb.impl;

import com.mdjin.springcloud.lb.MyLoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jinmaodong
 * @date 2021/6/13
 * @since 1.0.0
 **/
@Slf4j
@Component
public class MyLoadBalancerImpl implements MyLoadBalancer {

    /**
     * 访问次数计数器
     */
    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public ServiceInstance chooseServer(List<ServiceInstance> list) {
        int index =getAndIncrement() % list.size();
        return list.get(index);
    }

    /**
     * 获取当前访问次数，采用CAS+自旋锁实现并发
     * @return
     */
    private Integer getAndIncrement(){
        int current;
        int next;
        for(;;){
            current = count.get();
            next = current > Integer.MAX_VALUE ? 0 : current+1;
            if(count.compareAndSet(current, next)){
                log.info("访问次数为："+next);
                return next;
            }
        }
    }
}
