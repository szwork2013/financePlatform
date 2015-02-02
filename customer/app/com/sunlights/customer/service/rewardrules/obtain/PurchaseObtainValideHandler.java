package com.sunlights.customer.service.rewardrules.obtain;


import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.vo.Message;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.factory.ActivityServiceFactory;
import com.sunlights.customer.service.ActivitySceneService;
import com.sunlights.customer.service.ActivityService;
import com.sunlights.customer.service.impl.ActivityServiceImpl;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import models.ActivityScene;
import org.apache.commons.lang3.StringUtils;
import play.Logger;

import java.util.List;

/**
 * 确定购买活动是否是首次购买，并最终确定活动场景(是首次购买还是购买)
 *
 * Created by tangweiqun on 2014/12/2.
 */
public class PurchaseObtainValideHandler extends AbstractObtainRuleHandler {
    private ActivityService activityService = new ActivityServiceImpl();

    private ActivitySceneService activitySceneService = ActivityServiceFactory.getActivitySceneService();

    public PurchaseObtainValideHandler() {

    }

    public PurchaseObtainValideHandler(ObtainRuleHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void obtainInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {
        boolean hasFirstPurchase = activityService.validateHasFirstPurchase(requestVo.getCustId(), requestVo.getActivityId());
        if(!hasFirstPurchase) {
            requestVo.setScene(ActivityConstant.ACTIVITY_FIRST_PURCHASE_SCENE_CODE);
            Logger.debug("首次购买 scene = " + requestVo.getScene());
            return;
        } else {
            String prdType = requestVo.get("prdType", String.class);
            String prdCode = requestVo.get("prdCode", String.class);

            Logger.debug("不是首次购买 scene = " + requestVo.getScene() + " prdCode = " + prdCode);

            List<ActivityScene> activityScenes = activitySceneService.getScenesByActivityType(ActivityConstant.ACTIVITY_TYPE_PURCHASE);

            if(activityScenes != null && isSupportPrd(activityScenes, prdType, prdCode)) {
                requestVo.setScene(ActivityConstant.ACTIVITY_PURCHASE_RECOMMEND_SCENE_CODE);
            } else {
                Message message = new Message(Severity.INFO, MsgCode.NOT_CONFIG_ACTIVITY_SCENE);
                responseVo.setMessage(message);
                responseVo.setStatus(ActivityConstant.ACTIVITY_CUSTONER_STATUS_FORBIDDEN);
                responseVo.setNotGet(0L);
                responseVo.setAlreadyGet(0L);
                responseVo.setFlowStop(true);
                return;
            }
        }
    }

    private boolean isSupportPrd(List<ActivityScene> activityScenes, String prdType, String prdCode) {
        if(StringUtils.isEmpty(prdCode)) {
            return false;
        }
        for(ActivityScene activityScene : activityScenes) {
            if(prdCode.equals(activityScene.getPrdCode())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isPerformanceRecord() {
        return true;
    }

    @Override
    public String toString() {
        return "PurchaseObtainValideHandler";
    }
}
