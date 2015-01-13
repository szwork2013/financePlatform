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

<<<<<<< HEAD
=======

>>>>>>> master
public class RewardFlowServiceImplTest extends WithApplication {

    @Test
    public void testFindTodayFlowByCustIdAndScene() throws Exception {
        JPA.withTransaction(new F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                RewardFlowService rewardFlowService = new RewardFlowServiceImpl();
                RewardFlow rewardFlows = rewardFlowService.findTodayFlowByCustIdAndScene("20141119102210010000000029", "ASC002");
                if (rewardFlows != null) {
<<<<<<< HEAD

=======
                    Logger.info("---------");
>>>>>>> master
                    assertThat("20141119102210010000000029").isEqualTo(rewardFlows.getCustId());
                }
            }
        });

    }
}