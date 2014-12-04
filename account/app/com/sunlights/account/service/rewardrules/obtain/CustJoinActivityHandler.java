package com.sunlights.account.service.rewardrules.obtain;

import com.sunlights.account.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.account.service.rewardrules.vo.ActivityResponseVo;

/**
 * 客户参加活动后的总结
 * 功能：将客户与参加这次活动记录下来，方便下次参加活动时为做活动决策提供数据来源
 *
 * Created by tangweiqun on 2014/12/2.
 */
public class CustJoinActivityHandler extends AbstractObtainRuleHandler {

    public CustJoinActivityHandler() {

    }

    public CustJoinActivityHandler(ObtainRuleHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void obtainInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {

    }
}
