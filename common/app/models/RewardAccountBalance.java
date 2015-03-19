package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
    private Long rewardAccountBalance;
    @Column(name = "reward_income_balance")
    private Long rewardIncomeBalance;
    @Column(name = "reward_expend_balance")
    private Long rewardExpendBalance;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Long getRewardAccountBalance() {
        return rewardAccountBalance;
    }

    public void setRewardAccountBalance(Long rewardAccountBalance) {
        this.rewardAccountBalance = rewardAccountBalance;
    }

    public Long getRewardIncomeBalance() {
        return rewardIncomeBalance;
    }

    public void setRewardIncomeBalance(Long rewardIncomeBalance) {
        this.rewardIncomeBalance = rewardIncomeBalance;
    }

    public Long getRewardExpendBalance() {
        return rewardExpendBalance;
    }

    public void setRewardExpendBalance(Long rewardExpendBalance) {
        this.rewardExpendBalance = rewardExpendBalance;
    }
}
