package com.sunlights.customer.service;

import models.ExchangeRewardRule;

/**
 * Created by tangweiqun on 2014/11/19.
 */
public interface ExchangeRewardRuleService {

    public ExchangeRewardRule findByRewardType(String rewardType);

}
