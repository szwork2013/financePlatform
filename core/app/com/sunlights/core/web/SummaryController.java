package com.sunlights.core.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.utils.log.logback.ext.DateUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.core.service.SummaryService;
import com.sunlights.core.service.impl.SummaryServiceImpl;
import com.sunlights.core.vo.AgreementVo;
import com.sunlights.core.vo.SyncIncomeStatVo;
import models.SyncIncomeStat;
import org.apache.commons.lang3.Validate;
import org.json4s.jackson.Json;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Edward on 2015/5/8 0008.
 */

@play.db.jpa.Transactional
public class SummaryController extends Controller {

    SummaryService summaryService = new SummaryServiceImpl();

    private MessageUtil messageUtil = MessageUtil.getInstance();

    public Result getBatchCount() {
        String batchDate = request().getQueryString("batchDate");
        if (!DateUtil.isValidDate(batchDate)) {
            messageUtil.setMessage(new Message(Severity.FATAL, MsgCode.NOT_A_VALID_DATE));
        } else {
            messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), summaryService.getBatchCount(batchDate));
        }
        return ok(messageUtil.toJson());
    }

    public Result getBatchCountAll() {
        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), summaryService.getBatchCountAll());
        return ok(messageUtil.toJson());
    }

    public Result getTradeCustomer() {
        String batchNo = request().getQueryString("batchNo");
        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), summaryService.getTradedCust(batchNo));
        return ok(messageUtil.toJson());
    }

    public Result saveIncomeStat() {
        Http.RequestBody body = request().body();
        JsonNode json = request().body().asJson();
        ObjectMapper mapper = new ObjectMapper();
        try {
            SyncIncomeStat[] myObjects = mapper.readValue(json.get("parameters").textValue(),SyncIncomeStat[].class);
            summaryService.saveFundIncomes(Arrays.asList(myObjects));
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return ok(messageUtil.toJson());
    }
}
