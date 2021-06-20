package com.mdjin.springcloud;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletRegistration;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author jinmaodong
 * @date 2021/6/14
 * @since 1.0.0
 **/
@SpringBootApplication
@EnableFeignClients//有它的时候就不用Eureka注解了？
@EnableHystrix
public class HystrixOrder80Main {
    /**
     * main method
     */
    public static void main(String[] args) {
        SpringApplication.run(HystrixOrder80Main.class, args);
    }

    /**
     * 此配置时为了服务监控而配置，与服务容错本身无关，spring cloud升级后的坑。
     * ServletRegistrationBean因为springboot的默认路径不是"/hystrix.stream"，只要在自己的项目上配置下面的Servlet就好了
     * @return
     */
    @Bean
    public ServletRegistrationBean getServlet(){
        HystrixMetricsStreamServlet hystrixMetricsStreamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(hystrixMetricsStreamServlet);
        servletRegistrationBean.setLoadOnStartup(1);
        servletRegistrationBean.addUrlMappings("/hystrix.stream");
        servletRegistrationBean.setName("HystrixMetricsStreamServlet");
        return servletRegistrationBean;
    }
}
