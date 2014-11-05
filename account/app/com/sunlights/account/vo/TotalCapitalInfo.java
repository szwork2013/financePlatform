package com.sunlights.account.vo;

import com.sunlights.account.AccountConstant;

import java.io.Serializable;
import java.util.List;

/**
 * 我的资产页面需要的数据信息
 * 
 * @author tangweiqun	2014/10/22
 *
 */
public class TotalCapitalInfo implements Serializable {
	private static final long serialVersionUID = 4548594159264855489L;
	
	private String yesterdayProfit = AccountConstant.DEFAULT_MONEY;
	
	private String totalProfit = AccountConstant.DEFAULT_MONEY;
	
	private String rewardProfit = AccountConstant.DEFAULT_MONEY;
	
	private String totalCapital = AccountConstant.DEFAULT_MONEY;
	
	private List<Capital4Product> capital4Products;

    private String count;//产品条数


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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public void setCapital4Products(List<Capital4Product> capital4Products) {
		this.capital4Products = capital4Products;
	}
}
