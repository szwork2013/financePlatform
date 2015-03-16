package com.sunlights.core.web;

import com.sunlights.BaseTest;
import com.sunlights.common.AppConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.vo.MessageVo;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.service.impl.CustJoinActivityServiceImpl;
import models.CustJoinActivity;
import org.junit.Test;
import play.Logger;
import play.test.FakeRequest;

import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;
import static play.test.Helpers.route;

public class VersionCheckControllerTest extends BaseTest {

    @Test
    public void testCheckVersionReminderUpdateIOS() throws Exception {

        FakeRequest fakeRequest = fakeRequest(POST, "/core/version");
        fakeRequest.withHeader(AppConst.HEADER_USER_AGENT, "Mozilla/5.0 (iPhone; CPU iPhone OS 7_1 like Mac OS X) AppleWebKit/537.51.2 (KHTML, like Gecko) Mobile/11D167\\jindoujialicai\\1.3");


        play.mvc.Result result = route(fakeRequest);

        assertThat(status(result)).isEqualTo(OK);

        final MessageVo message = toMessageVo(result);

        Logger.info("============testCheckVersionReminderUpdateIOS result====\n" + contentAsString(result));

        assertThat(message.getMessage().getCode()).isEqualTo(MsgCode.REMIND_UPDATE_VERSION.getCode());
    }

    @Test
    public void testCheckVersionMustUpdateIOS() throws Exception {

        FakeRequest fakeRequest = fakeRequest(POST, "/core/version");
        fakeRequest.withHeader(AppConst.HEADER_USER_AGENT, "Mozilla/5.0 (iPhone; CPU iPhone OS 7_1 like Mac OS X) AppleWebKit/537.51.2 (KHTML, like Gecko) Mobile/11D167\\jindoujialicai\\1.2");


        play.mvc.Result result = route(fakeRequest);

        assertThat(status(result)).isEqualTo(OK);

        final MessageVo message = toMessageVo(result);

        Logger.info("============testSignInObtainReward result====\n" + contentAsString(result));

        assertThat(message.getMessage().getCode()).isEqualTo(MsgCode.UPDATE_VERSION_TO_CURRENT.getCode());
    }

    @Test
    public void testCheckVersionLatestVersionIOS() throws Exception {

        FakeRequest fakeRequest = fakeRequest(POST, "/core/version");
        fakeRequest.withHeader(AppConst.HEADER_USER_AGENT, "Mozilla/5.0 (iPhone; CPU iPhone OS 7_1 like Mac OS X) AppleWebKit/537.51.2 (KHTML, like Gecko) Mobile/11D167\\jindoujialicai\\1.6");


        play.mvc.Result result = route(fakeRequest);

        assertThat(status(result)).isEqualTo(OK);

        final MessageVo message = toMessageVo(result);

        Logger.info("============testCheckVersionLatestVersion result====\n" + contentAsString(result));

        assertThat(message.getMessage().getCode()).isEqualTo(MsgCode.CURRENT_LATEST_VERSION.getCode());
    }

    @Test
    public void testCheckVersionReminderUpdateAndroid() throws Exception {

        FakeRequest fakeRequest = fakeRequest(POST, "/core/version");
        fakeRequest.withHeader(AppConst.HEADER_USER_AGENT, "Mozilla/5.0 (Android; CPU Android OS 7_1 like Mac OS X) AppleWebKit/537.51.2 (KHTML, like Gecko) Mobile/11D167\\jindoujialicai\\1.2");


        play.mvc.Result result = route(fakeRequest);

        assertThat(status(result)).isEqualTo(OK);

        final MessageVo message = toMessageVo(result);

        Logger.info("============testSignInObtainReward result====\n" + contentAsString(result));

        assertThat(message.getMessage().getCode()).isEqualTo(MsgCode.REMIND_UPDATE_VERSION.getCode());
    }

    @Test
    public void testCheckVersionMustUpdateAndroid() throws Exception {

        FakeRequest fakeRequest = fakeRequest(POST, "/core/version");
        fakeRequest.withHeader(AppConst.HEADER_USER_AGENT, "Mozilla/5.0 (Android; CPU Android OS 7_1 like Mac OS X) AppleWebKit/537.51.2 (KHTML, like Gecko) Mobile/11D167\\jindoujialicai\\0.9");


        play.mvc.Result result = route(fakeRequest);

        assertThat(status(result)).isEqualTo(OK);

        final MessageVo message = toMessageVo(result);

        Logger.info("============testSignInObtainReward result====\n" + contentAsString(result));

        assertThat(message.getMessage().getCode()).isEqualTo(MsgCode.UPDATE_VERSION_TO_CURRENT.getCode());
    }

    @Test
    public void testCheckVersionLatestVersionAndroid() throws Exception {

        FakeRequest fakeRequest = fakeRequest(POST, "/core/version");
        fakeRequest.withHeader(AppConst.HEADER_USER_AGENT, "Mozilla/5.0 (Android; CPU Android OS 7_1 like Mac OS X) AppleWebKit/537.51.2 (KHTML, like Gecko) Mobile/11D167\\jindoujialicai\\1.3");


        play.mvc.Result result = route(fakeRequest);

        assertThat(status(result)).isEqualTo(OK);

        final MessageVo message = toMessageVo(result);

        Logger.info("============testCheckVersionLatestVersion result====\n" + contentAsString(result));

        assertThat(message.getMessage().getCode()).isEqualTo(MsgCode.CURRENT_LATEST_VERSION.getCode());
    }

    @Test
    public void refreshVersion() throws Exception {

        FakeRequest fakeRequest = fakeRequest(GET, "/core/version/ios");
        fakeRequest.withHeader(AppConst.HEADER_USER_AGENT, "Mozilla/5.0 (iPhone; CPU iPhone OS 7_1 like Mac OS X) AppleWebKit/537.51.2 (KHTML, like Gecko) Mobile/11D167\\jindoujialicai\\1.3");


        play.mvc.Result result = route(fakeRequest);

        assertThat(status(result)).isEqualTo(OK);

        final MessageVo message = toMessageVo(result);

        Logger.info("============refreshVersion result====\n" + contentAsString(result));

        assertThat(message.getMessage().getCode()).isEqualTo(MsgCode.OPERATE_SUCCESS.getCode());
    }
}