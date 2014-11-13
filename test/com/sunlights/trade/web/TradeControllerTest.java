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
        final String mobilePhoneNo = "15821948594";
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
                formParams.put("prdCode", "GYHB");
                play.mvc.Result result = getResult("/trade/productdetail", formParams, cookie);
                Logger.info("=============testFindCapitalProductDetailTrade result======\n" + contentAsString(result));
                assertThat(status(result)).isEqualTo(OK);
                MessageVo message = toMessageVo(result);
                assertThat(message.getMessage().getCode()).isEqualTo("0000");
                assertThat(message.getMessage().getSummary()).isEqualTo("操作成功");

            }
        });
    }


}
