package web;

import junit.framework.TestCase;
import play.test.FakeRequest;

import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

public class CapitalControllerTest extends TestCase {

    public void testGetTotalCapitalInfo() throws Exception {
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

    public void testGetMyCapital() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Map<String, String> formParams = new HashMap<String, String>();
                formParams.put("mobile", "15821948594");
                FakeRequest formRequest = fakeRequest(POST, "/account/accountital/mycapital").withFormUrlEncodedBody(formParams);
                play.mvc.Result result = route(formRequest);
                assertThat(contentAsString(result).concat("0000"));

            }
        });

    }

    public void testFindTotalProfitList() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Map<String, String> formParams = new HashMap<String, String>();
                formParams.put("mobile", "15821948594");
                FakeRequest formRequest = fakeRequest(POST, "/account/accountital/findtotalprofits").withFormUrlEncodedBody(formParams);
                play.mvc.Result result = route(formRequest);
                assertThat(contentAsString(result).concat("0000"));

            }
        });
    }

    public void testFindTotalProfitDetail() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Map<String, String> formParams = new HashMap<String, String>();
                formParams.put("mobile", "15821948594");
                FakeRequest formRequest = fakeRequest(POST, "/account/accountital/findtotalprofitdetail").withFormUrlEncodedBody(formParams);
                play.mvc.Result result = route(formRequest);
                assertThat(contentAsString(result).concat("0000"));

            }
        });
    }
}