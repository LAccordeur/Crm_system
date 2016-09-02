package com.example.jjj.crm_system.service.po;

public class Customer {
    private Integer customid;

    private String telephonenumber;

    private String customname;

    private Integer bonuspoint;

    private byte[] custompic;

    public Integer getCustomid() {
        return customid;
    }

    public void setCustomid(Integer customid) {
        this.customid = customid;
    }

    public String getTelephonenumber() {
        return telephonenumber;
    }

    public void setTelephonenumber(String telephonenumber) {
        this.telephonenumber = telephonenumber == null ? null : telephonenumber.trim();
    }

    public String getCustomname() {
        return customname;
    }

    public void setCustomname(String customname) {
        this.customname = customname == null ? null : customname.trim();
    }

    public Integer getBonuspoint() {
        return bonuspoint;
    }

    public void setBonuspoint(Integer bonuspoint) {
        this.bonuspoint = bonuspoint;
    }

    public byte[] getCustompic() {
        return custompic;
    }

    public void setCustompic(byte[] custompic) {
        this.custompic = custompic;
    }
}