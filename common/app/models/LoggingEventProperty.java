package models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by yuan on 9/26/14.
 */
@Entity
@Table(name = "M_LOGGING_EVENT_PROPERTY")
public class LoggingEventProperty  {

    /**
    * constructor
    * just read & delete
    */
    public LoggingEventProperty() {
    }

    /**
    * data field
    */
     @Id
    @Column(name="log_event_id" ,length=10)
    private Long eventId;
//    @Column(length = 254)
    @Column(name="log_mapped_key" ,length=10)
    private String mappedKey;
//    @Column(length = 1024)
    @Column(name="log_mapped_value" ,length=10)
    private String mapped_value;

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

    public String getMappedKey() {
        return mappedKey;
    }

    public void setMappedKey(String mappedKey) {
        this.mappedKey = mappedKey;
    }

    public String getMapped_value() {
        return mapped_value;
    }

    public void setMapped_value(String mapped_value) {
        this.mapped_value = mapped_value;
    }

}
