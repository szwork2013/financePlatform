package com.sunlights.op.common.enums;

/**
 * Created by guxuelong on 2014/12/3.
 */
public enum AccountAdjustmentStatusEnum {

    NO_ACTIVE("0", "未调账"),
    ON_GOING("1", "调账处理中"),
    SUCCESS("2", "调账成功"),
    FAIL("3", "调账失败");

    private String code;
    private String value;

    AccountAdjustmentStatusEnum(String code, String value) {
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
        for (AccountAdjustmentStatusEnum status : AccountAdjustmentStatusEnum.values()) {
            if (status.getCode().equals(code)) {
                return status.getValue();
            }
        }
        return "";
    }
}
