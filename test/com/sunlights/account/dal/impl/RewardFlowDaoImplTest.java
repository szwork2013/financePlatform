package com.sunlights.account.dal.impl;


import com.sunlights.customer.dal.RewardFlowDao;
import com.sunlights.customer.dal.impl.RewardFlowDaoImpl;
import models.RewardFlow;
import org.junit.Test;
import play.Logger;
import play.db.jpa.JPA;
import play.libs.F;
import play.test.WithApplication;

import java.util.List;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

public class RewardFlowDaoImplTest extends WithApplication {

    @Test
    public void testFindOneByCondition() throws Exception {

        JPA.withTransaction(new F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                RewardFlowDao rewardFlowDao = new RewardFlowDaoImpl();
                RewardFlow rewardFlow = new RewardFlow();
                rewardFlow.setCustId("20141119102210010000000029");
                rewardFlow.setRewardType("ART001");
                List<RewardFlow> rewardFlows = rewardFlowDao.findByCondition(rewardFlow);
                Logger.info(rewardFlows == null ? null : rewardFlows.size() + "");

            }
        });

    }
}