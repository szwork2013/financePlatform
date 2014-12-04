package com.sunlights.customer.service.rewardrules.obtain;

import com.sunlights.customer.service.CustJoinActivityService;
import com.sunlights.customer.service.impl.CustJoinActivityServiceImpl;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
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

    }
}
