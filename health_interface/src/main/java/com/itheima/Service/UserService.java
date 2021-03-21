package com.itheima.Service;

import com.itheima.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    public User findByUsername(String username) ;

    String findbyadd(String username);

    void updata(String s1, String username);

    List<Map> findmenu();
}
