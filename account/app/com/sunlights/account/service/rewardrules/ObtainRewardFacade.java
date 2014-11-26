package com.sunlights.account.service.rewardrules;

import com.sunlights.account.AccountConstant;
import com.sunlights.account.vo.ObtainRewardVo;
import com.sunlights.account.vo.RewardResultVo;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.vo.Message;
import play.Logger;
import play.db.jpa.JPA;
import play.libs.F;

/**
 * Created by tangweiqun on 2014/11/25.
 *
 * 提供给外部模块来调用的服务
 *
 */
public class ObtainRewardFacade {

    public void obtainReward(final String custNo, final String scene, final Long activityId) {

                new Thread(new Runnable() {
                    @Override
                    public void run () {
                        JPA.withTransaction(new F.Callback0() {
                            @Override
                            public void invoke() throws Throwable {

                                IObtainRewardRule iObtainRewardRule = RewardRuleFactory.getIObtainRuleHandler(scene);

                                if (iObtainRewardRule == null) {
                                    Logger.info("还没有配置签到的场景");
                                    return;
                                }
                                //4:处理获取奖励
                                RewardResultVo rewardResultVo = iObtainRewardRule.obtainReward(custNo, activityId);
                            }
                        });
                    }
                }).start();
        }
    }
