package com.springboot.springbootshop.service.impl;


import com.springboot.springbootshop.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;



@Service  //加入@Service注解变为spring的bean
public class MailServiceImpl implements MailService {

    /**
     *
     */
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${mail.fromMail.addr}")
    private String from;


    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        try {
            javaMailSender.send(message);

            System.out.println("成功");
        } catch (Exception e) {
            System.out.println("异常");
        }

    }



}
