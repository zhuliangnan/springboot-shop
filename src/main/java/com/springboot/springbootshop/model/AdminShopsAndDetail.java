package com.springboot.springbootshop.model;

import lombok.*;

/**
 * @ClassName AAA
 * @Description TODO
 * @Author zhuln
 * @Date 2020/4/9 10.39
 * @Version 1.0
 **/
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AdminShopsAndDetail {
    private  Goods goods;
    private Integer stock;


}
