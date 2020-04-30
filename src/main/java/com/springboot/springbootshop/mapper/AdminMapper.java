package com.springboot.springbootshop.mapper;

import com.springboot.springbootshop.model.Admin;
import com.springboot.springbootshop.model.AdminKey;
import com.springboot.springbootshop.util.AbstractDaoImpl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminMapper {
    int deleteByPrimaryKey(AdminKey key);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(AdminKey key);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    List<Admin> getAllAdmin();

    Admin selectAdminByNameAndPassword(@Param("name") String name, @Param("password") String password);
}