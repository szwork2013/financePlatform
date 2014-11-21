package com.sunlights.account.dal;

import models.HoldReward;

/**
 * Created by Administrator on 2014/11/19.
 */
public interface HoldRewardDao {

    public HoldReward findByCustIdAndRewardType (String custId, String rewardType);

    public void doInsert(HoldReward holdReward);

    public void doUpdate(HoldReward holdReward);

}
