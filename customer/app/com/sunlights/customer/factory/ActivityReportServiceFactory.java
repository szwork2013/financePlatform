package com.sunlights.customer.factory;

import com.sunlights.common.cache.CacheFactory;
import com.sunlights.customer.service.ActivityReportService;
import com.sunlights.customer.service.impl.ActivityReportServiceImpl;
import com.sunlights.customer.service.impl.ActivityServiceImpl;

/**
 * Created by Administrator on 2015/3/30.
 */
public class ActivityReportServiceFactory {

    public static ActivityReportService getActivityReportService() {
        return CacheFactory.getProxyCacheObject(ActivityReportServiceImpl.class);
    }
}
