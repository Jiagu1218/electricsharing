package com.cyxy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class OrderDTO {
    private Integer orderId;

    private Integer userId;

    private Integer powerId;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date orderRentalTime;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8") //格式后端传到前端的时间格式
    private Date orderReturnTime;

    private Double orderSum;

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPowerId() {
        return powerId;
    }

    public void setPowerId(Integer powerId) {
        this.powerId = powerId;
    }

    public Date getOrderRentalTime() {
        return orderRentalTime;
    }

    public void setOrderRentalTime(Date orderRentalTime) {
        this.orderRentalTime = orderRentalTime;
    }

    public Date getOrderReturnTime() {
        return orderReturnTime;
    }

    public void setOrderReturnTime(Date orderReturnTime) {
        this.orderReturnTime = orderReturnTime;
    }

    public Double getOrderSum() {
        return orderSum;
    }

    public void setOrderSum(Double orderSum) {
        this.orderSum = orderSum;
    }
}
