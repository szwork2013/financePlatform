package com.sunlights.trade.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: TradeInfoFormVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class TradeForecastFormVo implements Serializable {
    private String FundCode;
    private String TradeAccount;
    private String ApplySerial;
    private String Amount;
    private String ApplyDateTime;
    private String Status;// 9 （待处理）
    private String BusinessType;//022(充值)，024（赎回）
    
    
    private Date tradeTime;

    public String getFundCode() {
        return FundCode;
    }

    public void setFundCode(String fundCode) {
        FundCode = fundCode;
    }

    public String getTradeAccount() {
        return TradeAccount;
    }

    public void setTradeAccount(String tradeAccount) {
        TradeAccount = tradeAccount;
    }

    public String getApplySerial() {
        return ApplySerial;
    }

    public void setApplySerial(String applySerial) {
        ApplySerial = applySerial;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getApplyDateTime() {
        return ApplyDateTime;
    }

    public void setApplyDateTime(String applyDateTime) {
        ApplyDateTime = applyDateTime;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getBusinessType() {
        return BusinessType;
    }

    public void setBusinessType(String businessType) {
        BusinessType = businessType;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }
}
