package com.sunlights.customer.service.rewardrules.exchange;

import com.sunlights.customer.service.RewardAccountService;
import com.sunlights.customer.service.impl.RewardAccountServiceImpl;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import models.ExchangeScene;
import models.RewardAccountDetails;

import java.math.BigDecimal;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: ExchangeRewardAccountHandler.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class ExchangeRewardAccountHandler extends AbstractExchangeRuleHandler{
    private RewardAccountService rewardAccountService = new RewardAccountServiceImpl();

    @Override
    public void exchangeInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {
        BigDecimal exchangeMoney = requestVo.get("exchangeMoney", BigDecimal.class);
        Long exchangeMoneyTrans = exchangeMoney.multiply(new BigDecimal(100)).longValue();
        ExchangeScene exchangeScene = requestVo.get("exchangeScene", ExchangeScene.class);

        rewardAccountService.updateRewardAccount(requestVo.getCustId(), requestVo.getScene(), exchangeScene.getRewardType(), exchangeMoneyTrans, RewardAccountDetails.FundFlowType.EXPEND.getType());
    }

    @Override
    public String toString() {
        return "ExchangeRewardAccountHandler";
    }
}
