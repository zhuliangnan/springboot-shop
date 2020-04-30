package com.springboot.springbootshop.model;

public class Shoucang {
    private Integer sid;

    private Integer uid;

    private Integer gid;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Shoucang(Integer uid, Integer gid) {
        this.uid = uid;
        this.gid = gid;
    }

    @Override
    public String toString() {
        return "Shoucang{" +
                "sid=" + sid +
                ", uid=" + uid +
                ", gid=" + gid +
                '}';
    }
}