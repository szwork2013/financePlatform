package com.sunlights.account.service;

import com.sunlights.account.service.rewardrules.vo.ObtainRewardRuleVo;
import models.ObtainRewardRule;

import java.util.List;

/**
 * Created by Administrator on 2014/11/19.
 */
public interface ObtainRewardRuleService {

    public List<ObtainRewardRule> getByActivityId(Long activityId);

    public List<ObtainRewardRuleVo> getByVosActivityId(Long activityId);


}
