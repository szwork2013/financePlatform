package com.sunlights.customer.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.customer.dal.ActivitySceneDao;
import models.ActivityScene;

import java.util.List;

/**
 * Created by tangweiqun on 2014/12/3.
 */
public class ActivitySceneDaoImpl extends EntityBaseDao implements ActivitySceneDao {

    @Override
    public ActivityScene getSceneByCode(String scene) {
        List<ActivityScene> activityScenes = super.findBy(ActivityScene.class, "scene", scene);
        if(activityScenes == null || activityScenes.isEmpty()) {
            return null;
        }
        return activityScenes.get(0);
    }

    @Override
    public List<ActivityScene> getScenesByActivityType(String activityType) {
        List<ActivityScene> activityScenes = super.findBy(ActivityScene.class, "activityType", activityType);
        return activityScenes;
    }
}
