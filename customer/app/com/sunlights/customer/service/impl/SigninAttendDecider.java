package com.sunlights.customer.service.impl;

import com.sunlights.common.utils.CommonUtil;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.service.ActivityAttendDecider;
import models.CustJoinActivity;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by tangweiqun on 2014/12/17.
 * 今天没有签到的可以签到
 */

public class SigninAttendDecider implements ActivityAttendDecider {

    @Override
    public boolean decide(String custId, Map<String, List<CustJoinActivity>> listMap) {
        String currentDate = CommonUtil.dateToString(new Date(), CommonUtil.DATE_FORMAT_SHORT);
        List<CustJoinActivity> list = listMap.get(ActivityConstant.ACTIVITY_SIGNIN_SCENE_CODE);
        if(list == null || list.isEmpty()) {
            return true;
        }
        for(CustJoinActivity custJoinActivity : list) {
            if(currentDate.equals(CommonUtil.dateToString(custJoinActivity.getCreateTime(), CommonUtil.DATE_FORMAT_SHORT))) {
                return false;
            }
        }
        return true;
    }
}
