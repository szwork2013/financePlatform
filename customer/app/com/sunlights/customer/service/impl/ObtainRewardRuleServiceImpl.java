package com.sunlights.customer.service.impl;


import com.sunlights.common.cache.Cacheable;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.customer.dal.ExchangeRewardRuleDao;
import com.sunlights.customer.dal.ObtainRewardRuleDao;
import com.sunlights.customer.dal.RewardTypeDao;
import com.sunlights.customer.dal.impl.ExchangeRewardRuleDaoImpl;
import com.sunlights.customer.dal.impl.ObtainRewardRuleDaoImpl;
import com.sunlights.customer.dal.impl.RewardTypeDaoImpl;
import com.sunlights.customer.service.ObtainRewardRuleService;
import com.sunlights.customer.service.rewardrules.vo.ObtainRewardRuleVo;
import models.ExchangeRewardRule;
import models.ObtainRewardRule;
import models.RewardType;
import play.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014/11/19.
 */
public class ObtainRewardRuleServiceImpl implements ObtainRewardRuleService {

    private ObtainRewardRuleDao obtainRewardRuleDao = new ObtainRewardRuleDaoImpl();

    private RewardTypeDao rewardTypeDao = new RewardTypeDaoImpl();

    private ExchangeRewardRuleDao exchangeRewardRuleDao = new ExchangeRewardRuleDaoImpl();

    @Cacheable(key = "getByActivityId", duration = 3000)
    @Override
    public List<ObtainRewardRule> getByActivityId(Long activityId) {

        return obtainRewardRuleDao.getByActivityId(activityId);
    }

    @Cacheable(key = "getByVosActivityId", duration = 3000)
    @Override
    public List<ObtainRewardRuleVo> getByVosActivityId(Long activityId) {

        List<ObtainRewardRule> obtainRewardRules = getByActivityId(activityId);
        if(obtainRewardRules == null || obtainRewardRules.isEmpty()) {
            return null;
        }
        List<ObtainRewardRuleVo> results = new ArrayList<ObtainRewardRuleVo>();
        ObtainRewardRuleVo obtainRewardRuleVo = null;
        for(ObtainRewardRule obtainRewardRule : obtainRewardRules) {
            obtainRewardRuleVo = new ObtainRewardRuleVo();
            try {
                obtainRewardRuleVo = ConverterUtil.fromEntity(obtainRewardRuleVo, obtainRewardRule);
            } catch (Exception e) {
                Logger.error("获取奖励规则对象转换错误", e);
                continue;
            }
            RewardType rewardType = rewardTypeDao.findByTypeCode(obtainRewardRule.getRewardType());
            ExchangeRewardRule exchangeRewardRule = exchangeRewardRuleDao.findByRewardType(obtainRewardRule.getRewardType());
            obtainRewardRuleVo.setExchangeRewardRule(exchangeRewardRule);
            obtainRewardRuleVo.setRewardTypeModel(rewardType);
            results.add(obtainRewardRuleVo);
        }
        return results;
    }
}
