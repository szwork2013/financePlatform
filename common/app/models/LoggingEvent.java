package models;

import javax.persistence.*;

/**
 * <p>Project: financePlatform</p>
 * <p>Title: LoggingEvent.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@Entity
@Table(name = "logging_event", schema = "public")
public class LoggingEvent {
    private Long timestmp;
    private String formattedMessage;
    private String loggerName;
    private String levelString;
    private String threadName;
    private Long referenceFlag;
    private String arg0;
    private String arg1;
    private String arg2;
    private String arg3;
    private String callerFilename;
    private String callerClass;
    private String callerMethod;
    private String callerLine;
    private Long eventId;

    @Basic
    @Column(name = "timestmp")
    public Long getTimestmp() {
        return timestmp;
    }

    public void setTimestmp(Long timestmp) {
        this.timestmp = timestmp;
    }

    @Basic
    @Column(name = "formatted_message")
    public String getFormattedMessage() {
        return formattedMessage;
    }

    public void setFormattedMessage(String formattedMessage) {
        this.formattedMessage = formattedMessage;
    }

    @Basic
    @Column(name = "logger_name")
    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    @Basic
    @Column(name = "level_string")
    public String getLevelString() {
        return levelString;
    }

    public void setLevelString(String levelString) {
        this.levelString = levelString;
    }

    @Basic
    @Column(name = "thread_name")
    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    @Basic
    @Column(name = "reference_flag")
    public Long getReferenceFlag() {
        return referenceFlag;
    }

    public void setReferenceFlag(Long referenceFlag) {
        this.referenceFlag = referenceFlag;
    }

    @Basic
    @Column(name = "arg0")
    public String getArg0() {
        return arg0;
    }

    public void setArg0(String arg0) {
        this.arg0 = arg0;
    }

    @Basic
    @Column(name = "arg1")
    public String getArg1() {
        return arg1;
    }

    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    @Basic
    @Column(name = "arg2")
    public String getArg2() {
        return arg2;
    }

    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }

    @Basic
    @Column(name = "arg3")
    public String getArg3() {
        return arg3;
    }

    public void setArg3(String arg3) {
        this.arg3 = arg3;
    }

    @Basic
    @Column(name = "caller_filename")
    public String getCallerFilename() {
        return callerFilename;
    }

    public void setCallerFilename(String callerFilename) {
        this.callerFilename = callerFilename;
    }

    @Basic
    @Column(name = "caller_class")
    public String getCallerClass() {
        return callerClass;
    }

    public void setCallerClass(String callerClass) {
        this.callerClass = callerClass;
    }

    @Basic
    @Column(name = "caller_method")
    public String getCallerMethod() {
        return callerMethod;
    }

    public void setCallerMethod(String callerMethod) {
        this.callerMethod = callerMethod;
    }

    @Basic
    @Column(name = "caller_line")
    public String getCallerLine() {
        return callerLine;
    }

    public void setCallerLine(String callerLine) {
        this.callerLine = callerLine;
    }

    @Id
    @Column(name = "event_id")
    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }


}
