package models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Created by yuan on 9/26/14.
 */
@Entity
@Table(name = "M_LOGGING_EVENT")
public class LoggingEvent {
    /**
    * constructors
    * just delete never create for cur
    */
    public LoggingEvent() {
    }
    /**
    * data field
    */
    @Column(name="log_event_timestmp" ,length=10)
    private Long  timestmp;
    @Column(name="log_event_format_msg" ,length=10)
    private String formattedMessage;
//    @Column(length = 254)
    @Column(name="log_event_logger_name" ,length=10)
    private String loggerName;
//    @Column(length = 254)
    @Column(name="log_event_level" ,length=10)
    private String levelString;
//    @Column(length = 254)
    @Column(name="log_event_thread_name" ,length=10)
    private String threadName;
    @Column(name="log_event_ref_flag" ,length=10)
    private int referenceFlag;
//    @Column(length = 254)
    @Column(name="log_event_arg0" ,length=10)
    private String arg0;
//    @Column(length = 254)
    @Column(name="log_event_arg1" ,length=10)
    private String arg1;
//    @Column(length = 254)
    @Column(name="log_event_arg2" ,length=10)
    private String arg2;
//    @Column(length = 254)
    @Column(name="log_event_arg3" ,length=10)
    private String arg3;
//    @Column(length = 254)
    @Column(name="log_event_caller_filename" ,length=10)
    private String callerFilename;
//    @Column(length = 254)
    @Column(name="log_event_caller_class" ,length=10)
    private String callerClass;
//    @Column(length = 254)
    @Column(name="log_event_caller_method" ,length=10)
    private String callerMethod;
//    @Column(length = 4)
    @Column(name="log_event_caller_line" ,length=10)
    private String callerLine;
    @Id
    @Column(name="log_event_id")
    private Long eventId;
//
//    @Version
//    public long version;
    /**
    * functions
    * setter getter s
    * finder
    */
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

    public String getLevelString() {
        return levelString;
    }

    public void setLevelString(String levelString) {
        this.levelString = levelString;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public int getReferenceFlag() {
        return referenceFlag;
    }

    public void setReferenceFlag(int referenceFlag) {
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

}
