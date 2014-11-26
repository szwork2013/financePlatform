package com.sunlights.account.service.rewardrules;

import com.google.common.collect.Lists;
import com.sunlights.account.AccountConstant;

import java.util.List;

/**
 * Created by tangweiqun on 2014/11/19.
 */
public class RewardRuleFactory {

    public static IObtainRewardRule getIObtainRuleHandler(String scene) {
        if(AccountConstant.ACTIVITY_INVITE_SCENE_CODE.equals(scene)) {
            return new InviteObtainRewardRule();
        } else if(AccountConstant.ACTIVITY_SIGNIN_SCENE_CODE.equals(scene)) {
            return new SignInObtainRewardRule();
        } else if(AccountConstant.ACTIVITY_REGISTER_SCENE_CODE.equals(scene)) {
            return new RegisterObtainRewardRule();
        } else if(AccountConstant.ACTIVITY_FIRST_PURCHASE_SCENE_CODE.equals(scene)) {
            return new FirstPurchseObtainRewardRule();
        }
        return null;
    }
}
