package com.sunlights.customer.service.rewardrules.exchange;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.vo.Message;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.service.ExchangeSceneService;
import com.sunlights.customer.service.HoldRewardService;
import com.sunlights.customer.service.impl.ExchangeSceneServiceImpl;
import com.sunlights.customer.service.impl.HoldRewardServiceImpl;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import models.ExchangeScene;
import models.RewardType;
import play.Configuration;
import play.Logger;

import java.math.BigDecimal;

/**
 * 兑换奖励的校验节点处理类
 * 1：检验兑换奖励的数量是否超限，超限的话则终止流程
 * 2：判断兑换奖励的数量是否大于客户持有数量-冻结数量  如果大于的话则终止流程
 *
 * Created by tangweiqun on 2014/12/3.
 */
public class ExchangeValidHandler extends AbstractExchangeRuleHandler{

    private HoldRewardService holdRewardService = new HoldRewardServiceImpl();

    public ExchangeValidHandler() {

    }

    public ExchangeValidHandler(ExchangeRuleHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void exchangeInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {
        ExchangeScene exchangeScene =requestVo.get("exchangeScene", ExchangeScene.class);
        RewardType rewardType = requestVo.get("rewardType", RewardType.class);
        String exchangeMoneyStr = requestVo.get("exchangeAmt", String.class);
        BigDecimal exchangeMoney = BigDecimal.valueOf(Double.valueOf(exchangeMoneyStr));
        //单位转换
        Long subRewardAmt = exchangeMoney.multiply(BigDecimal.valueOf(rewardType.getUnit())).longValue();

        if(subRewardAmt > exchangeScene.getExchangeLimit()) {
            Message message = new Message(Severity.INFO, MsgCode.EXCHANGE_OVER_LIMIT);
            message.setDetail(Configuration.root().getString("detail.exchange" + MsgCode.EXCHANGE_OVER_LIMIT.getCode()));
            responseVo.setMessage(message);
            responseVo.setFlowStop(true);
            return;
        }

        Long totalReward = holdRewardService.getHoldRewardByCustId(requestVo.getCustId(), requestVo.getRewardType());
        if(totalReward < subRewardAmt) {
            Message message = new Message(Severity.INFO, MsgCode.EXCHANGE_OVER_LIMIT);
            message.setDetail(Configuration.root().getString("detail.exchange" + MsgCode.EXCHANGE_OVER_LIMIT.getCode()));
            responseVo.setMessage(message);

            responseVo.setFlowStop(true);
            return;
        }

        requestVo.set("exchangeMoney", exchangeMoney);
        requestVo.set("subRewardAmt", subRewardAmt);
        Logger.debug("兑换校验成功");
    }

    @Override
    public boolean isPerformanceRecord() {
        return true;
    }

    @Override
    public String toString() {
        return "ExchangeValidHandler";
    }
}
