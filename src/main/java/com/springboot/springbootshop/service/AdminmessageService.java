package com.springboot.springbootshop.service;

import com.springboot.springbootshop.model.Adminmessage;

/**
 * @ClassName AAA
 * @Description TODO
 * @Author zhuln
 * @Date 2020/4/9 10.39
 * @Version 1.0
 **/
public interface AdminmessageService {

    int deleteByPrimaryKey(Integer admid);

    int insert(Adminmessage record);

    int insertSelective(Adminmessage record);

    Adminmessage selectByPrimaryKey(Integer admid);

    int updateByPrimaryKeySelective(Adminmessage record);

    int updateByPrimaryKey(Adminmessage record);
}
