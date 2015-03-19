package com.sunlights.account.web;

import com.sunlights.BaseTest;
import com.sunlights.common.AppConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.vo.MessageVo;
import com.sunlights.common.vo.PageVo;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.service.impl.CustJoinActivityServiceImpl;
import com.sunlights.customer.vo.ActivityVo;
import com.sunlights.customer.vo.ExchangeResultVo;
import com.sunlights.customer.vo.ObtainRewardVo;
import com.sunlights.customer.vo.TradeObtainRewardFailVo;
import models.CustJoinActivity;
import org.junit.Before;
import org.junit.Test;
import play.Logger;
import play.db.jpa.JPA;
import play.libs.F;
import play.libs.Json;
import play.mvc.Http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.status;

public class ActivityControllerTest extends BaseTest {
    private static Http.Cookie cookie;

    @Before
    public void getCookie() {
        super.startPlay();
        final String mobilePhoneNo = "15821948369";
        final String password = "111111";
        String channel = AppConst.CHANNEL_IOS;
        cookie = getCookieAfterLogin(mobilePhoneNo, password, channel);

    }

    @Test
    public void testSignInObtainReward() throws Exception {
        JPA.withTransaction(new F.Callback0() {
            @Override
            public void invoke() throws Throwable {

                Map<String, String> formParams = new HashMap<String, String>();
                play.mvc.Result result = null;
                com.sunlights.customer.service.CustJoinActivityService custJoinActivityService = new CustJoinActivityServiceImpl();

                CustJoinActivity custJoinActivity = custJoinActivityService.getTodayRecordByCustAndActivity("20150310104724010000000292", null, ActivityConstant.ACTIVITY_SIGNIN_SCENE_CODE);
                //2:签到获取金豆正常测试
                formParams = new HashMap<String, String>();
                formParams.put("scene", ActivityConstant.ACTIVITY_SIGNIN_SCENE_CODE);
                result = getResult("/account/activity/signin", formParams, cookie);
                assertThat(status(result)).isEqualTo(OK);

                final MessageVo message = toMessageVo(result);

                if (custJoinActivity != null) {
                    assertThat(message.getMessage().getCode()).isEqualTo(MsgCode.ALREADY_SIGN.getCode());
                } else {
                    assertThat(message.getMessage().getCode()).isEqualTo(MsgCode.OBTAIN_SUCC.getCode());
                }

                Logger.info("============testSignInObtainReward result====\n" + contentAsString(result));

            }
        });
    }

    @Test
    public void testGetActivityList() throws Exception {

        Logger.info("============testGetActivityList start====");
        String index = "0";
        String pageSize = "4";


        Map<String, String> formParams = new HashMap<>();
        formParams.put("index", index);
        formParams.put("pageSize", pageSize);
        formParams.put("filter", "1");
        play.mvc.Result result = getResult("/account/activity/list", formParams, cookie);
        Logger.info("============testGetActivityList result====\n" + contentAsString(result));

        /**
         * 验证message与value
         */
        String testString = null;
        try {
            testString = getJsonFile("json/CustActivityList.json");//获得json文件内容
        } catch (IOException e) {
            e.printStackTrace();
        }
        MessageVo message = toMessageVo(result);
        MessageVo testMessage = toMessageVo(testString);
        assertThat(testMessage).isEqualTo(message);//此处判断message
        PageVo pageVo = Json.fromJson(Json.toJson(message.getValue()), PageVo.class);
        PageVo testPageVo = Json.fromJson(Json.toJson(testMessage.getValue()), PageVo.class);
        assertThat(testPageVo).isEqualTo(pageVo);//此处判断page
        ActivityVo activityVo = Json.fromJson(Json.toJson(pageVo.getList().get(0)), ActivityVo.class);
        ActivityVo testActivityVo = Json.fromJson(Json.toJson(testPageVo.getList().get(0)), ActivityVo.class);
        assertThat(activityVo).isEqualTo(testActivityVo);//此处判断list

    }


