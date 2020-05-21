package com.springboot.springbootshop.model;

import lombok.*;

/**
 * @ClassName AAA
 * @Description TODO
 * @Author zhuln
 * @Date 2020/4/9 10.39
 * @Version 1.0
 **/

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderUser {
    private Integer oid;

    private Integer uid;

    private String addname;

    private String goodsname;

    private Integer number;
    //订单生成时间
    private String upTime;

    private Integer state;

    private String address;

    private String phone;

    private String model;

    private String color;

    private Double price;

    private String picture;
    //发货时间
    private String sendTime1;
    //签收时间
    private String sendTime2;

    private Integer shopid;

    private  User user;
}
