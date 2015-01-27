/*
 * Project Name:thirdPartyService
 * File Name:JsapiTicketController
 * Package Name:controllers
 * Date:2015/1/20 13:57
 * Copyright (c) 2015, YiYue Company All Rights Reserved.
*/
package web;

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
public class JsapiTicketController extends Controller {

    private static String ticket = "";

    public Result getTicket() {
        Controller.response().setHeader("Access-Control-Allow-Origin","*");
        try {
            return ok(ticket);
        } catch (Exception e) {
            return badRequest();
        }
    }

    public Result flushTicket() {
        JsapiTicket jsapiTicket = new JsapiTicket();
        try {
            ticket = jsapiTicket.getJsapiTicket(jsapiTicket.getAccessToken());
            return ok();
        } catch (Exception e) {
            return badRequest();
        }
    }
}