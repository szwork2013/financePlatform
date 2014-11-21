package com.sunlights.account.service.rewardrules;

import com.sunlights.account.vo.RewardResultVo;
import com.sunlights.common.MsgCode;
import org.junit.Test;
import play.Logger;
import play.db.jpa.JPA;
import play.libs.F;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

public class InviteObtainRewardRuleTest {
    private IObtainRewardRule iObtainRewardRule = null;

    @Test
    public void testObtainReward() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                JPA.withTransaction(new F.Callback0() {
                    @Override
                    public void invoke() throws Throwable {
                        iObtainRewardRule = new InviteObtainRewardRule();
                        RewardResultVo vo = iObtainRewardRule.obtainReward("testtang");
                        Logger.info("rewardResultVo = " + vo);
                        assertThat(vo.getReturnMessage().getCode()).isEqualTo(MsgCode.OBTAIN_SUCC.getCode());
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
                        iObtainRewardRule = new InviteObtainRewardRule();
                        RewardResultVo vo = iObtainRewardRule.getCanObtainRewards("testtang");
                        Logger.info("rewardResultVo = " + vo);
                        assertThat(vo.getReturnMessage().getCode()).isEqualTo(MsgCode.ACTIVITY_QUERY_SUCC.getCode());
                    }
                });

            }
        });
    }
}