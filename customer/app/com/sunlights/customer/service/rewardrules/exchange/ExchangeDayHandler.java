package com.sunlights.customer.service.rewardrules.exchange;

import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.service.RewardFlowService;
import com.sunlights.customer.service.impl.RewardFlowServiceImpl;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import models.ExchangeScene;
import models.RewardFlow;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: ExchangeDayHandler.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class ExchangeDayHandler extends AbstractExchangeRuleHandler{
    @Override
    public void exchangeInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {
        ExchangeScene exchangeScene =requestVo.get("exchangeScene", ExchangeScene.class);

        String scene = exchangeScene.getScene();

        if (ActivityConstant.ACTIVITY_EXCHANGE_BEAN_SCENE_CODE.equals(scene)) {
            RewardFlowService rewardFlowService = new RewardFlowServiceImpl();

            RewardFlow rewardFlow = rewardFlowService.findTodayFlowByCustIdAndScene(requestVo.getCustId(), exchangeScene.getScene());
            if (rewardFlow != null) {
//                Message message = new Message(Severity.WARN, MsgCode.BEAN_EXCHANGE_REPEAT);
//                responseVo.setMessage(message);
//                responseVo.setFlowStop(true);
//                return;
            }

        }

    }
}
