package com.sunlights.customer.factory;

import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.service.ActivityAttendDecider;
import com.sunlights.customer.service.impl.FirstPurchaseAttendDecider;
import com.sunlights.customer.service.impl.RegisterAttendDecider;
import com.sunlights.customer.service.impl.SigninAttendDecider;

/**
 * Created by Administrator on 2014/12/17.
 */
public class ActivityAttendDeciderFactory {

    public static ActivityAttendDecider getDecider(String scene) {
        if(ActivityConstant.ACTIVITY_FIRST_PURCHASE_SCENE_CODE.equals(scene)) {
            return new FirstPurchaseAttendDecider();
        } else if(ActivityConstant.ACTIVITY_REGISTER_SCENE_CODE.equals(scene)) {
            return new RegisterAttendDecider();
        } else if(ActivityConstant.ACTIVITY_SIGNIN_SCENE_CODE.equals(scene)) {
            return new SigninAttendDecider();
        }
        return null;
    }
}
