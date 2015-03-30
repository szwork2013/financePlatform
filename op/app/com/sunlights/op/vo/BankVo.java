package com.sunlights.op.vo;

import models.Bank;

import java.util.Date;

/**
 * Created by Yuan on 2015/3/17.
 */
public class BankVo {
	private Long id;
	// 代码
	private String bankCode;
	// 名称
	private String bankName;
	// 英文名称
	private String enName;
	// 状态
	private String status;
	// 创建时间
	private Date createdTime;
	// 更新时间
	private Date updateTime;

	public BankVo() {
	}

	public BankVo(Bank bank) {
		inBank(bank);
	}

	public void inBank(Bank bank) {
		this.setId(bank.getId());
		this.setBankCode(bank.getBankCode());
		this.setBankName(bank.getBankName());
		this.setEnName(bank.getEnName());
		this.setStatus(bank.getStatus());
		this.setCreatedTime(bank.getCreatedTime());
		this.setUpdateTime(bank.getUpdateTime());
	}

	public Bank convertToBank() {
		Date date = new Date();
		Bank bank = new Bank();
		bank.setId(this.getId());
		bank.setBankCode(this.getBankCode());
		bank.setBankName(this.getBankName());
		bank.setEnName(this.getEnName());
		bank.setStatus(this.getStatus());
		if (bank.getId() == null) {
			bank.setCreatedTime(date);
		} else {
			bank.setCreatedTime(this.getCreatedTime());
		}
		bank.setUpdateTime(date);
		return bank;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
