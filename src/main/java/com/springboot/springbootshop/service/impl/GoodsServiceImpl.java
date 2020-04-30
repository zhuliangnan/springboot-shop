package com.springboot.springbootshop.service.impl;

import com.springboot.springbootshop.mapper.GoodsMapper;
import com.springboot.springbootshop.model.Comment;
import com.springboot.springbootshop.model.Detail;
import com.springboot.springbootshop.model.Goods;
import com.springboot.springbootshop.service.GoodsService;
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
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> getAllGoodsByPage(int startRow, int pageSize,int goodstate) {
        return goodsMapper.getAllGoodsByPage(startRow,pageSize,goodstate);
    }

    @Override
    public List<Goods> getAllGoodsByPageLike(String search, int startRow, int pageSize,int goodstate) {
        return goodsMapper.getAllGoodsByPageLike(search,startRow,pageSize,goodstate);
    }

    @Override
    public List<Goods> getAllGoods(int goodstate) {
        return goodsMapper.getAllGoods(goodstate);
    }

    @Override
    public List<Goods> getAllGoodsLike(String search,int goodstate) {
        return goodsMapper.getAllGoodsLike(search,goodstate);
    }

    @Override
    public List<Goods> getAllGoodsByshopid(int shopid) {
        return goodsMapper.getAllGoodsByshopid(shopid);
    }

    @Override
    public Goods getGoodsByGid(int gid) {
        return goodsMapper.getGoodsByGid(gid);
    }

    @Override
    public List<Detail> getDetailByGid(int gid) {
        return goodsMapper.getDetailByGid(gid);
    }

    @Override
    public Detail getDetailByModelandColor(String model, String color, int gid) {
        return goodsMapper.getDetailByModelandColor(model,color,gid);
    }

    @Override
    public List<Comment> getCommentByGid(int gid) {
        return goodsMapper.getCommentByGid(gid);
    }

    @Override
    public List<Comment> getCommentByGidandState(int gid, int state1, int state2) {
        return goodsMapper.getCommentByGidandState(gid,state1,state2);
    }



    @Override
    public boolean addshoucang(int uid, int gid) {
        return goodsMapper.addshoucang(uid,gid);
    }

    @Override
    public Goods getGoodsByGoodsname(String goodsname) {
        return goodsMapper.getGoodsByGoodsname(goodsname);
    }

    @Override
    public int deleteByPrimaryKey(Integer gid) {
        return goodsMapper.deleteByPrimaryKey(gid);
    }

    @Override
    public int insert(Goods record) {
        return goodsMapper.insert(record);
    }

    @Override
    public int insertSelective(Goods record) {
        return goodsMapper.insertSelective(record);
    }

    @Override
    public Goods selectByPrimaryKey(Integer gid) {
        return goodsMapper.selectByPrimaryKey(gid);
    }

    @Override
    public int updateByPrimaryKeySelective(Goods record) {
        return goodsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Goods record) {
        return updateByPrimaryKey(record);
    }

    @Override
    public List<Goods> getAllGoodsBySecondClassify(String secondclassify) {
        return goodsMapper.getAllGoodsBySecondClassify(secondclassify);
    }
}
