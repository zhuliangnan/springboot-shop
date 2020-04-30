package com.springboot.springbootshop.model;

public class Comment {
    private Integer cid;

    private Integer gid;

    private String comment;

    private Integer state;

    private String picture;

    private String upDate;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public String getUpDate() {
        return upDate;
    }

    public void setUpDate(String upDate) {
        this.upDate = upDate == null ? null : upDate.trim();
    }


    @Override
    public String toString() {
        return "Comment{" +
                "cid=" + cid +
                ", gid=" + gid +
                ", comment='" + comment + '\'' +
                ", state=" + state +
                ", picture='" + picture + '\'' +
                ", upDate='" + upDate + '\'' +
                '}';
    }
}