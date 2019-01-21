package com.iamlook.system;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/***
* @Description: 用户服务提供启动类
* @Param:  
* @return:  
* @Author: youxun 
* @Date: 2019/1/21 
*/ 
@EnableDubbo
@SpringBootApplication
public class DubboSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboSystemApplication.class, args);
    }
}
