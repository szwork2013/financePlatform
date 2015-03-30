package com.sunlights.op.web;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.utils.RequestUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.DepositInterestService;
import com.sunlights.op.service.impl.DepositInterestServiceImpl;
import com.sunlights.op.vo.DepositInterestVo;
import org.apache.commons.lang3.StringUtils;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;

/**
 * <p>Project: OperationPlatform</p>
 * <p>Title: DepositInterestController.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@Transactional
public class DepositInterestController extends Controller{

    private MessageUtil messageUtil = MessageUtil.getInstance();

    private DepositInterestService depositInterestService = new DepositInterestServiceImpl();

    public Result findDepositInterests() {
        PageVo pageVo = new PageVo();
		Http.Request request = request();

		if (!StringUtils.isBlank(request.getHeader("params"))) {
			pageVo = RequestUtil.getHeaderValue("params", PageVo.class);
		}

		Http.RequestBody body = request.body();

        if (body.asJson() != null) {
            pageVo = Json.fromJson(body.asJson(), PageVo.class);
        }

        List<DepositInterestVo> depositInterestVos = depositInterestService.findDepositInterests(pageVo);
        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), depositInterestVos);
        return ok(messageUtil.toJson());
    }

    public Result deleteDepositInterest() {
		DepositInterestVo depositInterestVo = null;

		Http.Request request = request();
		if (request.queryString() != null) {
			depositInterestVo = RequestUtil.fromQueryString(request.queryString(), DepositInterestVo.class);
		}

		Http.RequestBody body = request.body();

		if (body.asJson() != null) {
            depositInterestVo = Json.fromJson(body.asJson(), DepositInterestVo.class);
        }

		if(depositInterestVo != null ) {
			depositInterestService.delete(depositInterestVo);
			messageUtil.setMessage(new Message(Severity.INFO, MsgCode.DELETE_SUCCESS));
			return ok(messageUtil.toJson());
		}
        messageUtil.setMessage(new Message(Severity.ERROR, MsgCode.DELETE_FAILURE));
        return badRequest(messageUtil.toJson());
    }

    public Result saveDepositInterest() {
        Http.RequestBody body = request().body();

        if (body.asJson() != null) {
            DepositInterestVo depositInterestVo = Json.fromJson(body.asJson(), DepositInterestVo.class);
            if (depositInterestVo.getId() == null) {
                depositInterestService.create(depositInterestVo);
            } else {
                depositInterestService.update(depositInterestVo);
            }
            messageUtil.setMessage(new Message(Severity.INFO, MsgCode.UPDATE_SUCCESS));
            return ok(messageUtil.toJson());

        }
        messageUtil.setMessage(new Message(Severity.ERROR, MsgCode.OPERATE_FAILURE));
        return badRequest(messageUtil.toJson());
    }
}
