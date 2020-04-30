package com.springboot.springbootshop.model;

public class Admin extends AdminKey {
    private String password;

    private Integer shopid;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "password='" + password + '\'' +
                ", shopid=" + shopid +
                '}';
    }

    public Admin(String password, Integer shopid) {
        this.password = password;
        this.shopid = shopid;
    }

    public Admin() {
    }
}