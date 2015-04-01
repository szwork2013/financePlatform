package com.sunlights.op.service.activity;

import com.sunlights.op.vo.activity.ObtainRewardRuleVo;
import models.ObtainRewardRule;

import java.util.List;

/**
 * Created by Administrator on 2014/11/14.
 */
public interface ObtainRewardRuleService {

    public List<ObtainRewardRule> findAllTypes();

    public ObtainRewardRuleVo add(ObtainRewardRule rule);

    public boolean modify(ObtainRewardRule rule);

    public boolean remove(Long id);

    public List<ObtainRewardRule> findRulesByActivityId(Long activityId);

    public void deleteByActivityId(Long activityId);

    public List<ObtainRewardRuleVo> findVosByActivityId(Long activityId);

}
