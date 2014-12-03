package com.sunlights.customer.dal;


import models.ActivityScene;

import java.util.List;

/**
 * Created by Administrator on 2014/12/3.
 */
public interface ActivitySceneDao {

    public ActivityScene getSceneByCode(String scene);


    public List<ActivityScene> getScenesByActivityType(String activityType);

    public List<ActivityScene> getScenes(ActivityScene activityScene);
}
