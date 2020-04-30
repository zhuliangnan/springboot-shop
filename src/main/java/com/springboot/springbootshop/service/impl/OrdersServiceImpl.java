package com.springboot.springbootshop.service.impl;

import com.springboot.springbootshop.mapper.OrdersMapper;
import com.springboot.springbootshop.model.Orders;
import com.springboot.springbootshop.service.OrdersService;
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
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public boolean addOrder(Orders order) {
        return ordersMapper.addOrder(order);
    }

    @Override
    public List<Orders> getAllOrdersByShopid(int shopid) {
        return ordersMapper.getAllOrdersByShopid(shopid);
    }

    @Override
    public boolean updateOrder(int oid,int state) {
        return ordersMapper.updateOrder(oid,state);
    }

    @Override
    public int updateByPrimaryKeySelective(Orders orders) {
        System.out.println("进入OrdersServiceImpl 准备更新数据 数据为"+orders.toString());
        return ordersMapper.updateByPrimaryKeySelective(orders);
    }

    @Override
    public int insertSelective(Orders orders) {
        return ordersMapper.insertSelective(orders);
    }

    @Override
    public List<Orders> getAllOrdersByUid(int uid) {
        return ordersMapper.getAllOrdersByUid(uid);
    }

    @Override
    public Orders selectByPrimaryKey(Integer oid) {
        return ordersMapper.selectByPrimaryKey(oid);
    }

    @Override
    public List<Orders> selectByStateAndUsername(Integer state, String addname) {
        return ordersMapper.selectByStateAndUsername(state,addname);
    }

    @Override
    public int updateByPrimaryKey(Orders record) {
        return ordersMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Orders> selectByUsername(String addname) {
        return ordersMapper.selectByUsername(addname);
    }

    @Override
    public List<Orders> selectByState(Integer state) {
        return ordersMapper.selectByState(state);
    }

    @Override
    public Orders selectByupTime(String upTime,String goodsname) {
        return ordersMapper.selectByupTime(upTime,goodsname);
    }

    @Override
    public int deleteByPrimaryKey(Integer oid) {
        return ordersMapper.deleteByPrimaryKey(oid);
    }

    @Override
    public int insert(Orders record) {
        return ordersMapper.insert(record);
    }


}
