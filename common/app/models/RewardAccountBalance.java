package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
* <p>Project: financeplatform</p>
* <p>Title: RewardAccountBalance.java</p>
* <p>Description: </p>
* <p>Copyright (c) 2014 Sunlights.cc</p>
* <p>All Rights Reserved.</p>
*
* @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
*/
@Entity
@Table(name = "f_reward_account_balance")
public class RewardAccountBalance extends BaseEntity{
    @Column(name = "customer_id", length = 30)
    private String customerId;
    @Column(name = "reward_account_balance")
    private BigDecimal rewardAccountBalance;
    @Column(name = "reward_income_balance")
    private BigDecimal rewardIncomeBalance;
    @Column(name = "reward_expend_balance")
    private BigDecimal rewardExpendBalance;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getRewardAccountBalance() {
        return rewardAccountBalance;
    }

    public void setRewardAccountBalance(BigDecimal rewardAccountBalance) {
        this.rewardAccountBalance = rewardAccountBalance;
    }

    public BigDecimal getRewardIncomeBalance() {
        return rewardIncomeBalance;
    }

    public void setRewardIncomeBalance(BigDecimal rewardIncomeBalance) {
        this.rewardIncomeBalance = rewardIncomeBalance;
    }

    public BigDecimal getRewardExpendBalance() {
        return rewardExpendBalance;
    }

    public void setRewardExpendBalance(BigDecimal rewardExpendBalance) {
        this.rewardExpendBalance = rewardExpendBalance;
    }
}
