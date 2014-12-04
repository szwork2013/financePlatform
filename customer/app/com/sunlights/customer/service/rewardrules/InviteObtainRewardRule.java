package com.sunlights.customer.service.rewardrules;


import com.sunlights.customer.ActivityConstant;

/**
 * 邀请好友获取金豆
 * Created by tangweiqun on 2014/11/20.
 */
@Deprecated
public class InviteObtainRewardRule extends AbstractObtainRewardRule{

    @Override
    public String getScene() {
        return ActivityConstant.ACTIVITY_INVITE_SCENE_CODE;
    }

}
