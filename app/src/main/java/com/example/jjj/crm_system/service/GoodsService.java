package com.example.jjj.crm_system.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.jjj.crm_system.service.po.Goods;
import com.example.jjj.crm_system.utils.HttpUtil;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HeathHose on 2016/8/9.
 */
public class GoodsService {

    //获取所有商品信息
    public static List<Goods> getGoodsInf() throws Exception {

        // 定义发送请求的URL
        String url = HttpUtil.BASE_URL + "getGoodsInf.action";
        // 发送请求
        String str = HttpUtil.getRequest(url);
        List<Goods> list = JSON.parseObject(str,new TypeReference<List<Goods>>(){});
        return list;
    }

    //按名称获取商品信息
//    public Goods getGoodsInfByName(String goodsName) throws Exception{
//    }
//
    //更新商品
    public static boolean upadateGoodsInf(String goodsID, Goods good) throws Exception{

        Map<String,String> map = new HashMap<>();
        map.put("GoodsID",goodsID);
        map.put("GoodsName",good.getGoodsname());
        map.put("GoodsMoney",good.getGoodsmoney()+"");
        map.put("GoodsDetail",good.getGoodsdetail());

        // 定义发送请求的URL
        String url = HttpUtil.BASE_URL + "upadateGoodsInf.action";
        // 发送请求
        String str = HttpUtil.postRequest(url,map);

        JSONObject jsonObject = JSON.parseObject(str);
        return jsonObject.getBoolean("boolean");
    }

    //添加新商品
    public static boolean insertGoods(Goods good)throws Exception{
        Map<String,String> map = new HashMap<>();
        map.put("GoodsName",good.getGoodsname());
        map.put("GoodsMoney",good.getGoodsmoney()+"");
        map.put("GoodsDetail",good.getGoodsdetail());
        map.put("GoodsStorage",good.getGoodsstorage()+"");

        // 定义发送请求的URL
        String url = HttpUtil.BASE_URL + "insertGoods.action";
        // 发送请求
        String str = HttpUtil.postRequest(url,map);

        JSONObject jsonObject = JSON.parseObject(str);
        return jsonObject.getBoolean("boolean");
    }

    //删除商品
    public static boolean deleteGoods(String goodsID) throws Exception{
        Map<String,String> map = new HashMap<>();
        map.put("GoodsID",goodsID);
        // 定义发送请求的URL
        String url = HttpUtil.BASE_URL + "deleteGoods.action";
        // 发送请求
        String str = HttpUtil.postRequest(url,map);

        JSONObject jsonObject = JSON.parseObject(str);
        return jsonObject.getBoolean("boolean");
    }

}
