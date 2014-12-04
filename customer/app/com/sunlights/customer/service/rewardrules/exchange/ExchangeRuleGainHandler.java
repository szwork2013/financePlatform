package com.sunlights.customer.service.rewardrules.exchange;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.vo.Message;
import com.sunlights.customer.service.ExchangeSceneService;
import com.sunlights.customer.service.RewardTypeService;
import com.sunlights.customer.service.impl.ExchangeSceneServiceImpl;
import com.sunlights.customer.service.impl.RewardTypeServiceImpl;
import com.sunlights.customer.service.rewardrules.exchange.AbstractExchangeRuleHandler;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import models.ExchangeScene;
import models.RewardType;

/**
 * 兑换奖励功能链上的节点处理类--获取兑换规则（奖励类型）等兑换需要的信息
 * 并将这些信息当作请求的属性往下传，供后续节点使用
 *
 * 判断场景是否存在，不存在的话则终止流程
 *
 * Created by tangweiqun on 2014/12/3.
 */
public class ExchangeRuleGainHandler extends AbstractExchangeRuleHandler {
    private RewardTypeService rewardTypeService = new RewardTypeServiceImpl();
    private ExchangeSceneService exchangeSceneService = new ExchangeSceneServiceImpl();

    public ExchangeRuleGainHandler() {

    }

    public ExchangeRuleGainHandler(ExchangeRuleHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void exchangeInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {

        ExchangeScene exchangeScene = exchangeSceneService.findByscene(requestVo.getScene());
        if(exchangeScene == null) {
            Message message = new Message(Severity.INFO, MsgCode.NOT_CONFIG_ACTIVITY_SCENE);
            responseVo.setMessage(message);
            responseVo.setFlowStop(true);
            return;
        }

        requestVo.set("exchangeScene", exchangeScene);

        RewardType rewardType = rewardTypeService.findByTypeCode(requestVo.getRewardType());

        requestVo.set("rewardType", rewardType);
    }
}
