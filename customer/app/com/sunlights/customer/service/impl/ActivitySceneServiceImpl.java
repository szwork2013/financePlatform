package com.sunlights.customer.service.impl;

import com.sunlights.common.cache.Cacheable;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.dal.ActivitySceneDao;
import com.sunlights.customer.dal.impl.ActivitySceneDaoImpl;
import com.sunlights.customer.service.ActivitySceneService;
import com.sunlights.customer.vo.ActivitySceneVo;
import models.Activity;
import models.ActivityScene;
import play.Logger;

import java.util.List;

/**
 * Created by Administrator on 2014/12/2.
 */
public class ActivitySceneServiceImpl implements ActivitySceneService {
    private ActivitySceneDao activitySceneDao = new ActivitySceneDaoImpl();

    @Cacheable(key = "getSceneByCode", duration = 3000)
    @Override
    public ActivitySceneVo getSceneByCode(String scene) {
        ActivityScene activityScene = activitySceneDao.getSceneByCode(scene);
        ActivitySceneVo activitySceneVo = null;
        if(activityScene != null) {
            activitySceneVo = new ActivitySceneVo();
            try {
                activitySceneVo = ConverterUtil.fromEntity(activitySceneVo, activityScene);
            } catch (Exception e) {
                Logger.error("活动场景转换对象错误", e);
            }

        }

        return activitySceneVo;
    }

    @Cacheable(key = "getScenesByActivityType", duration = 3000)
    @Override
    public List<ActivityScene> getScenesByActivityType(String activityType) {
        return activitySceneDao.getScenesByActivityType(activityType);
    }
}
