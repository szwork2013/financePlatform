package com.sunlights.account.service.rewardrules.calculate;

import com.sunlights.account.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.account.service.rewardrules.vo.ObtainRewardRuleVo;

/**
 * Created by tangweiqun on 2014/12/2.
 */
public interface ObtainRewardCalculator {

    public ObtainRewardCalcVo calcObtainReward(ActivityRequestVo requestVo, ObtainRewardRuleVo obtainRewardRuleVo);

}
