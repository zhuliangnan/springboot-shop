package com.springboot.springbootshop.service;

import com.springboot.springbootshop.model.Address;
import com.springboot.springbootshop.model.Orders;
import com.springboot.springbootshop.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: zhuliangnan
 * @date :
 * @blog:
 * @version: 1.0.0
 * @description:
 **/
public interface PersonalService {


    //根据ID查询用户
    public User getUserByUid(Integer uid);

    //修改密码
    public void uppsd(User u);

    //设置安全问题
    public void upQuestion(User u);


    //查询所有地址
    public List<Address> showaddress(Integer uid);

    //删除地址
    public void deladdress(int adid);

    //添加地址
    public void adaddress(Address address);

    //根据ADID查询地址
    public Address getAdByAdid(Integer adid);

    //修改地址
    public void upadd(Address a);

    //模糊查询地址
    public List<Address> select(@Param(value="name") String name, @Param("uid") Integer uid);



    //查询所有订单
    public List<Orders> showorders(Integer uid);

    //订单付款
    public void fukuan(Integer oid);

    //订单提醒发货
    public void tixing(Integer oid);

    //删除订单
    public void deldd(Integer oid);

    //模糊查询订单
    public List<Orders> select2(@Param(value="goodsname") String goodsname,@Param("uid") Integer uid);
}
