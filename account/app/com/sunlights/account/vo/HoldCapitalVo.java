package com.sunlights.account.vo;

import com.sunlights.account.AccountConstant;

import java.io.Serializable;

/**
 * <p>Project: fsp</p>
 * <p>Title: TotalCapitalVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class HoldCapitalVo implements Serializable{
    private String prdCode;
    private String prdName;
    private String marketValue = AccountConstant.DEFAULT_MONEY;
    private String totalProfit = AccountConstant.DEFAULT_MONEY;
    private String bankCardNo = "0000";//TODO
    private String millionOfProfit;//万份收益
    private String oneWeekProfit;//七日年化
    private String holdQuotient = "0";//持有份额//TODO
    private String availableQuotient = "0";//可用份额 //TODO
    private String profitLatestTime;

    public HoldCapitalVo() {
    }

    public String getPrdCode() {
        return prdCode;
    }

    public void setPrdCode(String prdCode) {
        this.prdCode = prdCode;
    }

    public String getPrdName() {
        return prdName;
    }

    public void setPrdName(String prdName) {
        this.prdName = prdName;
    }

    public String getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(String marketValue) {
        this.marketValue = marketValue;
    }

    public String getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(String totalProfit) {
        this.totalProfit = totalProfit;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getMillionOfProfit() {
        return millionOfProfit;
    }

    public void setMillionOfProfit(String millionOfProfit) {
        this.millionOfProfit = millionOfProfit;
    }

    public String getOneWeekProfit() {
        return oneWeekProfit;
    }

    public void setOneWeekProfit(String oneWeekProfit) {
        this.oneWeekProfit = oneWeekProfit;
    }

    public String getHoldQuotient() {
        return holdQuotient;
    }

    public void setHoldQuotient(String holdQuotient) {
        this.holdQuotient = holdQuotient;
    }

    public String getAvailableQuotient() {
        return availableQuotient;
    }

    public void setAvailableQuotient(String availableQuotient) {
        this.availableQuotient = availableQuotient;
    }

    public String getProfitLatestTime() {
        return profitLatestTime;
    }

    public void setProfitLatestTime(String profitLatestTime) {
        this.profitLatestTime = profitLatestTime;
    }
}
