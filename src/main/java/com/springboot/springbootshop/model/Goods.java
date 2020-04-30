package com.springboot.springbootshop.model;

public class Goods {
    private Integer gid;

    private String goodsname;

    private Integer sales;

    private String secondclassify;

    private String addTime;

    private String picture1;

    private String picture2;

    private String picture3;

    private String describe;

    private Integer sid;

    private Integer goodstate;

    private Integer shopid;

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname == null ? null : goodsname.trim();
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getSecondclassify() {
        return secondclassify;
    }

    public void setSecondclassify(String secondclassify) {
        this.secondclassify = secondclassify == null ? null : secondclassify.trim();
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime == null ? null : addTime.trim();
    }

    public String getPicture1() {
        return picture1;
    }

    public void setPicture1(String picture1) {
        this.picture1 = picture1 == null ? null : picture1.trim();
    }

    public String getPicture2() {
        return picture2;
    }

    public void setPicture2(String picture2) {
        this.picture2 = picture2 == null ? null : picture2.trim();
    }

    public String getPicture3() {
        return picture3;
    }

    public void setPicture3(String picture3) {
        this.picture3 = picture3 == null ? null : picture3.trim();
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getGoodstate() {
        return goodstate;
    }

    public void setGoodstate(Integer goodstate) {
        this.goodstate = goodstate;
    }

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "gid=" + gid +
                ", goodsname='" + goodsname + '\'' +
                ", sales=" + sales +
                ", secondclassify='" + secondclassify + '\'' +
                ", addTime='" + addTime + '\'' +
                ", picture1='" + picture1 + '\'' +
                ", picture2='" + picture2 + '\'' +
                ", picture3='" + picture3 + '\'' +
                ", describe='" + describe + '\'' +
                ", sid=" + sid +
                ", goodstate=" + goodstate +
                ", shopid=" + shopid +
                '}';
    }
}