package com.jacky.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfo_OK(Integer id){
        return "线程池：" +Thread.currentThread().getName() + " paymentInfo_OK,Id:"+id;
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentInfo_Timeout(Integer id){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" +Thread.currentThread().getName() + " paymentInfo_Timeout,Id:"+id;
    }

    public String paymentInfo_TimeOutHandler(Integer id){
        return "处理失败线程池：" +Thread.currentThread().getName() + " paymentInfo_TimeOutHandler,Id:"+id;
    }

    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            /**
             * 配置断路器信息
             * 1、判断阈值
             * 2、判断百分比
             * 3、断路 Closed---->Open
             * 4、时间窗口期尝试Half-Open
             */
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),  //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),   //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),  //时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"), //失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(Integer id){
        if (id < 0){
            throw new RuntimeException("*****id 不能负数");
        }
        //等价于UUID.randomUUID().toString() huTool中方法
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功,流水号："+serialNumber;
    }
    public String paymentCircuitBreaker_fallback(Integer id){
        return "id 不能负数，请稍候再试,(┬＿┬)/~~     id: " +id;
    }

}
