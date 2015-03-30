package com.sunlights.op.dal.activity;

import com.sunlights.op.vo.activity.ObtainRewardRuleVo;
import models.ObtainRewardRule;

import java.util.List;

/**
 * Created by Administrator on 2014/11/14.
 */
public interface ObtainRewardRuleDao {
    public List<ObtainRewardRule> findAllTypes();

    public ObtainRewardRule doInsert(ObtainRewardRule rule);

    public boolean doUpdate(ObtainRewardRule rule);

    public boolean doDelete(Long id);

    public ObtainRewardRule findById(Long id);

    public List<ObtainRewardRule> findRulesByActivityId(Long activityId);

    public void deleteByActivityId(Long activityId);

    public List<ObtainRewardRuleVo> findVosByActivityId(Long activityId);

}
