package com.sunlights.op.common.enums;

/**
 * Created by guxuelong on 2014/12/3.
 */
public enum ReconcileStatusEnum {

    NO_ACTIVE("0", "未对账"),
    SUCCESS("1", "对账成功"),
    FAIL("2", "对账不平");

    private String code;
    private String value;

    ReconcileStatusEnum(String code, String value) {
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
        for (ReconcileStatusEnum status : ReconcileStatusEnum.values()) {
            if (status.getCode().equals(code)) {
                return status.getValue();
            }
        }
        return "";
    }

}