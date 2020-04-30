package com.springboot.springbootshop.service;

import com.springboot.springbootshop.model.Admin;
import com.springboot.springbootshop.model.AdminKey;

import java.util.List;

public interface AdminService {

    //用来测试事物的update方法
    public  int update();

    int deleteByPrimaryKey(AdminKey key);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(AdminKey key);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    List<Admin> getAllAdmin();

    Admin selectAdminByNameAndPassword(String name, String password);

}
