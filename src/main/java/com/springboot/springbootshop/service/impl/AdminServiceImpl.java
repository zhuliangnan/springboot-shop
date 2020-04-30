package com.springboot.springbootshop.service.impl;


import com.springboot.springbootshop.mapper.AdminMapper;
import com.springboot.springbootshop.model.Admin;
import com.springboot.springbootshop.model.AdminKey;
import com.springboot.springbootshop.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service  //加入@Service注解变为spring的bean
public class AdminServiceImpl implements AdminService {

    //使用@Autowired将AdminMapper注入
    @Autowired
    private AdminMapper adminMapper;

    //经过pom和核心配置文件的配置,springboot已经帮我们注入好了RedisTemplate注意<Object,Object>,<String,String>就这两种
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;


    //测试redis缓存
    @Override
    public  List<Admin> getAllAdmin() {

        //设置字符序列化 为了让缓存的数据看起来不难么乱 可以不写
        RedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);

        /*
        在高并发情况下会出现--缓存穿透
        即10000个人同时进来同时查询缓存没查到,都进入数据库了.
        而我们向=想要的是第一个人查了数据库后,其他人都查缓存
        redis是直接访问内存的效率比mysql高很多
        解决方法:最简单的是在方法上面加上synchronized锁,这样只有一个人进来,完成了其他人再进来,此时的redis数据已经存好了 效率低点牺牲了性能
        实际上我们可以更加精确的去锁,我们在if语句那边加锁,因为事实上只是if那边不可以同时进入
        * */
        //查询缓存 根据key=allAdmin
        List<Admin> adminlist=( List<Admin>)redisTemplate.opsForValue().get("allAdmin");

        //判断缓存是不是空的,是空的就去查询数据库 如果不为空直接返回 这里为了防止高并发的缓存穿透 加锁
        if (adminlist==null){ // 为空的话 加锁
            synchronized (this){
                //这里为了严谨我再判断一下
                adminlist=( List<Admin>)redisTemplate.opsForValue().get("allAdmin");
                if (adminlist==null){
                    System.out.println("查询数据库");
                    //查询数据库
                    adminlist=adminMapper.getAllAdmin();
                    //将数据库的数据放入缓存中 同样使用redisTemplate.opsForValue() 这个方法
                    redisTemplate.opsForValue().set("allAdmin",adminlist);

                }

            }

        }


        return adminlist;
    }

    @Override
    public Admin selectAdminByNameAndPassword(String name, String password) {
        return adminMapper.selectAdminByNameAndPassword(name,password);
    }

    @Transactional  //用来表示我这个方法是有事物的
    @Override
    public int update() {
        Admin a=new Admin();
        a.setAid(1);
        a.setName("admin");
        a.setPassword("123456");
        int updatenumber=adminMapper.updateByPrimaryKeySelective(a);
        System.out.println("更新了"+updatenumber+"条");
       //用来抛出一个运行时异常 除数不为0  这时候上一步的更新就会回滚 这是@Transactional 发挥的作用
        int b=10/0;

        return updatenumber;
    }

    @Override
    public int deleteByPrimaryKey(AdminKey key) {
        return adminMapper.deleteByPrimaryKey(key);
    }

    @Override
    public int insert(Admin record) {
        return adminMapper.insert(record);
    }

    @Override
    public int insertSelective(Admin record) {
        return adminMapper.insertSelective(record);
    }

    @Override
    public Admin selectByPrimaryKey(AdminKey key) {
        return adminMapper.selectByPrimaryKey(key);
    }

    @Override
    public int updateByPrimaryKeySelective(Admin record) {
        return adminMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Admin record) {
        return adminMapper.updateByPrimaryKey(record);
    }
}
