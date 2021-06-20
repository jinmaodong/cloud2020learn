package com.mdjin.springcloud.service;

import com.mdjin.springcloud.entities.CommonResult;
import com.mdjin.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author jinmaodong
 * @date 2021/6/13
 * @since 1.0.0
 **/
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")//不区分大小写
@Component
public interface PaymentFeignService {

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment);

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") long id);

    @GetMapping("/payment/getAll")
    public CommonResult getAllPayment();

    @GetMapping("/payment/discovery")
    public CommonResult discovery();

    @GetMapping("/payment/lb")
    public CommonResult lb();

    @GetMapping("/payment/timeout")
    public CommonResult timeOut();
}
