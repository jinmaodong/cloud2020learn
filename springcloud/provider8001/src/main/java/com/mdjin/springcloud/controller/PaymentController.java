package com.mdjin.springcloud.controller;

import com.mdjin.springcloud.constant.StatusConstant;
import com.mdjin.springcloud.entities.CommonResult;
import com.mdjin.springcloud.entities.Payment;
import com.mdjin.springcloud.service.PaymentService;
import com.mdjin.springcloud.service.impl.PaymentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author jinmaodong
 * @date 2021/6/12
 * @since 1.0.0
 **/
@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;


    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("插入结果："+result);

        if(result > 0){
            return new CommonResult(StatusConstant.STATUS_200.code(), "插入数据库成功",result,serverPort);
        }else{
            return new CommonResult(StatusConstant.STATUS_404.code(), "插入数据库失败",null,serverPort);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") long id) {
        Payment result = paymentService.getPaymentById(id);
        log.info("查询结果："+result);

        if(result != null){
            return new CommonResult(StatusConstant.STATUS_200.code(), "查询成功",result,serverPort);
        }else{
            return new CommonResult(StatusConstant.STATUS_404.code(), "没有对应记录,查询ID："+id,null,serverPort);
        }
    }

    @GetMapping("/payment/getAll")
    public CommonResult getAllPayment() {
        List<Payment> result = paymentService.getAllPayment();
        log.info("查询结果："+result);

        if(result != null){
            return new CommonResult(StatusConstant.STATUS_200.code(), "查询成功",result,serverPort);
        }else{
            return new CommonResult(StatusConstant.STATUS_404.code(), "没有记录。",null,serverPort);
        }
    }

    @GetMapping("/payment/discovery")
    public CommonResult discovery(){
        List<String> services = discoveryClient.getServices();
        for(String s : services){
            log.info("service-name:"+s);
            List<ServiceInstance> instances = discoveryClient.getInstances(s);
            for(ServiceInstance si : instances){
                log.info("service-info::host:"+si.getHost()+",InstanceId:"+si.getInstanceId()+",Scheme:"+si.getScheme()+",ServiceId:"+si.getServiceId()+",Metadata"+si.getMetadata()
                        +",Port:"+si.getPort()+",Uri:"+si.getUri());
            }
        }
//        final String SERVICE_NAME = "cloud-order-service";
        return new CommonResult(StatusConstant.STATUS_200.code(), "服务信息",discoveryClient,serverPort);
//        return this.discoveryClient;
    }

    @GetMapping("/payment/lb")
    public CommonResult lb(){
        return new CommonResult(StatusConstant.STATUS_200.code(), "服务信息",null,serverPort);
    }

    @GetMapping("/payment/timeout")
    public CommonResult timeOut(){
        try{
            TimeUnit.SECONDS.sleep(30);
        }catch (Exception e){
            log.error(e.getMessage());
        }finally {
            log.info("TimeOut Over.");
        }
        return new CommonResult(StatusConstant.STATUS_200.code(), "TimeOut Over.",null,serverPort);
    }
}
