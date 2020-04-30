package com.springboot.springbootshop.service;


import com.springboot.springbootshop.model.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: zhuliangnan
 * @date :
 * @blog:
 * @version: 1.0.0
 * @description:
 **/
public interface OrdersService {

    boolean addOrder(Orders order);

    List<Orders> getAllOrdersByShopid(@Param("shopid")int shopid);

    boolean updateOrder(int oid,int state);


    int updateByPrimaryKeySelective(Orders orders);

    int insertSelective(Orders orders);


    List<Orders> getAllOrdersByUid(int uid);



    Orders selectByupTime(String upTime,String goodsname);

    int deleteByPrimaryKey(Integer oid);

    int insert(Orders record);


    Orders selectByPrimaryKey(Integer oid);

    List<Orders> selectByStateAndUsername(@Param("state")Integer state,@Param("addname")String addname);


    //这个比较无脑 直接更新 不好
    int updateByPrimaryKey(Orders record);

    List<Orders> selectByUsername(@Param("addname")String addname);

    List<Orders> selectByState(@Param("state")Integer state);






}
