package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by tangweiqun on 2014/11/12.
 */
@Entity
@Table(name = "F_GET_REWARD_RULE")
public class ObtainRewardRule extends BaseEntity{
    @Column(name = "ACTIVITY_ID")
    private String activityId;
    @Column(name = "REWARD_TYPE")
    private String rewardType;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "SHOULD_REWARD")
    private BigDecimal shouldReward;
    @Column(name = "REAL_REWARD")
    private BigDecimal realReward;
    @Column(name = "BACK_REWARD")
    private BigDecimal backReward;
    @Column(name = "EFFECT_TIME")
    private Integer effectTime;
    @Column(name = "VALID_TIME")
    private Integer validTime;
    @Column(name = "TOTAL_LIMIT_AMT")
    private BigDecimal totalLimitAmt;
    @Column(name = "PRODUCT_TYPE")
    private String prdType;
    @Column(name = "PRODUCT_CODE")
    private String prdCode;
    @Column(name = "ACTIVITY_CHANNEL")
    private Integer activityChannel;
    @Column(name = "TRADE_AMT")
    private BigDecimal tradeMoney;
    @Column(name = "BACK_FUNDS")
    private BigDecimal backFunds;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getRewardType() {
        return rewardType;
    }

    public void setRewardType(String rewardType) {
        this.rewardType = rewardType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getShouldReward() {
        return shouldReward;
    }

    public void setShouldReward(BigDecimal shouldReward) {
        this.shouldReward = shouldReward;
    }

    public BigDecimal getRealReward() {
        return realReward;
    }

    public void setRealReward(BigDecimal realReward) {
        this.realReward = realReward;
    }

    public BigDecimal getBackReward() {
        return backReward;
    }

    public void setBackReward(BigDecimal backReward) {
        this.backReward = backReward;
    }

    public Integer getEffectTime() {
        return effectTime;
    }

    public void setEffectTime(Integer effectTime) {
        this.effectTime = effectTime;
    }

    public Integer getValidTime() {
        return validTime;
    }

    public void setValidTime(Integer validTime) {
        this.validTime = validTime;
    }

    public BigDecimal getTotalLimitAmt() {
        return totalLimitAmt;
    }

    public void setTotalLimitAmt(BigDecimal totalLimitAmt) {
        this.totalLimitAmt = totalLimitAmt;
    }

    public String getPrdType() {
        return prdType;
    }

    public void setPrdType(String prdType) {
        this.prdType = prdType;
    }

    public String getPrdCode() {
        return prdCode;
    }

    public void setPrdCode(String prdCode) {
        this.prdCode = prdCode;
    }

    public Integer getActivityChannel() {
        return activityChannel;
    }

    public void setActivityChannel(Integer activityChannel) {
        this.activityChannel = activityChannel;
    }

    public BigDecimal getTradeMoney() {
        return tradeMoney;
    }

    public void setTradeMoney(BigDecimal tradeMoney) {
        this.tradeMoney = tradeMoney;
    }

    public BigDecimal getBackFunds() {
        return backFunds;
    }

    public void setBackFunds(BigDecimal backFunds) {
        this.backFunds = backFunds;
    }
}
