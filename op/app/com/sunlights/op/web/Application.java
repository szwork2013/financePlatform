package com.sunlights.op.web;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.utils.RequestUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.op.service.LoginService;
import com.sunlights.op.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.Date;

@Transactional
public class Application extends Controller {
	private static MessageUtil messageUtil = MessageUtil.getInstance();
	private LoginService loginService = new LoginService();

	private static Form<UserVo> userForm = Form.form(UserVo.class);

	private static String INDEX_URI = "/assets/faces/index.html";

	public static Result index() {
		return redirect(INDEX_URI);
	}

	public Result login() {
		UserVo userVo = new UserVo();
		Http.Request request = request();
		Http.RequestBody body = request.body();
		if (body.asFormUrlEncoded() != null) {
			userVo = userForm.bindFromRequest().get();
		}
		if (body.asJson() != null) {
			userVo = Json.fromJson(body.asJson(), UserVo.class);
		}
		if (!StringUtils.isBlank(request.getHeader("params"))) {
			userVo = RequestUtil.getHeaderValue("params", UserVo.class);
		}

		UserVo currentUser = loginService.login(userVo);
		currentUser = loginService.getCurrentUser(currentUser.getUsername());
		session().clear();
		session("user", currentUser.getUsername());
		messageUtil.setMessage(new Message(Severity.INFO, MsgCode.LOGIN_SUCCESS), currentUser);
		return ok(messageUtil.toJson());
	}

	public Result getCurrentUser() {
		String user = session().get("user");
        UserVo userVo = new UserVo();

        Message message = new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS);

        if (StringUtils.isNotBlank(user)) {
            userVo = loginService.getCurrentUser(user);
        }

        MessageUtil.getInstance().setMessage(message, userVo);
		return ok(MessageUtil.getInstance().toJson());
	}

	public Result logout() {
		Logger.info("[logout]" + new Date());
		session().remove("user");
		messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS));
		return ok(messageUtil.toJson());
	}

	public Result resetPassword() {
		UserVo userVo = null;
		Http.RequestBody body = request().body();

		if (body.asJson() != null) {
			userVo = Json.fromJson(body.asJson(), UserVo.class);
			loginService.reset(userVo);
			messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS));
			return ok(messageUtil.toJson());
		}
		messageUtil.setMessage(new Message(Severity.ERROR, MsgCode.OPERATE_FAILURE));
		return badRequest(messageUtil.toJson());
	}
}
