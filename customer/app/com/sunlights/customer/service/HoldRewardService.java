package com.sunlights.customer.service;


import com.sunlights.customer.service.rewardrules.vo.RewardFlowRecordVo;
import com.sunlights.customer.vo.HoldRewardVo;
import models.RewardFlow;

import java.math.BigDecimal;

/**
 * Created by tangweiqun on 2014/11/19.
 */
public interface HoldRewardService {

    public void modifyHoldReward(String custId, String rewardType, String activityType, BigDecimal money, Long rewardAmt);

    public Long getHoldRewardByCustId(String custId, String rewardType);

    public HoldRewardVo getMyRewardDetail(String custId, String rewardType);

    public RewardFlow genRewardFlow(RewardFlowRecordVo rewardFlowRecordVo);

    public void frozenReward(String custId, String rewardType, String activityType, Long frozenAmt, BigDecimal exchangeMoney);

    public HoldRewardVo getTotalReward(String custId);
}
