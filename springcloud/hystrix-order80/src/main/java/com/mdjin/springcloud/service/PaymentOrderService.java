package com.mdjin.springcloud.service;

import com.mdjin.springcloud.service.impl.PaymentOrderServiceImpl;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author jinmaodong
 * @date 2021/6/14
 * @since 1.0.0
 **/
@FeignClient(name = "CLOUD-HYSTRIX-PROVIDER-SERVICE",fallback = PaymentOrderServiceImpl.class)
public interface PaymentOrderService {

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id")  Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable("id")  Integer id);

}
