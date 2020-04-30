package com.springboot.springbootshop.service.impl;

import com.springboot.springbootshop.mapper.UserMapper;
import com.springboot.springbootshop.model.User;
import com.springboot.springbootshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service  //加入@Service注解变为spring的bean
public class UserServiceImpl implements UserService {

    //使用@Autowired将UserMapper注入
    @Autowired
    private UserMapper userMapper;

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User selectUserByName(String nickname) {
        return userMapper.selectUserByName(nickname);
    }

    @Override
    public User selectUserByEmail(String email) {
        return userMapper.selectUserByEmail(email);
    }

    @Override
    public boolean updateUserByEmailAndPassword(String email, String password) {
        return userMapper.updateUserByEmailAndPassword(email,password);
    }

    @Override
    public User selectUserByNameAndPassword(String nickname, String password) {
        return userMapper.selectUserByNameAndPassword(nickname,password);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }
}
