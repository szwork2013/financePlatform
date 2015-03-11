package com.sunlights.customer.service.rewardrules.obtain;


import com.sunlights.customer.service.rewardrules.HandlerAdapter;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;

/**
 * Created by tangweiqun on 2014/12/1.
 */
public class ObtainHandlerAdapter implements HandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return handler instanceof ObtainRuleHandler;
    }

    @Override
    public void handle(ActivityRequestVo requestVo, ActivityResponseVo responseVo, Object handler) throws Exception {
        ((ObtainRuleHandler) handler).obtain(requestVo, responseVo);
    }
}
