package com.sunlights.customer.service.impl;

import com.sunlights.customer.factory.ActivityAttendDeciderFactory;
import com.sunlights.customer.service.AbstractActivityListQuery;
import com.sunlights.customer.service.ActivityAttendDecider;
import com.sunlights.customer.service.ActivityListQuery;
import com.sunlights.customer.service.CustJoinActivityService;
import com.sunlights.customer.vo.ActivityQueryContext;
import com.sunlights.customer.vo.ActivityVo;
import models.Activity;
import models.CustJoinActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tangweiqun on 2014/12/17.
 */
public class JuBaoPenActivityListQueryImpl extends AbstractActivityListQuery {
    private CustJoinActivityService custJoinActivityService = new CustJoinActivityServiceImpl();

    @Override
    public List<Activity> filter(List<Activity> allActivities, ActivityQueryContext context) {
        Map<String, List<CustJoinActivity>> listMap = custJoinActivityService.mapWithScene(context.getCustNo());
        List<Activity> result = new ArrayList<Activity>();
        List<Activity> temp = super.filter(allActivities, context);
        ActivityAttendDecider decider = null;
        //TODO 可能会有性能问题
        for(Activity activity : temp) {
            String scene = activity.getScene();
            decider = ActivityAttendDeciderFactory.getDecider(scene);
            if(decider == null || decider.decide(context.getCustNo(), listMap)) {
                result.add(activity);
            }
        }
        return result;
    }
}
