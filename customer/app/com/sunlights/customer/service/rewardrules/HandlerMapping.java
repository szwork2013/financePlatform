package com.sunlights.customer.service.rewardrules;


import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;

/**
 * 根据活动请求找到活动处理执行链的接口
 * 目前只用了了根据活动场景scene这个字段来获取执行链，以后也可以根据其他的方式来获取执行链
 * <p/>
 * Created by tangweiqun on 2014/12/1.
 */
public interface HandlerMapping {

    HandlerExecutionChain getHandler(ActivityRequestVo requestVo) throws Exception;

}
