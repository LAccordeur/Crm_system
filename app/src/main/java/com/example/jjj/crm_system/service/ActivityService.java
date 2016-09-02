package com.example.jjj.crm_system.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.jjj.crm_system.service.po.Activity;
import com.example.jjj.crm_system.service.po.Customer;
import com.example.jjj.crm_system.utils.HttpUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HeathHose on 2016/8/9.
 */
public  class ActivityService {
    //查看已编辑的活动详情
    public   static List<Activity> getActivityInfo() throws Exception{

        // 定义发送请求的URL
        String url = HttpUtil.BASE_URL + "getActivityInfo.action";
        // 发送请求
        String str = HttpUtil.getRequest(url);
        List<Activity> list = JSON.parseObject(str,new TypeReference<List<Activity>>(){});
        return list;
    }
    //保存已编辑活动内容
    public  static boolean updateActivityInf(int activityID, Activity activity) throws Exception{
        Map<String,String> map = new HashMap<>();
        map.put("ActivityID",activityID+"");
        map.put("ActivityName",activity.getActivityname());
        map.put("ActivityDetail",activity.getActivitydetail());
        map.put("ActivityStartTime",activity.getActivitystarttime()+"");
        map.put("ActivityCutTime",activity.getActivitycuttime()+"");
        map.put("ActivityState",activity.getActivitystate()+"");

        // 定义发送请求的URL
        String url = HttpUtil.BASE_URL + "updateActivityInfo.action";
        // 发送请求
        String str = HttpUtil.postRequest(url,map);

        JSONObject jsonObject = JSON.parseObject(str);
        return jsonObject.getBoolean("boolean");
    }

    //发布已编辑活动
    public  static boolean updateAcitvityState(int activityID, Boolean bool) throws Exception{
        Map<String,String> map = new HashMap<>();
        map.put("activityID",activityID+"");
        map.put("boolean",bool.toString());
        // 定义发送请求的URL
        String url = HttpUtil.BASE_URL + "updateAcitvityState.action";
        // 发送请求
        String str = HttpUtil.postRequest(url,map);

        JSONObject jsonObject = JSON.parseObject(str);
        return jsonObject.getBoolean("boolean");
    }

    //新增已编辑活动
    public  static boolean insertActivity(Activity activity) throws Exception{
        Map<String,String> map = new HashMap<>();
        map.put("AccountID",activity.getAccountid()+"");
        map.put("ActivityName",activity.getActivityname());
        map.put("ActivityDetail",activity.getActivitydetail());
        map.put("ActivityStartTime",activity.getActivitystarttime()+"");
        map.put("ActivityCutTime",activity.getActivitycuttime()+"");

        // 定义发送请求的URL
        String url = HttpUtil.BASE_URL + "insertActivity.action";
        // 发送请求
        String str = HttpUtil.postRequest(url,map);

        JSONObject jsonObject = JSON.parseObject(str);
        return jsonObject.getBoolean("boolean");
    }
}
