package models;

/**
 * <p>Project: financePlatform</p>
 * <p>Title: LoggingEventException.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class LoggingEventException {
    private Long eventId;
    private Long i;
    private String traceLine;

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }


    public Long getI() {
        return i;
    }

    public void setI(Long i) {
        this.i = i;
    }


    public String getTraceLine() {
        return traceLine;
    }

    public void setTraceLine(String traceLine) {
        this.traceLine = traceLine;
    }


}
