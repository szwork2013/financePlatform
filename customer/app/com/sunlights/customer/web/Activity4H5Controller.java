package com.sunlights.customer.web;

import com.sunlights.common.MsgCode;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.customer.factory.ActivityServiceFactory;
import com.sunlights.customer.service.ActivityService;
import com.sunlights.customer.vo.Activity4H5Vo;
import play.Logger;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
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
        Controller.response().setHeader("Access-Control-Allow-Origin","*");

        return ok(Json.toJson(activity4H5Vo));
    }


    public Result getActivityRemain(String id){
        Logger.info(">>getActivityRemain params:" + id);
        Long activityId = Long.valueOf(id);
        Integer remainCount = activityService.countActivityRemain(activityId);

        Controller.response().setHeader("Access-Control-Allow-Origin","*");

        MessageUtil.getInstance().setMessage(new Message(MsgCode.OPERATE_SUCCESS), remainCount);

        Logger.info(">>getActivityRemain return:" + MessageUtil.getInstance().toJson());

        return ok(Json.toJson(MessageUtil.getInstance().toJson()));
    }

    public Result validateActivityIsOver(String id){
        Logger.info(">>validateActivityIsOver params:" + id);
        Long activityId = Long.valueOf(id);
        boolean isOver = activityService.validateActivityIsOver(activityId);

        Controller.response().setHeader("Access-Control-Allow-Origin","*");

        MessageUtil.getInstance().setMessage(new Message(MsgCode.OPERATE_SUCCESS), isOver);

        Logger.info(">>validateActivityIsOver return:" + MessageUtil.getInstance().toJson());

        return ok(Json.toJson(MessageUtil.getInstance().toJson()));
    }

}
