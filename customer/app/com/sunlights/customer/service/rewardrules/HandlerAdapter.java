package com.sunlights.customer.service.rewardrules;


import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;

/**
 * //TODO: 这个接口需要重构一下， supports方法不应该在这个接口里面； handle方法应该抛出BusinessExcetpion， 或者活动自己的exception类。
 * 处理活动的适配器
 * 活动支持的活动动作：获取活动奖励、兑换奖励等
 * <p/>
 * Created by tangweiqun on 2014/12/1.
 */
public interface HandlerAdapter {

    /**
     * 验证活动系统是否支持指定的动作的处理
     *
     * @param handler 指定动作的处理类
     * @return true 表示支持 false表示不支持
     */
    boolean supports(Object handler);

    void handle(ActivityRequestVo requestVo, ActivityResponseVo responseVo, Object handler) throws Exception;

}
