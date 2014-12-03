package com.sunlights.customer.dal.impl;


import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.customer.dal.ExchangeRewardRuleDao;
import models.ExchangeRewardRule;

import java.util.List;

/**
 * Created by tangweiqun on 2014/11/19.
 */
public class ExchangeRewardRuleDaoImpl extends EntityBaseDao implements ExchangeRewardRuleDao {
    @Override
    public ExchangeRewardRule findByRewardType(String rewardType) {
        List<ExchangeRewardRule> exchangeRewardRuleList = findBy(ExchangeRewardRule.class, "rewardType", rewardType);
        if(exchangeRewardRuleList != null && !exchangeRewardRuleList.isEmpty()) {
            return exchangeRewardRuleList.get(0);
        }
        return null;
    }
}
