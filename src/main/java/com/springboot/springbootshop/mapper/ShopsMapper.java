package com.springboot.springbootshop.mapper;

import com.springboot.springbootshop.model.Shops;

public interface ShopsMapper {
    int deleteByPrimaryKey(Integer shopid);

    int insert(Shops record);

    int insertSelective(Shops record);

    Shops selectByPrimaryKey(Integer shopid);

    int updateByPrimaryKeySelective(Shops record);

    int updateByPrimaryKey(Shops record);
}