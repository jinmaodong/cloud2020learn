package com.mdjin.springcloud.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.mdjin.springcloud.constant.StatusConstant;
import com.mdjin.springcloud.entities.CommonResult;
import com.mdjin.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jinmaodong
 * @date 2021/6/12
 * @since 1.0.0
 **/
@RestController
@Slf4j
public class OrderController {

    static final String url = "http://CLOUD-PAYMENT-SERVICE";

    @Resource(name = "restTemplate")
    private RestTemplate restTemplate;

    @PostMapping("/order/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(url+"/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/order/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") long id){
        return restTemplate.getForObject(url+"/payment/get/"+id,  CommonResult.class);
    }
    @GetMapping("/order/payment/getAll")
    public CommonResult<Payment> getAllPayment(){
        return restTemplate.getForObject(url+"/payment/getAll",  CommonResult.class);
    }

    @GetMapping("/order/payment/discovery")
    public CommonResult<Object> discovery(){
        ResponseEntity<CommonResult> responseEntity = restTemplate.getForEntity(url+"/payment/discovery", CommonResult.class);
        if(responseEntity.getStatusCode().is2xxSuccessful()){
            return responseEntity.getBody();
        }else{
            return new CommonResult<>(StatusConstant.STATUS_404.code(),"操作失败.");
        }
    }

    @GetMapping("/order/payment/lb")
    public CommonResult<Object> lb() {
        return restTemplate.getForObject(url+"/payment/lb",  CommonResult.class);
    }

    @GetMapping("/order/payment/timeout")
    public CommonResult timeOut(){
        return restTemplate.getForObject(url+"/payment/timeout",  CommonResult.class);
    }


}
