package com.springboot.springbootshop.util;

/**
 * @author: zhuliangnan
 * @date :
 * @blog:
 * @version: 1.0.0
 * @description: 它的作用是产生一个新的位置坐标，并将其写入一个名为position.txt的文档中
 **/

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TimerTask extends java.util.TimerTask {
    //    private Position position;


    @Override
    public void run() {
        //随机生成坐标
        // 119.042349
        //33.559087
        //lon的取值范围是121.4600-121.4800
        //lat的取值范围是31.2200-31.2800
        Random randomLon = new Random();
        Random randomLat = new Random();
        double lon = ((double)(1214600+randomLon.nextInt(200)))/10000;
        double lat = ((double)(312200+randomLon.nextInt(600)))/10000;
//        this.position.setLon(lon);
//        this.position.setLat(lat);
        //把他写到文件里
        File positionFile = new File("D:\\GPSDataDisplayService\\position.txt");//文件路径
        try{
            FileWriter fw = new FileWriter(positionFile,true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(String.valueOf(lon)+"\n");//这里要写String类型,先写经度
            bw.write(String.valueOf(lat)+"\n");//写纬度
            bw.flush();
            bw.close();
            fw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}