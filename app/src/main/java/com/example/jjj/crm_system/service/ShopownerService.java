package com.example.jjj.crm_system.service;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.jjj.crm_system.net.MyRequestParams;
import com.example.jjj.crm_system.service.constant.ConstantValue;
import com.example.jjj.crm_system.service.po.Shopowner;
import com.example.jjj.crm_system.utils.HttpUtil;
import com.example.jjj.crm_system.utils.ImageLoader;

import org.apache.http.entity.StringEntity;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by HeathHose on 2016/8/9.
 */
public class ShopownerService {

    //验证商家登录;成功登陆返回商家ID，反之返回0
    public static int shopownerValidate(String username, String password) throws  Exception{

        // 使用Map封装请求参数
        Map<String, String> map = new HashMap<>();
        map.put("AccountUser", username);
        map.put("AccountPwd", password);
        // 定义发送请求的URL
        String url = HttpUtil.BASE_URL + "validateShopowner.action";
        // 发送请求
        JSONObject jsonObject;
        jsonObject = JSON.parseObject(HttpUtil.postRequest(url, map));

        int userID = jsonObject.getIntValue("userID");
        System.out.println(userID);
        return userID;
    }

    //注册商家*
    //public int registerShopowner() throws Exception;

    //获取商家信息，返回pojo
    public static Shopowner getShopownerInf(String userId) throws Exception{
        Map<String,String> map = new HashMap<>();
        //map.put("ShopownerID",userId);
        map.put("ShopownerID",userId);
        // 定义发送请求的URL
        String url = HttpUtil.BASE_URL + "getShopownerInf.action";
        String str = HttpUtil.postRequest(url,map);
        // 发送请求
        Shopowner shopowner = JSON.parseObject(str,Shopowner.class);
        return shopowner;
    }

    //更新商家信息
    public static boolean updateShopownerInf(String userId, Shopowner owner) throws Exception{
        Map<String,String> map = new HashMap<>();

        //提取更新信息
        map.put("AccountID",userId);
        map.put("AccountName",owner.getAccountname());
        map.put("AccountAddress",owner.getAccountaddress());
        map.put("OpeningTime",owner.getOpeningtime());
        map.put("ClosingTime",owner.getClosingtime());
        map.put("AccountDetail",owner.getAccountdetail());

        // 定义发送请求的URL
        String url = HttpUtil.BASE_URL + "updateShopownerInf.action";
        String str = HttpUtil.postRequest(url,map);
        // 发送请求
        JSONObject jsonObject = JSON.parseObject(str);
        boolean bool = jsonObject.getBoolean("boolean");
        return bool;


    }

    //获取日销售额（默认当前时间日期）
    public static int getDailySales() throws Exception{
        String url = HttpUtil.BASE_URL + "getDailySales.action";
        JSONObject jsonObject = JSON.parseObject( HttpUtil.getRequest(url));
        int sales = jsonObject.getIntValue("DailySales");
        return sales;
    }

    //获取月销售额
    public static int getMonthlySales() throws Exception{
        String url = HttpUtil.BASE_URL + "getMonthlySales.action";
        JSONObject jsonObject = JSON.parseObject( HttpUtil.getRequest(url));
        int sales = jsonObject.getIntValue("MonthlySales");
        return sales;
    }

    //	获取年销售额
    public static int getYearlySales() throws Exception{
        String url = HttpUtil.BASE_URL + "getYearlySales.action";
        JSONObject jsonObject = JSON.parseObject( HttpUtil.getRequest(url));
        int sales = jsonObject.getIntValue("YearlySales");
        return sales;
    }

    //上传商家头像
    public  static boolean uploadShopownerImage(ImageView imageView) throws Exception{
        //得到图片字符串
        String bytesString = ImageLoader.imageByteChange(imageView);

        //得到Map<String,String>
        Map<String,String> map = new HashMap<>();
        //map.put("ShopownerID",userId);
        map.put("AccountPic",bytesString);

        //上传图片
        String url = HttpUtil.BASE_URL + "uploadShopownerImage.action";
        String str = HttpUtil.postRequest(url,map);
        JSONObject jsonObject = JSON.parseObject( HttpUtil.getRequest(url));
        boolean bool = jsonObject.getBoolean("boolean");
        return bool;

    }
}
