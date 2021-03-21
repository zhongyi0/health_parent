package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.itheima.Service.MemberService;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisMessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.Member;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Reference
    private MemberService memberService;
    @Autowired
    private JedisPool jedisPool;
    @RequestMapping("/login")
    public Result member(HttpServletResponse response, @RequestBody Map map) {
        String validateCode = (String) map.get("validateCode");
        String telephone = (String) map.get("telephone");
        //redis中存储的电话号码+类型
        String s = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_LOGIN);
        //验证码不一致，登录失败
        if (s == null || s.equals(validateCode)) {
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
        Member member = memberService.findByTelephone(telephone);

       //没有进行会员注册
        if (member == null) {
            member = new Member();
            member.setName(telephone);
            member.setRegTime(new Date());
            memberService.add(member);
        }

        Cookie cookie=new Cookie("login_member_telephone",telephone);
        cookie.setPath("/");//路径
        cookie.setMaxAge(60*60*24);//有效期30天
        response.addCookie(cookie);
        //保存会员信息到Redis中
        String json = JSON.toJSON(member).toString();
        jedisPool.getResource().setex(telephone,60*30,json);

        return new Result(true,MessageConstant.LOGIN_SUCCESS);
    }

}
