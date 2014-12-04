package com.sunlights.customer.service.rewardrules;


import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.vo.Message;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.vo.RewardResultVo;
import play.Configuration;

/**
 * Created by tangweiqun on 2014/11/24.
 */
public class RegisterObtainRewardRule extends AbstractObtainRewardRule{

    @Override
    public String getScene() {
        return ActivityConstant.ACTIVITY_REGISTER_SCENE_CODE;
    }


    @Override
    public RewardResultVo validate(String custId) {
        RewardResultVo rewardResultVo = rewardFlowService.getLastObtainRewars(custId, this.getScene());

        if(rewardResultVo != null) {
            RewardResultVo vo = new RewardResultVo();
            Message message = new Message(Severity.INFO, MsgCode.ALREADY_REGISTER);
            vo.setStatus(ActivityConstant.ACTIVITY_CUSTONER_STATUS_FORBIDDEN);
            vo.setReturnMessage(message);
            vo.setNotGet(0L);
            vo.setAlreadyGet(0L);
            return vo;
        }

        return super.validate(custId);
    }

    @Override
    public RewardResultVo signValue4Obtain(RewardResultVo vo, Long rewardAmtResult) {
        Message message = new Message(Severity.INFO, MsgCode.OBTAIN_SUCC);
        message.setSummary(Configuration.root().getString("golden.summary"));
        message.setDetail(Configuration.root().getString("golden.detail"));
        vo.setReturnMessage(message);
        vo.setScene(this.getScene());
        vo.setAlreadyGet(rewardAmtResult);
        vo.setNotGet(0L);
        vo.setStatus(ActivityConstant.ACTIVITY_CUSTONER_STATUS_FORBIDDEN);
        return vo;
    }
}
