package com.sunlights.customer.service.rewardrules;


import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.vo.Message;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.vo.RewardResultVo;

/**
 * Created by tangweiqun on 2014/11/25.
 */
@Deprecated
public class FirstPurchseObtainRewardRule extends AbstractObtainRewardRule{

    @Override
    public String getScene() {
        return ActivityConstant.ACTIVITY_FIRST_PURCHASE_SCENE_CODE;
    }

    @Override
    public RewardResultVo validate(String custId) {
        RewardResultVo rewardResultVo = rewardFlowService.getLastObtainRewars(custId, this.getScene());

        if(rewardResultVo != null) {
            RewardResultVo vo = new RewardResultVo();
            Message message = new Message(Severity.INFO, MsgCode.ALREADY_PURCHASE);
            vo.setStatus(ActivityConstant.ACTIVITY_CUSTONER_STATUS_FORBIDDEN);
            vo.setReturnMessage(message);
            vo.setNotGet(0L);
            vo.setAlreadyGet(0L);
            return vo;
        }
        return super.validate(custId);
    }
}
