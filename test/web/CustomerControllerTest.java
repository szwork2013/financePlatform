package web;

import com.sunlights.common.vo.MessageVo;
import org.junit.Test;
import play.test.FakeRequest;

import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.*;
import static play.test.Helpers.*;
import static play.test.Helpers.route;

public class CustomerControllerTest {

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
                MessageVo message = TestUtil.toMessageVo(result);
                assertThat(message.getMessage().getCode()).isEqualTo("0000");
                assertThat(message.getMessage().getSummary()).isEqualTo("操作成功");
            }
        });
    }


}