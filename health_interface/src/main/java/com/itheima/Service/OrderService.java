package com.itheima.Service;

import com.itheima.entity.Result;

import java.util.List;
import java.util.Map;

public interface OrderService {
    Result order(Map map) throws Exception;

    Map findById(Integer id);

}
