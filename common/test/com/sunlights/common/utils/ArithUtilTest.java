package com.sunlights.common.utils;

import org.junit.Test;
import play.libs.Json;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ArithUtilTest {

    @Test
    public void testDiv() throws Exception {
        BigDecimal b1 = new BigDecimal("19000000000");
        BigDecimal b2 = new BigDecimal("100000000");
        System.out.println("[b1]" + b1);
        double div = ArithUtil.div(b1.doubleValue(), b2.doubleValue(), 2);
        System.out.println("[div]" + div);
    }

    @Test
    public void testFormmater() throws Exception {
        BigDecimal b2 = new BigDecimal("100.00");
        BigDecimal bigDecimal = ArithUtil.bigUpScale0(b2);
        System.out.println("[bigDecimal]" + Json.toJson(bigDecimal.toString()));
    }

    @Test
    public void testBigUpScale4() throws Exception {
        assertEquals("1.0000", ArithUtil.bigUpScale4(new BigDecimal("1")));
        assertEquals("1.0000", ArithUtil.bigUpScale4(new BigDecimal("1.0")));
        assertEquals("1.5000", ArithUtil.bigUpScale4(new BigDecimal("1.5")));
        assertEquals("1.1000", ArithUtil.bigUpScale4(new BigDecimal("1.10")));
        assertEquals("1.1000", ArithUtil.bigUpScale4(new BigDecimal("1.100")));
        assertEquals("1.1230", ArithUtil.bigUpScale4(new BigDecimal("1.1230")));
        assertEquals("1.1234", ArithUtil.bigUpScale4(new BigDecimal("1.1234")));
        assertEquals("1.1235", ArithUtil.bigUpScale4(new BigDecimal("1.1235")));
        assertEquals("1.0000", ArithUtil.bigUpScale4(new BigDecimal("1.00000")));
        assertEquals("1.0000", ArithUtil.bigUpScale4(new BigDecimal("1.00004")));
        assertEquals("1.0001", ArithUtil.bigUpScale4(new BigDecimal("1.00005")));
    }

    @Test
    public void testBigToScale2() throws Exception {
        assertEquals("4.46", ArithUtil.bigToScale2(new BigDecimal("0.0446").multiply(new BigDecimal(100))));
        assertEquals("4.00", ArithUtil.bigToScale2(new BigDecimal("0.04").multiply(new BigDecimal(100))));
        assertEquals("4.40", ArithUtil.bigToScale2(new BigDecimal("0.044").multiply(new BigDecimal(100))));
        assertEquals("4.44", ArithUtil.bigToScale2(new BigDecimal("0.04444").multiply(new BigDecimal(100))));
        assertEquals("4.45", ArithUtil.bigToScale2(new BigDecimal("0.04445").multiply(new BigDecimal(100))));
    }
}