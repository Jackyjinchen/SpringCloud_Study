package com.jacky.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class OrderFallbackService implements OrderHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "OrderFallbackService OK";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "OrderFallbackService 超时";
    }
}
