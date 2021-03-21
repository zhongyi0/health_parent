package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.Service.UserService;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Set;

@Component
public class SpringSecurityUserService implements UserDetailsService {

    @Reference//远程dubbo调用，用户服务 1
    private UserService userService;
    //根据用户名查询数据库用户信息
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userService.findByUsername(username);

        if (user==null){//证明没有这用户
            return null;
        }

        ArrayList<GrantedAuthority> list = new ArrayList<>();
        //动态获取当前用户授权
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            //遍历角色集合，为用户授予角色
            list.add(new SimpleGrantedAuthority(role.getKeyword()));
            Set<Permission> permissions = role.getPermissions();
            for (Permission permission : permissions) {
                //遍历权限集合，为用户授权
                list.add(new SimpleGrantedAuthority(permission.getKeyword()));
            }
        }

        UserDetails user1 = new
                org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),list);
        return user1;
    }
}
