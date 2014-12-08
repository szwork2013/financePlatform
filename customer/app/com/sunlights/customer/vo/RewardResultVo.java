package com.sunlights.customer.vo;

import com.sunlights.common.vo.Message;

import java.io.Serializable;

/**
 * Created by tangweiqu on 2014/11/19.
 */
public class RewardResultVo implements Serializable {
    /**
     * 获取积分返回
     */
    private Message returnMessage;

    /**
     * 获取奖励的场景
     */
    private String scene;

    /**
     * 可获得（或者兑换）的奖励
     */
    private Long rewards;

    private Long activityId;

    private String activityTitle;

    private String rewardType;

    private String operatorType;

    private String status;

    private Long alreadyGet;

    private Long notGet;

    /**
     * 处罚点击事件是否可以获取到奖励  true表示可以  false表示不可以
     */
    private boolean isCanNotObtain;

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public Long getRewards() {
        return rewards;
    }

    public void setRewards(Long rewards) {
        this.rewards = rewards;
    }

    public Message getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(Message returnMessage) {
        this.returnMessage = returnMessage;
    }

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

    public String getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public boolean isCanNotObtain() {
        return isCanNotObtain;
    }

    public void setCanNotObtain(boolean isCanNotObtain) {
        this.isCanNotObtain = isCanNotObtain;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getAlreadyGet() {
        return alreadyGet;
    }

    public void setAlreadyGet(Long alreadyGet) {
        this.alreadyGet = alreadyGet;
    }

    public Long getNotGet() {
        return notGet;
    }

    public void setNotGet(Long notGet) {
        this.notGet = notGet;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ scene = ").append(scene).append(",")
                .append(" alreadyGet = ").append(alreadyGet).append(",")
                .append(" rewardType = ").append(rewardType).append(",")
                .append(" notGet = ").append(notGet).append(",")
                .append(" activityId = ").append(activityId).append(",")
                .append(" code = ").append(returnMessage.getCode()).append("]");
        return sb.toString();
    }
}
