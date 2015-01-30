package com.sunlights.customer.service.rewardrules.exchange;

import com.sunlights.common.DictConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.MessageHeaderVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;

import java.text.MessageFormat;

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
        messageHeaderVo.buildParams(displayMobile, exchangeMoneyStr, "2");

        String detail = MessageFormat.format(MsgCode.BEAN_EXCHANGE_SUCC.getDetail(), exchangeMoneyStr, mobile, 2);
        Message message = new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS.getCode(), MsgCode.BEAN_EXCHANGE_SUCC.getMessage(), detail);

        responseVo.setMessage(message);
        responseVo.addMessageHeaderVo(messageHeaderVo);
    }

    @Override
    public String toString() {
        return "BeanExchangeSendMessageHandler";
    }
}
