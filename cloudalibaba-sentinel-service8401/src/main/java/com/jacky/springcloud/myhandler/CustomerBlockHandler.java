package com.jacky.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.jacky.springcloud.entities.CommonResult;

public class CustomerBlockHandler {

    public static CommonResult handleException2(BlockException exception) {
        return new CommonResult(2020, "自定义限流处理信息....CustomerBlockHandler");

    }
}
