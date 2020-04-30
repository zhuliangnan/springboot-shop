package com.springboot.springbootshop.service.impl;

import com.springboot.springbootshop.mapper.ShoucangMapper;
import com.springboot.springbootshop.model.Shoucang;
import com.springboot.springbootshop.service.ShouCangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: zhuliangnan
 * @date :
 * @blog:
 * @version: 1.0.0
 * @description:
 **/
@Service  //加入@Service注解变为spring的bean
public class ShouCangServiceImpl implements ShouCangService {

    @Autowired
    private ShoucangMapper shoucangMapper;


    @Override
    public boolean addShouCang(Shoucang shoucang) {
        return shoucangMapper.addShouCang(shoucang);
    }
}
