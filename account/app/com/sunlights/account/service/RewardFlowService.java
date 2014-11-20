package com.sunlights.account.service;

import models.RewardFlow;

import java.util.Date;
import java.util.List;

/**
 * Created by tangweiqun on 2014/11/19.
 */
public interface RewardFlowService {

    public void saveRewardFlow(RewardFlow rewardFlow);

    public RewardFlow findTodayFlowByCustIdAndScene(String custId, String scene);

    public List<RewardFlow> findByCustIdAndRewardType(String custId, String rewardType);

}
