package models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by yuan on 9/26/14.
 */
@Entity
@Table(name = "M_LOGGING_EVENT_EXCEPTION")
public class LoggingEventException  {

    /**
    * constructor
    * just read and delete
    */
    public LoggingEventException() {
    }

    /**
    * data field
    */
    @Id
    @Column(name="log_event_id" ,length=10)
    private Long eventId;
    @Column(name="log_event_i" ,length=10)
    private int i;
//    @Column(length = 254)
    @Column(name="log_event_trace_line" ,length=10)
    private String traceLine;
    /**
    * function
    * getter setter
    * finder
    */
    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getTraceLine() {
        return traceLine;
    }

    public void setTraceLine(String traceLine) {
        this.traceLine = traceLine;
    }

}
