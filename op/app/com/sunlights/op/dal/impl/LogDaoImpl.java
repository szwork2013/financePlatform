package com.sunlights.op.dal.impl;


import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.op.dal.LogDao;
import models.LoggingEvent;
import models.LoggingEventException;
import models.LoggingEventProperty;

import java.util.List;

/**
 * do query in database
 * table: loggingEvent , loggingEventException , loggingEventProperty
 * Created by Administrator on 2014/10/17.
 */
public class LogDaoImpl extends EntityBaseDao implements LogDao {

    /**
     * fetch a set of loggingEvents
     * via start index & end index
     * @param start
     * @param end
     * @return logs<List.LoggingEvent>
     */
    @Override
    public List<LoggingEvent> findSubset (int start,int end){
        return findAll(LoggingEvent.class).subList(start,end) ;
    }

    /**
     * fetch a loggingEvent
     * according to eventId
     * @param eventId
     * @return lg <LoggingEvent>
     */
    @Override
    public LoggingEvent getLoggingEventByEventId (long eventId) {
        return findBy(LoggingEvent.class,"eventId",eventId).get(0) ;
    }

    /**
     * fetch LoggingEventExceptions
     * according to eventId
     * @param eventId
     * @return list <List.LoggingEventException>
     */
    @Override
    public List<LoggingEventException> getLoggingEventExceptionsByEventId(Long eventId){
        return findBy(LoggingEventException.class, "eventId", eventId) ;
    }

    /**
     * fetch LoggingEventProperties
     * according to eventId
     * @param eventId
     * @return list <List.LoggingEventProperty>
     */
    @Override
    public List<LoggingEventProperty> getLoggingEventPropertiesByEventId(Long eventId){
        return findBy(LoggingEventProperty.class,"eventId",eventId) ;
    }

    /**
     * count the number of loggingEvents
     * @return <int>
     */
    @Override
    public int loggingEventsNum() {
        return findAll(LoggingEvent.class).size();
    }

}
