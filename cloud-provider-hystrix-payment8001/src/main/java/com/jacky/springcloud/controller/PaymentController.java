package com.jacky.springcloud.controller;

import com.jacky.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String st = paymentService.paymentInfo_OK(id);
        log.info(st);
        return st;
    }

    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable("id") Integer id){
        String st = paymentService.paymentInfo_Timeout(id);
        log.info(st);
        return st;
    }

    @GetMapping(value = "/payment/hystrix/break/{id}")
    public String paymentInfo_Break(@PathVariable("id") Integer id){
        String st = paymentService.paymentCircuitBreaker(id);
        log.info("*****result:"+st);
        return st;
    }
}
