package com.springboot.springbootshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*
 * @SpringBootApplication   为springboot的核心注解 开启spring自动配置
 * 他可以扫描的位置在与它同级别的位置,或者在它下面级别
 * 这里的controller 显然与它同级别 所以可以扫描到
 * */

@SpringBootApplication
@ServletComponentScan(basePackages = "com.springboot.springbootshop.filter")//添加对filter过滤器的扫描
@MapperScan(basePackages ="com.springboot.springbootshop.mapper") //添加对mapper包的扫描 这样 mapper包下面的类就可以不加@Mapper
@EnableTransactionManagement //开启springboot对于事物的支持
public class SpringbootShopApplication {
//启动springboot程序 和内嵌的tomcat
    public static void main(String[] args) {

        SpringApplication.run(SpringbootShopApplication.class, args);
    }

}
