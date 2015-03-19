package com.sunlights.customer.service.rewardrules.obtain;


import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.vo.Message;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.factory.ActivityServiceFactory;
import com.sunlights.customer.service.ActivitySceneService;
import com.sunlights.customer.service.ActivityService;
import com.sunlights.customer.service.ObtainRewardRuleService;
import com.sunlights.customer.service.RewardAccountService;
import com.sunlights.customer.service.impl.RewardAccountServiceImpl;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import com.sunlights.customer.service.rewardrules.vo.ObtainRewardRuleVo;
import com.sunlights.customer.service.rewardrules.vo.RewardFlowRecordVo;
import models.Activity;
import models.ActivityScene;
import models.RewardAccountDetails;
import play.Logger;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 根据客户参加的活动场景来获取客户将要参加的活动以及活动对应的获取奖励规则等信息
 * Created by tangweiqun on 2014/12/2.
 */
public class RewardAccountHandler extends AbstractObtainRuleHandler {

    private RewardAccountService rewardAccountService = new RewardAccountServiceImpl();


    public RewardAccountHandler() {

    }

    public RewardAccountHandler(ObtainRuleHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void obtainInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {
        List<RewardFlowRecordVo> rewardFlowRecordVos = responseVo.getRewardFlowRecordVos();
        for(RewardFlowRecordVo rewardFlowRecordVo : rewardFlowRecordVos) {
            String custId = rewardFlowRecordVo.getCustId();
            String scene = rewardFlowRecordVo.getScene();
            String rewardType = rewardFlowRecordVo.getRewardType();
            long amt = (rewardFlowRecordVo.getMoneyResult().multiply(BigDecimal.valueOf(100))).longValue();

            rewardAccountService.updateRewardAccount(custId, scene, rewardType, amt, RewardAccountDetails.FundFlowType.INCOME.getType());
        }
    }

    @Override
    public String toString() {
        return "RewardAccountHandler";
    }
}