    @Test
    public void testRegisterObtainReward() {

        JPA.withTransaction(new F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                Logger.info("============testRegisterObtainReward start====");
                Map<String, String> formParams = new HashMap<String, String>();
                play.mvc.Result result = null;
                com.sunlights.customer.service.CustJoinActivityService custJoinActivityService = new CustJoinActivityServiceImpl();

                CustJoinActivity custJoinActivity = custJoinActivityService.getByCustAndActivity("20141206134951010000000044", null, ActivityConstant.ACTIVITY_REGISTER_SCENE_CODE);
                Logger.info("custJoinActivity is :" + custJoinActivity);
                //2:签到获取金豆正常测试
                formParams = new HashMap<String, String>();

                result = getResult("/account/activity/register", formParams, cookie);
                assertThat(status(result)).isEqualTo(OK);
                final MessageVo message = toMessageVo(result);

                /**
                 * 验证message与value
                 */
                String testString = null;
                try {
                    testString = getJsonFile("json/CustRegisterReward.json");//获得json文件内容
                } catch (IOException e) {
                    e.printStackTrace();
                }
                MessageVo testMessage = toMessageVo(testString);
                assertThat(testMessage.getMessage()).isEqualTo(message.getMessage());//此处判断message
                ObtainRewardVo testObtainRewardVo = Json.fromJson(Json.toJson(testMessage.getValue()), ObtainRewardVo.class);
                ObtainRewardVo obtainRewardVo = Json.fromJson(Json.toJson(message.getValue()), ObtainRewardVo.class);
                assertThat(testObtainRewardVo).isEqualTo(obtainRewardVo);//此处判断value

                if (custJoinActivity != null) {
                    assertThat(message.getMessage().getCode()).isEqualTo(MsgCode.ALREADY_REGISTER.getCode());
                    Logger.info("已经注册过。。。获取积分失败");
                } else {
                    assertThat(message.getMessage().getCode()).isEqualTo(MsgCode.OBTAIN_SUCC.getCode());
                    Logger.info("注册成功获取积分成功");
                }
                Logger.info("============testRegisterObtainReward result====\n" + contentAsString(result));
            }
        });

    }

    @Test
    public void testPurchaseObtainReward() {

        Logger.info("============testPurchaseObtainReward start====");
        JPA.withTransaction(new F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                Map<String, String> formParams = new HashMap<String, String>();
                play.mvc.Result result = null;

                com.sunlights.customer.service.CustJoinActivityService custJoinActivityService = new CustJoinActivityServiceImpl();
                CustJoinActivity custJoinActivity = custJoinActivityService.getByCustAndActivity("20150310104724010000000292", null, ActivityConstant.ACTIVITY_FIRST_PURCHASE_SCENE_CODE);
                //2:签到获取金豆正常测试
                formParams = new HashMap<String, String>();
                formParams.put("tradeType", "0");
                formParams.put("fundCode", "33376");
                formParams.put("supplySum", "20");

                result = getResult("/account/activity/trade", formParams, cookie);
                assertThat(status(result)).isEqualTo(OK);
                final MessageVo message = toMessageVo(result);

                Logger.info("============testPurchaseObtainReward result====\n" + contentAsString(result));
                if (custJoinActivity != null) {
                    assertThat(message.getMessage().getCode()).isEqualTo(MsgCode.NOT_CONFIG_ACTIVITY_SCENE.getCode());
                    Logger.info("没有配置活动场景");
                } else {
                    assertThat(message.getMessage().getCode()).isEqualTo(MsgCode.OBTAIN_SUCC.getCode());
                    Logger.info("首次购买获取积分成功");
                }


            }

        });

    }

    //@Test
    public void testExchangeReward() {

        Logger.info("============testPurchaseObtainReward start====");
        JPA.withTransaction(new F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                Map<String, String> formParams = new HashMap<String, String>();
                play.mvc.Result result = null;

                //2:签到获取金豆正常测试
                formParams = new HashMap<String, String>();
                formParams.put("id", "1");
                formParams.put("amount", "0.01");
                formParams.put("bankName", "招行");
                formParams.put("bankCard", "1111");
                formParams.put("phone", "132323232");
                result = getResult("/account/activity/exchange", formParams, cookie);
                assertThat(status(result)).isEqualTo(OK);

                Logger.info("============testPurchaseObtainReward result====\n" + contentAsString(result));

                /**
                 * 验证message与value
                 */
                String testString = null;
                try {
                    testString = getJsonFile("json/CustExchangeReward.json");//获得json文件内容
                } catch (IOException e) {
                    e.printStackTrace();
                }
                MessageVo message = toMessageVo(result);
                MessageVo testMessage = toMessageVo(testString);
                assertThat(testMessage.getMessage()).isEqualTo(message.getMessage());//此处判断message
                ExchangeResultVo exchangeResultVo = Json.fromJson(Json.toJson(message.getValue()), ExchangeResultVo.class);
                ExchangeResultVo testExchangeResultVo = Json.fromJson(Json.toJson(testMessage.getValue()), ExchangeResultVo.class);
                assertThat(testExchangeResultVo).isEqualTo(exchangeResultVo);//此处判断value

            }

        });

    }

}