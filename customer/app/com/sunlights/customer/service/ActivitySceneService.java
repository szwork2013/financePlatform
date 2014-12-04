package com.sunlights.customer.service;

import com.sunlights.customer.vo.ActivitySceneVo;
import models.ActivityScene;

import java.util.List;

/**
 * Created by tangweiqun on 2014/12/2.
 */
public interface ActivitySceneService {

    ActivitySceneVo getSceneByCode(String scene);


    List<ActivityScene> getScenesByActivityType(String activityType);

}
