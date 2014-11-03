package web;

import com.fasterxml.jackson.databind.JsonNode;
import com.sunlights.common.vo.MessageVo;
import org.junit.Test;
import play.libs.Json;
import play.mvc.Result;
import play.test.FakeRequest;

import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.*;
import static play.test.Helpers.*;
import static play.test.Helpers.route;

public class CustomerControllerTest extends BaseTest{

    @Test
    public void testGenVerificationCode() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Map<String, String> formParams = new HashMap<>();
                formParams.put("mobilePhoneNo", "18522222223");
                formParams.put("deviceNo", "deviceNo");
                formParams.put("type", "REGISTER");

                FakeRequest formRequest = fakeRequest(POST, "/customer/verificationcode").withFormUrlEncodedBody(formParams);
                play.mvc.Result result = route(formRequest);
                assertThat(status(result)).isEqualTo(OK);
                MessageVo message = toMessageVo(result);
                assertThat(message.getMessage().getCode()).isEqualTo("0000");
                assertThat(message.getMessage().getSummary()).isEqualTo("操作成功");
            }
        });
    }


}