package com.sunlights.op.vo;

import models.LoggingEventProperty;

/**
 * <p>Project: operationPlatform</p>
 * <p>Title: LoggingEventPropertyVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class LoggingEventPropertyVo extends LoggingEventProperty {
    public LoggingEventPropertyVo() {
        super();
    }

    public LoggingEventPropertyVo(Object[] row) {
        setEventId(row[0] == null ? null : Long.valueOf(row[0].toString()));
        setMappedKey(row[1] == null ? null : row[1].toString());
        setMappedValue(row[2] == null ? null : row[2].toString());
    }
}
