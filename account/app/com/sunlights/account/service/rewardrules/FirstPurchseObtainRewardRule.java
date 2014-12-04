package com.sunlights.account.service.rewardrules;

import com.sunlights.account.AccountConstant;
import com.sunlights.account.vo.RewardResultVo;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.vo.Message;

/**
 * Created by tangweiqun on 2014/11/25.
 */
public class FirstPurchseObtainRewardRule extends AbstractObtainRewardRule{

    @Override
    public String getScene() {
        return AccountConstant.ACTIVITY_FIRST_PURCHASE_SCENE_CODE;
    }

    @Override
    public RewardResultVo validate(String custId) {
        RewardResultVo rewardResultVo = rewardFlowService.getLastObtainRewars(custId, this.getScene());

        if(rewardResultVo != null) {
            RewardResultVo vo = new RewardResultVo();
            Message message = new Message(Severity.INFO, MsgCode.ALREADY_PURCHASE);
            vo.setStatus(AccountConstant.ACTIVITY_CUSTONER_STATUS_FORBIDDEN);
            vo.setReturnMessage(message);
            vo.setNotGet(0L);
            vo.setAlreadyGet(0L);
            return vo;
        }
        return super.validate(custId);
    }
}
