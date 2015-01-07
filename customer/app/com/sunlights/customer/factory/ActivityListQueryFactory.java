package com.sunlights.customer.factory;

import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.service.ActivityListQuery;
import com.sunlights.customer.service.impl.CenterActivityListQueryImpl;
import com.sunlights.customer.service.impl.JuBaoPenActivityListQueryImpl;

/**
 * Created by Administrator on 2014/12/17.
 */
public class ActivityListQueryFactory {

    public static ActivityListQuery getQueryStyle(String filter) {
        if(ActivityConstant.ACTIVITY_QUERY_CENTER.equals(filter)) {
            return new CenterActivityListQueryImpl();
        } else if(ActivityConstant.ACTIVITY_QUERY_JUBAOPEN.equals(filter)) {
            return new JuBaoPenActivityListQueryImpl();
        }
        return null;
    }
}
