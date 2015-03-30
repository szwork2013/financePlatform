package com.sunlights.op.vo;

import com.sunlights.common.utils.CommonUtil;
import models.Bank;

import java.text.ParseException;

/**
 * Created by yanghong on 2014/11/27.
 */
public class BankInfoVo {

  private Long id;
  // 代码
  private String bankCode;
  // 名称
  private String bankName;
  // 英文名称
  private String enName;
  // 状态
  private String status;
  //创建时间
  private String createdTime;
  //更新时间
  private String updateTime;

  public BankInfoVo() {
    super();
  }

  public BankInfoVo(Bank bank) {
    inBank(bank);
  }

    public void inBank(Bank bank) {
        this.id = bank.getId();
        this.bankCode = bank.getBankCode();
        this.bankName = bank.getBankName();
        this.enName = bank.getEnName();
        this.status = bank.getStatus();
        this.createdTime = CommonUtil.dateToString(bank.getCreatedTime(), "yyyy-MM-dd");
        this.updateTime = CommonUtil.dateToString(bank.getUpdateTime(), "yyyy-MM-dd");
    }
    public Bank convertToBank() {
        Bank bank = new Bank();
        bank.setId(this.id);
        bank.setBankCode(this.bankCode);
        bank.setBankName(this.bankName);
        bank.setEnName(this.enName);
        bank.setStatus(this.status);

        try {
            bank.setCreatedTime(CommonUtil.stringToDate(this.createdTime, "yyyy-MM-dd"));
            bank.setUpdateTime(CommonUtil.stringToDate(this.updateTime, "yyyy-MM-dd"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

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

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

}