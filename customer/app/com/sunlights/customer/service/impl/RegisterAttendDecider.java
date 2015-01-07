package com.sunlights.customer.service.impl;

import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.service.ActivityAttendDecider;
import models.CustJoinActivity;

import java.util.List;
import java.util.Map;

/**
 * Created by tangweiqun on 2014/12/17.
 */
public class RegisterAttendDecider implements ActivityAttendDecider {

    @Override
    public boolean decide(String custId, Map<String, List<CustJoinActivity>> listMap) {
        if(listMap == null || listMap.isEmpty()) {
            return true;
        }
        if(listMap.containsKey(ActivityConstant.ACTIVITY_REGISTER_SCENE_CODE)) {
            return false;
        }
        return true;
    }
}
