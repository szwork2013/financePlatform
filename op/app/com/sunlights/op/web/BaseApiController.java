package com.sunlights.op.web;

import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.MessageVo;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by Yuan on 2015/3/12.
 */
public class BaseApiController extends Controller {

	public static Result JsonResponse(Object obj) {

		response().setContentType("application/json");
		response().setHeader("Access-Control-Allow-Origin", "*");
//		response().setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
//		response().setHeader("Access-Control-Allow-Headers", "Content-Type, api_key, Authorization");

		return ok(Json.toJson(obj));
	}

	public static Result JsonResponse(Message message, Object obj) {

		MessageVo<Object> messageVo = new MessageVo<>();
		messageVo.setMessage(message);
		messageVo.setValue(obj);

		response().setContentType("application/json");
		response().setHeader("Access-Control-Allow-Origin", "*");
//		response().setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
//		response().setHeader("Access-Control-Allow-Headers", "Content-Type, api_key, Authorization");

		return ok(Json.toJson(messageVo));
	}
}
