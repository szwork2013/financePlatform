package com.sunlights.op.web;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.utils.RequestUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.SupplierService;
import com.sunlights.op.service.impl.SupplierServiceImpl;
import com.sunlights.op.vo.SupplierVo;
import org.apache.commons.lang3.StringUtils;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;

/**
 * <p>Project: OperationPlatform</p>
 * <p>Title: SupplierController.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@Transactional
public class SupplierController extends Controller {

	private MessageUtil messageUtil = MessageUtil.getInstance();

	private SupplierService supplierService = new SupplierServiceImpl();

	public Result findSuppliers() {
		PageVo pageVo = new PageVo();
		Http.Request request = request();

		if (!StringUtils.isBlank(request.getHeader("params"))) {
			pageVo = RequestUtil.getHeaderValue("params", PageVo.class);
		}

		Http.RequestBody body = request.body();

		if (body.asJson() != null) {
			pageVo = Json.fromJson(body.asJson(), PageVo.class);
		}

		List<SupplierVo> supplierVos = supplierService.findSuppliersBy(pageVo);
		pageVo.setList(supplierVos);
		messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), pageVo);
		return ok(messageUtil.toJson());
	}

	public Result deleteSupplier() {
		SupplierVo supplierVo = null;
		Http.Request request = request();

		if (request.queryString() != null) {
			supplierVo = RequestUtil.fromQueryString(request.queryString(), SupplierVo.class);
		}

		Http.RequestBody body = request.body();

		if (body.asJson() != null) {
			supplierVo = Json.fromJson(body.asJson(), SupplierVo.class);

		}
		if (supplierVo != null) {
			supplierService.delete(supplierVo);
			messageUtil.setMessage(new Message(Severity.INFO, MsgCode.DELETE_SUCCESS));
			return ok(messageUtil.toJson());
		}
		messageUtil.setMessage(new Message(Severity.ERROR, MsgCode.DELETE_FAILURE));
		return badRequest(messageUtil.toJson());
	}

	public Result saveSupplier() {
		Http.RequestBody body = request().body();

		if (body.asJson() != null) {
			SupplierVo supplierVo = Json.fromJson(body.asJson(), SupplierVo.class);
			Message message = null;
			if (supplierVo.getId() == null) {
				supplierService.create(supplierVo);
				message = new Message(Severity.INFO, MsgCode.CREATE_SUCCESS);
			} else {
				supplierService.update(supplierVo);
				message = new Message(Severity.INFO, MsgCode.UPDATE_SUCCESS);
			}
			messageUtil.setMessage(message);
			return ok(messageUtil.toJson());

		}
		messageUtil.setMessage(new Message(Severity.ERROR, MsgCode.OPERATE_FAILURE));
		return badRequest(messageUtil.toJson());
	}
}
