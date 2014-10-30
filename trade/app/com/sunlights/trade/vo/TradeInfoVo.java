package com.sunlights.trade.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>Project: fsp</p>
 * <p>Title: TradeVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class TradeInfoVo implements Serializable{
    private String productCode;//产品代码
    private String productName;//产品名称
    private BigDecimal subCapital;//申购、赎回金额
    private BigDecimal yesterdayProfit;//昨日收益
    private String tradeTime;//交易时间
    private String tradeType;//交易类型   1:申购 2:赎回 3:分红
    private String tradeStatus;//交易状态  1：存/取钱中、2：存/取钱成功、3：存/取失败【失败原因】
    private String payStatus;//交易付款标志 0-不需付款；1-未付款；2-付款成功；3-付款失败；4-付款中；
    private String confirmStatus;//0-不需发送；1-待确认；2-待确认；3-部分确认；4-确认完成；5-确认失败
    private String tradeNo;//交易单号
    private String bankName;
    private String bankCardNo;

    public TradeInfoVo() {
    }

    public TradeInfoVo(String productCode, String productName, BigDecimal subCapital,
                       BigDecimal yesterdayProfit, String tradeTime, String tradeType,
                       String tradeStatus, String payStatus, String confirmStatus,
                       String tradeNo) {
        this.productCode = productCode;
        this.productName = productName;
        this.subCapital = subCapital;
        this.yesterdayProfit = yesterdayProfit;
        this.tradeTime = tradeTime;
        this.tradeType = tradeType;
        this.tradeStatus = tradeStatus;
        this.payStatus = payStatus;
        this.confirmStatus = confirmStatus;
        this.tradeNo = tradeNo;
    }

    public TradeInfoVo(String productCode, String productName, BigDecimal subCapital,
                       BigDecimal yesterdayProfit, String tradeTime, String tradeType,
                       String tradeStatus, String payStatus, String confirmStatus,
                       String tradeNo, String bankName, String bankCardNo) {
        this.productCode = productCode;
        this.productName = productName;
        this.subCapital = subCapital;
        this.yesterdayProfit = yesterdayProfit;
        this.tradeTime = tradeTime;
        this.tradeType = tradeType;
        this.tradeStatus = tradeStatus;
        this.payStatus = payStatus;
        this.confirmStatus = confirmStatus;
        this.tradeNo = tradeNo;
        this.bankName = bankName;
        this.bankCardNo = bankCardNo;
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

    public BigDecimal getYesterdayProfit() {
        return yesterdayProfit;
    }

    public void setYesterdayProfit(BigDecimal yesterdayProfit) {
        this.yesterdayProfit = yesterdayProfit;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
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

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public BigDecimal getSubCapital() {
        return subCapital;
    }

    public void setSubCapital(BigDecimal subCapital) {
        this.subCapital = subCapital;
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
}
