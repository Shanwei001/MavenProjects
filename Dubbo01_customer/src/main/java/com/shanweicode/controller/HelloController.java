package com.shanweicode.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shanweicode.service.HelloService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Package com.shanweicode.controller
 * @Author shanweih
 * @Date 2020/12/22 19:50
 * @TOOL IntelliJ IDEA
 */
@Controller
@RequestMapping("/demo")
public class HelloController {
    @Reference
    private HelloService helloService;
    /**
     * @ResponseBody:加注释不会跳转页面，直接返回字符串
     * 不加注释:直接跳转页面
     * */
    @RequestMapping("/hello")
    @ResponseBody
    public String getName(String name){
        String result = helloService.sayHello(name);
        return result;
    }
}
