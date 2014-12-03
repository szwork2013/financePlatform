package com.sunlights.customer.service.rewardrules;


import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.vo.Message;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.vo.RewardResultVo;
import models.RewardFlow;
import play.Configuration;

/**
 * 签到场景获取奖励规则
 *
 * Created by tangweiqun on 2014/11/19.
 */
public class SignInObtainRewardRule extends AbstractObtainRewardRule{
    @Override
    public String getScene() {
        return ActivityConstant.ACTIVITY_SIGNIN_SCENE_CODE;
    }

    @Override
    public RewardResultVo validate(String custId) {
        RewardResultVo vo = new RewardResultVo();
        Message message = null;
        RewardFlow rewardFlow = rewardFlowService.findTodayFlowByCustIdAndScene(custId, this.getScene());
        if(rewardFlow != null) {
            message = new Message(Severity.INFO, MsgCode.ALREADY_SIGN);
            vo.setReturnMessage(message);
            vo.setStatus(ActivityConstant.ACTIVITY_CUSTONER_STATUS_FORBIDDEN);
            vo.setAlreadyGet(rewardFlow.getRewardAmt());
            vo.setNotGet(0L);
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
