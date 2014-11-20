package com.sunlights.account.service.rewardrules;

import com.sunlights.account.AccountConstant;
import com.sunlights.account.vo.RewardResultVo;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.vo.Message;

/**
 * 邀请好友获取金豆
 * Created by tangweiqun on 2014/11/20.
 */
public class InviteObtainRewardRule extends AbstractObtainRewardRule{

    @Override
    public String getScene() {
        return AccountConstant.ACTIVITY_INVITE_SCENE_CODE;
    }

}
