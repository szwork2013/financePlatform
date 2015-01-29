package com.sunlights.customer.service.rewardrules.exchange;

import com.sunlights.common.DictConst;
import com.sunlights.common.vo.MessageHeaderVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;

/**
 * 红包取现发送消息
 *
 * Created by tangweiqun on 2014/12/19.
 */
public class BeanExchangeSendMessageHandler extends AbstractExchangeRuleHandler {

    public BeanExchangeSendMessageHandler() {

    }

    public BeanExchangeSendMessageHandler(ExchangeRuleHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void exchangeInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {
        String scene = requestVo.getScene();
        String custNo = requestVo.getCustId();

        String exchangeMoneyStr = requestVo.get("exchangeAmt", String.class);

        String mobile = requestVo.get("phone", String.class);
        String displayMobile = mobile.substring(0, 3) + "******";

        MessageHeaderVo messageHeaderVo = new MessageHeaderVo(DictConst.PUSH_TYPE_2, scene, custNo);
        messageHeaderVo.buildParams(displayMobile, exchangeMoneyStr);


        responseVo.addMessageHeaderVo(messageHeaderVo);
    }

    @Override
    public String toString() {
        return "BeanExchangeSendMessageHandler";
    }
}
