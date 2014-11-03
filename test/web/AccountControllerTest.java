package web;

import org.junit.Test;
import play.test.FakeRequest;

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
public class AccountControllerTest {

    @Test
    public void testRegisterBaseAccount() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Map<String, String> formParams = new HashMap<String, String>();
                formParams.put("mobile", "15821948594");
                FakeRequest formRequest = fakeRequest(POST, "/account/accountital/totalcapital").withFormUrlEncodedBody(formParams);
                play.mvc.Result result = route(formRequest);
                assertThat(contentAsString(result).concat("0000"));

            }
        });
    }

    @Test
    public void testGetAllCapital4Prd() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Map<String, String> formParams = new HashMap<String, String>();
                formParams.put("mobile", "15821948594");
                FakeRequest formRequest = fakeRequest(POST, "/account/accountital/getAllCapital4Prd").withFormUrlEncodedBody(formParams);
                play.mvc.Result result = route(formRequest);
                assertThat(contentAsString(result).concat("0000"));

            }
        });
    }

}
