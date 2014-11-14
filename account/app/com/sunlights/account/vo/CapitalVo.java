package com.sunlights.account.vo;

import com.sunlights.account.AccountConstant;

import java.io.Serializable;

/**
 * <p>Project: fsp</p>
 * <p>Title: TotalCapitalVo.java</p>
 * <p>Description: 收益信息</p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class CapitalVo implements Serializable {
    private String prdCode;
    private String prdName;
    private String yesterdayProfit = AccountConstant.DEFAULT_MONEY;
    private String profitLatestTime;


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

    public String getYesterdayProfit() {
        return yesterdayProfit;
    }

    public void setYesterdayProfit(String yesterdayProfit) {
        this.yesterdayProfit = yesterdayProfit;
    }

    public String getProfitLatestTime() {
        return profitLatestTime;
    }

    public void setProfitLatestTime(String profitLatestTime) {
        this.profitLatestTime = profitLatestTime;
    }
}

