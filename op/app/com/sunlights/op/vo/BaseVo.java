package com.sunlights.op.vo;

import com.sunlights.common.vo.PageVo;

/**
 * Created by guxuelong on 2014/12/11.
 */
public class BaseVo extends PageVo{
    private String returnCode;
    private String returnMessage;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }
}
