package com.sunlights.customer.service.share;

import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.dal.CustomerDao;
import com.sunlights.customer.dal.impl.CustomerDaoImpl;
import com.sunlights.customer.factory.ActivityServiceFactory;
import com.sunlights.customer.service.ActivityService;
import com.sunlights.customer.vo.ShareInfoVo;
import models.Activity;
import models.Customer;
import models.ShareInfo;
import org.apache.commons.lang3.StringUtils;
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
        Logger.debug("ActivityShareInfoServiceImpl getLongUrl call");
        ShareInfo shareInfo = context.getShareInfo();
        StringBuilder sb = new StringBuilder();
        sb.append(shareInfo.getBaseUrl());
        Activity activity = activityService.getByUnknowCondition(context.getRefId());
        if (Integer.valueOf(ActivityConstant.ACCOUNT_COMMON_ONE).equals(shareInfo.getRelateRefId())) {
            sb.append("/" + activity.getUrl());
        }
        sb.append("?info=" + context.getMobile() + "|" + activity.getId());

        return sb.toString();
    }


}
