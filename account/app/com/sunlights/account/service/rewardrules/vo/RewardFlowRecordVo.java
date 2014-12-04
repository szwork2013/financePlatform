package com.sunlights.account.service.rewardrules.vo;

import java.math.BigDecimal;

/**
 * Created by tangweiqun on 2014/12/2.
 */
public class RewardFlowRecordVo {

    private String custId;

    private Long activityId;

    private String activityTitle;

    private String activityType;

    private String rewardType;
    /**
     * 本次活动得到的奖励数
     */
    private Long rewardAmtResult;
    /**
     * 奖励对应着折现
     */
    private BigDecimal moneyResult;

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
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

    public Long getRewardAmtResult() {
        return rewardAmtResult;
    }

    public void setRewardAmtResult(Long rewardAmtResult) {
        this.rewardAmtResult = rewardAmtResult;
    }

    public BigDecimal getMoneyResult() {
        return moneyResult;
    }

    public void setMoneyResult(BigDecimal moneyResult) {
        this.moneyResult = moneyResult;
    }
}
