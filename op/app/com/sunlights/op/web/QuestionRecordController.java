package com.sunlights.op.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.exceptions.ConverterException;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.utils.RequestUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.QuestionRecordService;
import com.sunlights.op.service.impl.QuestionRecordServiceImpl;
import com.sunlights.op.vo.QuestionRecordVo;
import org.apache.commons.lang3.StringUtils;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;

/**
 * <p>Project: operationplatform</p>
 * <p>Title: QuestionRecordController.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Transactional
public class QuestionRecordController extends Controller{

    private QuestionRecordService questionRecordService = new QuestionRecordServiceImpl();

    public Result findQuestionRecords(){
        PageVo pageVo = new PageVo();
		Http.Request request = request();
		if (!StringUtils.isBlank(request.getHeader("params"))) {
			pageVo = RequestUtil.getHeaderValue("params", PageVo.class);
		}

		Http.RequestBody body = request.body();

        if (body.asJson() != null) {
            pageVo = Json.fromJson(body.asJson(), PageVo.class);
        }

        List<QuestionRecordVo> questionRecords = null;
        try {
            questionRecords = questionRecordService.findQuestionRecords(pageVo);
			pageVo.setList(questionRecords);
        } catch (ConverterException e) {
            e.printStackTrace();
            JsonNode json = MessageUtil.getInstance().msgToJson(new Message(Severity.ERROR, MsgCode.OPERATE_FAILURE));
            return badRequest(json);
        }

        JsonNode json = MessageUtil.getInstance().msgToJson(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), pageVo);
        return ok(json);
    }

    public Result saveQuestionRecord(){
        Http.RequestBody body = request().body();

        Message message = null;
        if (body.asJson() != null) {
            QuestionRecordVo questionRecordVo = Json.fromJson(body.asJson(), QuestionRecordVo.class);
            try {
                if (questionRecordVo.getId() == null) {
                    questionRecordService.createQuestionRecord(questionRecordVo);
                    message = new Message(Severity.INFO, MsgCode.CREATE_SUCCESS);
                } else {
                    questionRecordService.updateQuestionRecord(questionRecordVo);
                    message = new Message(Severity.INFO, MsgCode.UPDATE_SUCCESS);
                }
            } catch (ConverterException e) {
                e.printStackTrace();
                JsonNode json = MessageUtil.getInstance().msgToJson(new Message(Severity.ERROR, MsgCode.OPERATE_FAILURE));
                return badRequest(json);
            }
        }
        return ok(MessageUtil.getInstance().msgToJson(message));
    }

    public Result deleteQuestionRecord(){
		QuestionRecordVo questionRecordVo = null;
		Http.Request request = request();
		if (request.queryString() != null) {
			questionRecordVo = RequestUtil.fromQueryString(request.queryString(), QuestionRecordVo.class);
		}

		Http.RequestBody body = request.body();

        Message message = null;
        if (body.asJson() != null) {
            questionRecordVo = Json.fromJson(body.asJson(), QuestionRecordVo.class);
        }
		if(questionRecordVo != null) {
			questionRecordService.deleteQuestionRecord(questionRecordVo);
			message = new Message(Severity.INFO, MsgCode.DELETE_SUCCESS);
		} else{
			message = new Message(Severity.ERROR, MsgCode.DELETE_FAILURE);
		}
        return ok(MessageUtil.getInstance().msgToJson(message));
    }
}
