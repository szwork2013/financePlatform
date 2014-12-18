package com.sunlights.customer.vo;

import java.io.Serializable;

/**
 * TODO: 为何所有的属性都整成了String类型？
 * Created by tangweiqun on 2014/11/20.
 */
public class HoldRewardVo implements Serializable{
    //总金豆数量
    private String totalReward;

    //等价现金
    private String totalCash;

    private String ruleUrl;

    private String redPacket;

    public String getTotalReward() {
        return totalReward;
    }

    public void setTotalReward(String totalReward) {
        this.totalReward = totalReward;
    }


    public String getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(String totalCash) {
        this.totalCash = totalCash;
    }

    public String getRuleUrl() {
        return ruleUrl;
    }

    public void setRuleUrl(String ruleUrl) {
        this.ruleUrl = ruleUrl;
    }

    public String getRedPacket() {
        return redPacket;
    }

    public void setRedPacket(String redPacket) {
        this.redPacket = redPacket;
    }
}
