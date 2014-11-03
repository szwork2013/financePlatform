package com.sunlights.account.vo;

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
    private String id;
    private String prdCode;
    private String prdName;
    private String totalProfit;
    private String yesterdayProfit;
    private String profitDateTime;
    private String profitRate;
    private String principal;//本金

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

    public String getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(String totalProfit) {
        this.totalProfit = totalProfit;
    }

    public String getYesterdayProfit() {
        return yesterdayProfit;
    }

    public void setYesterdayProfit(String yesterdayProfit) {
        this.yesterdayProfit = yesterdayProfit;
    }

    public String getProfitDateTime() {
        return profitDateTime;
    }

    public void setProfitDateTime(String profitDateTime) {
        this.profitDateTime = profitDateTime;
    }

    public String getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(String profitRate) {
        this.profitRate = profitRate;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
