package com.springboot.springbootshop.service;

import com.springboot.springbootshop.model.User;

public interface UserService {


    public int insert(User user);


    public User selectUserByName(String nickname);


    public User selectUserByEmail(String email);

    public boolean updateUserByEmailAndPassword(String email,String password);

    public User selectUserByNameAndPassword(String nickname,String password);

    int updateByPrimaryKeySelective(User record);
}
