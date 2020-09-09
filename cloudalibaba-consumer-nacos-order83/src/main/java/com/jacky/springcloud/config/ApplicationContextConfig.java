package com.jacky.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {
    @Bean //注入RestTemplates
    @LoadBalanced //开启ribbon负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
