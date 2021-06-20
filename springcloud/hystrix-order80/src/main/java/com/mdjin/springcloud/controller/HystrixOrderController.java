package com.mdjin.springcloud.controller;

import com.mdjin.springcloud.service.PaymentOrderService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author jinmaodong
 * @date 2021/6/14
 * @since 1.0.0
 **/
@RestController
@Slf4j
public class HystrixOrderController {

    @Resource
    private PaymentOrderService paymentOrderService;

    @GetMapping("/order/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        return paymentOrderService.paymentInfo_OK(id);
    }

    @GetMapping("/order/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000")})
    public String paymentInfo_Timeout(@PathVariable("id") Integer id){
        log.info("当前线程："+Thread.currentThread().getName());
        return paymentOrderService.paymentInfo_Timeout(id);
    }

    public String paymentInfo_TimeOutHandler(@PathVariable("id") Integer id){
        return "我是消费者80，对方支付系统繁忙，请10S之后重试或者自己运行出错，检查自己.";
    }
}
