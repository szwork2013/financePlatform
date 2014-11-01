package com.sunlights.account.vo;

import com.sunlights.account.AccountConstant;

import java.io.Serializable;

public class Capital4Product implements Serializable {
	private static final long serialVersionUID = 8471977226403621283L;

	private String prdCode;
	
	private String prdName;
	
	private String totalProfit = AccountConstant.DEFAULT_MONEY;
	
	private String marketValue = AccountConstant.DEFAULT_MONEY;


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

	public String getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(String marketValue) {
		this.marketValue = marketValue;
	}

}
