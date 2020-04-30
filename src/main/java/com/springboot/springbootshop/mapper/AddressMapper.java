package com.springboot.springbootshop.mapper;

import com.springboot.springbootshop.model.Address;
import org.apache.ibatis.annotations.Param;

public interface AddressMapper {

    Address ShowAllAddressByState(@Param("uid")int uid, @Param("state")String State);

    boolean addAddress(Address address);

    boolean updateAddress(@Param("uid") int uid);

    //以下是逆向工程自动生成的代码------万恶的分割线--------------------------------

    int deleteByPrimaryKey(Integer adid);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(Integer adid);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);
}