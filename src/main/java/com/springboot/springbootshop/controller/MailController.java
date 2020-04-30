package com.springboot.springbootshop.controller;






import com.alibaba.fastjson.JSON;
import com.springboot.springbootshop.service.UserService;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;


import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@RestController


public class MailController {


    @Autowired
    private UserService userService;



    private final Logger logger = LoggerFactory.getLogger(this.getClass());



    @Autowired

    private JavaMailSender mailSender;



    /*
     * 1，发送验证码 （注册和忘记密码找回时）
     * 2. 判断邮箱重复
     *
     * @date 2019.8.10 更改说明
     * 因为github给我报了给warning
     *  <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.22</version>
        </dependency>

        有严重安全漏洞 所以改为 1.2.31 版本
     * */
    @PostMapping("/SendEmail")
    public String sendMail(String email, String action, HttpSession session, Model model, HttpServletRequest request) {
        System.out.println("跳转到发送验证码页面");
        // String email=(String)request.getParameter("email");
        System.out.println("获取的邮箱地址是:" + email);
        System.out.println("获取的action地址是:" + action);


        // 判断邮箱重复
        //这个是忘记密码时候找回密码用的
        if (action.equals("renewPassWord")) {

            Map<String, Object> map = new HashMap<String, Object>();
            if ((userService.selectUserByEmail(email)) != null) {
                int randNum = 1 + (int) (Math.random() * ((999999 - 1) + 1));
                System.out.println("生成的随机码为" + randNum);

                SimpleMailMessage message = new SimpleMailMessage();

                message.setFrom("zhuliangnan7410@163.com");

                message.setTo(email);

                message.setSubject("it is a email for spring boot");

                message.setText("你好,我正在测试发送邮件,您的注册验证码是：" + randNum);

                try {

                    mailSender.send(message);

                    logger.info("小黄的测试邮件已发送。");

                } catch (Exception e) {

                    logger.error("小黄发送邮件时发生异常了！", e);

                }

                System.out.println("验证码发送无误");
                map.put("code", 0);
                map.put("valCode", randNum);

                return JSON.toJSONString(map);
            } else {
                map.put("code", 1);
                return JSON.toJSONString(map);
            }

        }

        //这个是注册时候用的
        if (action.equals("register")) {
            if ((userService.selectUserByEmail(email)) == null) {
                int randNum = 1 + (int) (Math.random() * ((999999 - 1) + 1));
                System.out.println("生成的随机码为 " + randNum);

                SimpleMailMessage message = new SimpleMailMessage();

                message.setFrom("zhuliangnan7410@163.com");

                message.setTo(email);

                message.setSubject("it is a email for spring boot ");

                message.setText("你好,我正在测试发送邮件,您的注册验证码是 ：" + randNum);

                try {

                    mailSender.send(message);

                    logger.info("小黄的测试邮件已发送 。");

                } catch (Exception e) {

                    logger.error("小黄发送邮件时发生异常了！", e);

                }
                System.out.println("验证码发送无误");

                return String.valueOf(randNum);
            } else {
                return "1111";
            }

        }

        return "未符合条件";
    }


}