package com.sunlights.account.service.impl;

import com.sunlights.account.service.RewardFlowService;
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

public class RewardFlowServiceImplTest {

    @Test
    public void testFindTodayFlowByCustIdAndScene() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                JPA.withTransaction(new F.Callback0() {
                    @Override
                    public void invoke() throws Throwable {
                        RewardFlowService rewardFlowService = new RewardFlowServiceImpl();
                        RewardFlow rewardFlows = rewardFlowService.findTodayFlowByCustIdAndScene("20141119102210010000000029", "ASC002");
                        if(rewardFlows != null) {

                            assertThat("20141119102210010000000029").isEqualTo(rewardFlows.getCustId());
                        }
                    }
                });

            }
        });
    }
}