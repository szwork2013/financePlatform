package com.sunlights.customer.vo;

/**
 * Created by tangweiqun on 2014/11/13.
 */
public class ExchangeParamter {

    private String id;
    private String bankCardNo;
    private String bankName;
    private String phone;
    private String exchangeAmt;

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getExchangeAmt() {
        return exchangeAmt;
    }

    public void setExchangeAmt(String exchangeAmt) {
        this.exchangeAmt = exchangeAmt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
