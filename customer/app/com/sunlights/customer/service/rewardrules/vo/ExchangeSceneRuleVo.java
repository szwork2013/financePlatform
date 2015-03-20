package com.sunlights.customer.service.rewardrules.vo;

import models.ExchangeRewardRule;
import models.ExchangeScene;
import models.RewardType;

/**
 * Created by Administrator on 2015/3/20.
 */
public class ExchangeSceneRuleVo extends ExchangeScene {
    private ExchangeScene exchangeScene;

    private RewardType rewardTypeModel;

    private ExchangeRewardRule exchangeRewardRule;

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

    public ExchangeScene getExchangeScene() {
        return exchangeScene;
    }

    public void setExchangeScene(ExchangeScene exchangeScene) {
        this.exchangeScene = exchangeScene;
    }
}
