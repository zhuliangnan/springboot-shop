package com.springboot.springbootshop.mapper;

import com.springboot.springbootshop.model.Shoucang;

public interface ShoucangMapper {


    boolean addShouCang(Shoucang shoucang);

    //以下是逆向工程自动生成的代码------万恶的分割线--------------------------------

    int deleteByPrimaryKey(Integer sid);

    int insert(Shoucang record);

    int insertSelective(Shoucang record);

    Shoucang selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(Shoucang record);

    int updateByPrimaryKey(Shoucang record);
}