package com.sunlights.common.utils;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class CommonUtilTest {

    @Test
    public void testStringToDate() throws Exception {
        Date date = CommonUtil.stringToDate("2014-11-12");
        assertEquals("1415721600000", date.getTime() + "");
        Timestamp smUpdateTime = new Timestamp(CommonUtil.stringToDate("2013-12-27 20:03:20.760", CommonUtil.DATE_FORMAT_ICU).getTime());
    }
}