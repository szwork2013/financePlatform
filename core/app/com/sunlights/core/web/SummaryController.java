package com.sunlights.core.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.utils.log.logback.ext.DateUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.core.service.SummaryService;
import com.sunlights.core.service.impl.SummaryServiceImpl;
import com.sunlights.core.vo.BatchVo;
import models.SyncBatchLog;
import models.SyncIncomeStat;
import models.SyncTrade;
import org.hibernate.engine.jdbc.batch.spi.Batch;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Edward on 2015/5/8 0008.
 */

@play.db.jpa.Transactional
public class SummaryController extends Controller {

    SummaryService summaryService = new SummaryServiceImpl();

    private MessageUtil messageUtil = MessageUtil.getInstance();

    public Result getBatchCount() {
        String batchDate = request().getQueryString("batchDate");
        List<String> results = summaryService.getBatchCount(batchDate);

        if (!DateUtil.isValidDate(batchDate)) {
            messageUtil.setMessage(new Message(Severity.FATAL, MsgCode.NOT_A_VALID_DATE));
        } else {
            messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), fillVos(results));
        }
        return ok(messageUtil.toJson());
    }

    public Result getBatchCountAll() {
        List<String> results = summaryService.getBatchCountAll();
        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS),fillVos(results));
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
            //SyncIncomeStat need to be change into vo.
            SyncIncomeStat[] myObjects = mapper.readValue(json.get("parameters").textValue(),SyncIncomeStat[].class);
            summaryService.saveFundIncomes(Arrays.asList(myObjects));
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return ok(messageUtil.toJson());
    }

    public Result saveTradeRecords() {
        Http.RequestBody body = request().body();
        JsonNode json = request().body().asJson();
        ObjectMapper mapper = new ObjectMapper();
        try {
            //SyncIncomeStat need to be change into vo.
            SyncTrade[] myObjects = mapper.readValue(json.get("parameters").textValue(),SyncTrade[].class);
            summaryService.saveSyncTrade(Arrays.asList(myObjects));
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return ok(messageUtil.toJson());
    }

    public Result saveBatchLog() {
        Http.RequestBody body = request().body();
        JsonNode json = request().body().asJson();
        ObjectMapper mapper = new ObjectMapper();
        try {
            //SyncBatchLog need to be change into vo.
            SyncBatchLog myObjects = mapper.readValue(json.get("parameters").textValue(),SyncBatchLog.class);
            summaryService.saveBatchLog(myObjects);
        }catch (Exception ex){
            ex.printStackTrace();
        }
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
        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), summaryService.isTaskFinished(date,taskName));
        return ok(messageUtil.toJson());
    }

    private List<BatchVo> fillVos(List<String> results){
        List<BatchVo> vos =new ArrayList<BatchVo>();
        for(String s:results){
            BatchVo vo = new BatchVo();
            vo.setBatchNo(s);
            vos.add(vo);
        }
        return vos;
    }

}
