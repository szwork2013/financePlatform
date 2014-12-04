package com.sunlights.customer.dal;

import models.ExchangeRewardRule;

/**
 * Created by Administrator on 2014/11/19.
 */
public interface ExchangeRewardRuleDao {

    public ExchangeRewardRule findByRewardType(String rewardType);
}
