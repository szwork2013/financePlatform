package com.sunlights.account.service.rewardrules.obtain;

import com.sunlights.account.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.account.service.rewardrules.vo.ActivityResponseVo;

/**
 * 获取奖励成功后结果赋值
 * Created by tangweiqun on 2014/12/2.
 */
public class ResultAssignHandler extends AbstractObtainRuleHandler {

    public ResultAssignHandler() {

    }

    public ResultAssignHandler(ObtainRuleHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void obtainInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {

    }

    @Override
    public String toString() {
        return "ResultAssignHandler";
    }

}
