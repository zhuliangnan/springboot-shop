package com.springboot.springbootshop.service.impl;

import com.springboot.springbootshop.mapper.CommentMapper;
import com.springboot.springbootshop.model.Comment;
import com.springboot.springbootshop.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: zhuliangnan
 * @date :
 * @blog:
 * @version: 1.0.0
 * @description:
 **/
@Service //加入@Service注解变为spring的bean
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public int deleteByPrimaryKey(Integer cid) {
        return commentMapper.deleteByPrimaryKey(cid);
    }

    @Override
    public int insert(Comment record) {
        return commentMapper.insert(record);
    }

    @Override
    public int insertSelective(Comment record) {
        return commentMapper.insertSelective(record);
    }

    @Override
    public Comment selectByPrimaryKey(Integer cid) {
        return commentMapper.selectByPrimaryKey(cid);
    }

    @Override
    public int updateByPrimaryKeySelective(Comment record) {
        return commentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Comment record) {
        return commentMapper.updateByPrimaryKey(record);
    }
}
