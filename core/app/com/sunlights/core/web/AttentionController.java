package com.sunlights.core.web;

import com.sunlights.common.DictConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.core.service.AttentionService;
import com.sunlights.core.service.impl.ProductAttentionService;
import com.sunlights.core.vo.AttentionVo;
import com.sunlights.core.vo.ProductVo;
import com.sunlights.customer.service.impl.CustomerService;
import models.CustomerSession;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Project: financePlatform</p>
 * <p>Title: AttentionController.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@Transactional
public class AttentionController extends Controller {
	private Form<AttentionVo> attentionVoForm = Form.form(AttentionVo.class);
	private Form<PageVo> pageForm = Form.form(PageVo.class);
	private CustomerService customerService = new CustomerService();

	private AttentionService attentionService = new ProductAttentionService();
	private MessageUtil messageUtil = MessageUtil.getInstance();

	public Result createProductAttention() {
		Http.RequestBody body = request().body();
		List<AttentionVo> attentionVos = new ArrayList<AttentionVo>();
		if (body.asFormUrlEncoded() != null) {
			CustomerSession customerSession = customerService.validateCustomerSession(request(), session(), response());
			AttentionVo attentionVo = attentionVoForm.bindFromRequest().get();
			// 基金
			attentionVo.setProductType(DictConst.FP_PRODUCT_TYPE_1);
			attentionVo.setCustomerId(customerSession.getCustomerId());
			attentionVos.add(attentionVo);
		}
		attentionService.createAttentions(attentionVos);
		messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS));
		return ok(messageUtil.toJson());
	}

	public Result createProductAttentions() {
		Http.RequestBody body = request().body();
		List<AttentionVo> attentionVos = new ArrayList<AttentionVo>();
		if (body.asFormUrlEncoded() != null) {
			CustomerSession customerSession = customerService.validateCustomerSession(request(), session(), response());
			AttentionVo attentionVo = attentionVoForm.bindFromRequest().get();
			List<String> codes = attentionVo.getCodes();
			for (String code : codes) {
				AttentionVo av = new AttentionVo();
				av.setCode(code);
				// 基金
				av.setProductType(DictConst.FP_PRODUCT_TYPE_1);
				av.setCustomerId(customerSession.getCustomerId());
				attentionVos.add(av);
			}
		}
		attentionService.createAttentions(attentionVos);
		messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS));
		return ok(messageUtil.toJson());
	}

	public Result cancelProductAttention() {
		Http.RequestBody body = request().body();
		if (body.asFormUrlEncoded() != null) {
			AttentionVo attentionVo = attentionVoForm.bindFromRequest().get();
			CustomerSession customerSession = customerService.validateCustomerSession(request(), session(), response());
			attentionVo.setCustomerId(customerSession.getCustomerId());
			attentionService.cancelAttention(attentionVo);
			messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS));
			return ok(messageUtil.toJson());
		}
		messageUtil.setMessage(new Message(Severity.ERROR, MsgCode.OPERATE_FAILURE));
		return ok(messageUtil.toJson());
	}

	public Result findProductAttentions() {
		PageVo pageVo = new PageVo();
		Http.RequestBody body = request().body();
		List<String> codes = new ArrayList<String>();
		if (body.asFormUrlEncoded() != null) {
			AttentionVo attentionVo = attentionVoForm.bindFromRequest().get();
			List<String> list = attentionVo.getCodes();
			if (list != null) {
				for (int i = list.size() - 1; i >= 0; i--) {
					codes.add(list.get(i));
				}
			}
		}
		pageVo.put("codes", codes);
		List<ProductVo> productVos = attentionService.findAttentions(pageVo);
		pageVo.setList(productVos);
		messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), pageVo);
		return ok(messageUtil.toJson());
	}
}
