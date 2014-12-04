package com.sunlights.customer.service.rewardrules.obtain;


import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.vo.Message;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.service.ActivitySceneService;
import com.sunlights.customer.service.CustJoinActivityService;
import com.sunlights.customer.service.impl.ActivitySceneServiceImpl;
import com.sunlights.customer.service.impl.CustJoinActivityServiceImpl;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import com.sunlights.customer.vo.ActivitySceneVo;
import models.ActivityScene;
import models.CustJoinActivity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 确定购买活动是否是首次购买，并最终确定活动场景(是首次购买还是购买)
 *
 * Created by tangweiqun on 2014/12/2.
 */
public class PurchaseObtainValideHandler extends AbstractObtainRuleHandler {
    private CustJoinActivityService custJoinActivityService = new CustJoinActivityServiceImpl();

    private ActivitySceneService activitySceneService = new ActivitySceneServiceImpl();

    public PurchaseObtainValideHandler() {

    }

    public PurchaseObtainValideHandler(ObtainRuleHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void obtainInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {
        CustJoinActivity custJoinActivity = custJoinActivityService.getByCustAndActivity(requestVo.getCustId(), requestVo.getActivityId(), ActivityConstant.ACTIVITY_FIRST_PURCHASE_SCENE_CODE);
        if(custJoinActivity == null) {
            requestVo.setScene(ActivityConstant.ACTIVITY_FIRST_PURCHASE_SCENE_CODE);
            return;
        } else {
            String prdType = requestVo.get("prdType", String.class);
            String prdCode = requestVo.get("prdCode", String.class);
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
