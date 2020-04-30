package com.springboot.springbootshop.mapper;

import com.springboot.springbootshop.model.Secondclassify;

public interface SecondclassifyMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(Secondclassify record);

    int insertSelective(Secondclassify record);

    Secondclassify selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(Secondclassify record);

    int updateByPrimaryKey(Secondclassify record);
}