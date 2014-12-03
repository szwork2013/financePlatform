package com.sunlights.customer.service.rewardrules;


import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;

/**
 * 活动处理的过滤器
 * 可以做一些权限之类的动作等
 *
 * Created by tangweiqun on 2014/12/1.
 */
public interface HandlerInterceptor {

    boolean preHandler(ActivityRequestVo requestVo, Object handler) throws Exception;

    void postHandler(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception;
}
