package com.itheima.Service;

import com.itheima.pojo.OrderSetting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface OrderSettingService {
    void add(ArrayList<OrderSetting> arrayList);

    List<Map> getOrderByMonth(String date);


    void editNumberByDate(OrderSetting orderSetting);
}
