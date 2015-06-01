package com.sunlights.op.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import models.FinancialPlanner;

import java.math.BigDecimal;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FinancialPlannerCustomerVo {

    private String   mobilePhone;   //理财师手机号
    private String   name;          //理财师姓名
    private String   mobile;        //被推荐人手机号
    private String   realName;      //被推荐人姓名
    private BigDecimal totalAmount;   //被推荐人购买总数

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }


    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
