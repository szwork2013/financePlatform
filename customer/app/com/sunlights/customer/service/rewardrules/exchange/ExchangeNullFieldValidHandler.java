package com.sunlights.customer.service.rewardrules.exchange;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.vo.Message;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import models.ExchangeScene;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2014/12/20.
 */
public class ExchangeNullFieldValidHandler extends AbstractExchangeRuleHandler {

    public ExchangeNullFieldValidHandler() {

    }

    public ExchangeNullFieldValidHandler(ExchangeRuleHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void exchangeInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {
        ExchangeScene exchangeScene = requestVo.get("exchangeScene", ExchangeScene.class);

        String bankName = requestVo.get("bankName", String.class);
        String bankCardNo = requestVo.get("bankCardNo", String.class);
        String exchangeAmt = requestVo.get("exchangeAmt", String.class);

        if (ActivityConstant.ACTIVITY_EXCHANGE_RED_PACKET_SCENE_CODE.equals(exchangeScene.getScene())) {
            Message message = new Message(Severity.INFO, MsgCode.PARAMTER_NOT_CAN_NULL);
            if (StringUtils.isEmpty(bankName)) {
                message.setDetail("银行名称不能为空");
                responseVo.setMessage(message);
                responseVo.setFlowStop(true);
                return;
            }

            if (StringUtils.isEmpty(bankCardNo)) {
                message.setDetail("银行卡号不能为空");
                responseVo.setMessage(message);
                responseVo.setFlowStop(true);
                return;
            }

            if (StringUtils.isEmpty(exchangeAmt)) {
                message.setDetail("兑换金额不能为空");
                responseVo.setMessage(message);
                responseVo.setFlowStop(true);
                return;
            }

            if (new BigDecimal(exchangeAmt).doubleValue() < Double.valueOf("0.01")) {
                message.setDetail("数量必须大于0.01元");
                responseVo.setMessage(message);
                responseVo.setFlowStop(true);
                return;
            }
        }
    }
}
