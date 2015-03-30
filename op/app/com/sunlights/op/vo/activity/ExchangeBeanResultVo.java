package com.sunlights.op.vo.activity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * <p>Project: operationplatform</p>
 * <p>Title: ExchangeBeanResultVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class ExchangeBeanResultVo implements Serializable{
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String exchangeTime;
    private String registerMobile;
    private String exchangeMobile;
    private String exchangeAmount;
    private String hasExchangeAmount;//已兑换金额
    private String exchangeBean;
    private String carrierCode;//运营商
    private String carrierName;//运营商
    private String status;
    private String statusDesc;


    public String getExchangeTime() {
        return exchangeTime;
    }

    public void setExchangeTime(String exchangeTime) {
        this.exchangeTime = exchangeTime;
    }

    public String getRegisterMobile() {
        return registerMobile;
    }

    public void setRegisterMobile(String registerMobile) {
        this.registerMobile = registerMobile;
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

    public String getExchangeBean() {
        return exchangeBean;
    }

    public void setExchangeBean(String exchangeBean) {
        this.exchangeBean = exchangeBean;
    }

    public String getCarrierCode() {
        return carrierCode;
    }

    public void setCarrierCode(String carrierCode) {
        this.carrierCode = carrierCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHasExchangeAmount() {
        return hasExchangeAmount;
    }

    public void setHasExchangeAmount(String hasExchangeAmount) {
        this.hasExchangeAmount = hasExchangeAmount;
    }
}
