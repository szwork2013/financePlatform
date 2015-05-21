package com.sunlights.core.web;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.utils.log.logback.ext.DateUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.core.service.SummaryService;
import com.sunlights.core.service.impl.SummaryServiceImpl;
import models.SyncBatchLog;
import models.SyncIncomeStat;
import models.SyncTrade;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.Arrays;

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
        try {
            SyncIncomeStat[] myObjects = Json.fromJson(body.asJson(), SyncIncomeStat[].class);
            summaryService.saveFundIncomes(Arrays.asList(myObjects));
        }catch (Exception ex){
            ex.printStackTrace();
            SyncIncomeStat myObject = Json.fromJson(body.asJson(), SyncIncomeStat.class);
            SyncIncomeStat[] myObjects = new SyncIncomeStat[]{myObject};
            summaryService.saveFundIncomes(Arrays.asList(myObjects));
        }

        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS));
        return ok(messageUtil.toJson());
    }

    public Result saveTradeRecords() {
        Http.RequestBody body = request().body();
        try {
            //SyncIncomeStat need to be change into vo.
            SyncTrade[] myObjects = Json.fromJson(body.asJson(), SyncTrade[].class);
            summaryService.saveSyncTrade(Arrays.asList(myObjects));
        }catch (Exception ex){
            SyncTrade myObject = Json.fromJson(body.asJson(), SyncTrade.class);
            SyncTrade[] myObjects = new SyncTrade[]{myObject};
            summaryService.saveSyncTrade(Arrays.asList(myObjects));
        }
        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS));
        return ok(messageUtil.toJson());
    }

    public Result saveBatchLog() {
        Http.RequestBody body = request().body();
        try {
            SyncBatchLog myObject = Json.fromJson(body.asJson(), SyncBatchLog.class);
            summaryService.saveBatchLog(myObject);
        }catch(Exception ex){
            messageUtil.setMessage(new Message(Severity.ERROR, MsgCode.OPERATE_FAILURE));
        }

        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS));
        return ok(messageUtil.toJson());
    }


    public Result isTaskFinished() {
        String taskName = request().getQueryString("taskName");
        String date = request().getQueryString("endDate");
        if (!DateUtil.isValidDate(date)) {
            messageUtil.setMessage(new Message(Severity.FATAL, MsgCode.NOT_A_VALID_DATE));
        }
        if(taskName==null||taskName.equals("")){
            messageUtil.setMessage(new Message(Severity.FATAL, MsgCode.TASK_NAME_EMPTY));
        }
        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), summaryService.isTaskFinished(taskName,date));
        return ok(messageUtil.toJson());
    }

}
