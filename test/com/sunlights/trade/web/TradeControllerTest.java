package com.sunlights.trade.web;

import com.sunlights.DBUnitBasedTest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: TradeControllerTest.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class TradeControllerTest extends DBUnitBasedTest{
    private String trade = "trade/";

    @Override
    public void rollback() {
        needRollbackData("c_customer,c_authentication,f_basic_account,c_customer_session,t_trade");
    }

    @Test
    public void testTradeInfoListRedeem(){
        initData(trade + "tradeInfoListRedeemInit.xml");

        Map<String, String> formParams = new HashMap<>();
        formParams.put("tradeNo", "20150324201093");

        String result = getResultWithLogin("/trade/tradeinfo", formParams);
        String expected = getJsonFile(trade + "tradeInfoListRedeemExpected.json");

        assertMessage(result, expected);
    }

    @Test
    public void testTradeInfoListPurchase(){
        initData(trade + "tradeInfoListPurchaseInit.xml");

        Map<String, String> formParams = new HashMap<>();
        formParams.put("tradeNo", "20150324201093");

        String result = getResultWithLogin("/trade/tradeinfo", formParams);
        String expected = getJsonFile(trade + "tradeInfoListPurchaseExpected.json");

        assertMessage(result, expected);
    }
}
