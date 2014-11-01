package com.sunlights.core.service.impl;

import akka.actor.ActorRef;
import com.sunlights.common.AppConst;
import com.sunlights.common.IParameterConst;
import com.sunlights.common.ParameterService;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.core.actor.Actors;
import com.sunlights.core.dal.SmsMessageDao;
import com.sunlights.core.models.SmsMessage;
import org.springframework.transaction.annotation.Transactional;

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

@Transactional
public class SafeServiceImpl {


    private ParameterService parameterService;

    private SmsMessageDao smsMessageDao;

    /**
     * 发送手机短信
     * @param mobilePhoneNo
     * @param verifyCode
     * @param type
     */
    public void sendMsg(String mobilePhoneNo, String verifyCode, String type){
        SmsMessage smsMessage = createSmsMessage(mobilePhoneNo, verifyCode, type);
        Actors.smsMasterActor.tell(smsMessage, ActorRef.noSender());
    }
    private SmsMessage createSmsMessage(String mobilePhoneNo, String verifyCode, String type){
        String typeStr = "";
        if (AppConst.VERIFY_CODE_REGISTER.equals(type)) {
            typeStr = "注册";
        }else if (AppConst.VERIFY_CODE_RESETPWD.equals(type)){
            typeStr = "修改登录密码";
        }else if (AppConst.VERIFY_CODE_RESET_ACCOUNT.equals(type)){
            typeStr = "修改交易密码";
        }
        long expriyTimes = parameterService.getParameterNumeric(IParameterConst.VERIFYCODE_EXPIRY);
        String content = MessageFormat.format("您的{0}的验证码为： " +
                "{1}（{2}分钟内有效）【艺岳投资】", typeStr, verifyCode, expriyTimes);

        Timestamp currentTime = DBHelper.getCurrentTime();
        SmsMessage smsMessage = new SmsMessage();
        smsMessage.setMobile(mobilePhoneNo);
        smsMessage.setSmsId(getSmsId());
        smsMessage.setContent(content);
        smsMessage.setCreatedDatetime(currentTime);
        smsMessage.setUpdatedDatetime(currentTime);
        smsMessageDao.saveSmsMessage(smsMessage);

        return smsMessage;
    }
    private static String getSmsId() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return formatter.format(DBHelper.getCurrentTime());
    }
}
