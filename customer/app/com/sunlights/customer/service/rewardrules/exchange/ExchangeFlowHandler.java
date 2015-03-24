package com.sunlights.customer.service.rewardrules.exchange;

import com.google.common.collect.Lists;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.service.HoldRewardService;
import com.sunlights.customer.service.impl.HoldRewardServiceImpl;
import com.sunlights.customer.service.rewardrules.calculate.ExchangeRewardCalcVo;
import com.sunlights.customer.service.rewardrules.calculate.ExchangeRewardCalculator;
import com.sunlights.customer.service.rewardrules.calculate.RewardCalculatorFactory;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import com.sunlights.customer.service.rewardrules.vo.ExchangeSceneRuleVo;
import com.sunlights.customer.service.rewardrules.vo.RewardFlowRecordVo;
import models.ExchangeScene;
import models.RewardFlow;
import play.Logger;

import java.math.BigDecimal;
import java.util.List;

/**
 * 兑换奖励流程中的节点
 * 负责冻结用户需要兑换的奖励，并产生兑换流水
 * <p/>
 * Created by tangweiqun on 2014/12/3.
 */
public class ExchangeFlowHandler extends AbstractExchangeRuleHandler {

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
        ExchangeScene exchangeScene = requestVo.get("exchangeScene", ExchangeScene.class);

        holdRewardService.frozenReward(requestVo.getCustId(), exchangeScene.getRewardType(), exchangeScene.getActivityType(), subRewardAmt, exchangeMoney);

        RewardFlowRecordVo rewardFlowRecordVo = new RewardFlowRecordVo();

        rewardFlowRecordVo.setCustId(requestVo.getCustId());
        rewardFlowRecordVo.setOperatorType(ActivityConstant.REWARD_FLOW_EXCHANGE);
        rewardFlowRecordVo.setRewardType(requestVo.getRewardType());
        rewardFlowRecordVo.setScene(requestVo.getScene());
        rewardFlowRecordVo.setActivityType(exchangeScene.getActivityType());
        rewardFlowRecordVo.setActivityTitle(exchangeScene.getTitle());

        ExchangeRewardCalculator calculator = RewardCalculatorFactory.getExchangeCalculator(requestVo.getScene());
        if(calculator == null) {
            rewardFlowRecordVo.setRewardAmtResult(subRewardAmt);
            rewardFlowRecordVo.setMoneyResult(exchangeMoney);
        } else {
            ExchangeRewardCalcVo exchangeRewardCalcVo = calculator.calcExchangeReward(requestVo, requestVo.get("exchangeSceneRuleVo", ExchangeSceneRuleVo.class));
            rewardFlowRecordVo.setRewardAmtResult(exchangeRewardCalcVo.getRewardAmtResult());
            rewardFlowRecordVo.setMoneyResult(rewardFlowRecordVo.getMoneyResult());
        }


        RewardFlow rewardFlow = holdRewardService.genRewardFlow(rewardFlowRecordVo);

        requestVo.set("rewardFlowId", rewardFlow.getId());
        List<RewardFlowRecordVo> rewardFlowRecordVos = Lists.newArrayList();
        rewardFlowRecordVos.add(rewardFlowRecordVo);

        responseVo.setRewardFlowRecordVos(rewardFlowRecordVos);

        Logger.debug("兑换产生奖励流水 rewardFlowRecordVo = " + rewardFlowRecordVo);

    }

    @Override
    public String toString() {
        return "ExchangeFlowHandler";
    }
}
