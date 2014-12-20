package com.sunlights.customer.service.rewardrules.vo;

import java.math.BigDecimal;

/**
 * Created by tangweiqun on 2014/12/2.
 */
public class RewardFlowRecordVo {

    private String scene;

    private String custId;

    private Long activityId;

    private String activityTitle;

    private String activityType;

    private String rewardType;

    private Integer operatorType;
    /**
     * 本次活动得到的奖励数
     */
    private Long rewardAmtResult;

    private BigDecimal rewardAmtFromTrans;
    /**
     * 奖励对应着折现
     */
    private BigDecimal moneyResult;


    private Long notGet;

    private String status;

    private String ruleUrl;

    private String detail;

    private Integer rewardFlowStatus;


    private boolean isRecommender;

    public boolean isRecommender() {
        return isRecommender;
    }

    public void setRecommender(boolean isRecommender) {
        this.isRecommender = isRecommender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public Long getNotGet() {
        return notGet;
    }

    public void setNotGet(Long notGet) {
        this.notGet = notGet;
    }

    public Integer getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(Integer operatorType) {
        this.operatorType = operatorType;
    }

    public String getRuleUrl() {
        return ruleUrl;
    }

    public void setRuleUrl(String ruleUrl) {
        this.ruleUrl = ruleUrl;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public BigDecimal getRewardAmtFromTrans() {
        return rewardAmtFromTrans;
    }

    public void setRewardAmtFromTrans(BigDecimal rewardAmtFromTrans) {
        this.rewardAmtFromTrans = rewardAmtFromTrans;
    }

    public Integer getRewardFlowStatus() {
        return rewardFlowStatus;
    }

    public void setRewardFlowStatus(Integer rewardFlowStatus) {
        this.rewardFlowStatus = rewardFlowStatus;
    }
}
