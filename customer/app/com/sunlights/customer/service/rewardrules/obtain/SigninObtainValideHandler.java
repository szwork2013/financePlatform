package com.sunlights.customer.service.rewardrules.obtain;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.vo.Message;
import com.sunlights.customer.ActivityConstant;
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

    public SigninObtainValideHandler() {

    }

    public SigninObtainValideHandler(ObtainRuleHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void obtainInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {

        CustJoinActivity custJoinActivity = custJoinActivityService.getTodayRecordByCustAndActivity(requestVo.getCustId(), requestVo.getActivityId(), requestVo.getScene());
        if(custJoinActivity != null) {
            Logger.debug("您今天已经签过到了");
            Message message = new Message(Severity.INFO, MsgCode.ALREADY_SIGN);
            message.setDetail("您今天已经签过到");
            responseVo.setMessage(message);
            responseVo.setFlowStop(true);
            return;
        }
    }

    @Override
    public boolean isPerformanceRecord() {
        return true;
    }
}
