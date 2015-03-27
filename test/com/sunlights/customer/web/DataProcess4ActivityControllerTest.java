package com.sunlights.customer.web;

import com.sunlights.BaseTest;
import org.junit.Test;
import play.Logger;
import play.test.FakeRequest;


import static org.junit.Assert.*;
import static play.test.Helpers.*;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.fakeRequest;

public class DataProcess4ActivityControllerTest extends BaseTest {

    @Test
    public void testGetRegisterNumbers4Activity() throws Exception {

        FakeRequest fakeRequest = fakeRequest(GET, "/account/activity/registers/count");

        setHeader(fakeRequest);

        play.mvc.Result result = route(fakeRequest);

        Logger.info("testGetRegisterNumbers4Activity is----" + contentAsString(result));
    }
}