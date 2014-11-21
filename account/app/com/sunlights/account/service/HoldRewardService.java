package com.sunlights.account.service;

import com.sunlights.account.vo.HoldRewardVo;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2014/11/19.
 */
public interface HoldRewardService {

    public void modifyHoldReward(String custId, String rewardType, BigDecimal money, Long rewardAmt);

    public Long getHoldRewardByCustId(String custId, String rewardType);

    public HoldRewardVo getMyRewardDetail(String custI, String rewardType);

}
