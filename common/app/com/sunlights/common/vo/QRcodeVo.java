package com.sunlights.common.vo;

import java.io.Serializable;

/**
 * Created by Administrator on 2014/12/3.
 */
public class QRcodeVo implements Serializable {
      private byte[] qrcodeByte;

    public byte[] getQrcodeByte() {
        return qrcodeByte;
    }

    public void setQrcodeByte(byte[] qrcodeByte) {
        this.qrcodeByte = qrcodeByte;
    }
}
