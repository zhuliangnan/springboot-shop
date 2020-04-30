package com.springboot.springbootshop.service;

import com.springboot.springbootshop.model.Comment;
import com.springboot.springbootshop.model.Detail;
import com.springboot.springbootshop.model.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: zhuliangnan
 * @date : 2019.7.31
 * @blog:
 * @version: 1.0.0
 * @description: 这个是商品controller 商品的操作都在这里面
 **/
public interface GoodsService  {

    List<Goods> getAllGoodsByPage(int startRow, int pageSize,int goodstate);

    List<Goods> getAllGoodsByPageLike(String search,int startRow,int pageSize,int goodstate);

    List<Goods> getAllGoods(int goodstate);

    List<Goods> getAllGoodsLike(String search,int goodstate);


    List<Goods> getAllGoodsByshopid(int shopid);

    Goods getGoodsByGid(int gid);

    List<Detail> getDetailByGid(int gid);

    Detail getDetailByModelandColor(String model,String color,int gid);

    List<Comment> getCommentByGid(int gid);
    //输出好评中评差评方法评价
    List<Comment> getCommentByGidandState(int gid,int state1,int state2);

    boolean addshoucang(int uid,int gid);

    Goods getGoodsByGoodsname(@Param("goodsname") String goodsname);

    int deleteByPrimaryKey(Integer gid);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer gid);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    List<Goods> getAllGoodsBySecondClassify(@Param("secondclassify") String secondclassify);

}
