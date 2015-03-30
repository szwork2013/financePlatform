package com.sunlights.op.service.activity.impl;

import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.op.dal.activity.ObtainRewardRuleDao;
import com.sunlights.op.dal.activity.RewardTypeDao;
import com.sunlights.op.dal.activity.impl.ObtainRewardRuleDaoImpl;
import com.sunlights.op.dal.activity.impl.RewardTypeDaoImpl;
import com.sunlights.op.service.activity.ObtainRewardRuleService;
import com.sunlights.op.vo.activity.ObtainRewardRuleVo;
import models.ObtainRewardRule;
import models.RewardType;

import java.util.List;

/**
 * Created by Administrator on 2014/11/14.
 */
public class ObtainRewardRuleServiceImpl implements ObtainRewardRuleService {
    private ObtainRewardRuleDao obtainRewardRuleDao = new ObtainRewardRuleDaoImpl();
    private RewardTypeDao rewardTypeDao = new RewardTypeDaoImpl();

    @Override
    public List<ObtainRewardRule> findAllTypes() {
        return obtainRewardRuleDao.findAllTypes();
    }

    @Override
    public List<ObtainRewardRule> findRulesByActivityId(Long activityId) {
        return obtainRewardRuleDao.findRulesByActivityId(activityId);
    }

    @Override
    public ObtainRewardRuleVo add(ObtainRewardRule rule) {
        ObtainRewardRule newRule = obtainRewardRuleDao.doInsert(rule);
        try {
            ObtainRewardRuleVo obtainRewardRuleVo = ConverterUtil.fromEntity(new ObtainRewardRuleVo(), newRule);
            RewardType rewardType = rewardTypeDao.findByCode(obtainRewardRuleVo.getRewardType());
            if(rewardType != null) {
                obtainRewardRuleVo.setRewardTypeStr(rewardType.getName());
            }
            return obtainRewardRuleVo;
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public boolean remove(Long id) {
        return obtainRewardRuleDao.doDelete(id);
    }

    @Override
    public boolean modify(ObtainRewardRule rule) {
        return obtainRewardRuleDao.doUpdate(rule);
    }

    @Override
    public void deleteByActivityId(Long activityId) {
        obtainRewardRuleDao.deleteByActivityId(activityId);
    }

    @Override
    public List<ObtainRewardRuleVo> findVosByActivityId(Long activityId) {
        return obtainRewardRuleDao.findVosByActivityId(activityId);
    }
}
