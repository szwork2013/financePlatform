package com.sunlights.op.vo;

import com.sunlights.common.exceptions.ConverterException;
import com.sunlights.common.utils.ConverterUtil;
import models.LoggingEvent;
import models.LoggingEventException;
import models.LoggingEventProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Project: operationplatform</p>
 * <p>Title: LoggingEventVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class LoggingEventVo {
    private Long timestmp;
    private String formattedMessage;
    private String loggerName;
    private String levelString;
    private String threadName;
    private Integer referenceFlag;
    private String arg0;
    private String arg1;
    private String arg2;
    private String arg3;
    private String callerFilename;
    private String callerClass;
    private String callerMethod;
    private String callerLine;
    private Long eventId;

    private List<LoggingEventException> loggingEventExceptions = new ArrayList<LoggingEventException>();
    private List<LoggingEventProperty> loggingEventProperties = new ArrayList<LoggingEventProperty>();


    public LoggingEventVo() {
        super();
    }

    public LoggingEventVo(LoggingEvent loggingEvent) {
        inLoggingEvent(loggingEvent);
    }

    public void inLoggingEvent(LoggingEvent loggingEvent) {
        try {
            ConverterUtil.fromEntity(this, loggingEvent);
        } catch (ConverterException e) {
            e.printStackTrace();
        }
    }

    public Long getTimestmp() {
        return timestmp;
    }

    public void setTimestmp(Long timestmp) {
        this.timestmp = timestmp;
    }

    public String getFormattedMessage() {
        return formattedMessage;
    }

    public void setFormattedMessage(String formattedMessage) {
        this.formattedMessage = formattedMessage;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public String getLevelString() {
        return levelString;
    }

    public void setLevelString(String levelString) {
        this.levelString = levelString;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public Integer getReferenceFlag() {
        return referenceFlag;
    }

    public void setReferenceFlag(Integer referenceFlag) {
        this.referenceFlag = referenceFlag;
    }

    public String getArg0() {
        return arg0;
    }

    public void setArg0(String arg0) {
        this.arg0 = arg0;
    }

    public String getArg1() {
        return arg1;
    }

    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    public String getArg2() {
        return arg2;
    }

    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }

    public String getArg3() {
        return arg3;
    }

    public void setArg3(String arg3) {
        this.arg3 = arg3;
    }

    public String getCallerFilename() {
        return callerFilename;
    }

    public void setCallerFilename(String callerFilename) {
        this.callerFilename = callerFilename;
    }

    public String getCallerClass() {
        return callerClass;
    }

    public void setCallerClass(String callerClass) {
        this.callerClass = callerClass;
    }

    public String getCallerMethod() {
        return callerMethod;
    }

    public void setCallerMethod(String callerMethod) {
        this.callerMethod = callerMethod;
    }

    public String getCallerLine() {
        return callerLine;
    }

    public void setCallerLine(String callerLine) {
        this.callerLine = callerLine;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public List<LoggingEventException> getLoggingEventExceptions() {
        return loggingEventExceptions;
    }

    public void setLoggingEventExceptions(List<LoggingEventException> loggingEventExceptions) {
        this.loggingEventExceptions = loggingEventExceptions;
    }

    public List<LoggingEventProperty> getLoggingEventProperties() {
        return loggingEventProperties;
    }

    public void setLoggingEventProperties(List<LoggingEventProperty> loggingEventProperties) {
        this.loggingEventProperties = loggingEventProperties;
    }
}
