package com.sunlights.customer.factory;

import com.sunlights.common.cache.CacheFactory;
import com.sunlights.customer.service.*;
import com.sunlights.customer.service.impl.*;

/**
 * Created by tangweiqun on 2014/12/6.
 */
public class ActivityServiceFactory {

    public static ActivityService getActivityService()  {
        return CacheFactory.getProxyCacheObject(ActivityServiceImpl.class);
    }

    public static ActivityReturnMsgService getActivityReturnMsgService(){
        return CacheFactory.getProxyCacheObject(ActivityReturnMsgServiceImpl.class);
    }

    public static ActivitySceneService getActivitySceneService() {
        return CacheFactory.getProxyCacheObject(ActivitySceneServiceImpl.class);
    }

    public static ExchangeRewardRuleService getExchangeRewardRuleService() {
        return CacheFactory.getProxyCacheObject(ExchangeRewardRuleServiceImpl.class);
    }

    public static ObtainRewardRuleService getObtainRewardRuleService() {
        return CacheFactory.getProxyCacheObject(ObtainRewardRuleServiceImpl.class);
    }

    public static RewardTypeService getRewardTypeService() {
        return CacheFactory.getProxyCacheObject(RewardTypeServiceImpl.class);
    }
}
