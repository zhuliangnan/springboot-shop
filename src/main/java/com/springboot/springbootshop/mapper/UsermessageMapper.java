package com.springboot.springbootshop.mapper;

import com.springboot.springbootshop.model.Usermessage;

import java.util.List;

public interface UsermessageMapper {
    int deleteByPrimaryKey(Integer mid);

    int insert(Usermessage record);

    int insertSelective(Usermessage record);

    Usermessage selectByPrimaryKey(Integer mid);

    int updateByPrimaryKeySelective(Usermessage record);

    int updateByPrimaryKey(Usermessage record);

    List<Usermessage> getAllUsermessagen();

    Usermessage selectByOid(Integer oid);

}