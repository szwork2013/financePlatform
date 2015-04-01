package com.sunlights.op.vo.activity;

import models.ExchangeScene;

/**
 * Created by Administrator on 2014/12/11.
 */
public class ExchangeSceneVo extends ExchangeScene {

    private String rewardTypeStr;

    private String activityTypeStr;

    private String exchangeTypeStr;

    public String getActivityTypeStr() {
        return ActivityType.getDescByType(getActivityType());
    }

    public void setActivityTypeStr(String activityTypeStr) {
        this.activityTypeStr = activityTypeStr;
    }

    public String getRewardTypeStr() {
        return rewardTypeStr;
    }

    public void setRewardTypeStr(String rewardTypeStr) {
        this.rewardTypeStr = rewardTypeStr;
    }

    public String getExchangeTypeStr() {
        return ExchangeType.getDescByType(getExchangeType());
    }

    public void setExchangeTypeStr(String exchangeTypeStr) {
        this.exchangeTypeStr = exchangeTypeStr;
    }
}
