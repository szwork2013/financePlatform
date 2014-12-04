package com.sunlights.account.web;

import com.sunlights.BaseTest;

import com.sunlights.account.service.CustJoinActivityService;
import com.sunlights.common.MsgCode;
import com.sunlights.common.vo.MessageVo;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.service.RewardFlowService;
import com.sunlights.customer.service.impl.CustJoinActivityServiceImpl;
import com.sunlights.customer.service.impl.RewardFlowServiceImpl;
import com.sunlights.customer.vo.RewardResultVo;
import models.CustJoinActivity;
import models.RewardFlow;
import org.junit.Before;
import org.junit.Test;
import play.Logger;
import play.db.jpa.JPA;
import play.libs.F;
import play.mvc.Http;

import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

public class ActivityControllerTest extends BaseTest{
    private static Http.Cookie cookie;

    @Before
    public void getCookie(){
        final String mobilePhoneNo = "13811599307";
        final String password = "1";
        running(fakeApplication(), new Runnable() {
            public void run() {
                cookie = getCookieAfterLogin(mobilePhoneNo, password);
            }
        });
    }

    @Test
    public void testSignInObtainReward() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Logger.info("============testSignInObtainReward start====");
                JPA.withTransaction(new F.Callback0() {
                    @Override
                    public void invoke() throws Throwable {
                        Map<String, String> formParams = new HashMap<String, String>();
                        play.mvc.Result result = null;

                        RewardFlowService rewardFlowService = new RewardFlowServiceImpl();
                        RewardFlow rewardFlows = rewardFlowService.findTodayFlowByCustIdAndScene("20141119102210010000000029", ActivityConstant.ACTIVITY_SIGNIN_SCENE_CODE);

                        //2:签到获取金豆正常测试
                        formParams = new HashMap<String, String>();
                        formParams.put("scene", ActivityConstant.ACTIVITY_SIGNIN_SCENE_CODE);
                        result = getResult("/account/activity/signin", formParams, cookie);
                        assertThat(status(result)).isEqualTo(OK);

                        final MessageVo message = toMessageVo(result);

                        if(rewardFlows != null) {
                            assertThat(message.getMessage().getCode()).isEqualTo(MsgCode.ALREADY_SIGN.getCode());
                            assertThat("20141119102210010000000029").isEqualTo(rewardFlows.getCustId());
                        } else {
                            assertThat(message.getMessage().getCode()).isEqualTo(MsgCode.OBTAIN_SUCC.getCode());
                        }

                        Logger.info("============testSignInObtainReward result====\n" + contentAsString(result));
                    }
                });

            }
        });
    }

    @Test
    public void testGetActivityList() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Logger.info("============testGetActivityList start====");
                String index = "0";
                String pageSize = "3";


                Map<String, String> formParams = new HashMap<>();
                formParams.put("index", index);
                formParams.put("pageSize", pageSize);


                play.mvc.Result result = getResult("/account/activity/list", formParams);
                Logger.info("============testGetActivityList result====\n" + contentAsString(result));

            }
        });
    }



    @Test
    public void testRegisterObtainReward() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                JPA.withTransaction(new F.Callback0() {
                    @Override
                    public void invoke() throws Throwable {
                        Logger.info("============testRegisterObtainReward start====");
                        Map<String, String> formParams = new HashMap<String, String>();
                        play.mvc.Result result = null;

                        RewardFlowService rewardFlowService = new RewardFlowServiceImpl();
                        RewardResultVo rewardResultVo = rewardFlowService.getLastObtainRewars("20141119102210010000000029", ActivityConstant.ACTIVITY_REGISTER_SCENE_CODE);

                        //2:签到获取金豆正常测试
                        formParams = new HashMap<String, String>();

                        result = getResult("/account/activity/register", formParams, cookie);
                        assertThat(status(result)).isEqualTo(OK);
                        final MessageVo message = toMessageVo(result);

                        if(rewardResultVo != null) {
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
        });
    }

    @Test
    public void testPurchaseObtainReward() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Logger.info("============testPurchaseObtainReward start====");
                JPA.withTransaction(new F.Callback0() {
                    @Override
                    public void invoke() throws Throwable {
                        Map<String, String> formParams = new HashMap<String, String>();
                        play.mvc.Result result = null;

                        RewardFlowService rewardFlowService = new RewardFlowServiceImpl();
                        RewardResultVo rewardResultVo = rewardFlowService.getLastObtainRewars("20141119102210010000000029", ActivityConstant.ACTIVITY_FIRST_PURCHASE_SCENE_CODE);
                        com.sunlights.customer.service.CustJoinActivityService custJoinActivityService = new CustJoinActivityServiceImpl();
                        CustJoinActivity custJoinActivity = custJoinActivityService.getByCustAndActivity("20141119102210010000000029", null, ActivityConstant.ACTIVITY_FIRST_PURCHASE_SCENE_CODE);
                        //2:签到获取金豆正常测试
                        formParams = new HashMap<String, String>();
                        formParams.put("tradeType", "0");
                        formParams.put("fundCode", "33376");
                        formParams.put("supplySum", "20");

                        result = getResult("/account/activity/trade", formParams, cookie);
                        assertThat(status(result)).isEqualTo(OK);
                        final MessageVo message = toMessageVo(result);
                        Logger.info("============testPurchaseObtainReward result====\n" + contentAsString(result));
                        if(custJoinActivity != null) {
                            assertThat(message.getMessage().getCode()).isEqualTo(MsgCode.NOT_CONFIG_ACTIVITY_SCENE.getCode());
                            Logger.info("已经购买过。。。获取积分失败");
                        } else {
                            assertThat(message.getMessage().getCode()).isEqualTo(MsgCode.OBTAIN_SUCC.getCode());
                            Logger.info("首次购买获取积分成功");
                        }


                    }
                });
            }
        });
    }

    @Test
    public void testExchangeReward() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Logger.info("============testPurchaseObtainReward start====");
                JPA.withTransaction(new F.Callback0() {
                    @Override
                    public void invoke() throws Throwable {
                        Map<String, String> formParams = new HashMap<String, String>();
                        play.mvc.Result result = null;

                        //2:签到获取金豆正常测试
                        formParams = new HashMap<String, String>();
                        formParams.put("scene", ActivityConstant.ACTIVITY_EXCHANGE_RED_PACKET_SCENE_CODE);
                        formParams.put("exchangeAmt", "20");
                        formParams.put("rewardType", "ART00H");
                        formParams.put("bankCardNo", "111111111111111");
                        result = getResult("/account/activity/exchange", formParams, cookie);
                        assertThat(status(result)).isEqualTo(OK);
                        final MessageVo message = toMessageVo(result);
                        Logger.info("============testPurchaseObtainReward result====\n" + contentAsString(result));
                    }
                });
            }
        });
    }

}