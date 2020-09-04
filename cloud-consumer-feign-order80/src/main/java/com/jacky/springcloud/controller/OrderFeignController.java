package com.jacky.springcloud.controller;

import com.jacky.springcloud.entities.CommonResult;
import com.jacky.springcloud.entities.Payment;
import com.jacky.springcloud.service.PaymentFeignService80;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeignService80 paymentFeignService80;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService80.getPaymentById(id);

    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout(){
        return paymentFeignService80.paymentFeignTimeout();
    }
}
