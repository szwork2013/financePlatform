package com.sunlights.customer.web;

import com.sunlights.BaseTest;
import com.sunlights.common.MsgCode;
import com.sunlights.common.vo.MessageVo;
import com.sunlights.common.vo.PageVo;
import com.sunlights.customer.vo.Data4ExchangeVo;
import org.junit.Before;
import org.junit.Test;
import play.Logger;
import play.libs.Json;
import play.mvc.Http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

public class ExchangeRewardControllerTest extends BaseTest {

    private static Http.Cookie cookie;

    @Before
    public void getCookie(){
        final String mobilePhoneNo = "15821948594";
        final String password = "111111";
        running(fakeApplication(), new Runnable() {
            public void run() {
                cookie = getCookieAfterLogin(mobilePhoneNo, password);
            }
        });
    }

    @Test
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

                /**
                 * 验证message与value
                 */
                String testString= null;
                try {
                    testString = getJsonFile("json/CustQueryExchangeScenes.json");//获得json文件内容
                } catch (IOException e) {
                    e.printStackTrace();
                }
                MessageVo testMessage = toMessageVo(testString);
                assertThat(testMessage).isEqualTo(message);//此处判断message
                PageVo pageVo = Json.fromJson(Json.toJson(message.getValue()), PageVo.class);
                PageVo testPageVo = Json.fromJson(Json.toJson(testMessage.getValue()), PageVo.class);
                assertThat(testPageVo).isEqualTo(pageVo);//此处判断page

//                ExchangeSceneVo exchangeSceneVo = Json.fromJson(Json.toJson(pageVo.getList().get(0)), ExchangeSceneVo.class);
//                ExchangeSceneVo testExchangeSceneVo = Json.fromJson(Json.toJson(testPageVo.getList().get(0)), ExchangeSceneVo.class);
//                assertThat(exchangeSceneVo).isEqualTo(testExchangeSceneVo);//此处判断list


            }
        });
    }

    @Test
    public void testPrepareDataBeforeExchange() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Logger.info("============testPrepareDataBeforeExchange start====");

                Map<String, String> formParams = new HashMap<>();
                formParams.put("id", "1");

                play.mvc.Result result = getResult("/account/activity/beforeexchange", formParams, cookie);  //
                Logger.info("============testPrepareDataBeforeExchange result====\n" + contentAsString(result));
                assertThat(status(result)).isEqualTo(OK);
                MessageVo message = toMessageVo(result);
                assertThat(message.getMessage().getCode()).isEqualTo(MsgCode.BEFORE_EXCHANGE_QUERY_SUCC.getCode());


                /**
                 * 验证message与value
                 */
                String testString1= null;
                try {
                    testString1 = getJsonFile("json/CustBeforeexChange.json");//获得json文件内容
                } catch (IOException e) {
                    e.printStackTrace();
                }

                MessageVo testMessage1 = toMessageVo(testString1);
                Logger.info("---"+contentAsString(result));
                Logger.info("---"+testString1);
                assertThat(testMessage1.getMessage()).isEqualTo(message.getMessage());//此处判断message
                Data4ExchangeVo testData4ExchangeVo = Json.fromJson(Json.toJson(testMessage1.getValue()), Data4ExchangeVo.class);
                Data4ExchangeVo data4ExchangeVo = Json.fromJson(Json.toJson(message.getValue()), Data4ExchangeVo.class);
                assertThat(testData4ExchangeVo).isEqualTo(data4ExchangeVo);//此处判断value

            }
        });
    }


}