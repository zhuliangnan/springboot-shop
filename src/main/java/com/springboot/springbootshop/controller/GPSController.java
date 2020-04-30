package com.springboot.springbootshop.controller;

/**
 * @author: zhuliangnan
 * @date :
 * @blog:
 * @version: 1.0.0
 * @description:这个Controller用于和前端交互和传递数据，
 * 前端页面每发来一次请求，此Controller就去position.txt
 * 中读取最新的一条数据并返回此坐标点。GPSController
 **/


import com.springboot.springbootshop.model.Orders;
import com.springboot.springbootshop.service.OrdersService;
import com.springboot.springbootshop.util.GetLatAndLngByBaidu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//这是一个测试类，会去txt文件中读取数据并返回最新的经纬度给我
@Controller
public class GPSController {

    @Autowired
    private OrdersService ordersService;

    //这个方法用来生成地址数据(经纬度) 模拟物流信息进度 最后返回视图
    @GetMapping  (value = "/currentMap/{oid}")
    public String toCurrentPositionMap(@PathVariable("oid") Integer oid, HttpSession session, HttpServletRequest request, Model model) throws IOException {


        Orders orders=ordersService.selectByPrimaryKey(oid);
        GetLatAndLngByBaidu getLatAndLngByBaidu=new GetLatAndLngByBaidu();
        //根据地址获取目的地的经纬度
        Object[]  definiteAddress = getLatAndLngByBaidu.getCoordinate(orders.getAddress());
        //封装经纬度
        model.addAttribute("lon",Double.parseDouble((String) definiteAddress[0]));//经度
        model.addAttribute("lat",Double.parseDouble((String) definiteAddress[1]));//纬度
        model.addAttribute("orders",orders);//物流信息

        //开始生成position.text文件
        //随机生成坐标 淮阴工学院
        // 119.042349  经度
        //33.559087 纬度
        //lon的取值范围是121.4600-121.4800
        //lat的取值范围是31.2200-31.2800
        //淮安市清江浦区翔宇中路169号万达广场
        //119.052061
        //33.606663

        return "TrackMap";
    }
  /*  //这个方法在前台用ajax调用，返回最新的位置坐标
    @RequestMapping(value = "/currentMapFuck",method = RequestMethod.GET)
    public Map<String,Object> getCurrentPosition(){

        System.out.print("进入currentMapFuck");




       *//* List<Double> doubleList = new ArrayList<Double>();//用来存储从文件中读取到的数据

        File file = new File("D:\\GPSDataDisplayService\\position.txt");

        try{
            String readData;
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while ((readData = br.readLine())!=null){//没有读到文件尾的s时候
                try{
                    doubleList.add(Double.parseDouble(readData));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
*//*
  *//*      double lon = doubleList.get(doubleList.size()-2);
        double lat = doubleList.get(doubleList.size()-1);

        Map<String,Object> modelMap = new HashMap<String, Object>(2);
        modelMap.put("data",new Position(lon,lat));
        modelMap.put("success","true");*//*

        return modelMap;

    }*/
}
