package com.sunlights.core.vo;

import java.util.Date;

/**
 * Created by Administrator on 2015/5/11.
 */
public class SyncIncomeStatVo {

    private String customerId;

    private String fundCode;

    private Date systemDay;

    private Date currDay;

    private String totalHoldGatherNum;

    private String hadNetValueHol;

    private double cityValue;

    private double holdCost;

    private double todayIncome;

    private double holdIncome;

    private double holdIncomeRate;

    private Date createTime;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public Date getSystemDay() {
        return systemDay;
    }

    public void setSystemDay(Date systemDay) {
        this.systemDay = systemDay;
    }

    public Date getCurrDay() {
        return currDay;
    }

    public void setCurrDay(Date currDay) {
        this.currDay = currDay;
    }

    public String getTotalHoldGatherNum() {
        return totalHoldGatherNum;
    }

    public void setTotalHoldGatherNum(String totalHoldGatherNum) {
        this.totalHoldGatherNum = totalHoldGatherNum;
    }

    public String getHadNetValueHol() {
        return hadNetValueHol;
    }

    public void setHadNetValueHol(String hadNetValueHol) {
        this.hadNetValueHol = hadNetValueHol;
    }

    public double getCityValue() {
        return cityValue;
    }

    public void setCityValue(double cityValue) {
        this.cityValue = cityValue;
    }

    public double getHoldCost() {
        return holdCost;
    }

    public void setHoldCost(double holdCost) {
        this.holdCost = holdCost;
    }

    public double getTodayIncome() {
        return todayIncome;
    }

    public void setTodayIncome(double todayIncome) {
        this.todayIncome = todayIncome;
    }

    public double getHoldIncome() {
        return holdIncome;
    }

    public void setHoldIncome(double holdIncome) {
        this.holdIncome = holdIncome;
    }

    public double getHoldIncomeRate() {
        return holdIncomeRate;
    }

    public void setHoldIncomeRate(double holdIncomeRate) {
        this.holdIncomeRate = holdIncomeRate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
