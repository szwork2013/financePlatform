package com.sunlights.customer.service.impl;

import com.sunlights.customer.service.ActivityService;
import org.junit.Test;
import play.db.jpa.JPA;
import play.libs.F;

import java.util.List;

import static org.junit.Assert.*;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

public class ActivityServiceImplTest {

    @Test
    public void testGetActivityTitles() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                JPA.withTransaction(new F.Callback0() {
                    @Override
                    public void invoke() throws Throwable {
                        ActivityService activityService = new ActivityServiceImpl();
                        List<String> titles = activityService.getActivityTitles("3333");
                    }
                });

            }
        });
    }
}