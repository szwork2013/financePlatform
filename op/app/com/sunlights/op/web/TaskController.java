package com.sunlights.op.web;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.utils.RequestUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.jobs.FundGrabService;
import com.sunlights.op.jobs.JobVo;
import com.sunlights.op.service.TaskService;
import com.sunlights.op.service.impl.TaskServiceImpl;
import org.apache.commons.lang3.StringUtils;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;

@Transactional
public class TaskController extends Controller {
	private MessageUtil messageUtil = MessageUtil.getInstance();
	private TaskService taskService = new TaskServiceImpl();

	public Result changeTaskStatus () {
		JobVo jobVo = null;
		Http.RequestBody body = request().body();

		if (body.asJson() != null) {
			jobVo = Json.fromJson(body.asJson(), JobVo.class);
		}

		if(jobVo != null) {
			if(0 == jobVo.getStatus()) {
				// 暂停
				taskService.pause(jobVo);
				messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS));
			} if (1 == jobVo.getStatus()) {
				// 运行
				taskService.resume(jobVo);
				messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS));
			}
			return ok(messageUtil.toJson());
		}
		messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_FAILURE));
		return badRequest(messageUtil.toJson());
	}

	public Result grabFund() {
		FundGrabService fundGrabService = new FundGrabService();
		fundGrabService.grabFund();
		messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS));
		return ok(messageUtil.toJson());
	}

	public Result grabFundCode() {
		FundGrabService fundGrabService = new FundGrabService();
		fundGrabService.grabFundCode();
		messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS));
		return ok(messageUtil.toJson());
	}

	public Result save() {
		Http.RequestBody body = request().body();

		if (body.asJson() != null) {
			JobVo jobVo = Json.fromJson(body.asJson(), JobVo.class);
			taskService.save(jobVo);
			messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS));
		} else {
			messageUtil.setMessage(new Message(Severity.ERROR, MsgCode.OPERATE_FAILURE));
		}
		return ok(messageUtil.toJson());
	}

	public Result findTasks() {
		PageVo pageVo = buildPageVo();
		if(pageVo == null){
			throw new IllegalArgumentException("page vo cannot be null.");
		}

		List<JobVo> jobs = taskService.findTasksBy(pageVo);
		pageVo.setList(jobs);
		messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), pageVo);
		return ok(messageUtil.toJson());
	}

	private PageVo buildPageVo() {
		PageVo pageVo = null;
		Http.Request request = request();
		if (!StringUtils.isBlank(request.getHeader("params"))) {
			pageVo = RequestUtil.getHeaderValue("params", PageVo.class);
		}

		if(pageVo == null) {
			Http.RequestBody body = request.body();
			if (body.asJson() != null) {
				pageVo = Json.fromJson(body.asJson(), PageVo.class);
			}
		}
		return pageVo;
	}

	public Result delete() {
		JobVo jobVo = null;

		Http.Request request = request();
		if (request.queryString() != null) {
			jobVo = RequestUtil.fromQueryString(request.queryString(), JobVo.class);
		}

		Http.RequestBody body = request.body();
		if (body.asJson() != null) {
			jobVo = Json.fromJson(body.asJson(), JobVo.class);
		}

		if(jobVo != null) {
			taskService.delete(jobVo);
			messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS));
			return ok(messageUtil.toJson());
		}
		messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_FAILURE));
		return badRequest(messageUtil.toJson());
	}

}
