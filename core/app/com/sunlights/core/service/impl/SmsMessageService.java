package com.sunlights.core.service.impl;

import com.sunlights.common.AppConst;
import com.sunlights.common.ParameterConst;
import com.sunlights.common.service.ParameterService;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.customer.action.MsgCenterActionService;
import com.sunlights.customer.dal.MsgCenterDao;
import com.sunlights.customer.dal.impl.MsgCenterDaoImpl;
import models.MessageRule;
import models.MessageSmsTxn;
import play.Logger;

import java.sql.Timestamp;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;

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

    private static String VERIFY_CODE = "VERIFY_CODE";
    private ParameterService parameterService = new ParameterService();
    private MsgCenterDao msgCenterDao = new MsgCenterDaoImpl();
    private MsgCenterActionService msgCenterActionService = new MsgCenterActionService();

    /**
     * 发送手机短信
     *
     * @param mobilePhoneNo
     * @param verifyCode
     * @param type
     */
    public void sendSms(String mobilePhoneNo, String verifyCode, String type) {
        MessageSmsTxn smsMessage = createMessageSmsTxn(mobilePhoneNo, verifyCode, type);

        msgCenterActionService.executeSmsWS(smsMessage);
    }


    private MessageSmsTxn createMessageSmsTxn(String mobilePhoneNo, String verifyCode, String type) {

        MessageRule messageRule = msgCenterDao.findMessageRuleSmsByCode(VERIFY_CODE);
        if (messageRule == null) {
            Logger.error(">>验证码规则未配置！");
        }

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
        String contentSms = MessageFormat.format(messageRule.getContentSms(), mobileDisplayNo, typeStr, verifyCode, expiryTimes);

        Timestamp currentTime = DBHelper.getCurrentTime();
        MessageSmsTxn messageSmsTxn = new MessageSmsTxn();
        messageSmsTxn.setMessageRuleId(messageRule.getId());
        messageSmsTxn.setMobile(mobilePhoneNo);
        messageSmsTxn.setSmsId(getSmsId());
        messageSmsTxn.setContent(contentSms);
        messageSmsTxn.setTitle(messageRule.getTitle());
        messageSmsTxn.setCreateTime(currentTime);
//        msgCenterDao.createMessageSmsTxn(messageSmsTxn);
        return messageSmsTxn;
    }

    private static String getSmsId() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return formatter.format(DBHelper.getCurrentTime());
    }
}
