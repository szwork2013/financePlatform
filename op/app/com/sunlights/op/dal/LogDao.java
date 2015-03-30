package com.sunlights.op.dal;


import models.LoggingEvent;
import models.LoggingEventException;
import models.LoggingEventProperty;

import java.util.List;

/**
 * Created by Administrator on 2014/10/24.
 */
public interface LogDao {

    public List<LoggingEvent> findSubset(int start, int end);

    public LoggingEvent getLoggingEventByEventId(long eventId);

    public List<LoggingEventException> getLoggingEventExceptionsByEventId(Long eventId);

    public List<LoggingEventProperty> getLoggingEventPropertiesByEventId(Long eventId);

    public int loggingEventsNum();

}
