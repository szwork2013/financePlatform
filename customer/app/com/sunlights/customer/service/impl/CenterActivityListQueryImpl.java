package com.sunlights.customer.service.impl;

import com.sunlights.customer.service.AbstractActivityListQuery;
import com.sunlights.customer.vo.ActivityQueryContext;
import models.Activity;

import java.util.List;

/**
 * Created by tangweiqun on 2014/12/17.
 */
public class CenterActivityListQueryImpl extends AbstractActivityListQuery {

    @Override
    public List<Activity> filter(List<Activity> allActivities, ActivityQueryContext context) {
        return super.filter(allActivities, context);
    }
}
