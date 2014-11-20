package com.sunlights.account.dal;

import models.ObtainRewardRule;

import java.util.List;

/**
 * Created by Administrator on 2014/11/19.
 */
public interface ObtainRewardRuleDao {

    public List<ObtainRewardRule> getByActivityId(Long activityId);
}
