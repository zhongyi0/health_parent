package com.itheima.Service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckItem;

import java.util.List;

public interface CheckItemService {
    //检查项目服务接口
    public void add(CheckItem checkItem);

    //查找总记录数，当前页结果
    PageResult findPage(QueryPageBean queryPageBean);

    //通过id删除选项
    void deleteOne(Integer id);

    //通过id查询到数据
    CheckItem findByIdAll(Integer id);

    //修改数据
    void editUpdate(CheckItem checkItem);

    //查询所有
    List<CheckItem> findAll();
}
