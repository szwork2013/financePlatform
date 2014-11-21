package com.sunlights.common.web;

import org.junit.Test;
import play.Logger;
import play.libs.Json;
import play.test.FakeRequest;

import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

public class CommonControllerTest {

    @Test
    public void testFindDictsByCat() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), new Runnable() {

            public void run() {

                FakeRequest banksRequest = fakeRequest(POST, "/common/dicts");
                Map<String, String> paramMap = new HashMap<String, String>();
                paramMap.put("cat", "FP.RECOMMEND.TYPE");
                Logger.info("[paramMap]" + paramMap);

                FakeRequest jsonRequest = banksRequest.withHeader("Content-Type", "application/json").withJsonBody(Json.toJson(paramMap));
                play.mvc.Result result = route(jsonRequest);

                Logger.info("result is " + contentAsString(result));

                assertThat(contentAsString(result)).toString();

            }

        });
    }
}