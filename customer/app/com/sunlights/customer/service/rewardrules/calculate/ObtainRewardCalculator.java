package com.sunlights.customer.service.rewardrules.calculate;


import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ObtainRewardRuleVo;

/**
 * Created by tangweiqun on 2014/12/2.
 */
public interface ObtainRewardCalculator {

    public ObtainRewardCalcVo calcObtainReward(ActivityRequestVo requestVo, ObtainRewardRuleVo obtainRewardRuleVo);

}
