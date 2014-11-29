package com.sunlights.common.utils;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class CommonUtilTest {

    @Test
    public void testStringToDate() throws Exception {
        Date date = CommonUtil.stringToDate("2014-11-12");
        assertEquals("1415721600000", date.getTime()+"");
    }
}