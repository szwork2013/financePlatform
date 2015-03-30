package com.sunlights.op.vo.activity;

/**
 * Created by tangweiqun on 2014/12/11.
 */
public enum ActivityType {

    RIGISTER("ATT001","注册类"),
    FIRST_PURCHASE("ATT002","首次购买类"),
    PURCHASE("ATT003","购买类"),
    SIGNIN("ATT004","签到类"),
    INVITE("ATT005","邀请类");


    private String type;

    private String desc;

    private ActivityType(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public static String getDescByType(String type) {
        if(type == null) {
            return null;
        }
        for(ActivityType exchangeResultStatus : ActivityType.values()) {
            if(type.equals(exchangeResultStatus.getType())) {
                return exchangeResultStatus.getDesc();
            }
        }
        return null;
    }
}
