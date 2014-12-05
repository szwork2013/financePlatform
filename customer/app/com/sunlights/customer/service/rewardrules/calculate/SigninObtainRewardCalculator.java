package com.sunlights.customer.service.rewardrules.calculate;


import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ObtainRewardRuleVo;

import java.math.BigDecimal;

/**
 * Created by tangweiqun on 2014/12/2.
 */
public class SigninObtainRewardCalculator implements ObtainRewardCalculator{

    @Override
    public ObtainRewardCalcVo calcObtainReward(ActivityRequestVo requestVo, ObtainRewardRuleVo obtainRewardRuleVo) {
        ObtainRewardCalcVo obtainRewardCalcVo = new ObtainRewardCalcVo();
        //TODO 如果为了满足每天签到获得的奖励不一样的话，需要去阶梯表中获取可以获得的奖励
        obtainRewardCalcVo.setRewardAmtResult(obtainRewardRuleVo.getShouldReward());
        obtainRewardCalcVo.setRewardMoneyResult(obtainRewardRuleVo.getExchangeRewardRule().getRate().multiply(BigDecimal.valueOf(obtainRewardCalcVo.getRewardAmtResult())));
        obtainRewardCalcVo.setStatus(ActivityConstant.ACTIVITY_CUSTONER_STATUS_FORBIDDEN);
        obtainRewardCalcVo.setNotGet(0L);
        return obtainRewardCalcVo;
    }
}
