package com.sunlights.customer.service.impl;

import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.dal.ActivityDao;
import com.sunlights.customer.dal.ShareInfoDao;
import com.sunlights.customer.dal.impl.ActivityDaoImpl;
import com.sunlights.customer.dal.impl.ShareInfoDaoImpl;
import com.sunlights.customer.service.ShareInfoService;
import models.Activity;
import models.ShareInfo;

/**
 * Created by Administrator on 2014/12/16.
 */
public class ShareInfoServiceImpl implements ShareInfoService {
    private ShareInfoDao shareInfoDao = new ShareInfoDaoImpl();
    private ActivityDao activityDao = new ActivityDaoImpl();

    @Override
    public ShareInfo getShareInfoByType(String type, String refId) {
        ShareInfo shareInfo = shareInfoDao.getByType(type);
        if(shareInfo == null) {
            return null;
        }
        //TODO 文案需要产品给出
        if(ActivityConstant.SHARE_TYPE_ACTIVITY.equals(type)) {
            Activity activity = activityDao.findById(Long.valueOf(refId));
            shareInfo.setTitle(activity.getTitle());
        } else if(ActivityConstant.SHARE_TYPE_INVITER.equals(type)){


        } else {

        }
        return shareInfo;
    }
}
