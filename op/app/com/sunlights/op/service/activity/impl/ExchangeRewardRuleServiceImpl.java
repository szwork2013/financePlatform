package com.sunlights.op.service.activity.impl;


import com.sunlights.op.dal.activity.ExchangeRewardRuleDao;
import com.sunlights.op.dal.activity.impl.ExchangeRewardRuleDaoImpl;
import com.sunlights.op.service.activity.ExchangeRewardRuleService;
import models.ExchangeRewardRule;

import java.util.List;

/**
 * Created by tangweiqun on 2014/11/14.
 */
public class ExchangeRewardRuleServiceImpl implements ExchangeRewardRuleService {

    private ExchangeRewardRuleDao exchangeRewardRuleDao = new ExchangeRewardRuleDaoImpl();
    @Override
    public List<ExchangeRewardRule> findAllRules() {
        return exchangeRewardRuleDao.findAllTypes();
    }

    @Override
    public List<ExchangeRewardRule> findRulesByRewardType(String rewardType) {
        return exchangeRewardRuleDao.findRulesByRewardType(rewardType);
    }

    @Override
    public boolean add(ExchangeRewardRule rule) {
        return exchangeRewardRuleDao.doInsert(rule);
    }

    @Override
    public boolean remove(Long id) {
        return exchangeRewardRuleDao.doDelete(id);
    }

    @Override
    public boolean modify(ExchangeRewardRule rule) {
        return exchangeRewardRuleDao.doUpdate(rule);
    }

    @Override
    public void removeByTypeId(String rewardType) {
        exchangeRewardRuleDao.doDeleteByTypeId(rewardType);
    }
}
