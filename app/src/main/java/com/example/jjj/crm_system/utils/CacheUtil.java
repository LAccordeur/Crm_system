package com.example.jjj.crm_system.utils;


import android.content.Context;
import android.graphics.Bitmap;

import com.example.jjj.crm_system.service.po.Shopowner;

/**
 * Created by 坤霖 on 2016/11/13.
 */
public class CacheUtil {
    private static ACache acache;
    private Context context;

    public CacheUtil(Context context) {
        this.context = context;
    }
    private ACache getInstance(){
        if (acache == null){
            acache = ACache.get(context);
        }
        return acache;
    }

    //商家信息缓存

    public void setShopownerInfo(Shopowner shopowner){
        getInstance().put("shopowner",shopowner);
    }
    public Shopowner getShopownerInfo(){
        if (getInstance().getAsString("shopowner")!=null){
            return (Shopowner) getInstance().getAsObject("shopowner");
        }
        return null;
    }
    public void setYearlySales(String yearlySales){
        getInstance().put("yearlySales",yearlySales);
    }
    public String getYearlySales(){
        if (getInstance().getAsString("yearlySales")!=null){
            //return Integer.parseInt(getInstance().getAsString("yearlySales"));
            //return 0;
            return getInstance().getAsString("yearlySales");
        }
        return null;
    }
    public void setDaylySales(String daylySales){
        getInstance().put("daylySales",daylySales);
    }
    public String getDayrlySales(){
        if (getInstance().getAsString("daylySales")!=null){
            //return Integer.parseInt(getInstance().getAsString("daylySales"));
           // return 0;
            return getInstance().getAsString("daylySales");
        }
        return null;
    }
    public void setMonthlySales(String monthlySales){
        getInstance().put("monthlySales",monthlySales);
    }
    public String getMonthlySales(){
        if (getInstance().getAsString("monthlySales")!=null){
            //return Integer.parseInt(getInstance().getAsString("monthlySales"));
            //return 0;
            return getInstance().getAsString("monthlySales");
        }
        return null;
    }

    public void setBitmap(Bitmap bitmap){
        getInstance().put("bitmap",bitmap);
    }
    public Bitmap getBitmap(){
        if (getInstance().getAsBitmap("bitmap")!=null){
            return getInstance().getAsBitmap("bitmap");
        }
        return null;
    }

}
