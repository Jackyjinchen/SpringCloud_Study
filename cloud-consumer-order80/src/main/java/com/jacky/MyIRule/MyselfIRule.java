package com.jacky.MyIRule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MyselfIRule {

    @Bean
    public IRule myRule(){
        /**
         * com.netflix.loadbalancer中方式：
         *      RoundRobingRule 轮训（默认）
         *      RandomRule 随机
         *      RetryRule 默认轮训，若获取失败在指定时间内重试
         * WeightedResponseTimeRule 响应速度决定权重
         * BestAvailableRule 过滤多次访问故障而处于断路器跳闸状态的服务，选择并发量小的
         * AvailabilityFilteringRule 过滤故障实例，选择并发量较小
         * ZoneAvoidanceRule 默认，复合判断server所在区域性能和server的可用性选择服务器
         *
         */
        return new RandomRule();//定义为随机
    }
}
