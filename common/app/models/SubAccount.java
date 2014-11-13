package models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * <p>Project: fsp</p>
 * <p>Title: SubAccount.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Entity
@Table(name = "F_SUB_ACCOUNT")
@NamedQueries({
        @NamedQuery(name = "findSubAccount", query = "select sa from SubAccount sa,PrdAccountConfig pc where sa.subAccount = pc.subAccount and sa.custId = ?1 and pc.prdTypeCode = ?2")
})
public class SubAccount extends IdEntity {
  @Column(length = 30, name = "CUST_ID")
  private String custId;
  @Column(length = 10, name = "SUB_ACCOUNT")
  private String subAccount;//子账户号
  @Column(name = "BASIC_ACCOUNT", length = 30)
  private String basicAccount;//基本账户号
  @Column(name = "STATUS", length = 1)
  private String status;//子账户状态
  @Column(name = "BALANCE", precision = 18, scale = 4)
  private BigDecimal balance = BigDecimal.ZERO;//余额
  @Column(name = "YESTERDAY_PROFIT", precision = 18, scale = 4)
  private BigDecimal yesterdayProfit = BigDecimal.ZERO;//昨日收益
  @Column(name = "PROFIT", precision = 18, scale = 4)
  private BigDecimal profit = BigDecimal.ZERO;//累计收益
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CREATE_TIME")
  private Date createTime;
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "UPDATE_TIME")
  private Date updateTime;
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "DELETE_TIME")
  private Date deleteTime;

  public String getCustId() {
    return custId;
  }

  public void setCustId(String custId) {
    this.custId = custId;
  }

  public String getSubAccount() {
    return subAccount;
  }

  public void setSubAccount(String subAccount) {
    this.subAccount = subAccount;
  }

  public String getBasicAccount() {
    return basicAccount;
  }

  public void setBasicAccount(String basicAccount) {
    this.basicAccount = basicAccount;
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

  public BigDecimal getYesterdayProfit() {
    return yesterdayProfit;
  }

  public void setYesterdayProfit(BigDecimal yesterdayProfit) {
    this.yesterdayProfit = yesterdayProfit;
  }

  public BigDecimal getProfit() {
    return profit;
  }

  public void setProfit(BigDecimal profit) {
    this.profit = profit;
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

  public Date getDeleteTime() {
    return deleteTime;
  }

  public void setDeleteTime(Date deleteTime) {
    this.deleteTime = deleteTime;
  }
}
