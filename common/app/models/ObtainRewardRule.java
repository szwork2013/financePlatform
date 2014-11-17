package models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by tangweiqun on 2014/11/12.
 */
@Entity
@Table(name = "F_GET_REWARD_RULE")
public class ObtainRewardRule extends IdEntity{
    @Column(name = "ACTIVITY_ID")
    private Long activityId;
    @Column(name = "REWARD_TYPE")
    private String rewardType;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "SHOULD_REWARD")
    private Long shouldReward;
    @Column(name = "REAL_REWARD")
    private Long realReward;
    @Column(name = "BACK_REWARD")
    private Long backReward;
    @Column(name = "EFFECT_TIME")
    private Integer effectTime;
    @Column(name = "VALID_TIME")
    private Integer validTime;
    @Column(name = "TOTAL_LIMIT_AMT")
    private Long totalLimitAmt;
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;
    @Column(name = "CREATE_BY", length = 30)
    private String createBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    @Column(name = "UPDATE_BY", length = 30)
    private String updateBy;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
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

    public Long getShouldReward() {
        return shouldReward;
    }

    public void setShouldReward(Long shouldReward) {
        this.shouldReward = shouldReward;
    }

    public Long getRealReward() {
        return realReward;
    }

    public void setRealReward(Long realReward) {
        this.realReward = realReward;
    }

    public Long getBackReward() {
        return backReward;
    }

    public void setBackReward(Long backReward) {
        this.backReward = backReward;
    }

    public Long getTotalLimitAmt() {
        return totalLimitAmt;
    }

    public void setTotalLimitAmt(Long totalLimitAmt) {
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
}
