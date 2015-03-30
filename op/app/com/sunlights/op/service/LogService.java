package com.sunlights.op.service;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.vo.LoggingEventExceptionVo;
import com.sunlights.op.vo.LoggingEventPropertyVo;
import com.sunlights.op.vo.LoggingEventVo;

import java.util.List;

/**
 * Created by Administrator on 2014/10/24.
 */
public interface LogService {

    public List<LoggingEventVo> findLoggingEvents(PageVo pageVo);

    public List<LoggingEventPropertyVo> findLoggingEventPropertiesBy(PageVo pageVo);

    public List<LoggingEventExceptionVo> findLoggingEventExceptionsBy(PageVo pageVo);

    public LoggingEventVo findLoggingEventDetail(LoggingEventVo loggingEventVo);


}
