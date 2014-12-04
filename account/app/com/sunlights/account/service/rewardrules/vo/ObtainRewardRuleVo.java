package com.sunlights.account.service.rewardrules.vo;

import models.ActivityStep;
import models.ExchangeRewardRule;
import models.ObtainRewardRule;
import models.RewardType;

import java.util.List;

/**
 * Created by Administrator on 2014/12/2.
 */
public class ObtainRewardRuleVo extends ObtainRewardRule{

    private RewardType rewardTypeModel;

    private ExchangeRewardRule exchangeRewardRule;

    private List<ActivityStep> activitySteps;

    public RewardType getRewardTypeModel() {
        return rewardTypeModel;
    }

    public void setRewardTypeModel(RewardType rewardTypeModel) {
        this.rewardTypeModel = rewardTypeModel;
    }

    public ExchangeRewardRule getExchangeRewardRule() {
        return exchangeRewardRule;
    }

    public void setExchangeRewardRule(ExchangeRewardRule exchangeRewardRule) {
        this.exchangeRewardRule = exchangeRewardRule;
    }

    public List<ActivityStep> getActivitySteps() {
        return activitySteps;
    }

    public void setActivitySteps(List<ActivityStep> activitySteps) {
        this.activitySteps = activitySteps;
    }
}
