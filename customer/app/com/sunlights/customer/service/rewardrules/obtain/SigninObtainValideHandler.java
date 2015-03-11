package com.sunlights.customer.service.rewardrules.obtain;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.vo.Message;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.factory.ActivityServiceFactory;
import com.sunlights.customer.service.ActivityReturnMsgService;
import com.sunlights.customer.service.CustJoinActivityService;
import com.sunlights.customer.service.impl.CustJoinActivityServiceImpl;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import models.CustJoinActivity;
import play.Logger;

/**
 * Created by tangweiqun on 2014/12/2.
 */
public class SigninObtainValideHandler extends AbstractObtainRuleHandler {
    private CustJoinActivityService custJoinActivityService = new CustJoinActivityServiceImpl();

    private ActivityReturnMsgService activityReturnMsgService = ActivityServiceFactory.getActivityReturnMsgService();

    public SigninObtainValideHandler() {

    }

    public SigninObtainValideHandler(ObtainRuleHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void obtainInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {

        CustJoinActivity custJoinActivity = custJoinActivityService.getTodayRecordByCustAndActivity(requestVo.getCustId(), requestVo.getActivityId(), requestVo.getScene());
        if (custJoinActivity != null) {
            String template = activityReturnMsgService.getReturnMsg(requestVo.getScene(), null, null,
                    ActivityConstant.RETURN_MSG_CATEGORY_REWARD_TRADE, MsgCode.ALREADY_SIGN.getCode());
            Logger.debug("您今天已经签过到了");
            Message message = new Message(Severity.INFO, MsgCode.ALREADY_SIGN);
            message.setDetail(template);
            responseVo.setMessage(message);
            responseVo.setFlowStop(true);
            return;
        }
        Logger.debug("校验客户[" + requestVo.getCustId() + "]签到成功");
    }

    @Override
    public boolean isPerformanceRecord() {
        return true;
    }
}
