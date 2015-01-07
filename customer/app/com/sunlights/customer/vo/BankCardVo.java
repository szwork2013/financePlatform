package com.sunlights.customer.vo;

import models.BankCard;


/**
 * Created by Yuan on 2014/9/15.
 */
public class BankCardVo {
    private String id;
    private String bankId;
    private String bankCode;
    private String accountId;
    private String displayNo;
    // 0：验证为通过  1：验证通过  2：验证中
    private String validateStatus;

    //绑定银行卡返回数据
    private String bankName;
    private String bankCard;
    private String bankSerial;//银行序列号

    public BankCardVo() {
        super();
    }

    public BankCardVo(BankCard bankCard) {
        inBankCard(bankCard);
    }

    private void inBankCard(BankCard bankCard) {
        this.id = String.valueOf(bankCard.getId());
        this.bankName = bankCard.getBankName();
        this.accountId = bankCard.getCustomerId();
        String bankCardNo = "";
        String cardNo = bankCard.getBankCardNo();
        if (cardNo != null && cardNo.length() > 4) {
            for (int i = 0; i < cardNo.length() - 4; i++) {
                bankCardNo += "*";
            }

            this.displayNo = bankCardNo + cardNo.substring(cardNo.length() - 4);
        }
        this.validateStatus = bankCard.getStatus();
        this.bankCard = bankCard.getBankCardNo();
    }

    public String getBankSerial() {
        return bankSerial;
    }

    public void setBankSerial(String bankSerial) {
        this.bankSerial = bankSerial;
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

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getDisplayNo() {
        return displayNo;
    }

    public void setDisplayNo(String displayNo) {
        this.displayNo = displayNo;
    }

    public String getValidateStatus() {
        return validateStatus;
    }

    public void setValidateStatus(String validateStatus) {
        this.validateStatus = validateStatus;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }
}
