package web;

import org.junit.Test;
import play.mvc.Result;
import play.test.FakeRequest;

import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.*;
import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.route;

public class CustomerControllerTest {

    @Test
    public void testGenVerificationCode() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Map<String, String> formParams = new HashMap<String, String>();
                formParams.put("mobilePhoneNo", "18522222223");
                formParams.put("deviceNo", "deviceNo");
                formParams.put("type", "REGISTER");

                FakeRequest formRequest = fakeRequest(POST, "/customer/verificationcode").withFormUrlEncodedBody(formParams);
                play.mvc.Result result = route(formRequest);
                assertThat(contentAsString(result).concat("0000"));

            }
        });
    }
}