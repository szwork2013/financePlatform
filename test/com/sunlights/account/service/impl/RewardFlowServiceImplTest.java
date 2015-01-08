package com.sunlights.account.service.impl;


import com.sunlights.customer.service.RewardFlowService;
import com.sunlights.customer.service.impl.RewardFlowServiceImpl;
import models.RewardFlow;
import org.junit.Test;
import play.Logger;
import play.db.jpa.JPA;
import play.libs.F;
import play.test.WithApplication;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

public class RewardFlowServiceImplTest extends WithApplication {

    @Test
    public void testFindTodayFlowByCustIdAndScene() throws Exception {

                        RewardFlowService rewardFlowService = new RewardFlowServiceImpl();
                        RewardFlow rewardFlows = rewardFlowService.findTodayFlowByCustIdAndScene("20141119102210010000000029", "ASC002");
                        if(rewardFlows != null) {
                            Logger.info("---------");
                            assertThat("20141119102210010000000029").isEqualTo(rewardFlows.getCustId());
                        }

    }
}