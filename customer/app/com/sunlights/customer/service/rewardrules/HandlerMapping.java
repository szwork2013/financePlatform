package com.sunlights.customer.service.rewardrules;


import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;

/**
 * 定义活动请求和请求处理的接口
 *
 * Created by tangweiqun on 2014/12/1.
 */
public interface HandlerMapping {

    HandlerExecutionChain getHandler(ActivityRequestVo requestVo) throws Exception;

}
