package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.Service.UserService;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.Result;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserService userService;

    @RequestMapping("/getUsername")
    public Result getUsername(){
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            System.out.println(user);
            return new Result(true, MessageConstant.GET_USERNAME_SUCCESS,user);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_USERNAME_FAIL);
        }

    }

    @RequestMapping("/xiugai")
    public Result xiugai(@RequestBody Map<String,String> format,String username){
        String s1= (String) format.get("add");
        String s2= (String) format.get("att");

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (s1.equals(s2)){
            String findbyadd = userService.findbyadd(username);

            boolean flag=bCryptPasswordEncoder.matches((CharSequence) format.get("attention"),findbyadd);
            if (flag) {
                s1=bCryptPasswordEncoder.encode(s1);
                userService.updata(s1, username);
                return new Result(true,"修改成功");
            }else {
                return new Result(false,"原密码错误");
            }
        }else {
            return new Result(false,"密码两次修改错误");
        }
    }
    @RequestMapping("/findmenu")
    public Result findmenu(){
        try {
            List<Map> list =userService.findmenu();
            return new Result(true,"展示成功",list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"列表展示失败");
        }
    }
}
