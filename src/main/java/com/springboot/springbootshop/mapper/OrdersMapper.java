package com.springboot.springbootshop.mapper;

import com.springboot.springbootshop.model.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrdersMapper {


    boolean addOrder(Orders order);
    //修改订单状态
    boolean updateOrder(@Param("oid") int oid,@Param("state") int state);

    List<Orders> getAllOrdersByUid(@Param("uid")int uid);

    List<Orders> getAllOrdersByShopid(@Param("shopid")int shopid);

    Orders selectByupTime(@Param("upTime")String upTime,@Param("goodsname")String goodsname);

    //以下是逆向工程自动生成的代码------万恶的分割线--------------------------------

    int deleteByPrimaryKey(Integer oid);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(Integer oid);

    //这个是有判断的进行更新 null的就不进行更新
    int updateByPrimaryKeySelective(Orders record);
    //这个比较无脑 直接更新 不好
    int updateByPrimaryKey(Orders record);


    List<Orders> selectByStateAndUsername(@Param("state")Integer state,@Param("addname")String addname);
    List<Orders> selectByUsername(@Param("addname")String addname);


    List<Orders> selectByState(@Param("state")Integer state);



}