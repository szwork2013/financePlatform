package com.sunlights.customer.service.rewardrules.exchange;

import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.service.HoldRewardService;
import com.sunlights.customer.service.impl.HoldRewardServiceImpl;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import com.sunlights.customer.service.rewardrules.vo.RewardFlowRecordVo;
import models.ActivityScene;
import models.RewardType;

import java.math.BigDecimal;

/**
 * 兑换奖励流程中的节点
 * 负责冻结用户需要兑换的奖励，并产生兑换流水
 *
 * Created by tangweiqun on 2014/12/3.
 */
public class ExchangeFlowHandler extends AbstractExchangeRuleHandler{

    private HoldRewardService holdRewardService = new HoldRewardServiceImpl();

    public ExchangeFlowHandler() {

    }

    public ExchangeFlowHandler(ExchangeRuleHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void exchangeInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {
        BigDecimal exchangeMoney = requestVo.get("exchangeMoney", BigDecimal.class);
        Long subRewardAmt = requestVo.get("subRewardAmt", Long.class);
        ActivityScene activityScene = requestVo.get("activityScene", ActivityScene.class);

        holdRewardService.frozenReward(requestVo.getCustId(), requestVo.getRewardType(), subRewardAmt, exchangeMoney);

        RewardFlowRecordVo rewardFlowRecordVo = new RewardFlowRecordVo();
        rewardFlowRecordVo.setRewardAmtResult(subRewardAmt);
        rewardFlowRecordVo.setCustId(requestVo.getCustId());
        rewardFlowRecordVo.setOperatorType(ActivityConstant.REWARD_FLOW_EXCHANGE);
        rewardFlowRecordVo.setRewardType(requestVo.getRewardType());
        rewardFlowRecordVo.setScene(requestVo.getScene());
        rewardFlowRecordVo.setMoneyResult(exchangeMoney);
        rewardFlowRecordVo.setActivityTitle(activityScene.getTitle());
        holdRewardService.genRewardFlow(rewardFlowRecordVo);

    }

    @Override
    public String toString() {
        return "ExchangeFlowHandler";
    }
}
