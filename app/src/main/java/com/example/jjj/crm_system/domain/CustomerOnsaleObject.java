package com.example.jjj.crm_system.domain;

import java.io.Serializable;

/**
 * Created by jjj on 2016/7/22 0022.
 */
public class CustomerOnsaleObject implements Serializable{
    private String onsaleName;
    private String onsaleIntroduction ;
    private String onsaleStartTime ;
    private String onsaleEndTime;
    private String onsaleImageUrl ;

    public CustomerOnsaleObject() {
        this.onsaleName  = "我的活动";
        this.onsaleIntroduction = "活动简介";
        this.onsaleStartTime = "2016_7_21";
        this.onsaleEndTime = "2016_8_11";
        this.onsaleImageUrl = "http://img.taopic.com/uploads/allimg/140817/235030-140QFQ91875.jpg";
        //this.onsaleImageUrl = "http://img0.imgtn.bdimg.com/it/u=510878081,2337058705&fm=21&gp=0.jpg";
    }

    public String getOnsaleName() {
        return onsaleName;
    }

    public void setOnsaleName(String onsaleName) {
        this.onsaleName = onsaleName;
    }

    public String getOnsaleIntroduction() {
        return onsaleIntroduction;
    }

    public void setOnsaleIntroduction(String onsaleIntroduction) {
        this.onsaleIntroduction = onsaleIntroduction;
    }

    public String getOnsaleStartTime() {
        return onsaleStartTime;
    }

    public void setOnsaleStartTime(String onsaleTime) {
        this.onsaleStartTime = onsaleTime;
    }

    public String getOnsaleEndTime(){
        return onsaleEndTime;
    }
    public void setOnsaleEndTime(String onsaleEndTime){
        this.onsaleEndTime = onsaleEndTime;
    }

    public String getOnsaleImageUrl() {
        return onsaleImageUrl;
    }

    public void setOnsaleImageUrl(String onsaleImageUrl) {
        this.onsaleImageUrl = onsaleImageUrl;
    }



}
