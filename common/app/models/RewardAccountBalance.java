package models;

import javax.persistence.Column;

/**
* <p>Project: financeplatform</p>
* <p>Title: RewardAccountBalance.java</p>
* <p>Description: </p>
* <p>Copyright (c) 2014 Sunlights.cc</p>
* <p>All Rights Reserved.</p>
*
* @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
*/
public class RewardAccountBalance extends BaseEntity{
    @Column(name = "customer_Id", length = 30)
    private String customerId;
    @Column(name = "reword_account_balance")
    private Long rewordAccountBalance;
    @Column(name = "reword_income_balance")
    private Long rewordIncomeBalance;
    @Column(name = "reword_expend_balance")
    private Long rewordExpendBalance;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Long getRewordAccountBalance() {
        return rewordAccountBalance;
    }

    public void setRewordAccountBalance(Long rewordAccountBalance) {
        this.rewordAccountBalance = rewordAccountBalance;
    }

    public Long getRewordIncomeBalance() {
        return rewordIncomeBalance;
    }

    public void setRewordIncomeBalance(Long rewordIncomeBalance) {
        this.rewordIncomeBalance = rewordIncomeBalance;
    }

    public Long getRewordExpendBalance() {
        return rewordExpendBalance;
    }

    public void setRewordExpendBalance(Long rewordExpendBalance) {
        this.rewordExpendBalance = rewordExpendBalance;
    }
}
