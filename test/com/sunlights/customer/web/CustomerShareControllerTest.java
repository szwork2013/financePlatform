package com.sunlights.customer.web;

import com.sunlights.BaseTest;
import com.sunlights.common.vo.MessageVo;
import org.junit.Before;
import org.junit.Test;
import play.Logger;

import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

public class CustomerShareControllerTest extends BaseTest {
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



    //@Test
    public void testsendFriend() throws Exception {//分享好友
        running(fakeApplication(), new Runnable() {
            public void run() {
                Map<String, String> formParams = new HashMap<>();
                formParams.put("mobilePhoneNo", "13811599307");
                formParams.put("deviceNo", getDeviceNo());

                play.mvc.Result  result = getResult("/customer/activity/sharecontent", formParams, cookie);

                Logger.info(contentAsString(result));
                assertThat(status(result)).isEqualTo(OK);
                MessageVo message = toMessageVo(result);

            }
        });
    }



    //@Test
    public void testgetQRcodeToByte() throws Exception {//byte二维码
        running(fakeApplication(), new Runnable() {
            public void run() {
                Map<String, String> formParams = new HashMap<>();
                formParams.put("mobilePhoneNo", "13811599307");
                formParams.put("deviceNo", getDeviceNo());

                play.mvc.Result  result = getResult("/customer/activity/getqrcode", formParams, cookie);

                Logger.info(contentAsString(result));
                assertThat(status(result)).isEqualTo(OK);
                MessageVo message = toMessageVo(result);

            }
        });
    }

    @Test
    public void testShare() throws Exception {//分享好友
        running(fakeApplication(), new Runnable() {
            public void run() {
                Map<String, String> formParams = new HashMap<>();
                formParams.put("type", "0");
                formParams.put("id", "39540");

                play.mvc.Result  result = getResult("/customer/activity/share", formParams, cookie);

                Logger.info(contentAsString(result));
                assertThat(status(result)).isEqualTo(OK);
                MessageVo message = toMessageVo(result);

                Logger.info("============testSignInObtainReward result====\n" + contentAsString(result));

            }
        });
    }



}
