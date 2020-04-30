package com.springboot.springbootshop.mapper;

import com.springboot.springbootshop.model.Comment;
import com.springboot.springbootshop.model.Detail;
import com.springboot.springbootshop.model.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {

    List<Goods> getAllGoodsByPage(@Param("startRow") int startRow, @Param("pageSize") int pageSize,@Param("goodstate")int goodstate);

    List<Goods> getAllGoods(@Param("goodstate")int goodstate);

    List<Goods> getAllGoodsLike(@Param("search")String search,@Param("goodstate")int goodstate);

    List<Goods> getAllGoodsByPageLike(@Param("search")String search,@Param("startRow") int startRow,@Param("pageSize") int pageSize,@Param("goodstate")int goodstate);

    Goods getGoodsByGid(int gid);

    List<Detail> getDetailByGid(int gid);

    Detail getDetailByModelandColor(@Param("model")String model,@Param("color")String color,@Param("gid")int gid);

    List<Comment> getCommentByGid(@Param("gid")int gid);
    //输出好评中评差评方法评价
    List<Comment> getCommentByGidandState(@Param("gid")int gid,@Param("state1")int state1,@Param("state2")int state2);

    boolean addshoucang(@Param("uid")int uid,@Param("gid")int gid);

    Goods getGoodsByGoodsname(@Param("goodsname") String goodsname);

    List<Goods> getAllGoodsByshopid(@Param("shopid") int shopid);


    //以下是逆向工程自动生成的代码------万恶的分割线--------------------------------

    int deleteByPrimaryKey(Integer gid);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer gid);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    List<Goods> getAllGoodsBySecondClassify(@Param("secondclassify") String secondclassify);



}