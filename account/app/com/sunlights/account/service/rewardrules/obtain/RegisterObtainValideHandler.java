package com.sunlights.account.service.rewardrules.obtain;

import com.sunlights.account.AccountConstant;
import com.sunlights.account.service.RewardFlowService;
import com.sunlights.account.service.impl.RewardFlowServiceImpl;
import com.sunlights.account.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.account.service.rewardrules.vo.ActivityResponseVo;
import com.sunlights.account.vo.RewardResultVo;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.vo.Message;

/**
 * 校验注册场景的活动是否需要参加
 * Created by tangweiqun on 2014/12/2.
 */
public class RegisterObtainValideHandler extends AbstractObtainRuleHandler{

    private RewardFlowService rewardFlowService = new RewardFlowServiceImpl();


    public RegisterObtainValideHandler() {

    }

    public RegisterObtainValideHandler(ObtainRuleHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void obtainInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {
        RewardResultVo rewardResultVo = rewardFlowService.getLastObtainRewars(requestVo.getCustId(), requestVo.getScene());

        if(rewardResultVo != null) {
            Message message = new Message(Severity.INFO, MsgCode.ALREADY_REGISTER);
            responseVo.setMessage(message);
            responseVo.setStatus(AccountConstant.ACTIVITY_CUSTONER_STATUS_FORBIDDEN);
            responseVo.setNotGet(0L);
            responseVo.setAlreadyGet(0L);
            responseVo.setFlowStop(true);
        }

    }

    @Override
    public String toString() {
        return "RegisterObtainValideHandler";
    }
}
