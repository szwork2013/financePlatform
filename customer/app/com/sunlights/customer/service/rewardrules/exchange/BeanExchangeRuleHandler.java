package com.sunlights.customer.service.rewardrules.exchange;

import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import play.Logger;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: BeanExchangeRuleHandler.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class BeanExchangeRuleHandler extends AbstractExchangeRuleHandler{

    @Override
    public void exchange(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {
        Logger.debug("金豆兑换开始requestVo ：" + requestVo);
        ExchangeRuleGainHandler exchangeRuleGainHandler = new ExchangeRuleGainHandler();
        BeanExchangeNullFieldValidHandler beanExchangeNullFieldValidHandler = new BeanExchangeNullFieldValidHandler();
        BeanExchangeValidHandler exchangeValidHandler = new BeanExchangeValidHandler();
        ExchangeDayHandler exchangeDayHandler = new ExchangeDayHandler();// 一天一次
        ExchangeFlowHandler exchangeFlowHandler = new ExchangeFlowHandler();
        ExchangeResultHandler exchangeResultHandler = new ExchangeResultHandler();

        BeanExchangeSendMessageHandler beanExchangeSendMessageHandler = new BeanExchangeSendMessageHandler();

        setNextHandler(exchangeRuleGainHandler)
                .setNextHandler(beanExchangeNullFieldValidHandler)
                .setNextHandler(exchangeDayHandler)
                .setNextHandler(exchangeValidHandler)
                .setNextHandler(exchangeFlowHandler)
                .setNextHandler(exchangeResultHandler)
                .setNextHandler(beanExchangeSendMessageHandler);

        getNextHandler().exchange(requestVo, responseVo);

        Logger.debug("金豆兑换结束responseVo ：" + responseVo);
    }

    @Override
    public void exchangeInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {

    }
}
