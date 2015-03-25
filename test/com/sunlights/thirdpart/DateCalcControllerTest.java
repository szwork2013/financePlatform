package com.sunlights.thirdpart;

import com.sunlights.DBUnitBasedTest;
import com.sunlights.common.vo.MessageVo;
import junit.framework.Assert;
import org.junit.Test;
import play.Logger;
import play.libs.Json;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: DateCalcServiceTest.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class DateCalcControllerTest extends DBUnitBasedTest{

    @Override
    public void rollback() {

    }

    @Test
    public void testGetEndDate()  {
        Long start = System.currentTimeMillis();

        checkEndDate("2015-02-11 15:59:59", 2, "2015-02-16");

        Long end = System.currentTimeMillis();
        Logger.info("" + (end - start));

        checkEndDate("2015-02-12 15:59:59", 2, "2015-02-17");

        Long end2 = System.currentTimeMillis();
        Logger.info("" + (end2 - end));

        checkEndDate("2015-02-13 14:59:59", 1, "2015-02-16");

        Long end3 = System.currentTimeMillis();
        Logger.info("" + (end3 - end2));

        checkEndDate("2015-02-13 14:59:59", 2, "2015-02-17");

        Long end4 = System.currentTimeMillis();
        Logger.info("" + (end4 - end3));

        checkEndDate("2015-02-13 15:00:01", 1, "2015-02-17");
        checkEndDate("2015-02-13 15:00:01", 2, "2015-02-25");

        checkEndDate("2015-02-26 14:00:01", 1, "2015-02-27");
        checkEndDate("2015-02-26 15:00:01", 2, "2015-03-03");

        checkEndDate("2015-03-09 10:00:01", 1, "2015-03-10");
        checkEndDate("2015-03-09 20:00:01", 2, "2015-03-12");

        checkEndDate("2015-09-30 06:00:01", 1, "2015-10-08");
        checkEndDate("2015-09-30 20:00:01", 1, "2015-10-09");

        checkEndDate("2015-10-02 06:00:01", 1, "2015-10-09");

        checkEndDate("2015-03-25 16:00:01", 2, "2015-03-30");

    }



    private void checkEndDate(String startDateString, int workingDays, String expectedEndDateStr) {
        final Map<String, String> formParams = new HashMap<>();
        formParams.put("startDate", startDateString);
        formParams.put("durationDays", workingDays + "");

        String result = getResult("/thirdpart/datecalc", formParams);

        MessageVo messageVo = Json.fromJson(Json.parse(result), MessageVo.class);
        Assert.assertEquals(messageVo.getValue(), expectedEndDateStr);
    }

}
