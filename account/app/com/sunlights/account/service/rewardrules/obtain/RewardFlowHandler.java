package com.sunlights.account.service.rewardrules.obtain;

import com.sunlights.account.AccountConstant;
import com.sunlights.account.service.RewardFlowService;
import com.sunlights.account.service.rewardrules.calculate.ObtainRewardCalcVo;
import com.sunlights.account.service.rewardrules.calculate.ObtainRewardCalculator;
import com.sunlights.account.service.rewardrules.calculate.RewardCalculatorFactory;
import com.sunlights.account.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.account.service.rewardrules.vo.ActivityResponseVo;
import com.sunlights.account.service.rewardrules.vo.ObtainRewardRuleVo;
import com.sunlights.account.service.rewardrules.vo.RewardFlowRecordVo;
import models.Activity;
import models.ActivityScene;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 生成资金流水（分邀请人和被邀请人）
 * Created by tangweiqun on 2014/12/2.
 */
public class RewardFlowHandler extends AbstractObtainRuleHandler{
    private RewardFlowService rewardFlowService = null;

    public RewardFlowHandler() {

    }

    public RewardFlowHandler(ObtainRuleHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void obtainInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {
        ActivityScene activityScene = requestVo.getActivityScene();
        List<Activity> activities = requestVo.getActivities();
        Map<Long, List<ObtainRewardRuleVo>> obtainRewardRuleMap = requestVo.getObtainRewardRuleMap();
        for(Activity activity : activities) {
            List<ObtainRewardRuleVo> obtainRewardRuleVos = obtainRewardRuleMap.get(activity.getId());
            if(obtainRewardRuleVos == null || obtainRewardRuleVos.isEmpty()) {
                continue;
            }
            for(ObtainRewardRuleVo obtainRewardRuleVo : obtainRewardRuleVos) {
                RewardFlowRecordVo rewardFlowRecordVo = new RewardFlowRecordVo();
                if(obtainRewardRuleVo.getInviter() == AccountConstant.ACCOUNT_COMMON_ONE) {
                    rewardFlowRecordVo.setCustId(requestVo.getRecommendCustId());
                } else {
                    rewardFlowRecordVo.setCustId(requestVo.getCustId());
                }
                rewardFlowRecordVo.setActivityId(activity.getId());
                rewardFlowRecordVo.setActivityTitle(activity.getTitle());
                rewardFlowRecordVo.setActivityType(activityScene.getActivityType());
                rewardFlowRecordVo.setRewardType(obtainRewardRuleVo.getRewardType());

                ObtainRewardCalculator calculator = RewardCalculatorFactory.getCalculator(activityScene.getScene());
                if(calculator == null) {
                    rewardFlowRecordVo.setRewardAmtResult(obtainRewardRuleVo.getShouldReward());
                    rewardFlowRecordVo.setMoneyResult(obtainRewardRuleVo.getExchangeRewardRule().getRate().multiply(BigDecimal.valueOf(rewardFlowRecordVo.getRewardAmtResult())));
                } else {
                    ObtainRewardCalcVo calcVo = calculator.calcObtainReward(requestVo, obtainRewardRuleVo);
                    rewardFlowRecordVo.setRewardAmtResult(calcVo.getRewardAmtResult());
                    rewardFlowRecordVo.setMoneyResult(calcVo.getRewardMoneyResult());
                }

                responseVo.addRewardFlowRecordVo(rewardFlowRecordVo);

                rewardFlowService.genRewardFlow(rewardFlowRecordVo);
            }
        }
    }

    @Override
    public String toString() {
        return "RewardFlowHandler";
    }
}
