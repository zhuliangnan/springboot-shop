package com.springboot.springbootshop.service.impl;

import com.springboot.springbootshop.mapper.UserMapper;
import com.springboot.springbootshop.model.Address;
import com.springboot.springbootshop.model.Orders;
import com.springboot.springbootshop.model.User;
import com.springboot.springbootshop.service.PersonalService;
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
public class PersonalServiceImpl implements PersonalService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByUid(Integer uid) {
        return userMapper.getUserByUid(uid);
    }

    @Override
    public void uppsd(User u) {
        userMapper.uppsd(u);

    }

    @Override
    public void upQuestion(User u) {
        userMapper.upQuestion(u);

    }

    @Override
    public List<Address> showaddress(Integer uid) {
        return userMapper.showaddress(uid);
    }

    @Override
    public void deladdress(int adid) {
        userMapper.deladdress(adid);
    }

    @Override
    public void adaddress(Address address) {
        userMapper.adaddress(address);
    }

    @Override
    public Address getAdByAdid(Integer adid) {
        return userMapper.getAdByAdid(adid);
    }

    @Override
    public void upadd(Address a) {
        userMapper.upadd(a);
    }

    @Override
    public List<Address> select(String name, Integer uid) {
        return userMapper.select(name,uid);
    }

    @Override
    public List<Orders> showorders(Integer uid) {
        return userMapper.showorders(uid);
    }

    @Override
    public void fukuan(Integer oid) {
        userMapper.fukuan(oid);
    }

    @Override
    public void tixing(Integer oid) {
        userMapper.tixing(oid);
    }

    @Override
    public void deldd(Integer oid) {
        userMapper.deldd(oid);
    }

    @Override
    public List<Orders> select2(String goodsname, Integer uid) {
        return userMapper.select2(goodsname,uid);
    }
}
