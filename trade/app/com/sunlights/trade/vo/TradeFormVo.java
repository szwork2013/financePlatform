package com.sunlights.trade.vo;

import java.io.Serializable;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: TradeFormVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class TradeFormVo implements Serializable {
  private String tradeAmount;
  private String quantity;
  private String prdCode;
  private String bankCardNo;


  public String getTradeAmount() {
    return tradeAmount;
  }

  public void setTradeAmount(String tradeAmount) {
    this.tradeAmount = tradeAmount;
  }

  public String getQuantity() {
    return quantity;
  }

  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }

  public String getPrdCode() {
    return prdCode;
  }

  public void setPrdCode(String prdCode) {
    this.prdCode = prdCode;
  }

  public String getBankCardNo() {
    return bankCardNo;
  }

  public void setBankCardNo(String bankCardNo) {
    this.bankCardNo = bankCardNo;
  }
}
