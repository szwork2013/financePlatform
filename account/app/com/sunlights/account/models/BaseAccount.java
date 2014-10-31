package com.sunlights.account.models;

import com.sunlights.common.models.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 基本model
 * @author tangweiqun 2014/10/22
 *
 */
@Entity
@Table(name = "F_BASIC_ACCOUNT")
public class BaseAccount extends IdEntity {
	private static final long serialVersionUID = 549140805904750947L;
	
	@Column(name="CUST_ID")
	private String custId;
	@Column(name="ACCOUNT_NO")
	private String baseAccountNo;
	@Column(name="STATUS")
	private String status;
	@Column(name="BALANCE")
	private BigDecimal balance;
	@Column(name="TRADE_PASSWORD")
	private String tradePassword;
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
	public String getBaseAccountNo() {
		return baseAccountNo;
	}
	public void setBaseAccountNo(String baseAccountNo) {
		this.baseAccountNo = baseAccountNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public String getTradePassword() {
		return tradePassword;
	}
	public void setTradePassword(String tradePassword) {
		this.tradePassword = tradePassword;
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
