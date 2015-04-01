package com.sunlights.op.vo.statistics;

/**
 * Created by Administrator on 2015/1/13.
 */
public class PurchaseItemsVo {
    private String date;

    private String recommenderName;

    private String purchaseName;

    private String balance;

    private String inAmt;

    private String outAmt;

    private Integer bankCardNum;
     private String bankNames;
    public String getCbMobile() {
        return cbMobile;
    }

    public void setCbMobile(String cbMobile) {
        this.cbMobile = cbMobile;
    }

    private String cbMobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    private String mobile;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRecommenderName() {
        return recommenderName;
    }

    public void setRecommenderName(String recommenderName) {
        this.recommenderName = recommenderName;
    }

    public String getPurchaseName() {
        return purchaseName;
    }

    public void setPurchaseName(String purchaseName) {
        this.purchaseName = purchaseName;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getInAmt() {
        return inAmt;
    }

    public void setInAmt(String inAmt) {
        this.inAmt = inAmt;
    }

    public String getOutAmt() {
        return outAmt;
    }

    public void setOutAmt(String outAmt) {
        this.outAmt = outAmt;
    }

    public Integer getBankCardNum() {
        return bankCardNum;
    }

    public void setBankCardNum(Integer bankCardNum) {
        this.bankCardNum = bankCardNum;
    }

    public String getBankNames() {
        return bankNames;
    }

    public void setBankNames(String bankNames) {
        this.bankNames = bankNames;
    }
}
