package com.sunlights.core.web;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.core.service.SummaryService;
import com.sunlights.core.service.impl.SummaryServiceImpl;
import org.apache.commons.lang3.Validate;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by Edward on 2015/5/8 0008.
 */
@play.db.jpa.Transactional
public class SummaryController extends Controller {

    SummaryService summaryService = new SummaryServiceImpl();
    private MessageUtil messageUtil = MessageUtil.getInstance();

    public Result getBatchCount() {
        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), summaryService.getBatchCount(""));
        return ok(messageUtil.toJson());
    }

    public Result getTradeCustomer() {
        String batchNo = request().getQueryString("batchNo");
        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), summaryService.getTradedCust("",batchNo));
        return ok(messageUtil.toJson());
    }
}
