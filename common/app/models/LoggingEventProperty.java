package models;

/**
 * <p>Project: financePlatform</p>
 * <p>Title: LoggingEventProperty.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class LoggingEventProperty {
    private Long eventId;
    private String mappedKey;
    private String mappedValue;


    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }


    public String getMappedKey() {
        return mappedKey;
    }

    public void setMappedKey(String mappedKey) {
        this.mappedKey = mappedKey;
    }

    public String getMappedValue() {
        return mappedValue;
    }

    public void setMappedValue(String mappedValue) {
        this.mappedValue = mappedValue;
    }

}
