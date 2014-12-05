package com.sunlights.customer.service.rewardrules.obtain;


import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.vo.Message;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.service.ActivitySceneService;
import com.sunlights.customer.service.ActivityService;
import com.sunlights.customer.service.ObtainRewardRuleService;
import com.sunlights.customer.service.impl.ActivitySceneServiceImpl;
import com.sunlights.customer.service.impl.ActivityServiceImpl;
import com.sunlights.customer.service.impl.ObtainRewardRuleServiceImpl;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import com.sunlights.customer.service.rewardrules.vo.ObtainRewardRuleVo;
import models.Activity;
import models.ActivityScene;
import play.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 根据客户参加的活动场景来获取客户将要参加的活动以及活动对应的获取奖励规则等信息
 * Created by tangweiqun on 2014/12/2.
 */
public class ObtainRuleGainHandler extends AbstractObtainRuleHandler {
    private ActivitySceneService activitySceneService = new ActivitySceneServiceImpl();

    private ActivityService activityService = new ActivityServiceImpl();

    private ObtainRewardRuleService obtainRewardRuleService = new ObtainRewardRuleServiceImpl();

    public ObtainRuleGainHandler() {

    }

    public ObtainRuleGainHandler(ObtainRuleHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void obtainInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {
        boolean isNotConfig = false;
        ActivityScene activityScene = requestVo.getActivityScene();
        if(activityScene == null) {
            activityScene = activitySceneService.getSceneByCode(requestVo.getScene());
        }

        if(activityScene != null && ActivityConstant.ACTIVITY_CUSTONER_STATUS_NOMAL.equals(activityScene.getStatus())) {
            requestVo.setActivityScene(activityScene);
            List<Activity> activities = activityService.getActivityByScene(activityScene.getScene());
            if(activities == null || activities.isEmpty()) {
                isNotConfig = true;
            } else {
                requestVo.setActivities(activities);
                Map<Long, List<ObtainRewardRuleVo>> obtainRewardRuleMap = new HashMap<Long, List<ObtainRewardRuleVo>>();
                for(Activity activity : activities) {
                    List<ObtainRewardRuleVo> obtainRewardRules = obtainRewardRuleService.getByVosActivityId(activity.getId());
                    if(obtainRewardRules == null || obtainRewardRules.isEmpty() || ActivityConstant.ACTIVITY_CUSTONER_STATUS_FORBIDDEN.equals(activity.getStatus())) {
                        continue;
                    }
                    obtainRewardRuleMap.put(activity.getId(), obtainRewardRules);
                }
                if(obtainRewardRuleMap.isEmpty()) {
                    isNotConfig = true;
                } else {
                    requestVo.setObtainRewardRuleMap(obtainRewardRuleMap);
                }
            }
        } else {
            isNotConfig = true;
        }

        if(isNotConfig) {
            Logger.debug("还没有配置的活动场景");
            Message message = new Message(Severity.INFO, MsgCode.NOT_CONFIG_ACTIVITY_SCENE);
            responseVo.setMessage(message);
            responseVo.setStatus(ActivityConstant.ACTIVITY_CUSTONER_STATUS_FORBIDDEN);
            responseVo.setNotGet(0L);
            responseVo.setAlreadyGet(0L);
            responseVo.setFlowStop(true);
        }
    }

    @Override
    public String toString() {
        return "ObtainRuleGainHandler";
    }
}
