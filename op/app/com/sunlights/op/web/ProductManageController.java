package com.sunlights.op.web;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.utils.RequestUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.ProductManageService;
import com.sunlights.op.service.impl.ProductManageServiceImpl;
import com.sunlights.op.vo.CodeVo;
import com.sunlights.op.vo.ProductManageVo;
import org.apache.commons.lang3.StringUtils;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;

/**
 * <p>Project: OperationPlatform</p>
 * <p>Title: ProductManageController.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@Transactional
public class ProductManageController extends Controller {
	private MessageUtil messageUtil = MessageUtil.getInstance();

	private ProductManageService productManageService = new ProductManageServiceImpl();

	public Result grabFundProfits() {
		Http.RequestBody body = request().body();

		if (body.asJson() != null) {
			ProductManageVo productManageVo = Json.fromJson(body.asJson(), ProductManageVo.class);
			productManageService.grabProfitHistoryByCode(productManageVo.getProductCode());
			messageUtil.setMessage(new Message(Severity.INFO, MsgCode.CREATE_SUCCESS));
			return ok(messageUtil.toJson());
		}

		messageUtil.setMessage(new Message(Severity.ERROR, MsgCode.CREATE_FAILURE));
		return badRequest(messageUtil.toJson());
	}


	public Result grabAllFundProfits() {
		productManageService.grabAllProfitHistory();
		messageUtil.setMessage(new Message(Severity.INFO, MsgCode.CREATE_SUCCESS));
		return ok(messageUtil.toJson());

	}

	public Result findCodes() {
		PageVo pageVo = new PageVo();
		Http.Request request = request();
		if (!StringUtils.isBlank(request.getHeader("params"))) {
			pageVo = RequestUtil.getHeaderValue("params", PageVo.class);
		}

		Http.RequestBody body = request.body();

		if (body.asJson() != null) {
			pageVo = Json.fromJson(body.asJson(), PageVo.class);
		}

		List<CodeVo> codes = productManageService.findCodes(pageVo);
		messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), codes);
		return ok(messageUtil.toJson());
	}

	public Result findProductManages() {
		PageVo pageVo = new PageVo();

		Http.Request request = request();
		if (!StringUtils.isBlank(request.getHeader("params"))) {
			pageVo = RequestUtil.getHeaderValue("params", PageVo.class);
		}

		Http.RequestBody body = request.body();

		if (body.asJson() != null) {
			pageVo = Json.fromJson(body.asJson(), PageVo.class);
		}

		List<ProductManageVo> productManageVos = productManageService.findProductManagesBy(pageVo);
		pageVo.setList(productManageVos);
		messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), pageVo);
		return ok(messageUtil.toJson());
	}


	public Result deleteProductManage() {
		ProductManageVo productManageVo = null;
		Http.Request request = request();
		if (request.queryString() != null) {
			productManageVo = RequestUtil.fromQueryString(request.queryString(), ProductManageVo.class);
		}

		Http.RequestBody body = request.body();

		if (body.asJson() != null) {
			productManageVo = Json.fromJson(body.asJson(), ProductManageVo.class);
		}

		if(productManageVo != null) {
			productManageService.delete(productManageVo);
			messageUtil.setMessage(new Message(Severity.INFO, MsgCode.DELETE_SUCCESS));
			return ok(messageUtil.toJson());
		}
		messageUtil.setMessage(new Message(Severity.ERROR, MsgCode.DELETE_FAILURE));
		return badRequest(messageUtil.toJson());
	}


	public Result saveProductManage() {
		Http.RequestBody body = request().body();

		if (body.asJson() != null) {
			ProductManageVo productManageVo = Json.fromJson(body.asJson(), ProductManageVo.class);
			Message message = null;
			if (productManageVo.getId() == null) {
				productManageService.create(productManageVo);
				message = new Message(Severity.INFO, MsgCode.CREATE_SUCCESS);
			} else {
				productManageService.update(productManageVo);
				message = new Message(Severity.INFO, MsgCode.UPDATE_SUCCESS);
			}
			messageUtil.setMessage(message);
			return ok(messageUtil.toJson());

		}
		messageUtil.setMessage(new Message(Severity.ERROR, MsgCode.OPERATE_FAILURE));
		return badRequest(messageUtil.toJson());
	}

}
