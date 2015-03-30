package com.sunlights.op.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Yuan on 2015/3/26.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PurchaseStatisticsVo {

	private String customerId;
	private String mobile;
	private String name;
	private BigDecimal tradeAmount;
	private String bankName;
	private String referrerName;
	private String referrerMobile;
	private Date tradeDate;
	private Date registrationDate;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(BigDecimal tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getReferrerName() {
		return referrerName;
	}

	public void setReferrerName(String referrerName) {
		this.referrerName = referrerName;
	}

	public String getReferrerMobile() {
		return referrerMobile;
	}

	public void setReferrerMobile(String referrerMobile) {
		this.referrerMobile = referrerMobile;
	}

	public Date getTradeDate () {
		return tradeDate;
	}

	public void setTradeDate (Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	public Date getRegistrationDate () {
		return registrationDate;
	}

	public void setRegistrationDate (Date registrationDate) {
		this.registrationDate = registrationDate;
	}
}
