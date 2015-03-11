package com.sunlights.account.web;

import com.sunlights.BaseTest;
import com.sunlights.common.AppConst;
import com.sunlights.common.vo.MessageVo;
import org.junit.Before;
import org.junit.Test;
import play.Logger;
import play.mvc.Http;

import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: AccountControllerTest.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class AccountControllerTest extends BaseTest {
    private static Http.Cookie cookie;

    @Before
    public void getCookie() {
        super.startPlay();
        final String mobilePhoneNo = "13811599308";
        final String password = "1";
        String channel = AppConst.CHANNEL_PC;

        cookie = getCookieAfterLogin(mobilePhoneNo, password, channel);

    }

    @Test
    public void testResetAccountPwd() throws Exception {

        Logger.info("============testResetAccountPwd start====");
        String mobilePhoneNo = "18522222225";
        String deviceNo = getDeviceNo();
        String password = "1";

        Map<String, String> formParams = new HashMap<>();
        formParams.put("mobilePhoneNo", mobilePhoneNo);
        formParams.put("deviceNo", deviceNo);
        formParams.put("passWord", password);

        play.mvc.Result result = getResult("/account/resetaccpwd", formParams, cookie);
        Logger.info("============testResetAccountPwd result====\n" + contentAsString(result));
        assertThat(status(result)).isEqualTo(OK);
        MessageVo message = toMessageVo(result);
        assertThat(message.getMessage().getCode()).isEqualTo("0200");
        assertThat(message.getMessage().getSummary()).isEqualTo("交易密码重置成功");

    }

    @Test
    public void testGetTotalCapitalInfo() throws Exception {

        Logger.info("============testGetTotalCapitalInfo start====");
        Map<String, String> formParams = new HashMap<String, String>();
        play.mvc.Result result = getResult("/account/accountital/totalcapital", formParams, cookie);
        Logger.info("============testGetTotalCapitalInfo result====\n" + contentAsString(result));
        assertThat(status(result)).isEqualTo(OK);
        MessageVo message = toMessageVo(result);
        assertThat(message.getMessage().getCode()).isEqualTo("0000");
        assertThat(message.getMessage().getSummary()).isEqualTo("操作成功");

    }

    @Test
    public void testGetAllCapital4Prd() throws Exception {

        Logger.info("============testGetAllCapital4Prd start====");
        Map<String, String> formParams = new HashMap<String, String>();

        play.mvc.Result result = getResult("/account/accountital/allcapital4prd", formParams, cookie);
        Logger.info("============testGetAllCapital4Prd result====\n" + contentAsString(result));
        assertThat(status(result)).isEqualTo(OK);
        MessageVo message = toMessageVo(result);
        assertThat(message.getMessage().getCode()).isEqualTo("0000");
        assertThat(message.getMessage().getSummary()).isEqualTo("操作成功");

    }

    @Test
    public void testGetMyCapital() throws Exception {

        Logger.info("============testGetMyCapital start====");
        Map<String, String> formParams = new HashMap<String, String>();

        play.mvc.Result result = getResult("/account/accountital/mycapital", formParams, cookie);

        Logger.info("============testGetMyCapital result====\n" + contentAsString(result));

        assertThat(status(result)).isEqualTo(OK);
        MessageVo message = toMessageVo(result);
        assertThat(message.getMessage().getCode()).isEqualTo("0000");
        assertThat(message.getMessage().getSummary()).isEqualTo("操作成功");

    }

    @Test
    public void testFindYesterdayProfitList() throws Exception {

        Logger.info("============testFindYesterdayProfitList start====");
        Map<String, String> formParams = new HashMap<String, String>();
        formParams.put("index", "0");
        formParams.put("pageSize", "2");

        play.mvc.Result result = getResult("/account/accountital/yesterdayprofit", formParams, cookie);

        Logger.info("============testFindYesterdayProfitList result====\n" + contentAsString(result));

        assertThat(status(result)).isEqualTo(OK);
        MessageVo message = toMessageVo(result);
        assertThat(message.getMessage().getCode()).isEqualTo("0000");
        assertThat(message.getMessage().getSummary()).isEqualTo("操作成功");


    }


}
