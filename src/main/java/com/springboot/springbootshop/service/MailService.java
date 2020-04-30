package com.springboot.springbootshop.service;



public interface MailService {

    /**
     * 通过邮箱查询用户信息
     */

    public void sendSimpleMail(String to, String subject, String content);

}
