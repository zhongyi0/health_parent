package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.aliyuncs.exceptions.ClientException;
import com.itheima.Service.OrderService;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisMessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.Order;
import com.itheima.utils.SMSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Reference
    private OrderService orderService;

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/sumbit")
    public Result findByorderInfo(@RequestBody Map map) {
        String telephone = (String) map.get("telephone");//得到表单输入的电话号码
        String sendtypeOrder = RedisMessageConstant.SENDTYPE_ORDER;
        //得到电话号码+状态的组合成的 验证码
        String s = jedisPool.getResource().get(telephone +sendtypeOrder);
        String validateCodeButton = (String) map.get("validateCode");

        if (validateCodeButton == null || !validateCodeButton.equals(s)) {
            return new Result(false, MessageConstant.GET_ORDERSETTING_FAIL);
        }

        Result result = null;

        try {
            map.put("OrderTpye", Order.ORDERTYPE_WEIXIN);
            result = orderService.order(map);
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }

        if (result.isFlag()) {
            try {
                String orderDate = (String) map.get("orderDate");
                SMSUtils.sendShortMessage(SMSUtils.ORDER_NOTICE, telephone, orderDate);
            } catch (ClientException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    @RequestMapping("/findById")
    public Result findById(Integer id){
        try {
            Map map=orderService.findById(id);
            return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_ORDER_FAIL);
        }
    }
}



