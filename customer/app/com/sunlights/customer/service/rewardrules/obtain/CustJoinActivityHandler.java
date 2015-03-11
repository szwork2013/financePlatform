package com.sunlights.customer.service.rewardrules.obtain;


import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.service.CustJoinActivityService;
import com.sunlights.customer.service.impl.CustJoinActivityServiceImpl;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import com.sunlights.customer.service.rewardrules.vo.RewardFlowRecordVo;
import models.CustJoinActivity;

import java.util.Date;
import java.util.List;

/**
 * 客户参加活动后的总结
 * 功能：将客户与参加这次活动记录下来，方便下次参加活动时为做活动决策提供数据来源
 * <p/>
 * Created by tangweiqun on 2014/12/2.
 */
public class CustJoinActivityHandler extends AbstractObtainRuleHandler {
    private CustJoinActivityService custJoinActivityService = new CustJoinActivityServiceImpl();

    public CustJoinActivityHandler() {

    }

    public CustJoinActivityHandler(ObtainRuleHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void obtainInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {

        List<RewardFlowRecordVo> rewardFlowRecordVos = responseVo.getRewardFlowRecordVos();
        for (RewardFlowRecordVo rewardFlowRecordVo : rewardFlowRecordVos) {
            CustJoinActivity custJoinActivity = null;
            custJoinActivity = custJoinActivityService.getByCustAndActivity(rewardFlowRecordVo.getCustId(), rewardFlowRecordVo.getActivityId(), requestVo.getScene());
            if (custJoinActivity == null) {
                custJoinActivity = new CustJoinActivity();
                custJoinActivity.setCustId(rewardFlowRecordVo.getCustId());
                custJoinActivity.setActivityId(rewardFlowRecordVo.getActivityId());
                custJoinActivity.setScene(requestVo.getScene());
                custJoinActivity.setContinued(ActivityConstant.ACCOUNT_COMMON_ONE);
                custJoinActivity.setJoined(ActivityConstant.ACCOUNT_COMMON_ONE);
                custJoinActivity.setRelateTime(ActivityConstant.ACCOUNT_COMMON_ZERO);
                custJoinActivity.setJoinTime(new Date());

                custJoinActivityService.saveCustJoinActivity(custJoinActivity);
            }
        }
    }

    @Override
    public String toString() {
        return "CustJoinActivityHandler";
    }
}
