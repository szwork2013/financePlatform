package com.sunlights.account.web;

import com.sunlights.BaseTest;
import com.sunlights.common.AppConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.vo.MessageVo;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.service.RewardFlowService;
import com.sunlights.customer.service.impl.RewardFlowServiceImpl;
import com.sunlights.customer.vo.HoldRewardVo;
import com.sunlights.customer.vo.ObtainRewardVo;
import models.RewardFlow;
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

public class RewardControllerTest extends BaseTest {
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
    public void testGetSingInCanObtainRewards() throws Exception {

        JPA.withTransaction(new F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                RewardFlowService rewardFlowService = new RewardFlowServiceImpl();
                RewardFlow rewardFlows = rewardFlowService.findTodayFlowByCustIdAndScene("20141129090152010000000066", ActivityConstant.ACTIVITY_SIGNIN_SCENE_CODE);

                Logger.info("============testGetSingInCanObtainRewards start====");
                Map<String, String> formParams = new HashMap<String, String>();
                play.mvc.Result result = getResult("/account/reward/get_signin", formParams, cookie);
                Logger.info("============testGetSingInCanObtainRewards result====\n" + contentAsString(result));
                assertThat(status(result)).isEqualTo(OK);
                final MessageVo message = toMessageVo(result);
                assertThat(message.getMessage().getCode()).isEqualTo(MsgCode.ACTIVITY_QUERY_SUCC.getCode());
                Logger.info("============testSignInObtainReward result====\n" + contentAsString(result));


                /**
                 * 验证message与value
                 */
                String testString = null;
                try {
                    testString = getJsonFile("json/CustInitSignButton.json");//获得json文件内容
                } catch (IOException e) {
                    e.printStackTrace();
                }
                MessageVo testMessage = toMessageVo(testString);
                assertThat(testMessage).isEqualTo(message);//此处判断message
                ObtainRewardVo testObtainRewardVo = Json.fromJson(Json.toJson(testMessage.getValue()), ObtainRewardVo.class);
                ObtainRewardVo obtainRewardVo = Json.fromJson(Json.toJson(message.getValue()), ObtainRewardVo.class);
                assertThat(testObtainRewardVo).isEqualTo(obtainRewardVo);//此处判断value

            }

        });

    }

    @Test
    public void testGetMyRewardDetail() {

        Logger.info("============testGetMyRewardDetail start====");
        Map<String, String> formParams = new HashMap<String, String>();
        play.mvc.Result result = getResult("/account/reward/get_golden", formParams, cookie);
        Logger.info("============testGetMyRewardDetail result====\n" + contentAsString(result));
        assertThat(status(result)).isEqualTo(OK);
        MessageVo message = toMessageVo(result);
        assertThat(message.getMessage().getCode()).isEqualTo(MsgCode.REWARD_QUERY_SUCC.getCode());

        /**
         * 验证message与value
         */
        String testString = null;
        try {
            testString = getJsonFile("json/CustDetMyRewarDetail.json");//获得json文件内容
        } catch (IOException e) {
            e.printStackTrace();
        }
        MessageVo testMessage = toMessageVo(testString);
        assertThat(testMessage).isEqualTo(message);//此处判断message
        HoldRewardVo testHoldRewardVo = Json.fromJson(Json.toJson(testMessage.getValue()), HoldRewardVo.class);
        HoldRewardVo holdRewardVo = Json.fromJson(Json.toJson(message.getValue()), HoldRewardVo.class);
        assertThat(testHoldRewardVo).isEqualTo(holdRewardVo);//此处判断value


    }

    @Test
    public void testRewardFlowRecords() {

        Logger.info("============testRewardFlowRecords start====");
        String index = "0";
        String pageSize = "30";


        Map<String, String> formParams = new HashMap<>();
        formParams.put("index", index);
        formParams.put("pageSize", pageSize);

        play.mvc.Result result = getResult("/account/reward/records", formParams, cookie);
        Logger.info("============testRewardFlowRecords result====\n" + contentAsString(result));
        assertThat(status(result)).isEqualTo(OK);
        MessageVo message = toMessageVo(result);
        assertThat(message.getMessage().getCode()).isEqualTo(MsgCode.REWARD_FLOW_QUERY_SUCC.getCode());


    }
}