package com.oracle.domain;


import com.oracle.utils.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Product {
    private String id; // 主键
    private String carNum; // 车牌号
    private String name;//姓名
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date inTime; // 入库时间
    private int Time; //在库时长
    private Date outTime; //出库时见
    private String carHouse;//车库
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")

    private String VIN; //车架号
    private String cost; //停车费
    private String CITY; //城市
    private String identityNumber; //身份证号
    private String inTimeStr;
    private String costSum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public int getTime() {
        return Time;
    }

    public void setTime(int time) {
        Time = time;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCarHouse() {
        return carHouse;
    }

    public void setCarHouse(String carHouse) {
        this.carHouse = carHouse;
    }

    public String getCITY() {
        return CITY;
    }

    public void setCITY(String CITY) {
        this.CITY = CITY;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }
    public String getinTimeStr() {
        if(inTime!=null){
            inTimeStr= DateUtils.date2String(inTime,"yyyy-MM-dd HH:mm:ss");
        }
        return inTimeStr;
    }

    public void setinTimeStr(String inTimeStr) {
        this.inTimeStr = inTimeStr;
    }

    public String getCostSum() {
        return costSum;
    }

    public void setCostSum(String costSum) {
        this.costSum = costSum;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id='" + id + '\'' +
                ", carNum='" + carNum + '\'' +
                ", name='" + name + '\'' +
                ", inTime=" + inTime +
                ", Time=" + Time +
                ", outTime=" + outTime +
                ", VIN='" + VIN + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }
}
