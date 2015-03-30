package com.sunlights.op.web;

import com.sunlights.common.utils.RequestUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.CustomerVerifyCodeService;
import com.sunlights.op.service.impl.CustomerVerifyCodeServiceImpl;
import com.sunlights.op.vo.VerifyCodeVo;
import org.apache.commons.lang3.StringUtils;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;

/**
 * <p>Project: op</p>
 * <p>Title: WebCustomerVerifyCodeService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@Transactional
public class CustomerVerifyController extends BaseApiController {

    private CustomerVerifyCodeService customerVerifyCodeService = new CustomerVerifyCodeServiceImpl();

    public Result findCustomerVerifyCodes() {
        PageVo pageVo = new PageVo();
		Http.Request request = request();

		if (!StringUtils.isBlank(request.getHeader("params"))) {
			pageVo = RequestUtil.getHeaderValue("params", PageVo.class);
		}

		Http.RequestBody body = request.body();

        if (body.asJson() != null) {
            pageVo = Json.fromJson(body.asJson(), PageVo.class);
        }

        List<VerifyCodeVo> verifyCodes = customerVerifyCodeService.findCustomerVerifyCodes(pageVo);
        pageVo.setList(verifyCodes);
        return JsonResponse(pageVo);
    }
}
