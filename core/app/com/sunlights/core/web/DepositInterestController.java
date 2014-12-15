package com.sunlights.core.web;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.vo.Message;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import com.sunlights.common.utils.MessageUtil;
import com.sunlights.core.service.DepositInterestService;
import com.sunlights.core.service.impl.DepositInterestServiceImpl;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2014/12/15.
 */
@Transactional
public class DepositInterestController extends Controller {
	private DepositInterestService depositInterestService = new DepositInterestServiceImpl();

	private MessageUtil messageUtil = MessageUtil.getInstance();

	public Result findCurrent() {
		BigDecimal current = depositInterestService.findCurrent();
		messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), current);
		return ok(messageUtil.toJson());
	}
}
