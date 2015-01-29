package com.sunlights.common.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * Created by Administrator on 2014/12/3.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class QRcodeVo implements Serializable {

    private byte[] qrcodeByte;
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
