package com.sunlights.customer.service.impl;


import com.sunlights.common.cache.Cacheable;
import com.sunlights.customer.dal.ExchangeRewardRuleDao;
import com.sunlights.customer.dal.impl.ExchangeRewardRuleDaoImpl;
import com.sunlights.customer.service.ExchangeRewardRuleService;
import models.ExchangeRewardRule;

/**
 * Created by tangweiqun on 2014/11/19.
 */
public class ExchangeRewardRuleServiceImpl  implements ExchangeRewardRuleService {

    private ExchangeRewardRuleDao exchangeRewardRuleDao = new ExchangeRewardRuleDaoImpl();

    @Deprecated
    @Cacheable(key = "findByRewardType", duration = 300)
    @Override
    public ExchangeRewardRule findByRewardType(String rewardType) {
        return exchangeRewardRuleDao.findByRewardType(rewardType);
    }
}
