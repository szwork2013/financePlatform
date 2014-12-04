package com.sunlights.customer.service.rewardrules;


import com.sunlights.customer.vo.RewardResultVo;

/**
 * 获取奖励的接口
 * 根据场景有不同的积分规则
 *
 * Created by tangweiqun on 2014/11/19.
 */

public interface IObtainRewardRule {

    /**
     * 获取活动应用场景
     * @return
     */
    public String getScene();

    /**
     * 给客户custId生成获取积分的流水
     * @param custId    客户号
     * @return  获取奖励的结果
     */
    public RewardResultVo obtainReward(String custId, Long activityId);

    /**
     * 获取该场景下能获取到的奖励数
     *  @param custId    客户号
     * @return
     */
    public RewardResultVo getCanObtainRewards(String custId, Long activityId);

}
