package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.Service.OrderService;
import com.itheima.Service.TimeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Time")
public class TestController {

    @Reference
    private TimeService timeService;

    @RequestMapping("/findall")
    public List<Map> test(){
        List<Map> list = timeService.findAll();
        return list;
    }


}
