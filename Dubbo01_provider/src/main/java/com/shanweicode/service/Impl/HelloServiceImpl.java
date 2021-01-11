package com.shanweicode.service.Impl;

import org.springframework.stereotype.Service;

/**
 * @Package com.shanweicode.service.Impl
 * @Author shanweih
 * @Date 2020/12/22 19:01
 * @TOOL IntelliJ IDEA
 */
@Service
public class HelloServiceImpl {
    public String sayHello(String name){
        return "hello" + name;
    }
}
