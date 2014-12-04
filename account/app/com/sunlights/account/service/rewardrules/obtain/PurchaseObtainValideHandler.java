package com.sunlights.account.service.rewardrules.obtain;

import com.sunlights.account.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.account.service.rewardrules.vo.ActivityResponseVo;

/**
 * 确定购买活动是否是首次购买，并最终确定活动场景(是首次购买还是购买)
 *
 * Created by tangweiqun on 2014/12/2.
 */
public class PurchaseObtainValideHandler extends AbstractObtainRuleHandler {

    public PurchaseObtainValideHandler() {

    }

    public PurchaseObtainValideHandler(ObtainRuleHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void obtainInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {

    }

    @Override
    public String toString() {
        return "PurchaseObtainValideHandler";
    }
}
