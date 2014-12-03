package com.sunlights.customer.service.rewardrules.exchange;

import com.sunlights.customer.service.ExchangeResultService;
import com.sunlights.customer.service.impl.ExchangeResultServiceImpl;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import models.ExchangeResult;

/**
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
        ExchangeResult exchangeResult = new ExchangeResult();
        exchangeResult.setCustId(requestVo.getCustId());
        exchangeResult.setExchangeScene(requestVo.getScene());
        exchangeResult.setBankCardNo(requestVo.get("bankCardNo", String.class));
        exchangeResult.setBankCode(requestVo.get("bankCode", String.class));
        exchangeResult.setPhone(requestVo.get("phone", String.class));

        exchangeResultService.save(exchangeResult);

    }

    @Override
    public String toString() {
        return "ExchangeResultHandler";
    }
}
