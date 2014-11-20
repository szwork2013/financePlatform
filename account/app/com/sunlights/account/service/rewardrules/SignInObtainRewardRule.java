package com.sunlights.account.service.rewardrules;

import com.sunlights.account.AccountConstant;
import com.sunlights.account.service.RewardFlowService;
import com.sunlights.account.service.impl.RewardFlowServiceImpl;
import com.sunlights.account.vo.RewardResultVo;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.vo.Message;
import models.RewardFlow;

/**
 * 签到场景获取奖励规则
 *
 * Created by tangweiqun on 2014/11/19.
 */
public class SignInObtainRewardRule extends AbstractObtainRewardRule{


    private RewardFlowService rewardFlowService = new RewardFlowServiceImpl();


    @Override
    public String getScene() {
        return AccountConstant.ACTIVITY_SIGNIN_SCENE_CODE;
    }


    @Override
    public RewardResultVo validate(String custId) {
        //校验点击事件后是否可以获取到奖励
        RewardResultVo vo = new RewardResultVo();
        Message message = null;
        RewardFlow rewardFlow = rewardFlowService.findTodayFlowByCustIdAndScene(custId, this.getScene());
        if(rewardFlow != null) {
            message = new Message(Severity.INFO, MsgCode.ALREADY_SIGN);
            vo.setCanNotObtain(false);
            vo.setReturnMessage(message);
            return vo;
        }
        return super.validate(custId);
    }
}
