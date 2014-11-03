package models;

import models.IdEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;


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
public class Trade extends IdEntity {
    @Column(length = 30, name = "cust_id")
    private String custId;//客户号
    @Column(length = 20, name = "trade_no")
    private String tradeNo;//交易流水号
    @Column(precision = 18,scale = 4,name = "product_price")
    private BigDecimal productPrice;//产品单价
    @Column(length = 8,name = "product_code")
    private String productCode;//产品编码
    @Column(length = 100,name = "product_name")
    private String productName;//产品名称
    @Column(name = "quantity")
    private int quantity;//成交数量
    @Column(precision = 18,scale = 4, name = "trade_amount")
    private BigDecimal tradeAmount;//交易金额
    @Column(precision = 18,scale = 4,name = "fee")
    private BigDecimal fee;//手续费
    @Column(name = "type", length = 1)
    private String type;//1:申购 2:赎回 3:分红
    @Column(name = "trade_status", length = 1)
    private String tradeStatus;//1：存/取钱中、2：存/取钱成功、3：存/取失败【失败原因】
    @Column(name = "pay_status", length = 1)
    private String payStatus;//'交易付款标志 0-不需付款；1-未付款；2-付款成功；3-付款失败；4-付款中；
    @Column(name = "confirm_status", length = 1)
    private String confirmStatus;//'0-不需发送；1-待确认；2-待确认；3-部分确认；4-确认完成；5-确认失败
    @Column(name = "bank_name", length = 50)
    private String bankName;
    @Column(name = "bank_card_no", length = 40)
    private String bankCardNo;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "trade_time")
    private Date tradeTime;//'下单时间';
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;//'创建时间';
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    private Date updateTime;//'修改时间';
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "delete_time")
    private Date deleteTime;//'删除时间';

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

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Timestamp tradeTime) {
        this.tradeTime = tradeTime;
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

    public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(BigDecimal tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
