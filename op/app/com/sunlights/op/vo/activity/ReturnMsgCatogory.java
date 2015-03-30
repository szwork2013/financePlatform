package com.sunlights.op.vo.activity;

/**
 * Created by tangweiqun on 2014/12/11.
 */
public enum ReturnMsgCatogory {

    RETURN_MSG_CATEGORY_SHOW("1","展示"),
    RETURN_MSG_CATEGORY_MESSAGE_SEND("2","发消息"),
    RETURN_MSG_CATEGORY_REWARD_TRADE("3","奖励交易");


    private String type;

    private String desc;

    private ReturnMsgCatogory(String type, String desc) {
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
        for(ReturnMsgCatogory exchangeResultStatus : ReturnMsgCatogory.values()) {
            if(type.equals(exchangeResultStatus.getType())) {
                return exchangeResultStatus.getDesc();
            }
        }
        return null;
    }
}
