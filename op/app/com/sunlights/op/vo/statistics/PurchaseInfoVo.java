package com.sunlights.op.vo.statistics;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2015/1/14.
 */
public class PurchaseInfoVo {
    private String customerId;

    private String realName;

    private String recommendPhone;

    private String mobile;

    private String cbMobile;

    private String cbRealName;

    private String tradeNo;

    private BigDecimal tradeAmt;

    private BigDecimal totalAmt;

    private String tradeType;

    private Date createTime;

    private String bankName;

    private String cardNo;

    public BigDecimal getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCbMobile() {
        return cbMobile;
    }

    public void setCbMobile(String cbMobile) {
        this.cbMobile = cbMobile;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRecommendPhone() {
        return recommendPhone;
    }

    public void setRecommendPhone(String recommendPhone) {
        this.recommendPhone = recommendPhone;
    }

    public String getCbRealName() {
        return cbRealName;
    }

    public void setCbRealName(String cbRealName) {
        this.cbRealName = cbRealName;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
}
