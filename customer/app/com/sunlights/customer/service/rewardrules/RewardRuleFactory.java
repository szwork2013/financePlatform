package com.sunlights.customer.service.rewardrules;


import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.service.rewardrules.exchange.ExchangeHandlerAdapter;
import com.sunlights.customer.service.rewardrules.exchange.ExchangeHandlerMapping;
import com.sunlights.customer.service.rewardrules.exchange.RedPacketExchangeRuleHandler;
import com.sunlights.customer.service.rewardrules.obtain.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tangweiqun on 2014/11/19.
 */
public class RewardRuleFactory {

    @Deprecated
    public static IObtainRewardRule getIObtainRuleHandler(String scene) {
        if(ActivityConstant.ACTIVITY_INVITE_SCENE_CODE.equals(scene)) {
            return new InviteObtainRewardRule();
        } else if(ActivityConstant.ACTIVITY_SIGNIN_SCENE_CODE.equals(scene)) {
            return new SignInObtainRewardRule();
        } else if(ActivityConstant.ACTIVITY_REGISTER_SCENE_CODE.equals(scene)) {
            return new RegisterObtainRewardRule();
        } else if(ActivityConstant.ACTIVITY_FIRST_PURCHASE_SCENE_CODE.equals(scene)) {
            return new FirstPurchseObtainRewardRule();
        }
        return null;
    }

    public static List<HandlerAdapter> getHandlerAdapters() {
        List<HandlerAdapter> list = new ArrayList<HandlerAdapter>();
        list.add(new ObtainHandlerAdapter());
        list.add(new ExchangeHandlerAdapter());
        return list;
    }

    public static List<HandlerMapping> getHandlerMapping() {
        List<HandlerMapping> list = new ArrayList<HandlerMapping>();
        list.add(new ObtainHandlerMapping());
        list.add(new ExchangeHandlerMapping());
        return list;
    }

    public static Map<String, Object> getObtainRuleHandlerMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ActivityConstant.ACTIVITY_REGISTER_SCENE_CODE, new RegisterObtainRuleHandler());
        map.put(ActivityConstant.ACTIVITY_PURCHASE_SCENE_CODE, new PurchaseObtainRuleHandler());
        map.put(ActivityConstant.ACTIVITY_SIGNIN_SCENE_CODE, new SigninObtainRuleHandler());

        map.put(ActivityConstant.ACTIVITY_EXCHANGE_RED_PACKET_SCENE_CODE, new RedPacketExchangeRuleHandler());
        return map;
    }
}
