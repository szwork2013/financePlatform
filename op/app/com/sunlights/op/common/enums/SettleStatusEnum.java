package com.sunlights.op.common.enums;

/**
 * Created by guxuelong on 2014/12/4.
 */
public enum SettleStatusEnum {

    NO_ACTIVE("0", "未结算"),
    SUCCESS("1", "已结算");

    private String code;
    private String value;

    SettleStatusEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public static String getValueByCode(String code) {
        for (SettleStatusEnum status : SettleStatusEnum.values()) {
            if (status.getCode().equals(code)) {
                return status.getValue();
            }
        }
        return "";
    }
}
