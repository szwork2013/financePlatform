package com.sunlights.customer.web;

import com.sunlights.BaseTest;
import com.sunlights.common.AppConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.vo.MessageVo;
import org.junit.Test;
import play.Logger;
import play.test.FakeRequest;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;
import static play.test.Helpers.contentAsString;

public class ActivityReportControllerTest extends BaseTest {

    @Test
    public void testGetActivityReports() throws Exception {
        FakeRequest fakeRequest = fakeRequest(GET, "/customer/activity/report");
        fakeRequest.withHeader(AppConst.HEADER_USER_AGENT, "Mozilla/5.0 (iPhone; CPU iPhone OS 7_1 like Mac OS X) AppleWebKit/537.51.2 (KHTML, like Gecko) Mobile/11D167\\jindoujialicai\\1.3");


        play.mvc.Result result = route(fakeRequest);

        assertThat(status(result)).isEqualTo(OK);

        final MessageVo message = toMessageVo(result);

        Logger.info("============testCheckVersionReminderUpdateIOS result====\n" + contentAsString(result));

        assertThat(message.getMessage().getCode()).isEqualTo(MsgCode.OBTAIN_SUCC.getCode());
    }
}