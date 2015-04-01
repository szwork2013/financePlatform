package com.sunlights.op.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Yuan on 2015/3/25.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ReferrerDetailVo {
	private String custId;
	private String mobile;
	private String realName;
	private Date registrationDate;
	private Date tradeDate;
	private BigDecimal purchaseAmount;

	public String getCustId () {
		return custId;
	}

	public void setCustId (String custId) {
		this.custId = custId;
	}

	public String getMobile () {
		return mobile;
	}

	public void setMobile (String mobile) {
		this.mobile = mobile;
	}

	public String getRealName () {
		return realName;
	}

	public void setRealName (String realName) {
		this.realName = realName;
	}

	public Date getRegistrationDate () {
		return registrationDate;
	}

	public void setRegistrationDate (Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public BigDecimal getPurchaseAmount () {
		return purchaseAmount;
	}

	public void setPurchaseAmount (BigDecimal purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	public Date getTradeDate () {
		return tradeDate;
	}

	public void setTradeDate (Date tradeDate) {
		this.tradeDate = tradeDate;
	}
}
