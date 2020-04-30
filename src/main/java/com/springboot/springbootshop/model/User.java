package com.springboot.springbootshop.model;

public class User {
    private Integer uid;

    private String nickname;

    private String password;

    private String image;

    private String email;

    private String question;

    private String answer;

    private String resTime;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public String getResTime() {
        return resTime;
    }

    public void setResTime(String resTime) {
        this.resTime = resTime == null ? null : resTime.trim();
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", image='" + image + '\'' +
                ", email='" + email + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", resTime='" + resTime + '\'' +
                '}';
    }
}