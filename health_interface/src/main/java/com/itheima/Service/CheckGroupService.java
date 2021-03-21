package com.itheima.Service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;

import java.util.List;

public interface CheckGroupService {
    void addd(CheckGroup checkGroup, Integer[] checkItems);

    PageResult findAll(QueryPageBean queryPageBean);

    CheckGroup findById(Integer id);

    List<Integer> findGroupId(Integer id);

    void edit(CheckGroup checkGroup,Integer[] checkitemIds);


    List<CheckGroup> create();


}
