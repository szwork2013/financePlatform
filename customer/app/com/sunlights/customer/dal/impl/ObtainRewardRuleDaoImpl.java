package com.sunlights.customer.dal.impl;


import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.customer.dal.ObtainRewardRuleDao;
import models.ObtainRewardRule;
import models.Trade;

import java.util.List;

/**
 * Created by tangweiqun on 2014/11/19.
 */
public class ObtainRewardRuleDaoImpl extends EntityBaseDao implements ObtainRewardRuleDao {
    @Override
    public List<ObtainRewardRule> getByActivityId(Long activityId) {
        return findBy(ObtainRewardRule.class, "activityId", activityId);
    }

    @Override
    public List<Trade> getTradesByCustId(String custId) {
        return findBy(Trade.class, "custId", custId);
    }
}
