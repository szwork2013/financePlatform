package com.sunlights.trade.vo;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: CapitalProductTradeVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class CapitalProductTradeVo implements Serializable {
    private String prdCode;
    private String prdName;
    private String marketValue = "0.00";
    private String totalProfit = "0.00";
    private String principalValue = "0.00";
    private String millionOfProfit;//万份收益
    private String oneWeekProfit;//七日年化
    private String holdQuotient;//持有份额
    private String availableQuotient = "0";//可用份额 //TODO
    private String profitLatestTime;

    private List list;
    private int tradeCount;

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

    public String getPrincipalValue() {
        return principalValue;
    }

    public void setPrincipalValue(String principalValue) {
        this.principalValue = principalValue;
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

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getTradeCount() {
        return tradeCount;
    }

    public void setTradeCount(int tradeCount) {
        this.tradeCount = tradeCount;
    }
}
