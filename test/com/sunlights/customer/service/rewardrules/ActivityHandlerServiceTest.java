package com.sunlights.customer.service.rewardrules;


import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import org.junit.Test;
import play.db.jpa.JPA;
import play.libs.F;
import play.test.WithApplication;

import java.math.BigDecimal;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

public class ActivityHandlerServiceTest extends WithApplication {

    private ActivityHandlerService activityHandlerService = null;

    @Test
    public void testRedPacketExchangeService() throws Exception {
<<<<<<< HEAD
        JPA.withTransaction(new F.Callback0() {
            @Override
            public void invoke() throws Throwable {

                ActivityRequestVo requestVo = new ActivityRequestVo();
                ActivityResponseVo responseVo = new ActivityResponseVo();
                requestVo.set("exchangeAmt", BigDecimal.valueOf(0.1));
                requestVo.setRewardType("ART002");
                requestVo.setCustId("20141027100357461");
                requestVo.setScene(ActivityConstant.ACTIVITY_EXCHANGE_RED_PACKET_SCENE_CODE);

                activityHandlerService = new ActivityHandlerService();
=======

                JPA.withTransaction(new F.Callback0() {
                    @Override
                    public void invoke() throws Throwable {
                        ActivityRequestVo requestVo = new ActivityRequestVo();
                        ActivityResponseVo responseVo = new ActivityResponseVo();
                        requestVo.set("exchangeAmt", BigDecimal.valueOf(0.1));
                        requestVo.setRewardType("ART002");
                        requestVo.setCustId("20141027100357461");
                        requestVo.setScene(ActivityConstant.ACTIVITY_EXCHANGE_RED_PACKET_SCENE_CODE);
                        activityHandlerService = new ActivityHandlerService();
                        activityHandlerService.service(requestVo, responseVo);
>>>>>>> master

                activityHandlerService.service(requestVo, responseVo);

<<<<<<< HEAD
            }
        });
=======
>>>>>>> master

    }

    @Test
    public void testPurchaseObtainService() throws Exception {
                JPA.withTransaction(new F.Callback0() {
                    @Override
                    public void invoke() throws Throwable {

                        ActivityRequestVo requestVo = new ActivityRequestVo();
                        ActivityResponseVo responseVo = new ActivityResponseVo();
                        requestVo.setCustId("20141027100357461");
                        requestVo.set("prdCode", "");
                        requestVo.set("supplySum", BigDecimal.valueOf(300));
                        requestVo.setScene(ActivityConstant.ACTIVITY_PURCHASE_SCENE_CODE);

                        activityHandlerService = new ActivityHandlerService();
                        activityHandlerService.service(requestVo, responseVo);

                    }
                });

    }
}