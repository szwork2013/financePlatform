package com.sunlights.account.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sunlights.account.AccountConstant;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Project: fsp</p>
 * <p>Title: TotalCapitalVo.java</p>
 * <p>Description: 总资产信息</p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class TotalCapitalInfo implements Serializable {
    private static final long serialVersionUID = 4548594159264855489L;

    private String yesterdayProfit = AccountConstant.DEFAULT_MONEY;

    private String totalProfit = AccountConstant.DEFAULT_MONEY;

    private String rewardProfit = AccountConstant.DEFAULT_MONEY;

    private String totalCapital = AccountConstant.DEFAULT_MONEY;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Capital4Product> capital4Products;


    public String getYesterdayProfit() {
        return yesterdayProfit;
    }

    public void setYesterdayProfit(String yesterdayProfit) {
        this.yesterdayProfit = yesterdayProfit;
    }

    public String getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(String totalProfit) {
        this.totalProfit = totalProfit;
    }

    public String getRewardProfit() {
        return rewardProfit;
    }

    public void setRewardProfit(String rewardProfit) {
        this.rewardProfit = rewardProfit;
    }

    public String getTotalCapital() {
        return totalCapital;
    }

    public void setTotalCapital(String totalCapital) {
        this.totalCapital = totalCapital;
    }

    public List<Capital4Product> getCapital4Products() {
        return capital4Products;
    }

    public void setCapital4Products(List<Capital4Product> capital4Products) {
        this.capital4Products = capital4Products;
    }
}
