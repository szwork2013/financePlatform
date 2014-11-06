package com.sunlights.core.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import models.Bank;
import models.BankCard;


/**
 * Created by Yuan on 2014/9/15.
 */
public class BankCardVo {
    private String id;
    private String bankName;
    private String bankId;
    private String bankCode;
    private String accountId;
    private String displayNo;
    private String no;
    // 0：验证为通过  1：验证通过  2：验证中
    private String validateStatus;

    @JsonIgnore
    private String bankCardNo;

    public BankCardVo() {
        super();
    }

    public BankCardVo(BankCard bankCard, Bank bank) {
        inBankCard(bankCard, bank);
    }

    private void inBankCard(BankCard bankCard, Bank bank) {
        this.id = String.valueOf(bankCard.getId());
        this.bankId = String.valueOf(bank.getId());
        this.bankName = bank.getBankName();
        this.bankCode = bank.getBankCode();
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
        this.bankCardNo = bankCard.getBankCardNo();
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

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }
}
