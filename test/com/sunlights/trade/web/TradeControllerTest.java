package com.sunlights.trade.web;

import com.sunlights.DBUnitBasedTest;
import org.apache.http.client.utils.URIBuilder;
import org.junit.Test;
import play.mvc.Result;
import play.test.FakeRequest;

import java.net.URI;

import static play.test.Helpers.*;

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
        needRollbackData("c_customer,c_authentication,f_basic_account,c_customer_session,t_trade,t_trade_status_change_info");
    }


    @Test
    public void testTradeInfoListRedeem() throws Exception{
        initData(trade + "tradeInfoListRedeemInit.xml");

        URIBuilder uriBuilder = new URIBuilder("/trade/tradeforecast");
        uriBuilder.addParameter("FundCode", "000907");
        uriBuilder.addParameter("ApplySerial", "20150324201093");
        uriBuilder.addParameter("BusinessType", "024");
        uriBuilder.addParameter("TradeAccount", "111111");
        uriBuilder.addParameter("Amount", "100");
        uriBuilder.addParameter("ApplyDateTime", "2015-03-03'T'10:10:10.000");
        uriBuilder.addParameter("Status", "9");
        URI uri = uriBuilder.build();

        FakeRequest fakeRequest = fakeRequest(GET, uri.toString());
        setRequestHeader(fakeRequest);
        setRequestToken(fakeRequest);
        Result result = route(fakeRequest);

        String resultInfo = contentAsString(result);
        String expected = getJsonFile(trade + "tradeInfoListRedeemExpected.json");

        assertMessage(resultInfo, expected);
    }

    @Test
    public void testTradeInfoListPurchase() throws Exception{
        initData(trade + "tradeInfoListPurchaseInit.xml");

        URIBuilder uriBuilder = new URIBuilder("/trade/tradeforecast");
        uriBuilder.addParameter("FundCode", "000907");
        uriBuilder.addParameter("ApplySerial", "20150324201093");
        uriBuilder.addParameter("BusinessType", "022");
        uriBuilder.addParameter("TradeAccount", "111111");
        uriBuilder.addParameter("Amount", "100");
        uriBuilder.addParameter("ApplyDateTime", "2015-03-03'T'10:10:10.000");
        uriBuilder.addParameter("Status", "9");
        URI uri = uriBuilder.build();

        FakeRequest fakeRequest = fakeRequest(GET, uri.toString());
        setRequestHeader(fakeRequest);
        setRequestToken(fakeRequest);
        Result result = route(fakeRequest);

        String resultInfo = contentAsString(result);
        String expected = getJsonFile(trade + "tradeInfoListPurchaseExpected.json");

        assertMessage(resultInfo, expected);
    }
}
