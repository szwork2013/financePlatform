package com.sunlights.customer.service.rewardrules.exchange;

import com.sunlights.common.MsgCode;
import com.sunlights.common.vo.Message;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.factory.ActivityServiceFactory;
import com.sunlights.customer.service.ActivityReturnMsgService;
import com.sunlights.customer.service.ExchangeResultService;
import com.sunlights.customer.service.ExchangeSceneService;
import com.sunlights.customer.service.impl.ExchangeResultServiceImpl;
import com.sunlights.customer.service.impl.ExchangeSceneServiceImpl;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import com.sunlights.customer.vo.ExchangeResultVo;
import models.ExchangeResult;
import models.ExchangeScene;
import play.Configuration;
import play.Logger;

import java.math.BigDecimal;
import java.text.MessageFormat;

/**
 * 兑换奖励功能链上的节点处理类--兑换结果的处理
 * 1：分析和处理兑换的结果*
 * 2：结果写到响应对象中
 *
 * Created by tangweiqun on 2014/12/3.
 */
public class ExchangeResultHandler extends AbstractExchangeRuleHandler {
    private ExchangeResultService exchangeResultService = new ExchangeResultServiceImpl();

    private ExchangeSceneService exchangeSceneService = new ExchangeSceneServiceImpl();

    private ActivityReturnMsgService activityReturnMsgService = ActivityServiceFactory.getActivityReturnMsgService();

    public ExchangeResultHandler() {

    }

    public ExchangeResultHandler(ExchangeRuleHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void exchangeInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {
        BigDecimal amt = new BigDecimal(requestVo.get("exchangeAmt", String.class));
        String bankName = requestVo.get("bankName", String.class);

        ExchangeResult exchangeResult = new ExchangeResult();
        exchangeResult.setCustId(requestVo.getCustId());
        exchangeResult.setExchangeScene(requestVo.getScene());
        exchangeResult.setBankCardNo(requestVo.get("bankCardNo", String.class));
        exchangeResult.setBankCode(requestVo.get("bankCode", String.class));
        exchangeResult.setPhone(requestVo.get("phone", String.class));
        exchangeResult.setBankName(bankName);
        exchangeResult.setAmount(amt);
        exchangeResult.setStatus(ActivityConstant.EXCHANGE_RESULT_AUDIT_SUCC);
        exchangeResultService.save(exchangeResult);

        ExchangeScene exchangeScene = requestVo.get("exchangeScene", ExchangeScene.class);

        Message message = responseVo.getMessage();
        Logger.debug("rewardFlowRecordVo.getScene() = " + exchangeScene.getScene() + " rewardFlowRecordVo.getActivityType() = " + exchangeScene.getExchangeType() + " rewardFlowRecordVo.getRewardType() = " + exchangeScene.getRewardType());
        //String detailTemplate = Configuration.root().getString("detail.exchange." + exchangeScene.getExchangeType());
        String template = activityReturnMsgService.getReturnMsg(exchangeScene.getScene(), exchangeScene.getExchangeType(), exchangeScene.getRewardType(),
                ActivityConstant.RETURN_MSG_CATEGORY_REWARD_TRADE, MsgCode.OPERATE_SUCCESS.getCode());
        Logger.debug("template = " + template);
        String accountDate = exchangeSceneService.calcAccountDate(exchangeScene.getTimeLimit(), null, false);
        message.setDetail(MessageFormat.format(template, amt, bankName, accountDate));

    }

    @Override
    public String toString() {
        return "ExchangeResultHandler";
    }
}
