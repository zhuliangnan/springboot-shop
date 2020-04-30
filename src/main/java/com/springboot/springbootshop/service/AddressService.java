package com.springboot.springbootshop.service;

import com.springboot.springbootshop.model.Address;

/**
 * @author: zhuliangnan
 * @date :
 * @blog:
 * @version: 1.0.0
 * @description: 地址service
 **/
public interface AddressService  {

    Address ShowAllAddressByState(int uid, String State);

    boolean addAddress(Address address);

    boolean updateAddress(int uid);
}
