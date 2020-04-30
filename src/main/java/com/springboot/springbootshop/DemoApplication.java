package com.springboot.springbootshop;

import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;




@EnableAutoConfiguration

@MapperScan(basePackages = "com.springboot.springbootshop.mapper") //添加对mapper包的扫描 这样 mapper包下面的类就可以不加@Mapper

public class DemoApplication {



    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);

    }

}