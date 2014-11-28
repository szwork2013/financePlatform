package com.sunlights.account.service.rewardrules;

import com.sunlights.account.dal.ObtainRewardRuleDao;
import com.sunlights.account.dal.impl.ObtainRewardRuleDaoImpl;
import com.sunlights.account.vo.RewardResultVo;
import com.sunlights.common.MsgCode;
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
                        ObtainRewardRuleDao obtainRewardRuleDao = new ObtainRewardRuleDaoImpl();
                        List<Trade> trades = obtainRewardRuleDao.getTradesByCustId("1111");
                        if(trades != null && trades.size() > 0) {
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

                        ObtainRewardRuleDao obtainRewardRuleDao = new ObtainRewardRuleDaoImpl();
                        List<Trade> trades = obtainRewardRuleDao.getTradesByCustId("1111");
                        if(trades != null && trades.size() > 0) {
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