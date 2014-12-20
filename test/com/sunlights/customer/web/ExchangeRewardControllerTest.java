package com.sunlights.customer.web;

import com.sunlights.BaseTest;
import com.sunlights.common.MsgCode;
import com.sunlights.common.vo.MessageVo;
import org.junit.Before;
import org.junit.Test;
import play.Logger;
import play.mvc.Http;

import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

public class ExchangeRewardControllerTest extends BaseTest {

    private static Http.Cookie cookie;

    @Before
    public void getCookie(){
        final String mobilePhoneNo = "10000000014";
        final String password = "111111";
        running(fakeApplication(), new Runnable() {
            public void run() {
                cookie = getCookieAfterLogin(mobilePhoneNo, password);
            }
        });
    }

    //@Test
    public void testQueryExchangeScenes() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Logger.info("============testQueryExchangeScenes start====");



                String index = "0";
                String pageSize = "4";


                Map<String, String> formParams = new HashMap<>();
                formParams.put("index", index);
                formParams.put("pageSize", pageSize);


                play.mvc.Result result = getResult("/account/activity/exchangescenes", formParams, cookie);
                Logger.info("============testQueryExchangeScenes result====\n" + contentAsString(result));
                assertThat(status(result)).isEqualTo(OK);
                MessageVo message = toMessageVo(result);
                assertThat(message.getMessage().getCode()).isEqualTo(MsgCode.EXCHANGE_SCENE_QUERY_SUCC.getCode());

            }
        });
    }

    //@Test
    public void testPrepareDataBeforeExchange() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Logger.info("============testPrepareDataBeforeExchange start====");

                Map<String, String> formParams = new HashMap<>();
                formParams.put("id", "1");

                play.mvc.Result result = getResult("/account/activity/beforeexchange", formParams, cookie);
                Logger.info("============testPrepareDataBeforeExchange result====\n" + contentAsString(result));
                assertThat(status(result)).isEqualTo(OK);
                MessageVo message = toMessageVo(result);
                assertThat(message.getMessage().getCode()).isEqualTo(MsgCode.BEFORE_EXCHANGE_QUERY_SUCC.getCode());

            }
        });
    }


}