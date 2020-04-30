package com.springboot.springbootshop.mapper;

import com.springboot.springbootshop.model.Adminmessage;

public interface AdminmessageMapper {
    int deleteByPrimaryKey(Integer admid);

    int insert(Adminmessage record);

    int insertSelective(Adminmessage record);

    Adminmessage selectByPrimaryKey(Integer admid);

    int updateByPrimaryKeySelective(Adminmessage record);

    int updateByPrimaryKey(Adminmessage record);
}