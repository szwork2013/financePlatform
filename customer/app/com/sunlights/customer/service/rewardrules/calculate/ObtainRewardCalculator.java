package com.sunlights.customer.service.rewardrules.calculate;


import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ObtainRewardRuleVo;

/**
 * 获取奖励计算器
 * 计算客户可以获取的奖励
 * <p/>
 * Created by tangweiqun on 2014/12/2.
 */
public interface ObtainRewardCalculator {

    public ObtainRewardCalcVo calcObtainReward(ActivityRequestVo requestVo, ObtainRewardRuleVo obtainRewardRuleVo);

}
