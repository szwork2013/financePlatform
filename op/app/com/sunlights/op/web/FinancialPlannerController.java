package com.sunlights.op.web;

import com.google.inject.Inject;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.utils.RequestUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.FinancialPlannerService;
import com.sunlights.op.vo.FinancialPlannerVo;
import org.apache.commons.lang3.StringUtils;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;

@Transactional
public class FinancialPlannerController extends Controller {

	private MessageUtil messageUtil = MessageUtil.getInstance();

	@Inject
	private FinancialPlannerService financialPlannerService;

	public Result findFinancialPlanners() {
		PageVo pageVo = new PageVo();
		Http.Request request = request();

		if (!StringUtils.isBlank(request.getHeader("params"))) {
			pageVo = RequestUtil.getHeaderValue("params", PageVo.class);
		}

		Http.RequestBody body = request.body();

		if (body.asJson() != null) {
			pageVo = Json.fromJson(body.asJson(), PageVo.class);
		}

		List<FinancialPlannerVo> financialPlanners = financialPlannerService.findFinancialPlannersBy(pageVo);
		pageVo.setList(financialPlanners);
		messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), pageVo);
		return ok(messageUtil.toJson());
	}


	public Result saveFinancialPlanner() {
		Http.RequestBody body = request().body();

		if (body.asJson() != null) {
			FinancialPlannerVo financialPlannerVo = Json.fromJson(body.asJson(), FinancialPlannerVo.class);
			Message message = financialPlannerVo.getId() == null ? new Message(Severity.INFO, MsgCode.CREATE_SUCCESS) : new Message(Severity.INFO, MsgCode.UPDATE_SUCCESS);
			financialPlannerService.save(financialPlannerVo);
			messageUtil.setMessage(message);
			return ok(messageUtil.toJson());

		}
		messageUtil.setMessage(new Message(Severity.ERROR, MsgCode.OPERATE_FAILURE));
		return badRequest(messageUtil.toJson());
	}
}
