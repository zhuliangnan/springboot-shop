package com.springboot.springbootshop.model;

public class Usermessage {
    private Integer mid;

    private Integer oid;

    private String nickname;

    private String goodsname;

    private String address;

    private String phone;

    //用户发送信息
    private String details;

    private Integer state;

    private String sendTime;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
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

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime == null ? null : sendTime.trim();
    }

    @Override
    public String toString() {
        return "Usermessage{" +
                "mid=" + mid +
                ", oid=" + oid +
                ", nickname='" + nickname + '\'' +
                ", goodsname='" + goodsname + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", details='" + details + '\'' +
                ", state=" + state +
                ", sendTime='" + sendTime + '\'' +
                '}';
    }
}