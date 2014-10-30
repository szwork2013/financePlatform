package com.sunlights.trade.model;

import com.sunlights.common.dal.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * <p>Project: fsp</p>
 * <p>Title: Trade.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Entity
@Table(name = "T_TRADE")
public class Trade extends IdEntity{
    @Column(length = 30, name = "CUST_ID")
    private String custId;//客户号
    @Column(length = 20, name = "TRADE_NO")
    private String tradeNo;//交易流水号
    @Column(precision = 18,scale = 4,name = "PRD_PRICR")
    private BigDecimal prdPrice;//产品单价
    @Column(length = 8,name = "PRD_CODE")
    private String prdCode;//产品编码
    @Column(name = "HOLDCAPITAL_ID")
    private Long holdCapitalId;//持有资产号
    @Column(name = "AMOUNT")
    private int amount;//成交数量
    @Column(precision = 18,scale = 4,name = "FEE")
    private BigDecimal fee;//手续费
    @Column(name = "TYPE", length = 1)
    private String type;//1:申购 2:赎回 3:分红
    @Column(name = "TRADE_STATUS", length = 1)
    private String tradeStatus;//1：存/取钱中、2：存/取钱成功、3：存/取失败【失败原因】
    @Column(name = "PAY_STATU", length = 1)
    private String payStatus;//'交易付款标志 0-不需付款；1-未付款；2-付款成功；3-付款失败；4-付款中；
    @Column(name = "CONFIRM_STATUS", length = 1)
    private String confirmStatus;//'0-不需发送；1-待确认；2-待确认；3-部分确认；4-确认完成；5-确认失败
    @Column(name = "bank_name", length = 50)
    private String bankName;
    @Column(name = "bank_card_no", length = 40)
    private String bankCardNo;
    @Column(name = "TRADE_TIME")
    private Timestamp tradeTime;//'下单时间';
    @Column(name = "CREATE_TIME")
    private Timestamp createTime;//'创建时间';
    @Column(name = "UPDATE_TIME")
    private Timestamp updateTime;//'修改时间';
    @Column(name = "DELETE_TIME")
    private Timestamp deleteTime;//'删除时间';

    public Trade() {
    }

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

    public BigDecimal getPrdPrice() {
        return prdPrice;
    }

    public void setPrdPrice(BigDecimal prdPrice) {
        this.prdPrice = prdPrice;
    }

    public String getPrdCode() {
        return prdCode;
    }

    public void setPrdCode(String prdCode) {
        this.prdCode = prdCode;
    }

    public Long getHoldCapitalId() {
        return holdCapitalId;
    }

    public void setHoldCapitalId(Long holdCapitalId) {
        this.holdCapitalId = holdCapitalId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(String confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

    public Timestamp getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Timestamp tradeTime) {
        this.tradeTime = tradeTime;
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
