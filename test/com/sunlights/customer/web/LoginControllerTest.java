package com.sunlights.customer.web;

import com.sunlights.DBUnitBasedTest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: LoginControllerTest.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class LoginControllerTest extends DBUnitBasedTest{
    private static String login = "login/";

    @Override
    public void rollback() {
        needRollbackData("c_customer,c_authentication,c_customer_session,c_customer_msg_setting,c_login_history,f_basic_account");
    }

    @Test
    public void testGetusermstr()  {
        initData(login + "loginInit.xml");

        final Map<String, String> formParams = new HashMap<>();
        formParams.put("mobilePhoneNo", "15821948594");
        formParams.put("deviceNo", deviceNo);

        String result = getResult("/customer/getusermstr", formParams);

        String expected = getJsonFile(login + "loginGetUerMstrExpected.json");

        assertMessage(result, expected);
    }

    @Test
    public void testLogin(){
        initData(login + "loginInit.xml");

        final Map<String, String> formParams = new HashMap<>();
        formParams.put("mobilePhoneNo", "15821948594");
        formParams.put("passWord", "111111l");
        formParams.put("deviceNo", deviceNo);

        String result = getResult("/customer/login", formParams);

        String expected = getJsonFile(login + "loginExpected.json");

        assertMessage(result, expected);
    }

    @Test
    public void testLoginByGes(){
        initData(login + "loginInit.xml");

        final Map<String, String> formParams = new HashMap<>();
        formParams.put("mobilePhoneNo", "15821948594");
        formParams.put("gesturePassWord", "123456");
        formParams.put("deviceNo", deviceNo);

        String result = getResult("/customer/loginbyges", formParams);

        String expected = getJsonFile(login + "loginExpected.json");

        assertMessage(result, expected);
    }

    @Test
    public void testLoginByPc(){
        initData(login + "loginPcInit.xml");

        final Map<String, String> formParams = new HashMap<>();
        formParams.put("mobilePhoneNo", "15821948594");
        formParams.put("passWord", "111111l");
        formParams.put("channel", "1");

        String result = getResult("/customer/login", formParams);

        String expected = getJsonFile(login + "loginPcExpected.json");

        assertMessage(result, expected);
    }

    @Test
    public void testSaveGespwd(){
        initData(login + "loginSaveGesPwdInit.xml");

        final Map<String, String> formParams = new HashMap<>();
        formParams.put("mobilePhoneNo", "15821948594");
        formParams.put("gesturePassWord", "123456");
        formParams.put("gestureOpened", "1");

        String result = getResultWithLogin("/customer/savegespwd", formParams);

        String expected = getJsonFile(login + "loginSaveGespwdExpected.json");

        assertMessage(result, expected);
    }


    @Test
    public void testResetPwd() {
        initData(login + "loginInit.xml");
        Map<String, String> formParams = new HashMap<>();
        formParams.put("mobilePhoneNo", "15821948594");
        formParams.put("deviceNo", deviceNo);
        formParams.put("passWord", "888888");

        String result = getResultWithLogin("/customer/resetpwd", formParams);

        String expected = getJsonFile(login + "loginResetPwdExpected.json");

        assertMessage(result, expected);
    }

    @Test
    public void testLogout() {
        initData(login + "loginInit.xml");
        Map<String, String> formParams = new HashMap<>();
        formParams.put("mobilePhoneNo", "15821948594");
        formParams.put("deviceNo", deviceNo);

        String result = getResultWithLogin("/customer/logout", formParams);

        String expected = getJsonFile(login + "logout.json");

        assertMessage(result, expected);
    }
}
