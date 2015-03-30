package com.sunlights.op.service.activity;


import models.ExchangeRewardRule;

import java.util.List;

/**
 * Created by Administrator on 2014/11/14.
 */
public interface ExchangeRewardRuleService {

    public List<ExchangeRewardRule> findAllRules();

    public boolean add(ExchangeRewardRule rule);

    public boolean modify(ExchangeRewardRule rule);

    public boolean remove(Long id);

    public List<ExchangeRewardRule> findRulesByRewardType(String rewardType);

    public void removeByTypeId(String rewardType);

}
