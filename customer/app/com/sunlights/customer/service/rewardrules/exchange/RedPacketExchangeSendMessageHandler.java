package com.sunlights.customer.service.rewardrules.exchange;

import com.sunlights.common.DictConst;
import com.sunlights.common.vo.MessageHeaderVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import models.ExchangeScene;

/**
 * 红包取现发送消息
 * <p/>
 * Created by tangweiqun on 2014/12/19.
 */
public class RedPacketExchangeSendMessageHandler extends AbstractExchangeRuleHandler {

    public RedPacketExchangeSendMessageHandler() {

    }

    public RedPacketExchangeSendMessageHandler(ExchangeRuleHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void exchangeInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {
        String scene = requestVo.getScene();
        String custNo = requestVo.getCustId();

        String exchangeMoneyStr = requestVo.get("exchangeAmt", String.class);

        ExchangeScene exchangeScene = requestVo.get("exchangeScene", ExchangeScene.class);

        MessageHeaderVo messageHeaderVo = new MessageHeaderVo(DictConst.PUSH_TYPE_2, scene, custNo);
        messageHeaderVo.buildParams(exchangeMoneyStr, String.valueOf(exchangeScene.getTimeLimit()));


        responseVo.addMessageHeaderVo(messageHeaderVo);
    }

    @Override
    public String toString() {
        return "RedPacketExchangeSendMessageHandler";
    }
}
