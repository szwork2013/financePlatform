package com.sunlights.customer.service.rewardrules.exchange;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.vo.Message;
import com.sunlights.customer.service.ExchangeRewardRuleService;
import com.sunlights.customer.service.ExchangeSceneService;
import com.sunlights.customer.service.HoldRewardService;
import com.sunlights.customer.service.impl.ExchangeRewardRuleServiceImpl;
import com.sunlights.customer.service.impl.ExchangeSceneServiceImpl;
import com.sunlights.customer.service.impl.HoldRewardServiceImpl;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import com.sunlights.customer.vo.DataBean4ExchangeVo;
import models.ExchangeRewardRule;
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
public class BeanExchangeValidHandler extends AbstractExchangeRuleHandler{

    private HoldRewardService holdRewardService = new HoldRewardServiceImpl();

    public BeanExchangeValidHandler() {

    }

    public BeanExchangeValidHandler(ExchangeRuleHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void exchangeInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {
        ExchangeScene exchangeScene =requestVo.get("exchangeScene", ExchangeScene.class);
        RewardType rewardType = requestVo.get("rewardType", RewardType.class);
        String exchangeAmt = requestVo.get("exchangeAmt", String.class);
        BigDecimal exchangeMoney = BigDecimal.valueOf(Double.valueOf(exchangeAmt));


        ExchangeSceneService exchangeSceneService = new ExchangeSceneServiceImpl();
        DataBean4ExchangeVo dataBean4ExchangeVo = exchangeSceneService.getDataBean4ExchangeVo();

        String limitAmt = dataBean4ExchangeVo.getExchangeList().get(0);
        if(new BigDecimal(exchangeAmt).doubleValue() < Double.valueOf(limitAmt)) {
            responseVo.setMessage(new Message(Severity.WARN, MsgCode.BEAN_EXCHANGE_NOTENOUGH));
            responseVo.setFlowStop(true);
            return;
        }

        ExchangeRewardRuleService exchangeRewardRuleService = new ExchangeRewardRuleServiceImpl();
        ExchangeRewardRule exchangeRewardRule = exchangeRewardRuleService.findByRewardType(rewardType.getCode());
        //单位转换
        Long subRewardAmt = exchangeMoney.divide(exchangeRewardRule.getRate()).longValue();

        if(subRewardAmt > exchangeScene.getExchangeLimit()) {
            Message message = new Message(Severity.WARN, MsgCode.EXCHANGE_OVER_LIMIT);
            message.setDetail(Configuration.root().getString("detail.exchange" + MsgCode.EXCHANGE_OVER_LIMIT.getCode()));
            responseVo.setMessage(message);
            responseVo.setFlowStop(true);
            return;
        }


        Long totalReward = holdRewardService.getHoldRewardByCustId(requestVo.getCustId(), requestVo.getRewardType());
        if(totalReward < subRewardAmt) {
            Message message = new Message(Severity.WARN, MsgCode.EXCHANGE_OVER_LIMIT);
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
        return "BeanExchangeValidHandler";
    }
}
