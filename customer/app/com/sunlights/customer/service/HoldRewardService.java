package com.sunlights.customer.service;


import com.sunlights.customer.service.rewardrules.vo.RewardFlowRecordVo;
import com.sunlights.customer.vo.HoldRewardVo;

import java.math.BigDecimal;

/**
 * Created by tangweiqun on 2014/11/19.
 */
public interface HoldRewardService {

    public void modifyHoldReward(String custId, String rewardType, BigDecimal money, Long rewardAmt);

    public Long getHoldRewardByCustId(String custId, String rewardType);

    public HoldRewardVo getMyRewardDetail(String custI, String rewardType);

    public void genRewardFlow(RewardFlowRecordVo rewardFlowRecordVo);

    public void frozenReward(String custId, String rewardType, Long frozenAmt);


}
