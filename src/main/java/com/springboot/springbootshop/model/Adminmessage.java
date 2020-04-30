package com.springboot.springbootshop.model;

public class Adminmessage {
    private Integer admid;

    private Integer oid;

    private String nickname;

    private String goodsname;

    private String address;

    private String phone;

    private String details;

    private Integer state;

    private String cTime;

    public Integer getAdmid() {
        return admid;
    }

    public void setAdmid(Integer admid) {
        this.admid = admid;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname == null ? null : goodsname.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details == null ? null : details.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getcTime() {
        return cTime;
    }

    public void setcTime(String cTime) {
        this.cTime = cTime == null ? null : cTime.trim();
    }

    @Override
    public String toString() {
        return "Adminmessage{" +
                "admid=" + admid +
                ", oid=" + oid +
                ", nickname='" + nickname + '\'' +
                ", goodsname='" + goodsname + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", details='" + details + '\'' +
                ", state=" + state +
                ", cTime='" + cTime + '\'' +
                '}';
    }
}