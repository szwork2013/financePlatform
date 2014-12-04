package com.sunlights.account.service.rewardrules.obtain;

import com.sunlights.account.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.account.service.rewardrules.vo.ActivityResponseVo;
import play.Logger;


/**
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
    public void setNextHandler(ObtainRuleHandler nextHandler) {
        this.nextHandler = nextHandler;
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
            Logger.debug("[" + toString() + "]花费时间 : " + (System.currentTimeMillis() - start));
        }
    }


    public abstract void obtainInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception;
}
