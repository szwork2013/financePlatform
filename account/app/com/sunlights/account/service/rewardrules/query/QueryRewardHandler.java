package com.sunlights.account.service.rewardrules.query;

import com.sunlights.account.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.account.service.rewardrules.vo.ActivityResponseVo;

/**
 * Created by tangweiqun on 2014/12/2.
 */
public interface QueryRewardHandler {

    public ActivityResponseVo getCanObtainReward(ActivityRequestVo requestVo);

}
