package models;

import models.IdEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
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
public class SubAccount extends IdEntity {
    @Column(length = 30,name = "CUST_ID")
    private String custId;
    @Column(length = 10,name = "SUB_ACCOUNT")
    private String subAccount;//子账户号
    @Column(name = "BASIC_ACCOUNT")
    private Long basicAccount;//基本账户号
    @Column(name = "STATUS",length = 1)
    private String status;//子账户状态
    @Column(name="BALANCE", precision = 18,scale = 4)
    private BigDecimal balance;//余额
    @Column(name="YESTERDAY_PROFIT", precision = 18,scale = 4)
    private BigDecimal yesterdayProfit;//昨日收益
    @Column(name="PROFIT", precision = 18,scale = 4)
    private BigDecimal profit;//累计收益
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATE_TIME")
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="UPDATE_TIME")
    private Date updateTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DELETE_TIME")
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

    public Long getBasicAccount() {
        return basicAccount;
    }

    public void setBasicAccount(Long basicAccount) {
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

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Timestamp deleteTime) {
        this.deleteTime = deleteTime;
    }
}
