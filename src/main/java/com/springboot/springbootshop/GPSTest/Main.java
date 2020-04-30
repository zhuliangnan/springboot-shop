package com.springboot.springbootshop.GPSTest;

/**
 * @author: zhuliangnan
 * @date :
 * @blog:
 * @version: 1.0.0
 * @description:
 **/

import com.springboot.springbootshop.util.TimerTask;

import java.util.Timer;

public class Main {
    public static void main(String[] args) {


        //我们需要事先将位置数据写入文件
        Timer timer = new Timer();
        System.out.println("创建");
        //定时调用函数
        timer.schedule(new TimerTask() {
        }, 0, 2 * 1000);//每隔2秒将一个新点的坐标写入txt文件
    }
}