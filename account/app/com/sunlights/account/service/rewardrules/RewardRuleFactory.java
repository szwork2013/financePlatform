package com.sunlights.account.service.rewardrules;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by tangweiqun on 2014/11/19.
 */
public class RewardRuleFactory {
    private static List<IObtainRewardRule> obtainRuleHandlerList = Lists.newArrayList();

    static {
        obtainRuleHandlerList.add(new SignInObtainRewardRule());
        obtainRuleHandlerList.add(new InviteObtainRewardRule());
    }


    public static IObtainRewardRule getIObtainRuleHandler(String scene) {
        for(IObtainRewardRule obtainRewardRule : obtainRuleHandlerList) {
            if(obtainRewardRule.getScene().equals(scene)) {
                return obtainRewardRule;
            }
        }
        return null;
    }
}
