package com.example.jjj.crm_system.service.po;



import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Shopowner {
    private Integer accountid;

    private String accountname;

    private String accountpwd;

    private String accountaddress;

    //@JSONField(format="HH:mm:ss")
    //private Date openingtime;
    //
    //@JSONField(format="HH:mm:ss")
    //private Date closingtime;

    //private Time openingtime;
    //
    //private Time closingtime;

    private String openingtime;
    private String closingtime;

    private String accountdetail;

    private byte[] accountpic;

    public Integer getAccountid() {
        return accountid;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname == null ? null : accountname.trim();
    }

    public String getAccountpwd() {
        return accountpwd;
    }

    public void setAccountpwd(String accountpwd) {
        this.accountpwd = accountpwd == null ? null : accountpwd.trim();
    }

    public String getAccountaddress() {
        return accountaddress;
    }

    public void setAccountaddress(String accountaddress) {
        this.accountaddress = accountaddress == null ? null : accountaddress.trim();
    }

    public String getOpeningtime() {
        return openingtime;
    }

    public void setOpeningtime(String openingtime) {
        this.openingtime = openingtime;
    }

    public String getClosingtime() {
        return closingtime;
    }

    public void setClosingtime(String closingtime) {
        this.closingtime = closingtime;
    }

    public String getAccountdetail() {
        return accountdetail;
    }

    public void setAccountdetail(String accountdetail) {
        this.accountdetail = accountdetail == null ? null : accountdetail.trim();
    }

    public byte[] getAccountpic() {
        return accountpic;
    }

    public void setAccountpic(byte[] accountpic) {
        this.accountpic = accountpic;
    }
}