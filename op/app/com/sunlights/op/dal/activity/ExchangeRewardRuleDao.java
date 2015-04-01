package com.sunlights.op.dal.activity;

import models.ExchangeRewardRule;

import java.util.List;

/**
 * Created by Administrator on 2014/11/14.
 */
public interface ExchangeRewardRuleDao {

    public List<ExchangeRewardRule> findAllTypes();

    public boolean doInsert(ExchangeRewardRule rule);

    public boolean doUpdate(ExchangeRewardRule rule);

    public boolean doDelete(Long id);
    public boolean doDeleteByTypeId(String rewardType);
    public ExchangeRewardRule findById(Long id);

    public List<ExchangeRewardRule> findRulesByRewardType(String rewardType);
}
