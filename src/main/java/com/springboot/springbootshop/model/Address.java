package com.springboot.springbootshop.model;

public class Address {
    private Integer adid;

    private Integer uid;

    private String address;

    private String phone;

    private String name;

    private String state;

    public Integer getAdid() {
        return adid;
    }

    public void setAdid(Integer adid) {
        this.adid = adid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    @Override
    public String toString() {
        return "Address{" +
                "adid=" + adid +
                ", uid=" + uid +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    public Address(String address, String phone, String name) {
        this.address = address;
        this.phone = phone;
        this.name = name;
    }

    public Address() {
    }

    public Address(Integer uid, String address, String phone, String name, String state) {
        this.uid = uid;
        this.address = address;
        this.phone = phone;
        this.name = name;
        this.state = state;
    }
}