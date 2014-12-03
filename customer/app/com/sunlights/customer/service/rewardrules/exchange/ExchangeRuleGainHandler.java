package com.sunlights.customer.service.rewardrules.exchange;

import com.sunlights.customer.service.RewardTypeService;
import com.sunlights.customer.service.impl.RewardTypeServiceImpl;
import com.sunlights.customer.service.rewardrules.exchange.AbstractExchangeRuleHandler;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import models.RewardType;

/**
 * Created by tangweiqun on 2014/12/3.
 */
public class ExchangeRuleGainHandler extends AbstractExchangeRuleHandler {
    private RewardTypeService rewardTypeService = new RewardTypeServiceImpl();

    public ExchangeRuleGainHandler() {

    }

    public ExchangeRuleGainHandler(ExchangeRuleHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void exchangeInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {
        RewardType rewardType = rewardTypeService.findByTypeCode(requestVo.getRewardType());

        requestVo.set("rewardType", rewardType);
    }
}
