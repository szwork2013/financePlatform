package com.sunlights.op.vo.activity;

/**
 * Created by tangweiqun on 2014/12/11.
 */
public enum SceneType {

    SIGNIN("ASC001","签到赚金豆"),
    INVITER("ASC002","邀请好友场景"),
    REGISTER("ASC004","注册送金豆"),
    FIRT_PURCHASE("ASC005","首次购买"),
    PURCHASE("ASC006","购买推荐场景"),
    EXCHANGE_REDPACKET("EXC001","红包取现");


    private String type;

    private String desc;

    private SceneType(String type, String desc) {
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
        for(SceneType exchangeResultStatus : SceneType.values()) {
            if(type.equals(exchangeResultStatus.getType())) {
                return exchangeResultStatus.getDesc();
            }
        }
        return null;
    }
}
