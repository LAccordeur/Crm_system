package com.example.jjj.crm_system.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.jjj.crm_system.service.po.Customer;
import com.example.jjj.crm_system.service.po.Goods;
import com.example.jjj.crm_system.service.po.Purchasehistory;
import com.example.jjj.crm_system.utils.HttpUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by HeathHose on 2016/8/9.
 */
public class BonusPointService{
    //录入积分
    public static boolean logCreditAndHistory(int telephoneNumber, int bonusPoint, Map<String, Integer> goodsMap) throws  Exception{
        Map<String,String> map = new HashMap<>();
        String goodMapJson = JSON.toJSONString(goodsMap);
        map.put("TelephoneNumber",telephoneNumber+"");
        map.put("BonusPoint",bonusPoint+"");
        map.put("goodsMap",goodMapJson);

        // 定义发送请求的URL
        String url = HttpUtil.BASE_URL + "logCreditAndHistory.action";
        // 发送请求
        String str = HttpUtil.postRequest(url,map);

        JSONObject jsonObject = JSON.parseObject(str);
        return jsonObject.getBoolean("boolean");
    }

    // 查看会员列表（显示会员积分榜）
    public static List<Customer> getCustomersCreditByOrder() throws Exception{
        // 定义发送请求的URL
        String url = HttpUtil.BASE_URL + "getCustomersCreditByOrder.action";
        // 发送请求
        String str = HttpUtil.getRequest(url);
        List<Customer> list = JSON.parseObject(str,new TypeReference<List<Customer>>(){});
        return list;
    }

    //查看指定会员的消费记录
    public static List<Purchasehistory> getCustomerHistory(int customerID) throws Exception{
        Map<String,String> map = new HashMap<>();
        map.put("CustomerID",customerID+"");

        // 定义发送请求的URL
        String url = HttpUtil.BASE_URL + "getCustomerDetail.action";
        // 发送请求
        String str = HttpUtil.postRequest(url,map);

        List<Purchasehistory> list = JSON.parseObject(str,new TypeReference<List<Purchasehistory>>(){});
        return list;
    }

    //查看指定会员的消费总额
    public static int getCustomerExpenditure(int customerID) throws Exception{
        Map<String,String> map = new HashMap<>();
        map.put("CustomerID",customerID+"");

        // 定义发送请求的URL
        String url = HttpUtil.BASE_URL + "getCustomerExpenditure.action";
        // 发送请求
        String str = HttpUtil.postRequest(url,map);

        JSONObject jsonObject = JSON.parseObject(str);
        return jsonObject.getIntValue("Expenditure");
    }
}
