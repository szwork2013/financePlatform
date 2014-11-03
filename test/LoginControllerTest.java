import org.junit.Test;
import play.test.FakeRequest;

import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;
import static play.test.Helpers.contentAsString;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: LoginControllerTest.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class LoginControllerTest {

    @Test
    public void register() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Map<String, String> formParams = new HashMap<String, String>();
                formParams.put("mobilePhoneNo", "18522222223");
                formParams.put("deviceNo", "deviceNo");
                formParams.put("type", "REGISTER");
                formParams.put("verifyCode", "634834");
                formParams.put("passWord", "1");

                FakeRequest formRequest = fakeRequest(POST, "/customer/login/register").withFormUrlEncodedBody(formParams);
                play.mvc.Result result = route(formRequest);

                assertThat(contentAsString(result).concat("0000"));
                System.out.println("result is " + contentAsString(result));

            }
        });
    }

    @Test
    public void getverifycode() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Map<String, String> formParams = new HashMap<String, String>();
                formParams.put("mobilePhoneNo", "18522222223");
                formParams.put("deviceNo", "deviceNo");
                formParams.put("type", "REGISTER");

                FakeRequest formRequest = fakeRequest(POST, "/customer/login/verificationcode").withFormUrlEncodedBody(formParams);
                play.mvc.Result result = route(formRequest);
                System.out.println("result is " + contentAsString(result));
                assertThat(contentAsString(result).concat("0000"));

            }
        });
    }


}
