package com.sunlights.customer.service.rewardrules.obtain;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.vo.Message;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.factory.ActivityServiceFactory;
import com.sunlights.customer.service.ActivityService;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import models.Activity;

import java.sql.Timestamp;
import java.util.List;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: RegisterActivityValideHandler.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class RegisterActivityValidateHandler extends AbstractObtainRuleHandler {
    private ActivityService activityService = ActivityServiceFactory.getActivityService();

    @Override
    public void obtainInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {
        List<Activity> activities = activityService.getCurrentValidActivities();
        if (activities.isEmpty()) {
            responseVo.setFlowStop(true);
            return;
        }

        Timestamp currentTime = DBHelper.getCurrentTime();
        boolean activityOver = true;
        boolean purchaseActivityOver = true;
        for (Activity activity : activities) {
            if (activity.getScene().equals(requestVo.getScene()) && ActivityConstant.ACTIVITY_STATUS_NOMAL.equals(activity.getStatus())) {
                if (activity.getBeginTime() != null && activity.getBeginTime().before(currentTime)) {
                    if (activity.getEndTime() != null && activity.getEndTime().after(currentTime)) {
                        activityOver = false;
                    }
                }
            } else if (activity.getScene().equals(ActivityConstant.ACTIVITY_FIRST_PURCHASE_SCENE_CODE)) {
                purchaseActivityOver = activityService.validateActivityIsOver(activity.getId());
            }
        }

        if (!activityOver && purchaseActivityOver) {
            Message message = new Message(Severity.INFO, MsgCode.NOT_BEGIN_ACTIVITY_SCENE);
            responseVo.setMessage(message);
            responseVo.setFlowStop(true);
            return;
        }

    }
}
