package com.sunlights.customer.service.impl;

import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.service.CustJoinActivityService;
import com.sunlights.customer.service.rewardrules.ActivityHandlerService;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import org.junit.Test;
import play.db.jpa.JPA;
import play.libs.F;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

public class CustJoinActivityServiceImplTest {

    @Test
    public void testGetShortUrl() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                JPA.withTransaction(new F.Callback0() {
                    @Override
                    public void invoke() throws Throwable {

                        CustJoinActivityService custJoinActivityService = new CustJoinActivityServiceImpl();
                        custJoinActivityService.getShortUrl("20141119102210010000000029", 1L, ActivityConstant.ACTIVITY_INVITE_SCENE_CODE);

                    }
                });

            }
        });
    }

    @Test
    public void testSaveShortUrl() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                JPA.withTransaction(new F.Callback0() {
                    @Override
                    public void invoke() throws Throwable {



                    }
                });

            }
        });
    }
}