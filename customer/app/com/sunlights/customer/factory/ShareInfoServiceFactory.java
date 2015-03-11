package com.sunlights.customer.factory;

import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.service.ShareInfoService;
import com.sunlights.customer.service.impl.ActivityShareInfoServiceImpl;
import com.sunlights.customer.service.impl.InviteShareInfoServiceImpl;

/**
 * Created by tangweiqun on 2014/12/17.
 */
public class ShareInfoServiceFactory {

    public static ShareInfoService createShareInfoService(String type) {
        if (ActivityConstant.SHARE_TYPE_ACTIVITY.equals(type)) {
            return new ActivityShareInfoServiceImpl();
        } else if (ActivityConstant.SHARE_TYPE_INVITER.equals(type)) {
            return new InviteShareInfoServiceImpl();
        }
        return null;
    }
}
