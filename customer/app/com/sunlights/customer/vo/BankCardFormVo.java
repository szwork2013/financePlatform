package com.sunlights.customer.vo;


import java.io.Serializable;

/**
 * <p>Project: fsp</p>
 * <p>Title: BankCardFormVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class BankCardFormVo implements Serializable {
    private String No;
    private String TradeAccount;
    private String SubTradeAccount;
    private String IsVaild;
    private String Balance;
    private String Status;
    private String StatusToCN;
    private String IsFreeze;
    private String BankSerial;
    private String BankName;
    private String CapitalMode;
    private String BindWay;
    private String SupportAutoPay;
    private String DiscountRate;
    private String LimitDescribe;
    private String ContentDescribe;
    private String Priority;
    private String IsP2p;

    public String getNo() {
        return No;
    }

    public void setNo(String no) {
        No = no;
    }

    public String getTradeAccount() {
        return TradeAccount;
    }

    public void setTradeAccount(String tradeAccount) {
        TradeAccount = tradeAccount;
    }

    public String getIsVaild() {
        return IsVaild;
    }

    public void setIsVaild(String isVaild) {
        IsVaild = isVaild;
    }

    public String getBalance() {
        return Balance;
    }

    public void setBalance(String balance) {
        Balance = balance;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getStatusToCN() {
        return StatusToCN;
    }

    public void setStatusToCN(String statusToCN) {
        StatusToCN = statusToCN;
    }

    public String getIsFreeze() {
        return IsFreeze;
    }

    public void setIsFreeze(String isFreeze) {
        IsFreeze = isFreeze;
    }

    public String getBankSerial() {
        return BankSerial;
    }

    public void setBankSerial(String bankSerial) {
        BankSerial = bankSerial;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public String getCapitalMode() {
        return CapitalMode;
    }

    public void setCapitalMode(String capitalMode) {
        CapitalMode = capitalMode;
    }

    public String getBindWay() {
        return BindWay;
    }

    public void setBindWay(String bindWay) {
        BindWay = bindWay;
    }

    public String getSupportAutoPay() {
        return SupportAutoPay;
    }

    public void setSupportAutoPay(String supportAutoPay) {
        SupportAutoPay = supportAutoPay;
    }

    public String getDiscountRate() {
        return DiscountRate;
    }

    public void setDiscountRate(String discountRate) {
        DiscountRate = discountRate;
    }

    public String getLimitDescribe() {
        return LimitDescribe;
    }

    public void setLimitDescribe(String limitDescribe) {
        LimitDescribe = limitDescribe;
    }

    public String getContentDescribe() {
        return ContentDescribe;
    }

    public void setContentDescribe(String contentDescribe) {
        ContentDescribe = contentDescribe;
    }

    public String getPriority() {
        return Priority;
    }

    public void setPriority(String priority) {
        Priority = priority;
    }

    public String getIsP2p() {
        return IsP2p;
    }

    public String getSubTradeAccount() {
        return SubTradeAccount;
    }

    public void setSubTradeAccount(String subTradeAccount) {
        SubTradeAccount = subTradeAccount;
    }

    public void setIsP2p(String isP2p) {
        IsP2p = isP2p;
    }
}
