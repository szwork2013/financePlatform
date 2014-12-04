package com.sunlights.account.service.impl;

import com.sunlights.account.dal.ObtainRewardRuleDao;
import com.sunlights.account.dal.impl.ObtainRewardRuleDaoImpl;
import com.sunlights.account.service.ObtainRewardRuleService;
import com.sunlights.account.service.rewardrules.vo.ObtainRewardRuleVo;
import models.ObtainRewardRule;

import java.util.List;

/**
 * Created by Administrator on 2014/11/19.
 */
public class ObtainRewardRuleServiceImpl implements ObtainRewardRuleService{
    //TODO
    private ObtainRewardRuleDao obtainRewardRuleDao = new ObtainRewardRuleDaoImpl();

    @Override
    public List<ObtainRewardRule> getByActivityId(Long activityId) {

        return obtainRewardRuleDao.getByActivityId(activityId);
    }

    @Override
    public List<ObtainRewardRuleVo> getByVosActivityId(Long activityId) {
        return null;
    }
}
