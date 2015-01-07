package com.sunlights.customer.service.impl;

import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.dal.ActivityDao;
import com.sunlights.customer.dal.impl.ActivityDaoImpl;
import com.sunlights.customer.factory.ActivityServiceFactory;
import com.sunlights.customer.service.AbstractShareInfoService;
import com.sunlights.customer.service.ActivityService;
import com.sunlights.customer.vo.ShareInfoContext;
import com.sunlights.customer.vo.ShareInfoVo;
import models.Activity;
import models.ShareInfo;
import play.Logger;

import java.text.MessageFormat;

/**
 * Created by tangweiqun on 2014/12/17.
 */
public class ActivityShareInfoServiceImpl extends AbstractShareInfoService {

    private ActivityService activityService = ActivityServiceFactory.getActivityService();

    @Override
    public void prepareShareInfo(ShareInfoContext context) {
        Activity activity = activityService.getByUnknowCondition(context.getRefId());
        ShareInfo shareInfo = context.getShareInfo();
        ShareInfoVo shareInfoVo = context.getShareInfoVo();
        String activityTitle = activity.getTitle();
        Logger.debug("activityTitle == " + activityTitle);
        String descTemplate = shareInfo.getContent();
        shareInfoVo.setContent(MessageFormat.format(descTemplate, activityTitle));
    }

    @Override
    public String getLongUrl(ShareInfoContext context) {
        ShareInfo shareInfo = context.getShareInfo();
        StringBuilder sb = new StringBuilder();
        sb.append(shareInfo.getBaseUrl());
        if(Integer.valueOf(ActivityConstant.ACCOUNT_COMMON_ONE).equals(shareInfo.getRelateRefId())) {
            Activity activity = activityService.getByUnknowCondition(context.getRefId());
            sb.append("/" + activity.getUrl());
            sb.append(context.getCommonParamter());
            sb.append("&activityId = " + activity.getId());
        }
        return sb.toString();
    }
}
