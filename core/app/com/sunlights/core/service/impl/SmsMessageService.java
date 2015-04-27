package com.sunlights.core.service.impl;

import com.sunlights.common.AppConst;
import com.sunlights.common.DictConst;
import com.sunlights.common.ParameterConst;
import com.sunlights.common.service.ParameterService;
import com.sunlights.common.vo.MessageHeaderVo;

/**
 * <p>Project: fsp</p>
 * <p>Title: SafeSercice.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */

public class SmsMessageService {

    private ParameterService parameterService = new ParameterService();

    /**
     * 发送手机短信
     *
     * @param mobilePhoneNo
     * @param verifyCode
     * @param type
     */
    public MessageHeaderVo buildSmsParams(String mobilePhoneNo, String verifyCode, String type) {
        String typeStr = "";
        if (AppConst.VERIFY_CODE_REGISTER.equals(type)) {
            typeStr = "注册";
        } else if (AppConst.VERIFY_CODE_RESETPWD.equals(type)) {
            typeStr = "修改登录密码";
        } else if (AppConst.VERIFY_CODE_RESET_ACCOUNT.equals(type)) {
            typeStr = "修改交易密码";
        }
        String mobileDisplayNo = mobilePhoneNo.substring(0, 3) + "****" + mobilePhoneNo.substring(7);
        long expiryTimes = parameterService.getParameterNumeric(ParameterConst.VERIFYCODE_EXPIRY);

        MessageHeaderVo messageHeaderVo = new MessageHeaderVo();
        messageHeaderVo.setMessageType(DictConst.PUSH_TYPE_1);
        messageHeaderVo.buildParams(mobileDisplayNo, typeStr, verifyCode, expiryTimes + "");
        messageHeaderVo.setMobile(mobilePhoneNo);

        return messageHeaderVo;
    }

}
