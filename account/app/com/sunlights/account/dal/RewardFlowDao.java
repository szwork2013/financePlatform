package com.sunlights.account.dal;

import models.RewardFlow;

import java.util.List;

/**
 * Created by tangweiqun on 2014/11/19.
 */
public interface RewardFlowDao {

    public void saveRewardFlow(RewardFlow rewardFlow);

    public List<RewardFlow> findByCondition(RewardFlow rewardFlow, String startDate, String endDate, boolean isAsc) throws Exception ;

    public RewardFlow findOneByCondition(RewardFlow rewardFlow, String startDate, String endDate) throws Exception ;

    public List<RewardFlow> findByCondition(RewardFlow rewardFlow) throws Exception ;

}
