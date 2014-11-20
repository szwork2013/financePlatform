package com.sunlights.account.dal.impl;

import com.sunlights.account.dal.RewardFlowDao;
import com.sunlights.account.service.rewardrules.SignInObtainRewardRule;
import com.sunlights.account.vo.RewardResultVo;
import com.sunlights.common.MsgCode;
import models.RewardFlow;
import org.junit.Test;
import play.Logger;
import play.db.jpa.JPA;
import play.libs.F;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

public class RewardFlowDaoImplTest {

    @Test
    public void testFindOneByCondition() throws Exception {

        running(fakeApplication(), new Runnable() {
            public void run() {
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
        });

    }
}