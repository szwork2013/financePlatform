package com.sunlights.account.service.rewardrules;


import com.sunlights.common.MsgCode;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.dal.ObtainRewardRuleDao;
import com.sunlights.customer.dal.RewardFlowDao;
import com.sunlights.customer.dal.impl.ObtainRewardRuleDaoImpl;
import com.sunlights.customer.dal.impl.RewardFlowDaoImpl;
import com.sunlights.customer.service.RewardFlowService;
import com.sunlights.customer.service.impl.CustJoinActivityServiceImpl;
import com.sunlights.customer.service.impl.RewardFlowServiceImpl;
import com.sunlights.customer.service.rewardrules.FirstPurchseObtainRewardRule;
import com.sunlights.customer.service.rewardrules.IObtainRewardRule;
import com.sunlights.customer.vo.RewardResultVo;
import models.CustJoinActivity;
import models.RewardFlow;
import models.Trade;
import org.junit.Test;
import play.Logger;
import play.db.jpa.JPA;
import play.libs.F;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

public class FirstPurchaseObtainRewardRuleTest {
    private IObtainRewardRule iObtainRewardRule = null;

    @Test
    public void testObtainReward() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                JPA.withTransaction(new F.Callback0() {
                    @Override
                    public void invoke() throws Throwable {
                        iObtainRewardRule = new FirstPurchseObtainRewardRule();
                        RewardResultVo vo = iObtainRewardRule.obtainReward("1111", null);
                        Logger.info("rewardResultVo = " + vo);

                        RewardFlowService rewardFlowService = new RewardFlowServiceImpl();
                        RewardFlow rewardFlow = rewardFlowService.findTodayFlowByCustIdAndScene("", ActivityConstant.ACTIVITY_FIRST_PURCHASE_SCENE_CODE);
                        if(rewardFlow != null) {
                            assertThat(vo.getReturnMessage().getCode()).isEqualTo(MsgCode.ALREADY_PURCHASE.getCode());
                        } else {
                            assertThat(vo.getReturnMessage().getCode()).isEqualTo(MsgCode.OBTAIN_SUCC.getCode());
                        }

                    }
                });

            }
        });

    }

    @Test
    public void testGetCanObtainRewards() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                JPA.withTransaction(new F.Callback0() {
                    @Override
                    public void invoke() throws Throwable {
                        iObtainRewardRule = new FirstPurchseObtainRewardRule();
                        RewardResultVo vo = iObtainRewardRule.getCanObtainRewards("1111", null);
                        Logger.info("rewardResultVo = " + vo);
                        RewardFlowService rewardFlowService = new RewardFlowServiceImpl();
                        RewardFlow rewardFlow = rewardFlowService.findTodayFlowByCustIdAndScene("", ActivityConstant.ACTIVITY_FIRST_PURCHASE_SCENE_CODE);

                        if(rewardFlow != null) {
                            assertThat(vo.getReturnMessage().getCode()).isEqualTo(MsgCode.ALREADY_PURCHASE.getCode());
                        } else {
                            assertThat(vo.getReturnMessage().getCode()).isEqualTo(MsgCode.ACTIVITY_QUERY_SUCC.getCode());
                        }
                    }
                });

            }
        });
    }
}