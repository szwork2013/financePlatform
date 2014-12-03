package com.sunlights.customer.service.rewardrules.exchange;

import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.service.HoldRewardService;
import com.sunlights.customer.service.impl.HoldRewardServiceImpl;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import com.sunlights.customer.service.rewardrules.vo.RewardFlowRecordVo;
import models.RewardType;

import java.math.BigDecimal;

/**
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
        Long exchangeAmt = requestVo.get("exchangeAmt", Long.class);
        RewardType rewardType = requestVo.get("rewardType", RewardType.class);
        holdRewardService.frozenReward(requestVo.getCustId(), requestVo.getRewardType(), exchangeAmt);

        RewardFlowRecordVo rewardFlowRecordVo = new RewardFlowRecordVo();
        rewardFlowRecordVo.setRewardAmtResult(exchangeAmt * rewardType.getUnit());

        rewardFlowRecordVo.setCustId(requestVo.getCustId());
        rewardFlowRecordVo.setOperatorType(ActivityConstant.REWARD_FLOW_EXCHANGE);
        rewardFlowRecordVo.setRewardType(requestVo.getRewardType());
        rewardFlowRecordVo.setScene(requestVo.getScene());
        rewardFlowRecordVo.setMoneyResult(BigDecimal.valueOf(exchangeAmt));

        holdRewardService.genRewardFlow(rewardFlowRecordVo);

    }

    @Override
    public String toString() {
        return "ExchangeFlowHandler";
    }
}
