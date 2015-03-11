package com.sunlights.customer.service;

/**
 * 奖励体系提供的服务
 * <p/>
 * Created by tangweiqun on 2014/11/12.
 */
public interface RewardService {


    public void obtainRewards(String acticityCode);


    public void exchangeRewards(String custNo, String rewardType);


    //TODO: 这个接口不应该返回void类型
    public void getAllRewardByCustNo(String custNo);


    //TODO: 这个接口不应该返回void类型
    public void getRewardFlowByCustNo(String custNo);

}
