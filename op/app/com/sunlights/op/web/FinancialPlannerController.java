package com.sunlights.op.web;

import com.google.inject.Inject;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.utils.RequestUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.dto.PlannerCustomerExportXlsDto;
import com.sunlights.op.dto.ReferrerExportXlsDto;
import com.sunlights.op.service.FinancialPlannerService;
import com.sunlights.op.vo.FinancialPlannerCustomerVo;
import com.sunlights.op.vo.FinancialPlannerVo;
import com.sunlights.op.vo.statistics.ReferrerVo;
import org.apache.commons.lang3.StringUtils;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

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

	public Result findFinancialPlannerCustomers() {
		PageVo pageVo = new PageVo();
		Http.Request request = request();

		if (!StringUtils.isBlank(request.getHeader("params"))) {
			pageVo = RequestUtil.getHeaderValue("params", PageVo.class);
		}

		Http.RequestBody body = request.body();

		if (body.asJson() != null) {
			pageVo = Json.fromJson(body.asJson(), PageVo.class);
		}

		List<FinancialPlannerCustomerVo> plannerCustomers = financialPlannerService.findPlannerCustomers(pageVo);
		pageVo.setList(plannerCustomers);
		messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), pageVo);
		return ok(messageUtil.toJson());
	}

	public Result exportPlannerCustomer() {
		PageVo pageVo = new PageVo();
		Map<String, String[]> filterMap = request().queryString();
		for (String key : filterMap.keySet()) {
			String[] filter = filterMap.get(key);
			if (filter.length > 0 && StringUtils.isNotBlank(filter[0])) {
				pageVo.put(key, filter[0].replace("\"", ""));
			}
		}

		Timestamp currentTime = DBHelper.getCurrentTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String time = sdf.format(currentTime);

		response().setContentType("application/vnd.ms-excel;charset=UTF-8");
		response().setHeader("Content-Disposition", "attachment;filename=planner_customer_" + time + ".xls");

		List<FinancialPlannerCustomerVo> plannerCustomers = financialPlannerService.findPlannerCustomers(pageVo);
		PlannerCustomerExportXlsDto plannerCustomerExportXlsDto = new PlannerCustomerExportXlsDto();
		plannerCustomerExportXlsDto.setData(plannerCustomers);
		Chunks<byte[]> export = plannerCustomerExportXlsDto.export();
		return ok(export);
	}
}
