package com.sunlights.common.utils;

import com.sunlights.common.MsgCode;
import com.sunlights.common.exceptions.BusinessRuntimeException;
import com.sunlights.common.utils.msg.Message;
import org.apache.commons.lang.*;

import java.text.MessageFormat;

/**
 * <p>Project: fsp</p>
 * <p>Title: CommonUtil.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class CommonUtil {
    private static CommonUtil commonUtil = new CommonUtil();
    public static CommonUtil getInstance(){
        return commonUtil;
    }

    public CommonUtil(){

    }

    /**
     * 参数验证
     * @param params
     */
    public void validateParams(String... params){
        for (String param : params) {
            if (org.apache.commons.lang.StringUtils.isEmpty(param)) {
                throw errorBusinessException(MsgCode.ACCESS_FAIL, param);
            }
        }
    }
    public BusinessRuntimeException errorBusinessException(MsgCode msgCode, Object... params) {
        String detail = getDetail(msgCode, params);
        return new BusinessRuntimeException(Message.SEVERITY_ERROR, msgCode.getCode(), msgCode.getMessage(), detail);
    }

    private String getDetail(MsgCode msgCode, Object[] params) {
        String detail = msgCode.getDetail();
        if(params!=null){
            detail =  MessageFormat.format(detail, params);
        }
        return detail;
    }

    public BusinessRuntimeException fatalBusinessException(MsgCode msgCode, Object... params) {
        String detail = getDetail(msgCode, params);
        return new BusinessRuntimeException(Message.SEVERITY_FATAL, msgCode.getCode(), msgCode.getMessage(), detail);
    }
}
