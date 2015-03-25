package com.sunlights.common.utils;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.Test;

public class CommonUtilTest {

    @Test
    public void testStringToDate() throws Exception {
        Date date = CommonUtil.stringToDate("2014-11-12");
        assertEquals("1415721600000", date.getTime() + "");
        Timestamp smUpdateTime = new Timestamp(CommonUtil.stringToDate("2013-12-27 20:03:20.760", CommonUtil.DATE_FORMAT_ICU).getTime());
    }

    @Test
    public void testStringToDateTime() throws Exception {
		PropertyFilter ltd_beginDate = new PropertyFilter("LTD_beginDate", "2015-03-24T05:25:00.000Z");
		System.out.println(ltd_beginDate.getPropertyValue());
		PropertyFilter eqs_username = new PropertyFilter("EQS_username", "2015-03-24T05:25:00.000Z");
		System.out.println(eqs_username.getPropertyValue());
	}
}
