package com.sunlights.customer.service.rewardrules.exchange;

import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import play.Logger;

/**
 * Created by tangweiqun on 2014/12/3.
 */
public abstract class AbstractExchangeRuleHandler implements ExchangeRuleHandler {

    private ExchangeRuleHandler nextHandler;

    public AbstractExchangeRuleHandler() {

    }

    public AbstractExchangeRuleHandler(ExchangeRuleHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public void setNextHandler(ExchangeRuleHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public ExchangeRuleHandler getNextHandler() {
        return nextHandler;
    }

    @Override
    public void exchange(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {
        if(responseVo.isFlowStop()) {
            Logger.debug("退出获取奖励流程：" + toString() + " responseVo = " + responseVo.getMessage().getSummary());
            return;
        }
        long start = System.currentTimeMillis();
        try {
            exchangeInternal(requestVo, responseVo);
            if(this.nextHandler != null) {
                this.nextHandler.exchange(requestVo, responseVo);
            }

        } finally {
            if(isPerformanceRecord()) {
                Logger.debug("[" + toString() + "]花费时间 : " + (System.currentTimeMillis() - start));
            }
        }
    }

    public boolean isPerformanceRecord() {
        return false;
    }

    public abstract void exchangeInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception;
}
