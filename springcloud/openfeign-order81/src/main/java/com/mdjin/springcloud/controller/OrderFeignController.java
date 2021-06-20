package com.mdjin.springcloud.controller;

import com.mdjin.springcloud.entities.CommonResult;
import com.mdjin.springcloud.entities.Payment;
import com.mdjin.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author jinmaodong
 * @date 2021/6/13
 * @since 1.0.0
 **/
@RestController
@Slf4j
public class OrderFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/feignOrder/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id")long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/feignOrder/payment/getAll")
    public CommonResult<Payment> getAll(){
        return paymentFeignService.getAllPayment();
    }

    @PostMapping("/feignOrder/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment){
        return paymentFeignService.create(payment);
    }

    @GetMapping("/feignOrder/payment/discovery")
    public CommonResult discovery(){
        return paymentFeignService.discovery();
    }

    @GetMapping("/feignOrder/payment/lb")
    public CommonResult lb() {
        return paymentFeignService.lb();
    }

    @GetMapping("/feignOrder/payment/timeout")
    public CommonResult timeout() {
        return paymentFeignService.timeOut();
    }
}
