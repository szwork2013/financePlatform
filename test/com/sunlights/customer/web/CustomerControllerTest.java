package com.sunlights.customer.web;

import com.sunlights.BaseTest;
import com.sunlights.common.vo.MessageVo;
import org.junit.Test;
import play.Logger;
import play.mvc.Http;

import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

public class CustomerControllerTest extends BaseTest {

    @Test
    public void testGetCustomerByMobilePhoneNo() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Map<String, String> formParams = new HashMap<>();
                formParams.put("mobilePhoneNo", "15821948594");
                formParams.put("deviceNo", getDeviceNo());

                play.mvc.Result result = getResult("/customer/getusermstr", formParams);
                Logger.info(contentAsString(result));
                assertThat(status(result)).isEqualTo(OK);
                MessageVo message = toMessageVo(result);
                assertThat(message.getMessage().getCode()).isEqualTo("0000");
                assertThat(message.getMessage().getSummary()).isEqualTo("操作成功");
            }
        });
    }

    @Test
    public void testLogin() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                String mobilePhoneNo = "15821948594";
                String deviceNo = getDeviceNo();
                String passWord = "1";

                Map<String, String> formParams = new HashMap<>();
                formParams.put("mobilePhoneNo", mobilePhoneNo);
                formParams.put("deviceNo", deviceNo);
                formParams.put("passWord", passWord);

                Http.Cookie cookie = getCookieAfterLogin(mobilePhoneNo, passWord);

                Logger.info("===============savegespwd=====Test=============");
                savegespwd(formParams, cookie);


                Logger.info("===============logout========Test==========");
                logout(formParams, cookie);


                Logger.info("===============loginByges=====Test=============");
                loginbyges(formParams);
            }

            private void savegespwd(Map<String, String> formParams, Http.Cookie cookie) {
                formParams.put("gestureOpened", "1");
                formParams.put("gesturePassWord", "0000");

                play.mvc.Result result = getResult("/customer/savegespwd", formParams, cookie);
                assertThat(status(result)).isEqualTo(OK);
                MessageVo message = toMessageVo(result);
                assertThat(message.getMessage().getCode()).isEqualTo("0103");
                assertThat(message.getMessage().getSummary()).isEqualTo("手势密码设置成功");
            }

            private void logout(Map<String, String> formParams, Http.Cookie cookie) {
                play.mvc.Result result = getResult("/customer/logout", formParams, cookie);
                assertThat(status(result)).isEqualTo(OK);
                MessageVo message = toMessageVo(result);
                assertThat(message.getMessage().getCode()).isEqualTo("0104");
                assertThat(message.getMessage().getSummary()).isEqualTo("登出成功");
            }

            private void loginbyges(Map<String, String> formParams) {
                play.mvc.Result result = getResult("/customer/loginbyges", formParams);
                assertThat(status(result)).isEqualTo(OK);
                MessageVo message = toMessageVo(result);
                assertThat(message.getMessage().getCode()).isEqualTo("0101");
                assertThat(message.getMessage().getSummary()).isEqualTo("登录成功");
            }
        });
    }

    @Test
    public void testResetPwd() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                String mobilePhoneNo = "15821948594";
                String deviceNo = getDeviceNo();
                String passWord = "1";

                Map<String, String> formParams = new HashMap<>();
                formParams.put("mobilePhoneNo", mobilePhoneNo);
                formParams.put("deviceNo", deviceNo);
                formParams.put("passWord", passWord);

                Http.Cookie cookie = getCookieAfterLogin(mobilePhoneNo, passWord);

                Logger.info("===============resetpwd======Test============");
                resetpwdWithCookie(formParams, cookie);

                Logger.info("===============confirmpwd=====Test=============");
                confirmpwd(formParams, cookie);

                Logger.info("===============resetpwd=====Test=============");
                resetpwd(formParams);
            }

            private void resetpwdWithCookie(Map<String, String> formParams, Http.Cookie cookie) {
                formParams.put("passWord", "2");
                play.mvc.Result result = getResult("/customer/resetpwd", formParams, cookie);
                assertThat(status(result)).isEqualTo(OK);
                MessageVo message = toMessageVo(result);
                assertThat(message.getMessage().getSeverity() == 0);
            }

            private void confirmpwd(Map<String, String> formParams, Http.Cookie cookie) {
                play.mvc.Result result = getResult("/customer/confirmpwd", formParams, cookie);
                assertThat(status(result)).isEqualTo(OK);
                MessageVo message = toMessageVo(result);
                assertThat(message.getMessage().getCode()).isEqualTo("0000");
                assertThat(message.getMessage().getSummary()).isEqualTo("操作成功");
            }

            private void resetpwd(Map<String, String> formParams) {
                formParams.put("passWord", "1");
                play.mvc.Result result = getResult("/customer/resetpwd", formParams);
                assertThat(status(result)).isEqualTo(OK);
                MessageVo message = toMessageVo(result);
                assertThat(message.getMessage().getSeverity() == 0);
            }
        });
    }

}
