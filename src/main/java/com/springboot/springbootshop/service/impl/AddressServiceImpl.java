package com.springboot.springbootshop.service.impl;

import com.springboot.springbootshop.mapper.AddressMapper;
import com.springboot.springbootshop.model.Address;
import com.springboot.springbootshop.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: zhuliangnan
 * @date :
 * @blog:
 * @version: 1.0.0
 * @description:
 **/
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Override
    public Address ShowAllAddressByState(int uid, String State) {
        return addressMapper.ShowAllAddressByState(uid,State);
    }

    @Override
    public boolean addAddress(Address address) {
        return addressMapper.addAddress(address);
    }

    @Override
    public boolean updateAddress(int uid) {
        return addressMapper.updateAddress(uid);
    }
}
