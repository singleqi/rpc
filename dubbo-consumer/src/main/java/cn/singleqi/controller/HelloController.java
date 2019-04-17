package cn.singleqi.controller;

import cn.singleqi.service.HelloService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Reference
    private HelloService helloService;

    @RequestMapping("/hello")
    public String hello(){
        String hello = helloService.sayHello("world");
        System.out.println(helloService.sayHello("singleqi"));
        return hello;
    }
}
