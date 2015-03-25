package com.sunlights.customer.factory;

import com.sunlights.common.cache.CacheFactory;
import com.sunlights.customer.dal.ShareInfoDao;
import com.sunlights.customer.dal.impl.ShareInfoDaoImpl;

/**
 * Created by tangweiqun on 2015/3/24.
 */
public class ShareInfoDaoFactory {

    public static ShareInfoDao getShareInfoDao() {
        return CacheFactory.getProxyCacheObject(ShareInfoDaoImpl.class);
    }
}
