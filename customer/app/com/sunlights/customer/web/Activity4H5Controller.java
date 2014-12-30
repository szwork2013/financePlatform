package com.sunlights.customer.web;

import com.sunlights.customer.vo.Activity4H5Vo;
import play.Logger;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Result;

/**
 * Created by tangweiqun on 2014/12/26.
 */
@Transactional
public class Activity4H5Controller extends ActivityBaseController {

    public Result getContent() {
        Activity4H5Vo activity4H5Vo = new Activity4H5Vo();
        activity4H5Vo.setImageUrl("http://192.168.0.97/activity/images/sign_bannel.png");
        activity4H5Vo.setContent("<div>测试</div>");

        Logger.debug("activity4H5Vo = " + activity4H5Vo);
        return ok(Json.toJson(activity4H5Vo));
    }


}
