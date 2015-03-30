package com.sunlights.op.vo;

import models.LoggingEventException;

/**
 * <p>Project: operationPlatform</p>
 * <p>Title: LoggingEventExceptionVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class LoggingEventExceptionVo extends LoggingEventException {

    public LoggingEventExceptionVo() {
        super();
    }

    public LoggingEventExceptionVo(Object[] row) {
        setEventId(row[0] == null ? null : Long.valueOf(row[0].toString()));
        setI(row[1] == null ? null : Long.valueOf(row[1].toString()));
        setTraceLine(row[2] == null ? null : row[2].toString());
    }
}
