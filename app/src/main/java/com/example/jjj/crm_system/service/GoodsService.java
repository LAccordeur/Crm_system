package com.example.jjj.crm_system.service;

import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.jjj.crm_system.service.po.Goods;
import com.example.jjj.crm_system.utils.HttpUtil;
import com.example.jjj.crm_system.utils.ImageLoader;


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

    //上传商品图片
    public  static boolean uploadGoodsImage(String id,ImageView imageView) throws Exception{
        //得到图片字符串
        String bytesString = ImageLoader.imageByteChange(imageView);

        //得到Map<String,String>
        Map<String,String> map = new HashMap<>();
        map.put("GoodsID",id);
        map.put("GoodsPic",bytesString);

        //上传图片
        String url = HttpUtil.BASE_URL + "uploadGoodsImage.action";
        String str = HttpUtil.postRequest(url,map);
        JSONObject jsonObject = JSON.parseObject( HttpUtil.getRequest(url));
        boolean bool = jsonObject.getBoolean("boolean");
        return bool;

    }

    //获得商品图片
    public  static boolean getGoodsImage(int id) throws Exception{
        //得到Map<String,String>
        Map<String,String> map = new HashMap<>();
        //map.put("ShopownerID",userId);
        map.put("GoodsID",id+"");

        //上传图片
        String url = HttpUtil.BASE_URL + "getGoodsImage.action";
        String str = HttpUtil.postRequest(url,map);
        JSONObject jsonObject = JSON.parseObject( HttpUtil.getRequest(url));
        boolean bool = jsonObject.getBoolean("boolean");
        return bool;

    }

}
