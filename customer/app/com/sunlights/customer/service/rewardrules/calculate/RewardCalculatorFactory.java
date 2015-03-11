package com.sunlights.customer.service.rewardrules.calculate;

import com.sunlights.customer.ActivityConstant;

/**
 * 获取奖励计算器工厂类
 * Created by tangweiqun on 2014/12/2.
 */
public class RewardCalculatorFactory {

    public static ObtainRewardCalculator getCalculator(String scene) {
        if (ActivityConstant.ACTIVITY_SIGNIN_SCENE_CODE.equals(scene)) {
            return new SigninObtainRewardCalculator();
        }
        return null;
    }

}
