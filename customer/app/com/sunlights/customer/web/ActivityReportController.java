package com.sunlights.customer.web;

import com.sunlights.common.MsgCode;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.customer.factory.ActivityReportServiceFactory;
import com.sunlights.customer.service.ActivityReportService;
import com.sunlights.customer.vo.ActivityReportVo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import play.Logger;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by tangweiqun on 2015/3/30.
 */
@Transactional
@Api(value = "/customer", description = "活动播报信息服务")
public class ActivityReportController extends Controller {

    private ActivityReportService activityReportService = ActivityReportServiceFactory.getActivityReportService();

    @ApiOperation(value = "获取活动播报信息", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 0000, message = "操作成功", response = ActivityReportVo.class)
    })
    public Result getActivityReports() {
        Logger.debug(">>getActivityReports");

        ActivityReportVo activityReportVo = activityReportService.getActivityReports();

        MessageUtil.getInstance().setMessage(new Message(MsgCode.OPERATE_SUCCESS), activityReportVo);

        Logger.info(">>getActivityReports return:" + MessageUtil.getInstance().toJson());

        return ok(Json.toJson(MessageUtil.getInstance().toJson()));
    }

}
