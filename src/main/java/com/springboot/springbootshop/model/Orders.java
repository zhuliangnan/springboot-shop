package com.springboot.springbootshop.model;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Orders {
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

    public Orders(Integer uid, String addname, String goodsname, Integer number, String upTime,
                  int state, String address, String phone, String model, String color, Double price, String picture) {
        this.uid = uid;
        this.addname = addname;
        this.goodsname = goodsname;
        this.number = number;
        this.upTime = upTime;
        this.address = address;
        this.phone = phone;
        this.model = model;
        this.color = color;
        this.price = price;
        this.picture = picture;
        this.state = state;
    }


}