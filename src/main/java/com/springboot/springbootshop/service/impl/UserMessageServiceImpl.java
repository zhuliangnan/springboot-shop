package com.springboot.springbootshop.service.impl;

import com.springboot.springbootshop.mapper.UsermessageMapper;
import com.springboot.springbootshop.model.Usermessage;
import com.springboot.springbootshop.service.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zhuliangnan
 * @date :
 * @blog:
 * @version: 1.0.0
 * @description:
 **/
@Service  //加入@Service注解变为spring的bean
public class UserMessageServiceImpl implements UserMessageService {


    //使用@Autowired将UserMapper注入
    @Autowired
    private UsermessageMapper usermessageMapper;


    @Override
    public int deleteByPrimaryKey(Integer mid) {
        return usermessageMapper.deleteByPrimaryKey(mid);
    }

    @Override
    public int insert(Usermessage record) {
        return usermessageMapper.insert(record);
    }

    @Override
    public int insertSelective(Usermessage record) {
        return usermessageMapper.insertSelective(record);
    }

    @Override
    public Usermessage selectByPrimaryKey(Integer mid) {
        return usermessageMapper.selectByPrimaryKey(mid);
    }

    @Override
    public int updateByPrimaryKeySelective(Usermessage record) {
        return usermessageMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Usermessage record) {
        return usermessageMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Usermessage> getAllUsermessagen() {
        return usermessageMapper.getAllUsermessagen();
    }

    @Override
    public Usermessage selectByOid(Integer oid) {
        return usermessageMapper.selectByOid(oid);
    }
}
