package com.mdjin.springcloud.controller;

import com.mdjin.springcloud.entities.CommonResult;
import com.mdjin.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_OK(id);
        log.info("result:" + result);
        return result;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_TimeOut(id);
        log.info("result:" + result);
        return result;
    }

    @GetMapping("/payment/hystrix/paymentCircuitBreaker/{id}")
    public CommonResult paymentCircuitBreaker(@PathVariable("id") Integer id) {
        return paymentService.paymentCircuitBreaker(id);
    }
    @GetMapping("/payment/hystrix/paymentThreadPoolLimitFlow/{id}")
    public CommonResult paymentThreadPoolLimitFlow(@PathVariable("id") Integer id){
        return paymentService.paymentThreadPoolLimitFlow(id);
    }

    @GetMapping("/payment/hystrix/paymentSemaphoreLimitFlow/{id}")
    public CommonResult paymentSemaphoreLimitFlow(@PathVariable("id") Integer id){
        return paymentService.paymentSemaphoreLimitFlow(id);
    }
}
