package com.sunlights.customer.factory;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.exceptions.BusinessRuntimeException;
import com.sunlights.common.vo.Message;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.service.share.*;

/**
 * Created by tangweiqun on 2014/12/17.
 */
public class ShareInfoServiceFactory {

    public static ShareInfoService createShareInfoService(String type) {
        if (ActivityConstant.SHARE_TYPE_ACTIVITY.equals(type)) {
            return new ActivityShareInfoServiceImpl();
        } else if (ActivityConstant.SHARE_TYPE_INVITER.equals(type)) {
            return new InviteShareInfoServiceImpl();
        } else if(ActivityConstant.SHARE_TYPE_PRODUCT_JDJ.equals(type)) {
            return new JdjProductShareInfoServiceImpl();
        } else if(ActivityConstant.SHARE_TYPE_PRODUCT_P2P.equals(type)) {
            return new P2pProductShareInfoServiceImpl();
        }
        throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.NOT_SUPPORT_SHARE_TYPE));
    }
}
