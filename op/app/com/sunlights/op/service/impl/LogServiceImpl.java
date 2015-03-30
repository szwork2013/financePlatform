package com.sunlights.op.service.impl;


import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.service.PageService;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.LogService;
import com.sunlights.op.vo.LoggingEventExceptionVo;
import com.sunlights.op.vo.LoggingEventPropertyVo;
import com.sunlights.op.vo.LoggingEventVo;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * log related services
 * data query entrance mainly
 * Created by Administrator on 2014/10/16.
 */
public class LogServiceImpl extends EntityBaseDao implements LogService {

    private PageService pageService = new PageService();


    @Override
    public List<LoggingEventVo> findLoggingEvents(PageVo pageVo) {
        StringBuilder xsql = new StringBuilder();

        xsql.append(" select new com.sunlights.op.vo.LoggingEventVo(l) from LoggingEvent l");
        xsql.append(" where 1=1");
        xsql.append(" /~ and l.levelString = {levelString} ~/ ");
        xsql.append(" /~ and l.callerClass like {callerClass} ~/ ");
        xsql.append(" /~ and l.callerMethod like {callerMethod} ~/ ");
        xsql.append(" /~ and l.formattedMessage like {formattedMessage} ~/ ");

        List resultList = super.createQueryByMap(xsql.toString().replace("new vo.LoggingEventVo(l)", "count(l)"), pageVo.getFilter()).getResultList();
        pageVo.setCount(Integer.valueOf(resultList.get(0) + ""));

        xsql.append(" order by l.timestmp desc");

        Query query = super.createQueryByMap(xsql.toString(), pageVo.getFilter());
        query.setFirstResult(pageVo.getIndex());
        query.setMaxResults(pageVo.getPageSize());
        List<LoggingEventVo> loggingEventVos = query.getResultList();

        return loggingEventVos;
    }

    @Override
    public List<LoggingEventPropertyVo> findLoggingEventPropertiesBy(PageVo pageVo) {
        StringBuilder xsql = new StringBuilder();
        xsql.append(" select p.event_id");
        xsql.append(" ,p.mapped_key");
        xsql.append(" ,p.mapped_value");

        StringBuilder from = new StringBuilder();
        from.append(" from logging_event_property p");
        from.append(" where 1=1");
        from.append(" /~ and p.event_id = {eventId} ~/ ");
        Query nativeQuery = super.createNativeQueryByMap(xsql.append(from.toString()).toString(), pageVo.getFilter());
        nativeQuery.setFirstResult(pageVo.getIndex());
        nativeQuery.setMaxResults(pageVo.getPageSize());
        List<Object[]> rows = nativeQuery.getResultList();
        List<LoggingEventPropertyVo> loggingEventProperties = new ArrayList<LoggingEventPropertyVo>();
        for (Object[] row : rows) {
            loggingEventProperties.add(new LoggingEventPropertyVo(row));
        }
        List resultList = super.createNativeQueryByMap("select count(1) ".concat(from.toString()), pageVo.getFilter()).getResultList();
        pageVo.setCount(Integer.valueOf(resultList.get(0) + ""));
        return loggingEventProperties;
    }

    @Override
    public List<LoggingEventExceptionVo> findLoggingEventExceptionsBy(PageVo pageVo) {
        StringBuilder xsql = new StringBuilder();
        xsql.append(" select e.event_id");
        xsql.append(" ,e.i");
        xsql.append(" ,e.trace_line");

        StringBuilder from = new StringBuilder();
        from.append(" from logging_event_exception e");
        from.append(" where 1=1");
        from.append(" /~ and e.event_id = {eventId} ~/ ");

        Query nativeQuery = super.createNativeQueryByMap(xsql.append(from.toString()).toString(), pageVo.getFilter());
        nativeQuery.setFirstResult(pageVo.getIndex());
        nativeQuery.setMaxResults(pageVo.getPageSize());
        List<Object[]> rows = nativeQuery.getResultList();
        List<LoggingEventExceptionVo> loggingEventExceptions = new ArrayList<LoggingEventExceptionVo>();
        for (Object[] row : rows) {
            loggingEventExceptions.add(new LoggingEventExceptionVo(row));
        }
        List resultList = super.createNativeQueryByMap("select count(1) ".concat(from.toString()), pageVo.getFilter()).getResultList();
        pageVo.setCount(Integer.valueOf(resultList.get(0) + ""));
        return loggingEventExceptions;
    }

    @Override
    public LoggingEventVo findLoggingEventDetail(LoggingEventVo loggingEventVo) {
        return null;
    }
}
