package com.sunlights.customer.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.customer.dal.ActivitySceneDao;
import models.ActivityScene;
import models.HoldReward;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<ActivityScene> getScenes(ActivityScene activityScene) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("select h from ActivityScene h where 1 =1")
                .append("/~  and h.scene  = {scene} ~/")
                .append("/~  and h.activityType  = {activityType} ~/")
                .append("/~  and h.prdCode  = {prdCode} ~/");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("EQS_scene", activityScene.getScene());
        params.put("EQS_activityType", activityScene.getActivityType());
        params.put("EQS_prdCode", activityScene.getPrdCode());

        List<ActivityScene> list = findByMap(jpql.toString(), params);

        return list;

    }
}
