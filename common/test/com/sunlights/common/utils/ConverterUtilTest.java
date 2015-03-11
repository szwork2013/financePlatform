package com.sunlights.common.utils;

import models.Fund;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

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

    @Test
    public void testCovertObjValue() throws Exception {

        Fund old = new Fund();
        old.setId(1L);
        old.setFundCode("0001");

        Map<String, String> describe = BeanUtils.describe(old);


        Fund fund = new Fund();
        for (String key : describe.keySet()) {
            String value = describe.get(key);
            if (StringUtils.isNotEmpty(value)) {
                BeanUtils.setProperty(fund, key, value);
            }
        }


        Assert.assertSame(old.getFundCode(), fund.getFundCode());

    }
}