package com.sunlights.common.utils;

import models.Fund;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConverterUtilTest {

    @Test
    public void testCovertSameObjValue() throws Exception {

        Fund old = new Fund();
        old.setId(1L);
        old.setFundCode("0001");
        Fund newFund = new Fund();


        ConverterUtil.covertSameObjValue(old, newFund, Fund.class);

        Assert.assertSame(newFund.getFundCode(), old.getFundCode());

    }
}