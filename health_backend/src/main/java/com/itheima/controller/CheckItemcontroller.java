package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.Service.CheckItemService;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/checkitem")
public class CheckItemcontroller {

    /*
    * 体检检查项管理
    * */
    @Reference//查找服务
    private CheckItemService checkItemService;
    //新增检查项
    @RequestMapping("/add")
    public Result add( @RequestBody CheckItem checkItem){
        try {
            checkItemService.add(checkItem);
        } catch (Exception e) {
            e.printStackTrace();
            //服务调用失败
            return new Result(false,MessageConstant.ADD_CHECKITEM_FAIL);
        }
        return new Result(true,MessageConstant.ADD_CHECKITEM_SUCCESS);
    }
    //查询指定name或code来查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult page = checkItemService.findPage(queryPageBean);
        return page;
    }
    //删除一项信息
    @PreAuthorize("hasAnyAuthority('CHECKITEM_DELETE')")//权限校验
    @RequestMapping("/delete")
    public Result deleteOne( Integer id){
        try {
            checkItemService.deleteOne(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_CHECKITEM_FAIL);
        }
        return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }

    //通过id来回显数据
    @RequestMapping("/findById")
    public Result findById(Integer id){
        try {
            CheckItem checkItem = checkItemService.findByIdAll(id);
            return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkItem);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }

    }

    //修改数据
    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckItem checkItem){
        try {
            checkItemService.editUpdate(checkItem);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_CHECKITEM_FAIL);
        }
        return new Result(true,MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }
    @RequestMapping("/findAll")
    public Result findAll(){
        List<CheckItem> list=checkItemService.findAll();
        if (list!=null&&list.size()>0){
            return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,list);
        }
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);

    }
}
