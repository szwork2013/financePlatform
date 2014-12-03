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

import java.math.BigDecimal;

/**
 * Created by tangweiqun on 2014/12/3.
 */
public class ExchangeValidHandler extends AbstractExchangeRuleHandler{

    private ExchangeSceneService exchangeSceneService = new ExchangeSceneServiceImpl();

    private HoldRewardService holdRewardService = new HoldRewardServiceImpl();

    public ExchangeValidHandler() {

    }

    public ExchangeValidHandler(ExchangeRuleHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void exchangeInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {

        ExchangeScene exchangeScene = exchangeSceneService.findByscene(requestVo.getScene());
        if(exchangeScene == null) {
            Message message = new Message(Severity.INFO, MsgCode.NOT_CONFIG_ACTIVITY_SCENE);
            responseVo.setMessage(message);
            responseVo.setStatus(ActivityConstant.ACTIVITY_CUSTONER_STATUS_FORBIDDEN);
            responseVo.setNotGet(0L);
            responseVo.setAlreadyGet(0L);
            responseVo.setFlowStop(true);
            return;
        }
        RewardType rewardType = requestVo.get("rewardType", RewardType.class);
        BigDecimal exchangeMoney = requestVo.get("exchangeAmt", BigDecimal.class);
        Long subRewardAmt = exchangeMoney.multiply(BigDecimal.valueOf(rewardType.getUnit())).longValue();

        if(subRewardAmt > exchangeScene.getExchangeLimit()) {
            Message message = new Message(Severity.INFO, MsgCode.EXCHANG_OVER_LIMIT);
            responseVo.setMessage(message);
            responseVo.setStatus(ActivityConstant.ACTIVITY_CUSTONER_STATUS_FORBIDDEN);
            responseVo.setNotGet(0L);
            responseVo.setAlreadyGet(0L);
            responseVo.setFlowStop(true);
            return;
        }

        Long totalReward = holdRewardService.getHoldRewardByCustId(requestVo.getCustId(), requestVo.getRewardType());
        if(totalReward < subRewardAmt) {
            Message message = new Message(Severity.INFO, MsgCode.EXCHANG_OVER_LIMIT);
            responseVo.setMessage(message);
            responseVo.setStatus(ActivityConstant.ACTIVITY_CUSTONER_STATUS_FORBIDDEN);
            responseVo.setNotGet(0L);
            responseVo.setAlreadyGet(0L);
            responseVo.setFlowStop(true);
            return;
        }

        requestVo.set("exchangeScene", exchangeScene);
        requestVo.set("exchangeMoney", exchangeMoney);
        requestVo.set("subRewardAmt", subRewardAmt);
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
