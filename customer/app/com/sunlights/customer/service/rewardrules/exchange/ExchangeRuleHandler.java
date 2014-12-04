package com.sunlights.customer.service.rewardrules.exchange;


import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;

/**
 * 兑换奖励功能链的节点的接口--在活动中的兑换奖励模块的处理逻辑的最高接口
 *
 * 1：可以设置下一个处理类
 * 2：执行具体节点的处理逻辑
 *
 * Created by tangweiqun on 2014/12/1.
 */
public interface ExchangeRuleHandler {

    /**
     * 获取当前处理类的下一个处理类
     *
     * @return  如果有的话返回下一个处理类的实例，没有的话返回null
     *
     */
    public ExchangeRuleHandler getNextHandler();

    /**
     * 兑换奖励的具体逻辑的执行
     *
     * @param requestVo 兑换奖励的请求对象
     * @param responseVo    兑换奖励的响应对象
     * @throws Exception    异常
     */
    void exchange(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception;

}
