package com.sunlights.customer.service.rewardrules.obtain;


import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import play.Logger;


/**
 * 获取奖励处理节点的模版方法类：
 * 1：判断流程是否结束，如果结束则退出流程
 * 2：执行当前节点处理类的逻辑
 * 3：判断当前节点是否有下一个处理类，有的话则执行下一个处理类的逻辑
 * 4：性能统计
 *
 * Created by tangweiqun on 2014/12/2.
 */
public abstract class AbstractObtainRuleHandler implements ObtainRuleHandler{

    private ObtainRuleHandler nextHandler;

    public AbstractObtainRuleHandler() {

    }

    public AbstractObtainRuleHandler(ObtainRuleHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public ObtainRuleHandler setNextHandler(ObtainRuleHandler nextHandler) {
        this.nextHandler = nextHandler;
        return nextHandler;
    }

    @Override
    public ObtainRuleHandler getNextHandler() {
        return nextHandler;
    }

    @Override
    public void obtain(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {
        if(responseVo.isFlowStop()) {
            Logger.debug("退出获取奖励流程：" + toString());
            return;
        }
        long start = System.currentTimeMillis();
        try {
            obtainInternal(requestVo, responseVo);
            if(this.nextHandler != null) {
                this.nextHandler.obtain(requestVo, responseVo);
            }

        } finally {
            if(isPerformanceRecord()) {
                Logger.debug("[" + toString() + "]花费时间 : " + (System.currentTimeMillis() - start));
            }
        }
    }

    public boolean isPerformanceRecord() {
        return false;
    }


    public abstract void obtainInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception;
}
