package com.sunlights.customer.service;


import com.sunlights.customer.vo.RewardResultVo;
import models.RewardFlow;

import java.util.List;

/**
 * Created by tangweiqun on 2014/11/19.
 */
public interface RewardFlowService {

    //TODO: 应该返回是否保存成功，比如boolean，或者返回保存后的id
    public void saveRewardFlow(RewardFlow rewardFlow);

    public RewardFlow findTodayFlowByCustIdAndScene(String custId, String scene);

    public List<RewardFlow> findByCustIdAndRewardType(String custId, String rewardType);

    public RewardResultVo getLastObtainRewars(String custId, String scene);



}
