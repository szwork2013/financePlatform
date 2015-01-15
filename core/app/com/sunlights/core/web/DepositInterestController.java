package com.sunlights.core.web;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.core.service.DepositInterestService;
import com.sunlights.core.service.impl.DepositInterestServiceImpl;
import play.Logger;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import java.math.BigDecimal;

/**
 * Created by Yuan on 2014/12/15.
 */
@Transactional
public class DepositInterestController extends Controller {

	private DepositInterestService depositInterestService = new DepositInterestServiceImpl();



	private MessageUtil messageUtil = MessageUtil.getInstance();

	public Result findCurrent() {
        Logger.info("[DepositInterestController1] + findCurrent");
        BigDecimal current = depositInterestService.findCurrent();
		messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), current);
		return ok(messageUtil.toJson());
	}
}
