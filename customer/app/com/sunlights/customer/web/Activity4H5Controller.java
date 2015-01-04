package com.sunlights.customer.web;

import com.sunlights.customer.factory.ActivityServiceFactory;
import com.sunlights.customer.service.ActivityService;
import com.sunlights.customer.vo.Activity4H5Vo;
import models.Activity;
import play.Logger;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Result;

/**
 * Created by tangweiqun on 2014/12/26.
 */
@Transactional
public class Activity4H5Controller extends ActivityBaseController {

    private ActivityService activityService = ActivityServiceFactory.getActivityService();

    public Result getContent(String id) {

        Long activityId = Long.valueOf(id);


        Activity4H5Vo activity4H5Vo = activityService.getH5InfoById(activityId);

        Logger.debug("activity4H5Vo = " + activity4H5Vo);
        return ok(Json.toJson(activity4H5Vo));
    }
}
