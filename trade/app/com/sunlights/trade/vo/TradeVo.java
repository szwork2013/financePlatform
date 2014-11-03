package com.sunlights.trade.vo;

import java.io.Serializable;

/**
 * <p>Project: fsp</p>
 * <p>Title: TradeVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class TradeVo implements Serializable{
    private String productName;//产品名称
    private String tradeAmount;//交易金额
    private String tradeTime;//交易时间
    private String tradeTypeDesc;//交易类型   1:申购 2:赎回 3:分红
    private String tradeStatusDesc;//交易状态

//    private String tradeNo;//交易单号
//    private String paymentTypeDesc;//付款方式及金额

    public TradeVo() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(String tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getTradeTypeDesc() {
        return tradeTypeDesc;
    }

    public void setTradeTypeDesc(String tradeTypeDesc) {
        this.tradeTypeDesc = tradeTypeDesc;
    }

    public String getTradeStatusDesc() {
        return tradeStatusDesc;
    }

    public void setTradeStatusDesc(String tradeStatusDesc) {
        this.tradeStatusDesc = tradeStatusDesc;
    }
}
