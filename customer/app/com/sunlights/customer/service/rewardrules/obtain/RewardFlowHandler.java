package com.sunlights.customer.service.rewardrules.obtain;


import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.service.ActivityService;
import com.sunlights.customer.service.HoldRewardService;
import com.sunlights.customer.service.RewardFlowService;
import com.sunlights.customer.service.impl.ActivityServiceImpl;
import com.sunlights.customer.service.impl.HoldRewardServiceImpl;
import com.sunlights.customer.service.impl.RewardFlowServiceImpl;
import com.sunlights.customer.service.rewardrules.calculate.ObtainRewardCalcVo;
import com.sunlights.customer.service.rewardrules.calculate.ObtainRewardCalculator;
import com.sunlights.customer.service.rewardrules.calculate.RewardCalculatorFactory;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import com.sunlights.customer.service.rewardrules.vo.ObtainRewardRuleVo;
import com.sunlights.customer.service.rewardrules.vo.RewardFlowRecordVo;
import models.Activity;
import models.ActivityScene;
import org.apache.commons.lang3.StringUtils;
import play.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 生成奖励流水（分邀请人和被邀请人）
 *
 * Created by tangweiqun on 2014/12/2.
 */
public class RewardFlowHandler extends AbstractObtainRuleHandler{
    private HoldRewardService holdRewardService = new HoldRewardServiceImpl();

    private ActivityService activityService = new ActivityServiceImpl();

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
                if(ActivityConstant.ACTIVITY_CUSTONER_STATUS_FORBIDDEN.equals(obtainRewardRuleVo.getStatus())) {
                    Logger.debug("获取规则无效");
                    continue;
                }
                RewardFlowRecordVo rewardFlowRecordVo = new RewardFlowRecordVo();
                if(obtainRewardRuleVo.getInviter() == ActivityConstant.ACCOUNT_COMMON_ONE && StringUtils.isNotEmpty(requestVo.getRecommendCustId())) {
                    rewardFlowRecordVo.setCustId(requestVo.getRecommendCustId());
                } else if (obtainRewardRuleVo.getInviter() == ActivityConstant.ACCOUNT_COMMON_ZERO){
                    rewardFlowRecordVo.setCustId(requestVo.getCustId());
                } else {
                    Logger.info("没有推荐人，继续下一个获取规则的执行");
                    continue;
                }
                rewardFlowRecordVo.setActivityId(activity.getId());
                rewardFlowRecordVo.setActivityTitle(activity.getTitle());
                rewardFlowRecordVo.setActivityType(activityScene.getActivityType());
                rewardFlowRecordVo.setRewardType(obtainRewardRuleVo.getRewardType());
                rewardFlowRecordVo.setScene(requestVo.getScene());
                rewardFlowRecordVo.setOperatorType(ActivityConstant.REWARD_FLOW_OBTAIN);
                rewardFlowRecordVo.setRuleUrl(activityService.getFileFuleUrl(activity.getUrl(), "activity.html5Path"));

                ObtainRewardCalculator calculator = RewardCalculatorFactory.getCalculator(activityScene.getScene());
                Integer unit = obtainRewardRuleVo.getRewardTypeModel().getUnit();
                if(calculator == null) {
                    rewardFlowRecordVo.setRewardAmtResult(obtainRewardRuleVo.getShouldReward());
                    rewardFlowRecordVo.setRewardAmtFromTrans(BigDecimal.valueOf(obtainRewardRuleVo.getShouldReward()).divide(BigDecimal.valueOf(unit)));
                    rewardFlowRecordVo.setMoneyResult(obtainRewardRuleVo.getExchangeRewardRule().getRate().multiply(BigDecimal.valueOf(rewardFlowRecordVo.getRewardAmtResult())));
                    rewardFlowRecordVo.setNotGet(obtainRewardRuleVo.getShouldReward());
                    rewardFlowRecordVo.setStatus(ActivityConstant.ACTIVITY_CUSTONER_STATUS_NOMAL);
                } else {
                    ObtainRewardCalcVo calcVo = calculator.calcObtainReward(requestVo, obtainRewardRuleVo);
                    rewardFlowRecordVo.setRewardAmtResult(calcVo.getRewardAmtResult());
                    rewardFlowRecordVo.setRewardAmtFromTrans(BigDecimal.valueOf(calcVo.getRewardAmtResult()).divide(BigDecimal.valueOf(unit)));
                    rewardFlowRecordVo.setMoneyResult(calcVo.getRewardMoneyResult());
                    rewardFlowRecordVo.setNotGet(calcVo.getNotGet());
                    rewardFlowRecordVo.setStatus(calcVo.getStatus());
                }

                responseVo.addRewardFlowRecordVo(rewardFlowRecordVo);

                holdRewardService.genRewardFlow(rewardFlowRecordVo);
            }
        }
    }

    @Override
    public String toString() {
        return "RewardFlowHandler";
    }
}
