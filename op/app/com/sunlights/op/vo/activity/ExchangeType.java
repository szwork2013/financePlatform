package com.sunlights.op.vo.activity;

/**
 * Created by tangweiqun on 2014/12/11.
 */
public enum ExchangeType {
    WITHDRAW("0","取现"),
    TELEPHONE_CHARG("1","兑话费"),
    QQ_COIN("2","兑Q币");


    private String type;

    private String desc;

    private ExchangeType(String type, String desc) {
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
        for(ExchangeType exchangeResultStatus : ExchangeType.values()) {
            if(type.equals(exchangeResultStatus.getType())) {
                return exchangeResultStatus.getDesc();
            }
        }
        return null;
    }
}
