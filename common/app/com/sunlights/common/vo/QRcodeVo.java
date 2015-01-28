package com.sunlights.common.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * Created by Administrator on 2014/12/3.
 */
public class QRcodeVo implements Serializable {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private byte[] qrcodeByte;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String qrcodeStr;

    public byte[] getQrcodeByte() {
        return qrcodeByte;
    }

    public void setQrcodeByte(byte[] qrcodeByte) {
        this.qrcodeByte = qrcodeByte;
    }

    public String getQrcodeStr() {
        return qrcodeStr;
    }

    public void setQrcodeStr(String qrcodeStr) {
        this.qrcodeStr = qrcodeStr;
    }
}
