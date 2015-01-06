package com.sunlights.customer.service.impl;

import com.sunlights.common.MsgCode;
import com.sunlights.common.cache.Cacheable;
import com.sunlights.customer.dal.ActivityReturnMsgDao;
import com.sunlights.customer.dal.impl.ActivityReturnMsgDaoImpl;
import com.sunlights.customer.service.ActivityReturnMsgService;
import models.ActivityReturnMsg;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by tangweiqun on 2014/12/23.
 */
public class ActivityReturnMsgServiceImpl implements ActivityReturnMsgService {
    private ActivityReturnMsgDao activityReturnMsgDao = new ActivityReturnMsgDaoImpl();

    @Cacheable(key = "activityReturnMsg", duration = 300)
    @Override
    public String getReturnMsg(String scene, String type, String rewardType, String category, String errorCode) {

        if(StringUtils.isEmpty(errorCode)) {
            errorCode = MsgCode.OPERATE_SUCCESS.getCode();
        }
        ActivityReturnMsg activityReturnMsg = new ActivityReturnMsg();
        activityReturnMsg.setScene(scene);
        activityReturnMsg.setType(type);
        activityReturnMsg.setRewardType(rewardType);
        activityReturnMsg.setCategory(category);
        activityReturnMsg.setErrorCode(errorCode);

        activityReturnMsg = activityReturnMsgDao.getByCondition(activityReturnMsg);

        if(activityReturnMsg == null) {
            return "";
        }

        return activityReturnMsg.getTemplate();
    }
}
