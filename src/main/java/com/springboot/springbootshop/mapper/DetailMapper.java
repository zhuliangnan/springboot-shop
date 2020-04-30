package com.springboot.springbootshop.mapper;

import com.springboot.springbootshop.model.Detail;

import java.util.List;

public interface DetailMapper {
    int deleteByPrimaryKey(Integer did);

    int insert(Detail record);

    int insertSelective(Detail record);

    Detail selectByPrimaryKey(Integer did);

    int updateByPrimaryKeySelective(Detail record);

    int updateByPrimaryKey(Detail record);

    List<Detail>  getAllDetails();

    List<Detail> selectDetailsByGid(Integer gid);

    int updateByColorAndModelAndGidSelective(Detail record);

    int deleteBygid(Integer gid);
}