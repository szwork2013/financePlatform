package com.sunlights.op.web;


import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.impl.LogServiceImpl;
import com.sunlights.op.vo.LoggingEventExceptionVo;
import com.sunlights.op.vo.LoggingEventPropertyVo;
import com.sunlights.op.vo.LoggingEventVo;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;

/**
 * 处理页面交互的事务
 * 日志相关
 * Created by Administrator on 2014/10/17.
 */
@Transactional
public class LogController extends Controller {
    private MessageUtil messageUtil = MessageUtil.getInstance();
    private LogServiceImpl logService = new LogServiceImpl();

    public Result findLogs() {
        PageVo pageVo = new PageVo();
        Http.RequestBody body = request().body();

        if (body.asJson() != null) {
            pageVo = Json.fromJson(body.asJson(), PageVo.class);
        }

        List<LoggingEventVo> loggingEvents = logService.findLoggingEvents(pageVo);
        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), loggingEvents);
        return ok(messageUtil.toJson());
    }

    public Result findLogProperties() {
        PageVo pageVo = new PageVo();
        Http.RequestBody body = request().body();

        if (body.asJson() != null) {
            pageVo = Json.fromJson(body.asJson(), PageVo.class);
        }

        List<LoggingEventPropertyVo> properties = logService.findLoggingEventPropertiesBy(pageVo);
        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), properties);
        return ok(messageUtil.toJson());
    }

    public Result findLogExceptions() {
        PageVo pageVo = new PageVo();
        Http.RequestBody body = request().body();

        if (body.asJson() != null) {
            pageVo = Json.fromJson(body.asJson(), PageVo.class);
        }

        List<LoggingEventExceptionVo> exceptions = logService.findLoggingEventExceptionsBy(pageVo);
        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), exceptions);
        return ok(messageUtil.toJson());
    }

}
