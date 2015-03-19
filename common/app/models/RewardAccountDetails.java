package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: RewardAccountDetails.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Entity
@Table(name = "f_reward_account_details")
public class RewardAccountDetails extends BaseEntity {
    @Column(name = "customer_id", length = 30)
    private String customerId;
    @Column(name = "reward_sequence", length = 20)
    private String rewordSequence;
    @Column(name = "activity_type", length = 10)
    private String activityType;
    @Column(name = "reward_type", length = 10)
    private String rewardType;
    @Column(name = "reward_amount_balance")
    private Long rewardAmountBalance;
    @Column(name = "income_expend_balance")
    private Long incomeExpendBalance;
    @Column(name = "fund_flow_type", length = 10)
    private String fundFlowType;

    public static enum FundFlowType {
        INCOME("0","奖励收入"),
        EXPEND("1","奖励支出(兑换)");

        private String type;
        private String desc;

        private FundFlowType(String type, String desc) {
            this.type = type;
            this.desc = desc;
        }

        public String getType() {
            return type;
        }

        public String getDesc() {
            return desc;
        }

        public static String getDescByStatus(String status) {
            if(status == null) {
                return null;
            }
            for(FundFlowType temp : FundFlowType.values()) {
                if(status.equals(temp.getType())) {
                    return temp.getDesc();
                }
            }
            return null;
        }
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getRewordSequence() {
        return rewordSequence;
    }

    public void setRewordSequence(String rewordSequence) {
        this.rewordSequence = rewordSequence;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getRewardType() {
        return rewardType;
    }

    public void setRewardType(String rewardType) {
        this.rewardType = rewardType;
    }

    public Long getRewardAmountBalance() {
        return rewardAmountBalance;
    }

    public void setRewardAmountBalance(Long rewardAmountBalance) {
        this.rewardAmountBalance = rewardAmountBalance;
    }

    public Long getIncomeExpendBalance() {
        return incomeExpendBalance;
    }

    public void setIncomeExpendBalance(Long incomeExpendBalance) {
        this.incomeExpendBalance = incomeExpendBalance;
    }

    public String getFundFlowType() {
        return fundFlowType;
    }

    public void setFundFlowType(String fundFlowType) {
        this.fundFlowType = fundFlowType;
    }
}
