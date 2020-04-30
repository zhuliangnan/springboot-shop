package com.springboot.springbootshop.mapper;

import com.springboot.springbootshop.model.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {

    int addCart(Cart cart);

    List<Cart> showAllCart(int uid);

    int deleteCart(int cid);

    boolean updateNumber(@Param("number") int number, @Param("cid") int cid);


    Cart selectCartByCid(int cid);

    //以下是逆向工程自动生成的代码------万恶的分割线--------------------------------

    int deleteByPrimaryKey(Integer cid);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);
}