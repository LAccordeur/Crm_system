package com.example.jjj.crm_system.service.po;

import java.util.Date;

public class Purchasehistory {
    private Integer recordid;

    private Integer customid;

    private Date recordtime;

    private Float recordmoney;

    private String remark;

    public Integer getRecordid() {
        return recordid;
    }

    public void setRecordid(Integer recordid) {
        this.recordid = recordid;
    }

    public Integer getCustomid() {
        return customid;
    }

    public void setCustomid(Integer customid) {
        this.customid = customid;
    }

    public Date getRecordtime() {
        return recordtime;
    }

    public void setRecordtime(Date recordtime) {
        this.recordtime = recordtime;
    }

    public Float getRecordmoney() {
        return recordmoney;
    }

    public void setRecordmoney(Float recordmoney) {
        this.recordmoney = recordmoney;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}