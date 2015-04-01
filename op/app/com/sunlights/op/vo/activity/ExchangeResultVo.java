package com.sunlights.op.vo.activity;

import com.sunlights.common.utils.CommonUtil;
import models.ExchangeResult;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by tangweiqun on 2014/12/11.
 */
public class ExchangeResultVo extends ExchangeResult {

	private String exchangeSceneStr;

	private String createTimeStr;

	private String statusStr;

	private String realName;

	private String registerMobile;

	private Date beginTime;

	private Date endTime;

	private BigDecimal balance;

	public ExchangeResultVo () {
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getExchangeSceneStr() {
		return exchangeSceneStr;
	}

	public void setExchangeSceneStr(String exchangeSceneStr) {
		this.exchangeSceneStr = exchangeSceneStr;
	}

	public String getCreateTimeStr() {
		if (getCreateTime() != null) {
			return CommonUtil.dateToString(getCreateTime(), CommonUtil.DATE_FORMAT_LONG);
		}
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getStatusStr() {
		if (getStatus() != null) {
			return ExchangeResultStatus.getDescByStatus(getStatus());
		}
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getRegisterMobile() {
		return registerMobile;
	}

	public void setRegisterMobile(String registerMobile) {
		this.registerMobile = registerMobile;
	}

	public BigDecimal getBalance() {
		BigDecimal a = getAmount();
		BigDecimal b = getExchangedAmount();
		a = a == null ? BigDecimal.ZERO : a;
		b = b == null ? BigDecimal.ZERO : b;
		return b.subtract(a);
	}
}
