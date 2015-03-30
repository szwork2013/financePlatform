package com.sunlights.op.dto;

/**
 * Created by Administrator on 2014/12/23.
 */
public class ExchangeBeanResultXlsDto extends BaseXlsDto {
    private String id;
    private String exchangeTime;
    private String registerMobile;
    private String exchangeMobile;
    private String exchangeAmount;
    private String exchangeBean;
    private String carrierName;
    private String statusDesc;
    private String hasExchangeAmount;

    {
        addName("id");
        addName("exchangeTime");
        addName("registerMobile");
        addName("exchangeMobile");
        addName("exchangeAmount");
        addName("exchangeBean");
        addName("carrierName");
        addName("statusDesc");
        addName("hasExchangeAmount");
        setStartRow(1);
    }

    public String getHasExchangeAmount() {
        return hasExchangeAmount;
    }

    public void setHasExchangeAmount(String hasExchangeAmount) {
        this.hasExchangeAmount = hasExchangeAmount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExchangeTime() {
        return exchangeTime;
    }

    public void setExchangeTime(String exchangeTime) {
        this.exchangeTime = exchangeTime;
    }

    public String getExchangeMobile() {
        return exchangeMobile;
    }

    public void setExchangeMobile(String exchangeMobile) {
        this.exchangeMobile = exchangeMobile;
    }

    public String getExchangeAmount() {
        return exchangeAmount;
    }

    public void setExchangeAmount(String exchangeAmount) {
        this.exchangeAmount = exchangeAmount;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public String getExchangeBean() {
        return exchangeBean;
    }

    public void setExchangeBean(String exchangeBean) {
        this.exchangeBean = exchangeBean;
    }

    public String getRegisterMobile() {
        return registerMobile;
    }

    public void setRegisterMobile(String registerMobile) {
        this.registerMobile = registerMobile;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }
}
