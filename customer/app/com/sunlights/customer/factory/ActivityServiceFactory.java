package com.sunlights.customer.factory;

import com.sunlights.common.cache.CacheFactory;
import com.sunlights.common.exceptions.ConverterException;
import com.sunlights.customer.service.ActivityService;
import com.sunlights.customer.service.impl.ActivityServiceImpl;

/**
 * Created by tangweiqun on 2014/12/6.
 */
public class ActivityServiceFactory {

    public static ActivityService getActivityService() throws ConverterException {
        return CacheFactory.getProxyCacheObject(ActivityServiceImpl.class);
    }
}
