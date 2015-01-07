package com.sunlights.customer.factory;

import com.sunlights.common.cache.CacheFactory;
import com.sunlights.common.cache.Cacheable;
import com.sunlights.customer.dal.ExchangeSceneDao;
import com.sunlights.customer.dal.impl.ExchangeSceneDaoImpl;

/**
 * Created by Administrator on 2015/1/6.
 */
public class ActivityDaoFactory {

    public static ExchangeSceneDao getExchangeSceneDao() {
        return CacheFactory.getProxyCacheObject(ExchangeSceneDaoImpl.class);
    }

}
