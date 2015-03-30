package com.sunlights.op.web;

import com.sunlights.common.utils.RequestUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.CustomerService;
import com.sunlights.op.service.impl.CustomerServiceImpl;
import com.sunlights.op.vo.CustomerVo;
import org.apache.commons.lang3.StringUtils;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;

/**
 * Created by yuan on 9/24/14.
 */
public class CustomerController extends Controller {

	private CustomerService customerService = new CustomerServiceImpl();

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

	public Result unlockCustomer() {
		CustomerVo customerVo = null;

		Http.RequestBody body = request().body();
		if (body.asJson() != null) {
			customerVo = Json.fromJson(body.asJson(), CustomerVo.class);
		}

		if (customerVo != null && customerVo.getId() != null) {
			customerService.unlock(customerVo.getId());
			return ok("解锁成功。");
		}
		return ok("解锁失败。");
	}

}
