package com.springboot.springbootshop.service;

import com.springboot.springbootshop.model.Cart;

import java.util.List;

/**
 * @author: zhuliangnan
 * @date :7.29
 * @blog:
 * @version: 1.0.0
 * @description:购物车页面
 **/
public interface CartService {

    int addCart(Cart cart);

    List<Cart> showAllCart(int uid);

    int deleteCart(int cid);

    boolean updateNumber(int number,int cid);

    Cart selectCartByCid(int cid);


}
