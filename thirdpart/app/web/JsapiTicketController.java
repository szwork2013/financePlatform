/*
 * Project Name:thirdPartyService
 * File Name:JsapiTicketController
 * Package Name:controllers
 * Date:2015/1/20 13:57
 * Copyright (c) 2015, YiYue Company All Rights Reserved.
*/
package web;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import weixin.JsapiTicket;

/**
 * ClassName:    JsapiTicketController
 * Description:  ADD Description.
 * Date:         2015/1/20 13:57
 *
 * @author Xuelong.Gu
 * @version 1.0
 * @since JDK 1.6
 */
@Api(value = "/thirdpart", description = "第三方服务接口")
public class JsapiTicketController extends Controller {

    @ApiOperation(value = "获取微信的ticket",
            nickname = "getTicket",
            httpMethod = "GET")
    public Result getTicket() {
        Controller.response().setHeader("Access-Control-Allow-Origin","*");
        try {
            return ok(JsapiTicket.getInstance().getTicket());
        } catch (Exception e) {
            return badRequest();
        }
    }
}