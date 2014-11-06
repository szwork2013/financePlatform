package models;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: FundOpenAccount.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Entity
@Table(name = "c_fund_open_account")
public class FundOpenAccount extends IdEntity {
  @Column(name = "bank_Card_No", length = 32)
  private String bankCardNo;
  @Column(name = "bank_code", length = 20)
  private String bankCode;
  @Column(name = "bank_buyer_name", length = 10)
  private String bankBuyerName;
  @Column(name = "branch_bank_name", length = 60)
  private String branchBankName;
  @Column(name = "customer_Id", length = 30)
  private String customerId;
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_time", length = 32)
  private Date createTime;
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "update_time", length = 32)
  private Date updateTime;

  public String getBankCardNo() {
    return bankCardNo;
  }

  public void setBankCardNo(String bankCardNo) {
    this.bankCardNo = bankCardNo;
  }

  public String getBankCode() {
    return bankCode;
  }

  public void setBankCode(String bankCode) {
    this.bankCode = bankCode;
  }

  public String getBankBuyerName() {
    return bankBuyerName;
  }

  public void setBankBuyerName(String bankBuyerName) {
    this.bankBuyerName = bankBuyerName;
  }

  public String getBranchBankName() {
    return branchBankName;
  }

  public void setBranchBankName(String branchBankName) {
    this.branchBankName = branchBankName;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }
}
