package com.springboot.springbootshop.mapper;

import com.springboot.springbootshop.model.Address;
import com.springboot.springbootshop.model.Orders;
import com.springboot.springbootshop.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {


    //以下是登录模块的方法------万恶的分割线--------------------------------

    int insert(User user);

    User selectUserByName(@Param("nickname") String nickname);

    User selectUserByEmail(@Param("email") String email);

    boolean updateUserByEmailAndPassword(@Param("email") String email,@Param("password") String password);

    User selectUserByNameAndPassword(@Param("nickname") String nickname,@Param("password")String password);

   //以下是个人中心模块的方法------万恶的分割线--------------------------------

    //根据ID查询用户
    User getUserByUid(Integer uid);

    //修改密码
     void uppsd(User u);

    //设置安全问题
     void upQuestion(User u);


    //查询所有地址
     List<Address> showaddress(Integer uid);

    //删除地址
     void deladdress(int adid);

    //添加地址
     void adaddress(Address address);

    //根据ADID查询地址
     Address getAdByAdid(Integer adid);

    //修改地址
     void upadd(Address a);

    //模糊查询地址
     List<Address> select(@Param(value="name") String name,@Param("uid") Integer uid);


    //查询所有订单
     List<Orders> showorders(Integer uid);

    //订单付款
     void fukuan(Integer oid);

    //订单提醒发货
     void tixing(Integer oid);

    //删除订单
     void deldd(Integer oid);

    //模糊查询订单
     List<Orders> select2(@Param(value="goodsname") String goodsname,@Param("uid") Integer uid);

    //以下是逆向工程自动生成的代码------万恶的分割线--------------------------------

    int deleteByPrimaryKey(Integer uid);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}