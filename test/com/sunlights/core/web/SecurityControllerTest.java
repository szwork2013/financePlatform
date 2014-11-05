package com.sunlights.core.web;

import com.sunlights.BaseTest;
import com.sunlights.common.vo.MessageVo;
import org.junit.Test;
import play.test.FakeRequest;

import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

public class SecurityControllerTest extends BaseTest {

    @Test
    public void testGenVerificationCode() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Map<String, String> formParams = new HashMap<>();
                formParams.put("mobilePhoneNo", "18522222225");
                formParams.put("deviceNo", "deviceNo");
                formParams.put("type", "REGISTER");

                FakeRequest formRequest = fakeRequest(POST, "/core/verificationcode").withHeader("Content-Type", "application/x-www-form-urlencoded").withFormUrlEncodedBody(formParams);
                play.mvc.Result result = route(formRequest);
                assertThat(status(result)).isEqualTo(OK);
                MessageVo message = toMessageVo(result);
                assertThat(message.getMessage().getCode()).isEqualTo("0000");
                assertThat(message.getMessage().getSummary()).isEqualTo("操作成功");
            }
        });
    }
    @Test
    public void testCertify() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Map<String, String> formParams = new HashMap<>();
                formParams.put("userName", "testusername");
                formParams.put("idCardNo", "254545454545454554");

                FakeRequest formRequest = fakeRequest(POST, "/core/certify").withHeader("Content-Type", "application/x-www-form-urlencoded").withFormUrlEncodedBody(formParams);
                play.mvc.Result result = route(formRequest);
                assertThat(status(result)).isEqualTo(OK);
                MessageVo message = toMessageVo(result);
                assertThat(message.getMessage().getSeverity() == 0);
            }
        });
    }
    @Test
    public void testCertifyAndResetTradePwd() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Map<String, String> formParams = new HashMap<>();
                formParams.put("userName", "testusername");
                formParams.put("idCardNo", "254545454545454554");
                formParams.put("passWord", "0000");

                FakeRequest formRequest = fakeRequest(POST, "/core/certify").withHeader("Content-Type", "application/x-www-form-urlencoded").withFormUrlEncodedBody(formParams);
                play.mvc.Result result = route(formRequest);
                assertThat(status(result)).isEqualTo(OK);
                MessageVo message = toMessageVo(result);
                assertThat(message.getMessage().getSeverity() == 0);
            }
        });
    }
}
