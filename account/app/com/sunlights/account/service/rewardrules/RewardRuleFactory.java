package com.sunlights.account.service.rewardrules;

import com.sunlights.account.AccountConstant;
import com.sunlights.account.service.rewardrules.exchange.ExchangeHandlerAdapter;
import com.sunlights.account.service.rewardrules.exchange.ExchangeHandlerMapping;
import com.sunlights.account.service.rewardrules.obtain.ObtainHandlerAdapter;
import com.sunlights.account.service.rewardrules.obtain.ObtainHandlerMapping;
import com.sunlights.account.service.rewardrules.obtain.RegisterObtainRuleHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tangweiqun on 2014/11/19.
 */
public class RewardRuleFactory {

    public static IObtainRewardRule getIObtainRuleHandler(String scene) {
        if(AccountConstant.ACTIVITY_INVITE_SCENE_CODE.equals(scene)) {
            return new InviteObtainRewardRule();
        } else if(AccountConstant.ACTIVITY_SIGNIN_SCENE_CODE.equals(scene)) {
            return new SignInObtainRewardRule();
        } else if(AccountConstant.ACTIVITY_REGISTER_SCENE_CODE.equals(scene)) {
            return new RegisterObtainRewardRule();
        } else if(AccountConstant.ACTIVITY_FIRST_PURCHASE_SCENE_CODE.equals(scene)) {
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
        map.put(AccountConstant.ACTIVITY_REGISTER_SCENE_CODE, new RegisterObtainRuleHandler());
        return map;
    }
}
