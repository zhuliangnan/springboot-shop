package com.springboot.springbootshop.mapper;

import com.springboot.springbootshop.model.Firstclassify;

public interface FirstclassifyMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(Firstclassify record);

    int insertSelective(Firstclassify record);

    Firstclassify selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(Firstclassify record);

    int updateByPrimaryKey(Firstclassify record);
}