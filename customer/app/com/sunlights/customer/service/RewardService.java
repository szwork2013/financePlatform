package com.sunlights.customer.service;

/**
 * 奖励体系提供的服务
 *
 * Created by tangweiqun on 2014/11/12.
 */
public interface RewardService {


    public void obtainRewards(String acticityCode);


    public void exchangeRewards(String custNo, String rewardType);


    public void getAllRewardByCustNo(String custNo);


    public void getRewardFlowByCustNo(String custNo);

}
