package com.sunlights.op.web;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.utils.RequestUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.CustomerService;
import com.sunlights.op.service.impl.CustomerServiceImpl;
import com.sunlights.op.vo.BankCardVo;
import com.sunlights.op.vo.CustomerVo;
import com.sunlights.op.vo.FundTradeVo;
import com.sunlights.op.vo.statistics.ReferrerDetailVo;
import org.apache.commons.lang3.StringUtils;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;

/**
 * Created by yuan on 9/24/14.
 */
@Transactional
public class CustomerController extends Controller {

	private MessageUtil messageUtil = MessageUtil.getInstance();
	private CustomerService customerService = new CustomerServiceImpl();

	public Result findExchanges() {
		return play.mvc.Results.TODO;
	}

	public Result findBalance() {
		return play.mvc.Results.TODO;
	}

	public Result findFundTrades() {
		PageVo pageVo = new PageVo();
		Http.Request request = request();

		if (!StringUtils.isBlank(request.getHeader("params"))) {
			pageVo = RequestUtil.getHeaderValue("params", PageVo.class);
		}
		List<FundTradeVo> fundTradeVos = customerService.findFundTradeVos(pageVo);
		pageVo.setList(fundTradeVos);
		messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), pageVo);
		return ok(messageUtil.toJson());
	}

	public Result findReferrers() {
		PageVo pageVo = new PageVo();
		Http.Request request = request();

		if (!StringUtils.isBlank(request.getHeader("params"))) {
			pageVo = RequestUtil.getHeaderValue("params", PageVo.class);
		}
		List<ReferrerDetailVo> referrerDetails = customerService.findReferrerDetailsBy(pageVo);
		pageVo.setList(referrerDetails);
		messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), pageVo);
		return ok(messageUtil.toJson());
	}

	public Result findBankCardsBy() {
		PageVo pageVo = new PageVo();
		Http.Request request = request();

		if (!StringUtils.isBlank(request.getHeader("params"))) {
			pageVo = RequestUtil.getHeaderValue("params", PageVo.class);
		}

		List<BankCardVo> bankCards = customerService.findBankCardsBy(pageVo);
		pageVo.setList(bankCards);
		messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), pageVo);
		return ok(messageUtil.toJson());
	}

	public Result findCustomersBy() {

		PageVo pageVo = new PageVo();
		Http.Request request = request();

		if (!StringUtils.isBlank(request.getHeader("params"))) {
			pageVo = RequestUtil.getHeaderValue("params", PageVo.class);
		}

		Http.RequestBody body = request.body();

		if (body.asJson() != null) {
			pageVo = Json.fromJson(body.asJson(), PageVo.class);
		}

		List<CustomerVo> customerVos = customerService.findCustomersBy(pageVo);
		pageVo.setList(customerVos);

		return ok(Json.toJson(pageVo));
	}

	public Result saveCustomer() {
		CustomerVo customerVo = null;

		Http.RequestBody body = request().body();
		if (body.asJson() != null) {
			customerVo = Json.fromJson(body.asJson(), CustomerVo.class);
		}

		if (customerVo != null && customerVo.getId() != null) {
			customerService.saveCustomer(customerVo);
			messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS));
			return ok(messageUtil.toJson());
		}
		messageUtil.setMessage(new Message(Severity.ERROR, MsgCode.OPERATE_FAILURE));
		return badRequest(messageUtil.toJson());
	}

}
