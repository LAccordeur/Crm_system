package com.example.jjj.crm_system.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.jjj.crm_system.service.po.Activity;
import com.example.jjj.crm_system.service.po.Goods;
import com.example.jjj.crm_system.utils.HttpUtil;

import java.util.List;

/**
 * Created by HeathHose on 2016/8/9.
 */
public class CustomerService{
//    //验证顾客用户名与密码(返回用户ID)
//    public int validateCustomer(String username, String pwd);
//
//    //注册顾客用户名与密码
//    //public registerCustomer (String username,String pwd)
//
//    //修改顾客用户名与密码
//    // changeCustomer (String username,String pwd)
//
    //查看已发布活动详情
    public static List<Activity> seeAcitvityInf() throws Exception{

        // 定义发送请求的URL
        String url = HttpUtil.BASE_URL + "seeAcitvityInf.action";
        // 发送请求
        String str = HttpUtil.getRequest(url);
        List<Activity> list = JSON.parseObject(str,new TypeReference<List<Activity>>(){});

        return list;
    }
}
