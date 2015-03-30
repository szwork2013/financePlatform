package com.sunlights.op.vo.activity;

/**
 * Created by tangweiqun on 2014/12/11.
 */
public enum ShareInfoType {
    APP("0","分享APP"),
    PRODUCT("1","分享产品"),
    PROFIT("2","分享收益"),
    ACTIVITY("3","分享活动");


    private String type;

    private String desc;

    private ShareInfoType(String type, String desc) {
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
        for(ShareInfoType exchangeResultStatus : ShareInfoType.values()) {
            if(type.equals(exchangeResultStatus.getType())) {
                return exchangeResultStatus.getDesc();
            }
        }
        return null;
    }
}
