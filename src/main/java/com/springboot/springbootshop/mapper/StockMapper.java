package com.springboot.springbootshop.mapper;

import com.springboot.springbootshop.model.Stock;

public interface StockMapper {
    int deleteByPrimaryKey(Integer stockId);

    int insert(Stock record);

    int insertSelective(Stock record);

    Stock selectByPrimaryKey(Integer stockId);

    int updateByPrimaryKeySelective(Stock record);

    int updateByPrimaryKey(Stock record);
}