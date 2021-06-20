package com.mdjin.springcloud.service;

import com.mdjin.springcloud.constant.StatusConstant;
import com.mdjin.springcloud.entities.CommonResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author jinmaodong
 * @date 2021/6/14
 * @since 1.0.0
 **/
@Service
@Slf4j
public class PaymentService {

    @Value("${server.port}")
    public String serverPort;

    /**
     * 正常访问方法
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id){
        return "线程池"+Thread.currentThread().getName()+" paymentInfo_OK,id = "+id+"\t"+"O(∩_∩)O";
    }


    /**
     * 访问超时，服务降级
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000")})
    public String paymentInfo_TimeOut(Integer id){
        try{
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池"+Thread.currentThread().getName()+" paymentInfo_TimeOut,id = "+id+"\t"+"O(∩_∩)O";
    }

    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池"+Thread.currentThread().getName()+" paymentInfo_TimeOutHandler,id = "+id+"\t"+"O(∩_∩)O系统超时，请等待。";
    }

    /**
     * 服务熔断
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallBack",commandProperties = {
        @HystrixProperty(name="circuitBreaker.enabled",value = "true"),//开启断路器
        @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数阈值，满足这个阈值才会触发断路器判定
        @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//睡眠窗口（毫秒），断路器打开之后，每个一个窗口期会尝试放过一个请求
        @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "60"),//错误阈值百分比，在一个窗口期内错误请求比例超过多少触发断路器
    })
    public CommonResult paymentCircuitBreaker(Integer id){
        int randomInt = (new Random()).nextInt();
        log.info("随机数为："+id);
        if(id>0){
            throw new RuntimeException();
        }
        return new CommonResult(StatusConstant.STATUS_200.code(), "paymentCircuitBreaker.",null,serverPort);
    }
    public CommonResult paymentCircuitBreaker_fallBack(Integer id){
        return new CommonResult(StatusConstant.STATUS_404.code(), "paymentCircuitBreaker_fallBack.",null,serverPort);
    }

    /**
     * 线程池配置
     * @param id
     * @return
     */
    @HystrixCommand(
            commandKey = "paymentThreadPoolLimitFlow",
            groupKey = "paymentService",
            threadPoolKey = "paymentService",
            fallbackMethod = "paymentThreadPoolLimitFlow_fallBack",
            commandProperties = {
                @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
                @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),//配置为线程池隔离隔离
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
                    @HystrixProperty(name="fallback.isolation.semaphore.maxConcurrentRequests",value="50")//fallback降级方法最大并行数
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "5"),
//                    @HystrixProperty(name = "maximumSize", value = "5"),
                    @HystrixProperty(name = "maxQueueSize", value = "5")
            }
    )
    public CommonResult paymentThreadPoolLimitFlow(Integer id){
        log.info("成功-线程代码为："+Thread.currentThread().getName());
        return new CommonResult(StatusConstant.STATUS_200.code(), "paymentThreadPoolLimitFlow.",null,serverPort);
    }

    public CommonResult paymentThreadPoolLimitFlow_fallBack(Integer id){
        log.info("降级-线程代码为："+Thread.currentThread().getName());
        return new CommonResult(StatusConstant.STATUS_404.code(), "paymentThreadPoolLimitFlow_fallBack.",null,serverPort);
    }
    /**
     * 信号量配置
     * @param id
     * @return
     */
    @HystrixCommand(
            commandKey = "paymentThreadPoolLimitFlow",
            groupKey = "paymentService",
            fallbackMethod = "paymentThreadPoolLimitFlow_fallBack",
            commandProperties = {
//                @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
                @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),//配置为信号量隔离隔离
                @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "50"),//设置使用SEMAPHORE隔离策略的时候，允许访问的最大并发量，超过这个最大并发量，请求直接被reject
                    @HystrixProperty(name="fallback.isolation.semaphore.maxConcurrentRequests",value="50")//fallback降级方法最大并行数
            }
    )
    public CommonResult paymentSemaphoreLimitFlow(Integer id){
        log.info("成功-线程代码为："+Thread.currentThread().getName());
        return new CommonResult(StatusConstant.STATUS_200.code(), "paymentSemaphoreLimitFlow.",null,serverPort);
    }

    public CommonResult paymentSemaphoreLimitFlow_fallBack(Integer id){
        log.info("降级-线程代码为："+Thread.currentThread().getName());
        return new CommonResult(StatusConstant.STATUS_404.code(), "paymentSemaphoreLimitFlow_fallBack.",null,serverPort);
    }
}
