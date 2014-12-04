package com.sunlights.account.service.rewardrules.obtain;

import com.sunlights.account.service.rewardrules.AbstractHandlerMapping;
import com.sunlights.account.service.rewardrules.HandlerExecutionChain;
import com.sunlights.account.service.rewardrules.RewardRuleFactory;
import com.sunlights.account.service.rewardrules.vo.ActivityRequestVo;

/**
 * 根据获取奖励的请求获得相对应的处理链
 *
 * Created by tangweiqun on 2014/12/1.
 */
public class ObtainHandlerMapping extends AbstractHandlerMapping {

    @Override
    public Object getHandlerInternal(ActivityRequestVo requestVo) throws Exception {
        Object handler = RewardRuleFactory.getObtainRuleHandlerMap().get(requestVo.getScene());
        if(handler != null) {
            return buildChainHandler(handler);
        }
        return null;
    }

    protected Object buildChainHandler(Object handler) {
        HandlerExecutionChain chain = new HandlerExecutionChain(handler);
        //TODO 增加自己的过滤器

        return chain;
    }
}
