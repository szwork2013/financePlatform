package com.sunlights.trade.vo;

import java.io.Serializable;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: ShuMiTradeVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class ShuMiTradeFormVo implements Serializable{
   //申购、赎回
    private String applySerial;//申请编号
    private String fundCode;// -->基金代码
    private String fundName;
    private String applySum;//认购/赎回份额
    private String bankCardInfo;//  工商银行***1416; -->关联银行卡信息
    private String dateTime;//2013-05-10T17:56:57.396875+08:00; -->扣款/赎回时间

    //申购
    private String bankName;// = 工商银行; -->银行名称
    private String bankAcco;//银行卡号

    public String getApplySerial() {
        return applySerial;
    }

    public void setApplySerial(String applySerial) {
        this.applySerial = applySerial;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getApplySum() {
        return applySum;
    }

    public void setApplySum(String applySum) {
        this.applySum = applySum;
    }

    public String getBankCardInfo() {
        return bankCardInfo;
    }

    public void setBankCardInfo(String bankCardInfo) {
        this.bankCardInfo = bankCardInfo;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAcco() {
        return bankAcco;
    }

    public void setBankAcco(String bankAcco) {
        this.bankAcco = bankAcco;
    }
}
