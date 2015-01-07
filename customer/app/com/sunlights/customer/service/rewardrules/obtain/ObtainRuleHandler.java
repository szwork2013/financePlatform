package com.sunlights.customer.service.rewardrules.obtain;


import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;

/**
 *  参加活动获取奖励的处理接口
 *
 * Created by tangweiqun on 2014/12/1.
 */
public interface ObtainRuleHandler {

    public ObtainRuleHandler setNextHandler(ObtainRuleHandler nextHandler);

    /**
     * 获取处于当前处理类的下一个处理者
     *
     * @return  下一个处理类实例
     */
    public ObtainRuleHandler getNextHandler();

    /**
     *  参加活动获取奖励的处理方法
     *
     * @param requestVo 请求  调用者需要初始化
     * @param responseVo 相应 调用者需要初始化
     * @throws Exception
     */
    public void obtain(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception;

}
