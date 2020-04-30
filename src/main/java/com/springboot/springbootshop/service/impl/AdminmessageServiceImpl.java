package com.springboot.springbootshop.service.impl;

import com.springboot.springbootshop.mapper.AdminmessageMapper;
import com.springboot.springbootshop.model.Adminmessage;
import com.springboot.springbootshop.service.AdminmessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName AAA
 * @Description TODO
 * @Author zhuln
 * @Date 2020/4/9 10.39
 * @Version 1.0
 **/
@Service  //加入@Service注解变为spring的bean
public class AdminmessageServiceImpl implements AdminmessageService {
    @Autowired
    private AdminmessageMapper adminmessageMapper;

    @Override
    public int deleteByPrimaryKey(Integer admid) {
        return adminmessageMapper.deleteByPrimaryKey(admid);
    }

    @Override
    public int insert(Adminmessage record) {
        return adminmessageMapper.insert(record);
    }

    @Override
    public int insertSelective(Adminmessage record) {
        return adminmessageMapper.insertSelective(record);
    }

    @Override
    public Adminmessage selectByPrimaryKey(Integer admid) {
        return adminmessageMapper.selectByPrimaryKey(admid);
    }

    @Override
    public int updateByPrimaryKeySelective(Adminmessage record) {
        return adminmessageMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Adminmessage record) {
        return adminmessageMapper.updateByPrimaryKey(record);
    }
}
