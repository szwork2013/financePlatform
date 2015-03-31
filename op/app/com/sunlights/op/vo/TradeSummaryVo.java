package com.sunlights.op.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Yuan on 2015/3/27.
 */
public class TradeSummaryVo {
	// 交易日期
	private Date tradeDate;
	// 每天转入总金额
	private BigDecimal dayInAmount;
	// 转入总金额累加
	private BigDecimal inAmountTotal;
	// 每天转出总金额
	private BigDecimal dayOutAmount;
	// 转出总金额累加
	private BigDecimal outAmountTotal;

	// 注册日期
	private Date registrationDate;
	// 日注册人数
	private Long registrationCount;
	// 注册人数累加
	private Long registrationTotal;

	// 购买日期
	private Date purchaseDate;
	// 新增成功购买人数
	private Long inCustomerCount;
	// 新增成功购买人数累加
	private Long totalInCustomerCount;
	// 新增转出人数
	private Long outCustomerCount;
	// 新增成功购买人数累加
	private Long totalOutCustomerCount;

	public Date getTradeDate () {
		return tradeDate;
	}

	public void setTradeDate (Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	public BigDecimal getDayInAmount () {
		return dayInAmount;
	}

	public void setDayInAmount (BigDecimal dayInAmount) {
		this.dayInAmount = dayInAmount;
	}

	public BigDecimal getInAmountTotal () {
		return inAmountTotal;
	}

	public void setInAmountTotal (BigDecimal inAmountTotal) {
		this.inAmountTotal = inAmountTotal;
	}

	public BigDecimal getDayOutAmount () {
		return dayOutAmount;
	}

	public void setDayOutAmount (BigDecimal dayOutAmount) {
		this.dayOutAmount = dayOutAmount;
	}

	public BigDecimal getOutAmountTotal () {
		return outAmountTotal;
	}

	public void setOutAmountTotal (BigDecimal outAmountTotal) {
		this.outAmountTotal = outAmountTotal;
	}

	public Date getRegistrationDate () {
		return registrationDate;
	}

	public void setRegistrationDate (Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Long getRegistrationCount () {
		return registrationCount;
	}

	public void setRegistrationCount (Long registrationCount) {
		this.registrationCount = registrationCount;
	}

	public Long getRegistrationTotal () {
		return registrationTotal;
	}

	public void setRegistrationTotal (Long registrationTotal) {
		this.registrationTotal = registrationTotal;
	}

	public Date getPurchaseDate () {
		return purchaseDate;
	}

	public void setPurchaseDate (Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Long getInCustomerCount () {
		return inCustomerCount;
	}

	public void setInCustomerCount (Long inCustomerCount) {
		this.inCustomerCount = inCustomerCount;
	}

	public Long getTotalInCustomerCount () {
		return totalInCustomerCount;
	}

	public void setTotalInCustomerCount (Long totalInCustomerCount) {
		this.totalInCustomerCount = totalInCustomerCount;
	}

	public Long getOutCustomerCount () {
		return outCustomerCount;
	}

	public void setOutCustomerCount (Long outCustomerCount) {
		this.outCustomerCount = outCustomerCount;
	}

	public Long getTotalOutCustomerCount () {
		return totalOutCustomerCount;
	}

	public void setTotalOutCustomerCount (Long totalOutCustomerCount) {
		this.totalOutCustomerCount = totalOutCustomerCount;
	}
}