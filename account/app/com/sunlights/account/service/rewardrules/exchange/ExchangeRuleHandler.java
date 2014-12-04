package com.sunlights.account.service.rewardrules.exchange;

import com.sunlights.account.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.account.service.rewardrules.vo.ActivityResponseVo;

/**
 * Created by tangweiqun on 2014/12/1.
 */
public interface ExchangeRuleHandler {
    public ExchangeRuleHandler getNextHandler();

    void exchange(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception;

}
