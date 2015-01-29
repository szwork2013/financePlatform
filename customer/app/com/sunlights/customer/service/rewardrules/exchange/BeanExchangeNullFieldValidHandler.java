package com.sunlights.customer.service.rewardrules.exchange;

import com.sunlights.common.MsgCode;
import com.sunlights.common.ParameterConst;
import com.sunlights.common.Severity;
import com.sunlights.common.service.ParameterService;
import com.sunlights.common.vo.Message;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import models.ExchangeScene;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: BeanExchangeNullFieldValidHandler.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class BeanExchangeNullFieldValidHandler extends AbstractExchangeRuleHandler{


    @Override
    public void exchangeInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {
        ExchangeScene exchangeScene =requestVo.get("exchangeScene", ExchangeScene.class);

        String mobile = requestVo.get("phone", String.class);
        String exchangeAmt = requestVo.get("exchangeAmt", String.class);

        if(ActivityConstant.ACTIVITY_EXCHANGE_BEAN_SCENE_CODE.equals(exchangeScene.getScene())) {
            Message message = new Message(Severity.INFO, MsgCode.PARAMTER_NOT_CAN_NULL);
            if(StringUtils.isEmpty(mobile)) {
                message.setDetail("手机号码不能为空");
                responseVo.setMessage(message);
                responseVo.setFlowStop(true);
                return;
            }

            if (mobile.length() != 11) {
                message.setDetail("手机号码长度应为11位");
                responseVo.setMessage(message);
                responseVo.setFlowStop(true);
                return;
            }

            ParameterService parameterService = new ParameterService();
            if (mobile.matches(parameterService.getParameterByName(ParameterConst.CMCC))) {
                requestVo.set("carrierCode", ParameterConst.CMCC);
            }else if (mobile.matches(parameterService.getParameterByName(ParameterConst.CTCC))){
                requestVo.set("carrierCode", ParameterConst.CTCC);
            }else if (mobile.matches(parameterService.getParameterByName(ParameterConst.CUCC))) {
                requestVo.set("carrierCode", ParameterConst.CUCC);
            }
            if (requestVo.get("carrierCode", String.class) == null) {
                message.setDetail("请输入正确的手机号码");
                responseVo.setMessage(message);
                responseVo.setFlowStop(true);
                return;
            }

            if(StringUtils.isEmpty(exchangeAmt)) {
                message.setDetail("兑换金额不能为空");
                responseVo.setMessage(message);
                responseVo.setFlowStop(true);
                return;
            }

            if(new BigDecimal(exchangeAmt).doubleValue() < Double.valueOf("0.01")) {
                message.setDetail("数量必须大于0.01元");
                responseVo.setMessage(message);
                responseVo.setFlowStop(true);
                return;
            }
        }
    }
}
