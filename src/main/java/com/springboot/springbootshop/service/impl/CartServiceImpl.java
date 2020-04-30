package com.springboot.springbootshop.service.impl;

import com.springboot.springbootshop.mapper.CartMapper;
import com.springboot.springbootshop.model.Cart;
import com.springboot.springbootshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zhuliangnan
 * @date :
 * @blog:
 * @version: 1.0.0
 * @description:
 **/
@Service  //加入@Service注解变为spring的bean
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;


    @Override
    public int addCart(Cart cart) {
        return cartMapper.addCart(cart);
    }

    @Override
    public List<Cart> showAllCart(int uid) {
        return cartMapper.showAllCart(uid);
    }

    @Override
    public int deleteCart(int cid) {
        return cartMapper.deleteCart(cid);
    }

    @Override
    public boolean updateNumber(int number, int cid) {
        return cartMapper.updateNumber(number,cid);
    }

    @Override
    public Cart selectCartByCid(int cid) {
        return cartMapper.selectCartByCid(cid);
    }
}
