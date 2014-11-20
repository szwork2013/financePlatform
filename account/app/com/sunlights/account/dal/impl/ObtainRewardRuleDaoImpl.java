package com.sunlights.account.dal.impl;

import com.sunlights.account.dal.ObtainRewardRuleDao;
import com.sunlights.common.dal.EntityBaseDao;
import models.ObtainRewardRule;

import java.util.List;

/**
 * Created by tangweiqun on 2014/11/19.
 */
public class ObtainRewardRuleDaoImpl extends EntityBaseDao implements ObtainRewardRuleDao {
    @Override
    public List<ObtainRewardRule> getByActivityId(Long activityId) {
        return findBy(ObtainRewardRule.class, "activityId", activityId);
    }
}
