package com.sunlights.customer.vo;


import models.Bank;

/**
 * Created by Administrator on 2014/9/15.
 */
public class BankVo {

    public String id;
    // 代码
    public String bankCode;
    // 名称
    public String bankName;
    // 英文名称
    public String enName;
    // 状态
    public String status;

    public BankVo() {
        super();
    }

    public BankVo(Bank bank) {
        inBank(bank);
    }

    public void inBank(Bank bank) {
        this.id = bank.getId().toString();
        this.bankCode = bank.getBankCode();
        this.bankName = bank.getBankName();
        this.enName = bank.getEnName();
        this.status = bank.getStatus();

    }
}
