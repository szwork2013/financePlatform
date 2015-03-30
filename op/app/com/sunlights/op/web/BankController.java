package com.sunlights.op.web;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.utils.RequestUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.BankService;
import com.sunlights.op.service.impl.BankServiceImpl;
import com.sunlights.op.vo.BankVo;
import org.apache.commons.lang3.StringUtils;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;

/**
 * Created by Yuan on 2014/10/24.
 */
@Transactional
public class BankController extends Controller {

	private MessageUtil messageUtil = MessageUtil.getInstance();

	private BankService bankService = new BankServiceImpl();

	public Result deleteBank() {
		BankVo bankVo = null;
		Http.Request request = request();

		if (request.queryString() != null) {
			bankVo = RequestUtil.fromQueryString(request.queryString(), BankVo.class);
		}

		if (bankVo != null) {
			bankService.delete(bankVo);
			messageUtil.setMessage(new Message(Severity.INFO, MsgCode.DELETE_SUCCESS));
			return ok(messageUtil.toJson());
		}
		messageUtil.setMessage(new Message(Severity.ERROR, MsgCode.DELETE_FAILURE));
		return badRequest(messageUtil.toJson());
	}

	public Result findBanks() {
		PageVo pageVo = new PageVo();
		Http.Request request = request();

		if (!StringUtils.isBlank(request.getHeader("params"))) {
			pageVo = RequestUtil.getHeaderValue("params", PageVo.class);
		}

		Http.RequestBody body = request.body();

		if (body.asJson() != null) {
			pageVo = Json.fromJson(body.asJson(), PageVo.class);
		}
		List<BankVo> bankVos = bankService.findBanksBy(pageVo);
		pageVo.setList(bankVos);
		messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), pageVo);
		return ok(messageUtil.toJson());
	}

	public Result saveBank() {
		Http.RequestBody body = request().body();

		if (body.asJson() != null) {
			BankVo bankVo = Json.fromJson(body.asJson(), BankVo.class);
			Message message = bankVo.getId() == null ? new Message(Severity.INFO, MsgCode.CREATE_SUCCESS) : new Message(Severity.INFO,
					MsgCode.UPDATE_SUCCESS);
			bankService.save(bankVo);
			messageUtil.setMessage(message);
			return ok(messageUtil.toJson());

		}
		messageUtil.setMessage(new Message(Severity.ERROR, MsgCode.OPERATE_FAILURE));
		return badRequest(messageUtil.toJson());
	}
}
