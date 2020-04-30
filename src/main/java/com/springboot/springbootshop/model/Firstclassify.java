package com.springboot.springbootshop.model;

public class Firstclassify {
    private Integer fid;

    private String name;

    private String keywords;

    private Integer state;

    private String resume;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume == null ? null : resume.trim();
    }

    @Override
    public String toString() {
        return "Firstclassify{" +
                "fid=" + fid +
                ", name='" + name + '\'' +
                ", keywords='" + keywords + '\'' +
                ", state=" + state +
                ", resume='" + resume + '\'' +
                '}';
    }
}