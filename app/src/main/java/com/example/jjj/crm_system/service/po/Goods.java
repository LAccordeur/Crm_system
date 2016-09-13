package com.example.jjj.crm_system.service.po;

import java.io.Serializable;

public class Goods implements Serializable {
    private Integer goodsid;

    private String goodsname;

    private Float goodsmoney;

    private Integer goodsstorage;

    private String goodsdetail;

    private byte[] goodspic;

    public Integer getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname == null ? null : goodsname.trim();
    }

    public Float getGoodsmoney() {
        return goodsmoney;
    }

    public void setGoodsmoney(Float goodsmoney) {
        this.goodsmoney = goodsmoney;
    }

    public Integer getGoodsstorage() {
        return goodsstorage;
    }

    public void setGoodsstorage(Integer goodsstorage) {
        this.goodsstorage = goodsstorage;
    }

    public String getGoodsdetail() {
        return goodsdetail;
    }

    public void setGoodsdetail(String goodsdetail) {
        this.goodsdetail = goodsdetail == null ? null : goodsdetail.trim();
    }

    public byte[] getGoodspic() {
        return goodspic;
    }

    public void setGoodspic(byte[] goodspic) {
        this.goodspic = goodspic;
    }
}