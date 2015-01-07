package com.sunlights.core.factory;

import com.sunlights.common.cache.CacheFactory;
import com.sunlights.core.service.OpenAccountPactService;
import com.sunlights.core.service.impl.OpenAccountPactServiceImpl;

/**
 * Created by Administrator on 2015/1/6.
 */
public class CoreFactory {

    public static OpenAccountPactService getOpenAccountPactService() {
        return CacheFactory.getProxyCacheObject(OpenAccountPactServiceImpl.class);
    }
}
