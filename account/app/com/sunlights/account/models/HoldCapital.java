package com.sunlights.account.models;

import com.sunlights.common.models.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * 持有资产model
 * @author tangweiqun 2014/10/22
 *
 */
@Entity
@Table(name = "T_HOLDCAPITAL")
public class HoldCapital extends IdEntity{
	private static final long serialVersionUID = 3408103055932941065L;
	
	@Column(name="CUST_ID")
	private String custId;
	@Column(name="TRADE_NO")
	private String tradeNo;
	@Column(name="PRD_CODE")
	private String prdCode;
	@Column(name="TRADE_CAPITAL")
	private BigDecimal subCapital;
	@Column(name="HOLD_CAPITAL")
	private BigDecimal holdCapital;
	@Column(name="YESTERDAY_PROFIT")
	private BigDecimal yesterdayProfit;
	@Column(name="TOTAL_PROFIT")
	private BigDecimal totalProfit;
	@Column(name="B_DATE")
	private Date bDate;
	
	@Column(name="CREATE_TIME")
	private Timestamp createTime;
	@Column(name="UPDATE_TIME")
	private Timestamp updateTime;
	@Column(name="DELETE_TIME")
	private Timestamp deleteTime;

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getPrdCode() {
		return prdCode;
	}

	public void setPrdCode(String prdCode) {
		this.prdCode = prdCode;
	}

	public BigDecimal getSubCapital() {
		return subCapital;
	}

	public void setSubCapital(BigDecimal subCapital) {
		this.subCapital = subCapital;
	}

	public BigDecimal getHoldCapital() {
		return holdCapital;
	}

	public void setHoldCapital(BigDecimal holdCapital) {
		this.holdCapital = holdCapital;
	}

	public BigDecimal getYesterdayProfit() {
		return yesterdayProfit;
	}

	public void setYesterdayProfit(BigDecimal yesterdayProfit) {
		this.yesterdayProfit = yesterdayProfit;
	}

	public BigDecimal getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(BigDecimal totalProfit) {
		this.totalProfit = totalProfit;
	}

	public Date getbDate() {
		return bDate;
	}

	public void setbDate(Date bDate) {
		this.bDate = bDate;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Timestamp getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Timestamp deleteTime) {
		this.deleteTime = deleteTime;
	}
}
