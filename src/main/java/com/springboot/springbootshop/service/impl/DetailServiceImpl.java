package com.springboot.springbootshop.service.impl;

import com.springboot.springbootshop.mapper.DetailMapper;
import com.springboot.springbootshop.model.Detail;
import com.springboot.springbootshop.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName AAA
 * @Description TODO
 * @Author zhuln
 * @Date 2020/4/9 10.39
 * @Version 1.0
 **/
@Service  //加入@Service注解变为spring的bean
public class DetailServiceImpl implements DetailService {

    @Autowired
    private DetailMapper detailMapper;

    @Override
    public int deleteByPrimaryKey(Integer did) {
        return detailMapper.deleteByPrimaryKey(did);
    }

    @Override
    public int insert(Detail record) {
        return detailMapper.insert(record);
    }

    @Override
    public int insertSelective(Detail record) {
        return detailMapper.insertSelective(record);
    }

    @Override
    public Detail selectByPrimaryKey(Integer did) {
        return detailMapper.selectByPrimaryKey(did);
    }

    @Override
    public int updateByPrimaryKeySelective(Detail record) {
        return detailMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Detail record) {
        return detailMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Detail> getAllDetails() {
        return detailMapper.getAllDetails();
    }

    @Override
    public List<Detail> selectDetailsByGid(Integer gid) {
        return detailMapper.selectDetailsByGid(gid);
    }

    @Override
    public int updateByColorAndModelAndGidSelective(Detail record) {
        return detailMapper.updateByColorAndModelAndGidSelective(record);
    }

    @Override
    public int deleteBygid(Integer gid) {
        return detailMapper.deleteBygid(gid);
    }
}
