package com.sunlights.customer.service.rewardrules.obtain;


import com.sunlights.customer.service.rewardrules.AbstractHandlerMapping;
import com.sunlights.customer.service.rewardrules.HandlerExecutionChain;
import com.sunlights.customer.service.rewardrules.RewardRuleFactory;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;

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
