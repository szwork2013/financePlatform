package com.sunlights.account.service;

import models.ObtainRewardRule;

import java.util.List;

/**
 * Created by Administrator on 2014/11/19.
 */
public interface ObtainRewardRuleService {

    public List<ObtainRewardRule> getByActivityId(Long activityId);


}
