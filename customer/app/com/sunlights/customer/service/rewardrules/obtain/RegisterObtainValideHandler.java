package com.sunlights.customer.service.rewardrules.obtain;


import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.vo.Message;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.service.CustJoinActivityService;
import com.sunlights.customer.service.RewardFlowService;
import com.sunlights.customer.service.impl.CustJoinActivityServiceImpl;
import com.sunlights.customer.service.impl.RewardFlowServiceImpl;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import models.CustJoinActivity;
import play.Logger;

/**
 * 校验注册场景的活动是否需要参加
 * Created by tangweiqun on 2014/12/2.
 */
public class RegisterObtainValideHandler extends AbstractObtainRuleHandler {

    private RewardFlowService rewardFlowService = new RewardFlowServiceImpl();

    private CustJoinActivityService custJoinActivityService = new CustJoinActivityServiceImpl();


    public RegisterObtainValideHandler() {

    }

    public RegisterObtainValideHandler(ObtainRuleHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void obtainInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {

        CustJoinActivity custJoinActivity = custJoinActivityService.getByCustAndActivity(requestVo.getCustId(), null, ActivityConstant.ACTIVITY_REGISTER_SCENE_CODE);

        if (custJoinActivity != null) {
            Logger.debug("已经注册了");
            Message message = new Message(Severity.INFO, MsgCode.ALREADY_REGISTER);
            message.setDetail("您已经注册");
            responseVo.setMessage(message);
            responseVo.setFlowStop(true);
            return;
        }

        Logger.debug("校验客户[" + requestVo.getCustId() + "]注册成功");

    }

    @Override
    public boolean isPerformanceRecord() {
        return true;
    }

    @Override
    public String toString() {
        return "RegisterObtainValideHandler";
    }
}
