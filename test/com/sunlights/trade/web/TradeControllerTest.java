package com.sunlights.trade.web;

import com.sunlights.BaseTest;
import com.sunlights.common.vo.MessageVo;
import org.junit.Before;
import org.junit.Test;
import play.Logger;
import play.mvc.Http;

import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

public class TradeControllerTest extends BaseTest {
    
    private static Http.Cookie cookie;
    
    @Before
    public void getCookie(){
        final String mobilePhoneNo = "13811599308";
        final String password = "1";
        running(fakeApplication(), new Runnable() {
            public void run() {
                cookie = getCookieAfterLogin(mobilePhoneNo, password);
            }
        });
    }


    @Test
    public void testGetTradeList() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Logger.info("=============testGetTradeList start======");
                Map<String, String> formParams = new HashMap<String, String>();
                play.mvc.Result result = getResult("/trade/tradelist", formParams, cookie);
                Logger.info("=============testGetTradeList result======\n" + contentAsString(result));
                assertThat(status(result)).isEqualTo(OK);
                MessageVo message = toMessageVo(result);
                assertThat(message.getMessage().getCode()).isEqualTo("0000");
                assertThat(message.getMessage().getSummary()).isEqualTo("操作成功");

            }
        });
    }

    @Test
    public void testFindCapitalProductDetailTrade() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Logger.info("=============testFindCapitalProductDetailTrade start======");
                Map<String, String> formParams = new HashMap<String, String>();
                formParams.put("prdType", "0");
                formParams.put("prdCode", "350004");
                play.mvc.Result result = getResult("/trade/productdetail", formParams, cookie);
                Logger.info("=============testFindCapitalProductDetailTrade result======\n" + contentAsString(result));
                assertThat(status(result)).isEqualTo(OK);
                MessageVo message = toMessageVo(result);
                assertThat(message.getMessage().getCode()).isEqualTo("0000");
                assertThat(message.getMessage().getSummary()).isEqualTo("操作成功");

            }
        });
    }

    @Test
    public void testTradeOrder() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Logger.info("=============testTradeOrder start======");
                Map<String, String> formParams = new HashMap<String, String>();
                formParams.put("bankCardNo", "6225885105575635");
                formParams.put("prdType", "0");
                formParams.put("mobilePhoneNo", "13811599308");
                formParams.put("prdCode", "350004");
                formParams.put("tradeAmount", "100");
                formParams.put("quantity", "1");

                play.mvc.Result result = getResult("/trade/tradeorder", formParams, cookie);
                Logger.info("=============testTradeOrder result======\n" + contentAsString(result));
                assertThat(status(result)).isEqualTo(OK);
                MessageVo message = toMessageVo(result);
                assertThat(message.getMessage().getCode()).isEqualTo("0400");
                assertThat(message.getMessage().getSummary()).isEqualTo("下单成功");

            }
        });

    }

    @Test
    public void testTradeRedeem() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Logger.info("=============testTradeRedeem start======");
                Map<String, String> formParams = new HashMap<String, String>();
                formParams.put("prdType", "0");
                formParams.put("prdCode", "350004");
                formParams.put("mobilePhoneNo", "13811599308");
                formParams.put("tradeAmount", "100");
                formParams.put("quantity", "1");

                play.mvc.Result result = getResult("/trade/traderedeem", formParams, cookie);
                Logger.info("=============testTradeRedeem result======\n" + contentAsString(result));
                assertThat(status(result)).isEqualTo(OK);
                MessageVo message = toMessageVo(result);
                assertThat(message.getMessage().getCode()).isEqualTo("0401");
                assertThat(message.getMessage().getSummary()).isEqualTo("赎回成功");

            }
        });

    }
}
