package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by tangweiqun on 2014/11/19.
 */
@Entity
@Table(name = "F_REWARD_COUNT")
public class HoldReward extends IdEntity {
    @Column(name = "customer_Id")
    private String custId;
    @Column(name = "ACTIVITY_TYPE")
    private String activityType;
    @Column(name = "REWARD_TYPE")
    private String rewardType;
    @Column(name = "GET_AMOUNT")
    private Long getReward;
    @Column(name = "FROZEN_REWARD")
    private Long frozenReward;
    @Column(name = "HOLD_REWARD")
    private Long holdReward;
    @Column(name = "GET_MONEY")
    private BigDecimal getMoney;
    @Column(name = "HOLD_MONEY")
    private BigDecimal holdMoney;
    @Column(name = "FROZEN_MONEY")
    private BigDecimal frozenMoney;
    @Column(name = "CREATE_TIME")
    private Date createTime;
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getRewardType() {
        return rewardType;
    }

    public void setRewardType(String rewardType) {
        this.rewardType = rewardType;
    }

    public Long getGetReward() {
        return getReward;
    }

    public void setGetReward(Long getReward) {
        this.getReward = getReward;
    }

    public Long getFrozenReward() {
        return frozenReward;
    }

    public void setFrozenReward(Long frozenReward) {
        this.frozenReward = frozenReward;
    }

    public Long getHoldReward() {
        return holdReward;
    }

    public void setHoldReward(Long holdReward) {
        this.holdReward = holdReward;
    }

    public BigDecimal getGetMoney() {
        return getMoney;
    }

    public void setGetMoney(BigDecimal getMoney) {
        this.getMoney = getMoney;
    }

    public BigDecimal getHoldMoney() {
        return holdMoney;
    }

    public void setHoldMoney(BigDecimal holdMoney) {
        this.holdMoney = holdMoney;
    }

    public BigDecimal getFrozenMoney() {
        return frozenMoney;
    }

    public void setFrozenMoney(BigDecimal frozenMoney) {
        this.frozenMoney = frozenMoney;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }
}
