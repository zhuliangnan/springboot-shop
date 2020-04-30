package com.springboot.springbootshop.GPSTest;

/**
 * @author: zhuliangnan
 * @date :
 * @blog:
 * @version: 1.0.0
 * @description:
 **/

public class Position {
    private double lon;//经度
    private double lat;//纬度

    public Position(double lon, double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public Position() {
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "Position{" +
                "lon=" + lon +
                ", lat=" + lat +
                '}';
    }
}
