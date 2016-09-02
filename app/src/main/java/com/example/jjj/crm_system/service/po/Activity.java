package com.example.jjj.crm_system.service.po;

public class Activity {
    private Integer activityid;

    private String activityname;

    private Integer accountid;

    private String activitystarttime;

    private String activitycuttime;

    private String activitydetail;

    private Boolean activitystate;

    private byte[] activitypic;

    public Integer getActivityid() {
        return activityid;
    }

    public void setActivityid(Integer activityid) {
        this.activityid = activityid;
    }

    public String getActivityname() {
        return activityname;
    }

    public void setActivityname(String activityname) {
        this.activityname = activityname == null ? null : activityname.trim();
    }

    public Integer getAccountid() {
        return accountid;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    public String getActivitystarttime() {
        return activitystarttime;
    }

    public void setActivitystarttime(String activitystarttime) {
        this.activitystarttime = activitystarttime == null ? null : activitystarttime.trim();
    }

    public String getActivitycuttime() {
        return activitycuttime;
    }

    public void setActivitycuttime(String activitycuttime) {
        this.activitycuttime = activitycuttime == null ? null : activitycuttime.trim();
    }

    public String getActivitydetail() {
        return activitydetail;
    }

    public void setActivitydetail(String activitydetail) {
        this.activitydetail = activitydetail == null ? null : activitydetail.trim();
    }

    public Boolean getActivitystate() {
        return activitystate;
    }

    public void setActivitystate(Boolean activitystate) {
        this.activitystate = activitystate;
    }

    public byte[] getActivitypic() {
        return activitypic;
    }

    public void setActivitypic(byte[] activitypic) {
        this.activitypic = activitypic;
    }
}