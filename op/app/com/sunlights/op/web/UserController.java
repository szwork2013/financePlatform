package com.sunlights.op.web;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.utils.RequestUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.UserService;
import com.sunlights.op.service.impl.UserServiceImpl;
import com.sunlights.op.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;

/**
 * <p>Project: OperationPlatform</p>
 * <p>Title: UserController.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@Transactional
public class UserController extends Controller {

	private MessageUtil messageUtil = MessageUtil.getInstance();

	private UserService userService = new UserServiceImpl();

	public Result findUsers() {
		PageVo pageVo = new PageVo();
		Http.Request request = request();

		if (!StringUtils.isBlank(request.getHeader("params"))) {
			pageVo = RequestUtil.getHeaderValue("params", PageVo.class);
		}

		Http.RequestBody body = request.body();

		if (body.asJson() != null) {
			pageVo = Json.fromJson(body.asJson(), PageVo.class);
		}

		List<UserVo> users = userService.findUsersBy(pageVo);
		pageVo.setList(users);
		messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), pageVo);
		return ok(messageUtil.toJson());
	}

	public Result saveUser() {
		Http.RequestBody body = request().body();

		if (body.asJson() != null) {
			UserVo userVo = Json.fromJson(body.asJson(), UserVo.class);
			Message message = userVo.getId() == null ? new Message(Severity.INFO, MsgCode.CREATE_SUCCESS) : new Message(Severity.INFO, MsgCode.UPDATE_SUCCESS);
			userService.save(userVo);
			messageUtil.setMessage(message);
			return ok(messageUtil.toJson());

		}
		messageUtil.setMessage(new Message(Severity.ERROR, MsgCode.OPERATE_FAILURE));
		return badRequest(messageUtil.toJson());
	}
}
