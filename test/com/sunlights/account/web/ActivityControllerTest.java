package com.sunlights.account.web;

import com.sunlights.BaseTest;
import com.sunlights.account.vo.ActivityParamter;
import com.sunlights.common.MsgCode;
import com.sunlights.common.vo.MessageVo;
import com.sunlights.common.vo.PageVo;
import org.junit.Test;
import play.Logger;
import play.libs.Json;
import play.test.FakeRequest;

import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

public class ActivityControllerTest extends BaseTest{

    @Test
    public void testGetActivityList() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Logger.info("============testGetActivityList start====");
                String index = "0";
                String pageSize = "3";


                Map<String, String> formParams = new HashMap<>();
                formParams.put("index", index);
                formParams.put("pageSize", pageSize);


                play.mvc.Result result = getResult("/account/activity/list", formParams);
                Logger.info("============testGetActivityList result====\n" + contentAsString(result));

            }
        });


    }
}