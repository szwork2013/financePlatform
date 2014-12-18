package com.sunlights.customer.service.rewardrules.exchange;

import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.service.ExchangeResultService;
import com.sunlights.customer.service.impl.ExchangeResultServiceImpl;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import com.sunlights.customer.vo.ExchangeResultVo;
import models.ExchangeResult;
import models.ExchangeScene;

import java.math.BigDecimal;

/**
 * 兑换奖励功能链上的节点处理类--兑换结果的处理
 * 1：分析和处理兑换的结果*
 * 2：结果写到响应对象中
 *
 * Created by tangweiqun on 2014/12/3.
 */
public class ExchangeResultHandler extends AbstractExchangeRuleHandler {
    private ExchangeResultService exchangeResultService = new ExchangeResultServiceImpl();

    public ExchangeResultHandler() {

    }

    public ExchangeResultHandler(ExchangeRuleHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void exchangeInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {
        BigDecimal amt = new BigDecimal(requestVo.get("exchangeAmt", String.class));


        ExchangeResult exchangeResult = new ExchangeResult();
        exchangeResult.setCustId(requestVo.getCustId());
        exchangeResult.setExchangeScene(requestVo.getScene());
        exchangeResult.setBankCardNo(requestVo.get("bankCardNo", String.class));
        exchangeResult.setBankCode(requestVo.get("bankCode", String.class));
        exchangeResult.setPhone(requestVo.get("phone", String.class));
        exchangeResult.setBankName(requestVo.get("bankName", String.class));
        exchangeResult.setAmount(amt);
        exchangeResult.setStatus(ActivityConstant.EXCHANGE_RESULT_AUDIT_SUCC);
        exchangeResultService.save(exchangeResult);




    }

    @Override
    public String toString() {
        return "ExchangeResultHandler";
    }
}
