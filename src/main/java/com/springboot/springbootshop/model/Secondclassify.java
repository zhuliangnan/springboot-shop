package com.springboot.springbootshop.model;

public class Secondclassify {
    private Integer sid;

    private String sname;

    private String keyword;

    private String resumes;

    private Integer states;

    private Integer fid;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname == null ? null : sname.trim();
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public String getResumes() {
        return resumes;
    }

    public void setResumes(String resumes) {
        this.resumes = resumes == null ? null : resumes.trim();
    }

    public Integer getStates() {
        return states;
    }

    public void setStates(Integer states) {
        this.states = states;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    @Override
    public String toString() {
        return "Secondclassify{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", keyword='" + keyword + '\'' +
                ", resumes='" + resumes + '\'' +
                ", states=" + states +
                ", fid=" + fid +
                '}';
    }
}