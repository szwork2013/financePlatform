package com.sunlights.op.web;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.utils.RequestUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.ResourceService;
import com.sunlights.op.service.impl.ResourceServiceImpl;
import com.sunlights.op.vo.ResourceVo;
import com.sunlights.op.vo.RoleVo;
import com.sunlights.op.vo.TreeVo;
import org.apache.commons.lang3.StringUtils;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;

/**
 * <p>Project: OperationPlatform</p>
 * <p>Title: ResourceController.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@Transactional
public class ResourceController extends Controller {

	private MessageUtil messageUtil = MessageUtil.getInstance();

	private ResourceService resourceService = new ResourceServiceImpl();

	public Result findTree() {
		RoleVo roleVo = new RoleVo();
		Http.Request request = request();
		if (!StringUtils.isBlank(request.getHeader("params"))) {
			roleVo = RequestUtil.getHeaderValue("params", RoleVo.class);
		}
		TreeVo tree = resourceService.findTree(roleVo.getId());
		messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), tree);
		return ok(messageUtil.toJson());
	}

	public Result findResources() {
		PageVo pageVo = new PageVo();
		Http.Request request = request();

		if (!StringUtils.isBlank(request.getHeader("params"))) {
			pageVo = RequestUtil.getHeaderValue("params", PageVo.class);
		}

		Http.RequestBody body = request.body();

		if (body.asJson() != null) {
			pageVo = Json.fromJson(body.asJson(), PageVo.class);
		}

		List<ResourceVo> resources = resourceService.findResourcesBy(pageVo);
		pageVo.setList(resources);
		messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), pageVo);
		return ok(messageUtil.toJson());
	}

}
