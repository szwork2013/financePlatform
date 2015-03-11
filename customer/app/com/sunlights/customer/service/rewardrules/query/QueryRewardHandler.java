package com.sunlights.customer.service.rewardrules.query;


import com.sunlights.customer.vo.RewardResultVo;

/**
 * Created by tangweiqun on 2014/12/2.
 */
public interface QueryRewardHandler {

    public RewardResultVo getSigninCanObtainReward(String custId, Long activityId);

}
