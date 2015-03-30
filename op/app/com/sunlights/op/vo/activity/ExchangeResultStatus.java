package com.sunlights.op.vo.activity;


import com.sunlights.op.common.constants.ActivityConstant;

/**
 * Created by tangweiqun on 2014/12/11.
 */
public enum ExchangeResultStatus {

    NOT_AUDIT(0, ActivityConstant.NOT_AUDIT_DESC),
    AUDIT_PASS(1,ActivityConstant.AUDIT_PASS_DESC),
    AUDIT_NOT_PASS(2,ActivityConstant.AUDIT_NOT_PASS_DESC),
    EXCHANGEING(3,ActivityConstant.EXCHANGEING_DESC),
    EXCHANGE_SUCC(4,ActivityConstant.EXCHANGE_SUCC_DESC),
    EXCHANGE_FAIL(5,ActivityConstant.EXCHANGE_FAIL_DESC);

	private Integer status;

    private String desc;

    private ExchangeResultStatus(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }

    public static String getDescByStatus(Integer status) {
        for(ExchangeResultStatus exchangeResultStatus : ExchangeResultStatus.values()) {
            if(status == exchangeResultStatus.getStatus()) {
                return exchangeResultStatus.getDesc();
            }
        }
        return null;
    }

}
