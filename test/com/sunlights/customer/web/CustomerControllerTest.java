package com.sunlights.customer.web;

import com.sunlights.BaseTest;
import com.sunlights.common.AppConst;
import com.sunlights.common.vo.MessageVo;
import org.junit.Test;
import play.Logger;
import play.mvc.Http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.status;

public class CustomerControllerTest extends BaseTest {

    @Test
    public void testGetCustomerByMobilePhoneNo() throws Exception {

        Map<String, String> formParams = new HashMap<>();
        formParams.put("mobilePhoneNo", "15821948594");
        formParams.put("deviceNo", getDeviceNo());

        play.mvc.Result result = getResult("/customer/getusermstr", formParams);
        Logger.info("testGetCustomerByMobilePhoneNo is----" + contentAsString(result));
        assertThat(status(result)).isEqualTo(OK);
        MessageVo message = toMessageVo(result);
        assertThat(message.getMessage().getCode()).isEqualTo("0000");
        assertThat(message.getMessage().getSummary()).isEqualTo("操作成功");

        /**
         * 验证message与value
         */
        String testString = null;
        try {
            testString = getJsonFile("json/CustGetCustByMobile.json");//获得json文件内容
        } catch (IOException e) {
            e.printStackTrace();
        }
        MessageVo testMessage = toMessageVo(testString);
        assertThat(testMessage).isEqualTo(message);//此处判断message

    }

    @Test
    public void testLoginPC() throws Exception {

        String mobilePhoneNo = "15821948369";
        String deviceNo = getDeviceNo();
        String passWord = "111111";
        String channel = AppConst.CHANNEL_PC;

        getCookieAfterLogin(mobilePhoneNo, passWord, channel);

    }

    @Test
    public void testLogin() throws Exception {

        String mobilePhoneNo = "15821948369";
        String deviceNo = getDeviceNo();
        String passWord = "111111";
        String channel = AppConst.CHANNEL_IOS;

        Map<String, String> formParams = new HashMap<>();
        formParams.put("mobilePhoneNo", mobilePhoneNo);
        formParams.put("deviceNo", deviceNo);
        formParams.put("passWord", passWord);
        formParams.put("channel", channel);

        Http.Cookie cookie = getCookieAfterLogin(mobilePhoneNo, passWord, channel);

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
        Logger.info("result is:" + contentAsString(result));
        assertThat(status(result)).isEqualTo(OK);

        MessageVo message = toMessageVo(result);
        assertThat(message.getMessage().getCode()).isEqualTo("0101");
        assertThat(message.getMessage().getSummary()).isEqualTo("登录成功");

        /**
         * 验证message与value
         */
        String testString1 = null;
        try {
            testString1 = getJsonFile("json/CustLoginByges.json");//获得json文件内容
        } catch (IOException e) {
            e.printStackTrace();
        }
        MessageVo testMessage1 = toMessageVo(testString1);
        assertThat(testMessage1.getMessage()).isEqualTo(message.getMessage());//此处判断message

    }

    @Test
    public void testResetPwd() throws Exception {

        String mobilePhoneNo = "15821948594";
        String deviceNo = getDeviceNo();
        String passWord = "111111";
        String channel = "1";

        Map<String, String> formParams = new HashMap<>();
        formParams.put("mobilePhoneNo", mobilePhoneNo);
        formParams.put("deviceNo", deviceNo);
        formParams.put("passWord", passWord);

        Http.Cookie cookie = getCookieAfterLogin(mobilePhoneNo, passWord, channel);

        Logger.info("===============resetPwd======Test============");
        resetpwdWithCookie(formParams, cookie);

        Logger.info("===============confirmpwd=====Test=============");
        confirmpwd(formParams, cookie);

        Logger.info("===============resetPwd=====Test=============");
        resetpwd(formParams);
    }

    private void resetpwdWithCookie(Map<String, String> formParams, Http.Cookie cookie) {
        formParams.put("passWord", "111111");
        play.mvc.Result result = getResult("/customer/resetpwd", formParams, cookie);
        Logger.info("result is:" + contentAsString(result));
        assertThat(status(result)).isEqualTo(OK);
        MessageVo message = toMessageVo(result);
        assertThat(message.getMessage().getSeverity() == 0);
        /**
         * 验证message与value
         */
        String testString = null;
        try {
            testString = getJsonFile("json/CustLogRestPwd.json");//获得json文件内容
        } catch (IOException e) {
            e.printStackTrace();
        }
        MessageVo testMessage = toMessageVo(testString);
        assertThat(testMessage.getMessage()).isEqualTo(message.getMessage());//此处判断message

    }

    private void confirmpwd(Map<String, String> formParams, Http.Cookie cookie) {
        play.mvc.Result result = getResult("/customer/confirmpwd", formParams, cookie);
        Logger.info("confirmpwd result is:" + contentAsString(result));
        assertThat(status(result)).isEqualTo(OK);
        MessageVo message = toMessageVo(result);
        assertThat(message.getMessage().getCode()).isEqualTo("0000");
        assertThat(message.getMessage().getSummary()).isEqualTo("操作成功");

        /**
         * 验证message
         */
        String testString = null;
        try {
            testString = getJsonFile("json/CustConfirmpwd.json");//获得json文件内容
        } catch (IOException e) {
            e.printStackTrace();
        }
        MessageVo testMessage = toMessageVo(testString);
        assertThat(testMessage.getMessage()).isEqualTo(message.getMessage());//此处判断message

    }

    private void resetpwd(Map<String, String> formParams) {
        formParams.put("passWord", "111111");
        play.mvc.Result result = getResult("/customer/resetpwd", formParams);
        assertThat(status(result)).isEqualTo(OK);
        MessageVo message = toMessageVo(result);
        assertThat(message.getMessage().getSeverity() == 0);

    }

}
