package com.sunlights.core.service.impl;

import akka.actor.ActorRef;
import com.sunlights.common.AppConst;
import com.sunlights.common.ParameterConst;
import com.sunlights.common.service.ParameterService;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.core.actor.Actors;
import com.sunlights.core.dal.SmsMessageDao;
import com.sunlights.core.dal.impl.SmsMessageDaoImpl;
import com.sunlights.core.integration.SmsMessageClient;
import models.SmsMessage;
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

    private ParameterService parameterService = new ParameterService();
    private SmsMessageDao smsMessageDao = new SmsMessageDaoImpl();
    private SmsMessageClient smsMessageClient = new SmsMessageClient();

    /**
     * 发送手机短信
     *
     * @param mobilePhoneNo
     * @param verifyCode
     * @param type
     */
    public void tellActor(String mobilePhoneNo, String verifyCode, String type) {
        SmsMessage smsMessage = createSmsMessage(mobilePhoneNo, verifyCode, type);

        Logger.info("================sms tellActor ====");
        Actors.smsMasterActor.tell(smsMessage, ActorRef.noSender());
    }

    public void sendSms(SmsMessage smsMessage) {
        Logger.info("================sms sendSms ====");
        String result = smsMessageClient.sendSms(smsMessage);
        if ("0,成功".equals(result)) {
            smsMessage.setSuccessInd(AppConst.STATUS_VALID);
        }
        smsMessage.setReturnMsg(result);
        smsMessage.setUpdateTime(DBHelper.getCurrentTime());
        smsMessageDao.updateSmsMessage(smsMessage);
    }


    private SmsMessage createSmsMessage(String mobilePhoneNo, String verifyCode, String type) {

        String typeStr = "";
        if (AppConst.VERIFY_CODE_REGISTER.equals(type)) {
            typeStr = "注册";
        } else if (AppConst.VERIFY_CODE_RESETPWD.equals(type)) {
            typeStr = "修改登录密码";
        } else if (AppConst.VERIFY_CODE_RESET_ACCOUNT.equals(type)) {
            typeStr = "修改交易密码";
        }
        String mobileDisplayNo = mobilePhoneNo.substring(0, 3) + "****" + mobilePhoneNo.substring(7);
        long expriyTimes = parameterService.getParameterNumeric(ParameterConst.VERIFYCODE_EXPIRY);
        String content = MessageFormat.format("尊敬的用户({0})，您申请的{1}验证码为： " +
                "{2}（{3}分钟内有效）。请勿泄露您的验证码。谢谢！【金豆荚】", mobileDisplayNo, typeStr, verifyCode, expriyTimes);

        Timestamp currentTime = DBHelper.getCurrentTime();
        SmsMessage smsMessage = new SmsMessage();
        smsMessage.setMobile(mobilePhoneNo);
        smsMessage.setSmsId(getSmsId());
        smsMessage.setContent(content);
        smsMessage.setCreateTime(currentTime);
        smsMessage.setUpdateTime(currentTime);
        smsMessageDao.saveSmsMessage(smsMessage);

        return smsMessage;
    }

    private static String getSmsId() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return formatter.format(DBHelper.getCurrentTime());
    }
}
