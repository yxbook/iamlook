package com.iamlook.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.iamlook.api.service.DemoService;

/*** 
* @Description: 服务提供类
* @Param:  
* @return:  
* @Author: youxun 
* @Date: 2019/1/21 
*/ 
@Service(version = "${demo.service.version}")
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        return "Hello, " + name + " (from Spring Boot)";
    }
}
