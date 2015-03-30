package com.sunlights.op.common.enums;

/**
 * Created by guxuelong on 2014/12/3.
 */
public enum ReconciledErrorEnum {
    TRADE_DATE_ERROR("01","交易时间不平"),
    PRODUCT_CODE_ERROR("02","产品编码不平"),
    PRODUCT_TYPE_ERROR("03","产品类型不平"),
    TRADE_TYPE_ERROR("04","交易类型不平"),
    TRADE_AMOUNT_ERROR("05","交易金额不平"),
    PARTNER_NO_DATA_ERROR("06","合作伙伴无流水"),
    YI_YUE_NO_DATA_ERROR("07","艺韵无流水");

    private String code;
    private String value;

    ReconciledErrorEnum(String code,String value){
        this.code = code;
        this.value = value;
    }

    public String getCode(){
        return code;
    }

    public String getValue(){
        return value;
    }

    public static String  getValueByCode(String code){
        for(ReconciledErrorEnum errorEnum : ReconciledErrorEnum.values()){
            if(errorEnum.getCode().equals(code)){
                return errorEnum.getValue();
            }
        }
        return "";
    }
}
