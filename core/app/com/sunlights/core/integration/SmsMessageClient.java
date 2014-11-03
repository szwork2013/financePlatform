package com.sunlights.core.integration;

import com.sunlights.common.AppConst;
import com.sunlights.common.service.ParameterService;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.utils.MD5Helper;
import com.sunlights.core.dal.SmsMessageDao;
import com.sunlights.core.models.SmsMessage;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import play.Logger;

/**
* <p>Project: tradingsystem</p>
* <p>Title: SendMsgService.java</p>
* <p>Description: </p>
* <p>Copyright (c) 2014 Sunlights.cc</p>
* <p>All Rights Reserved.</p>
* @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
*/

public class SmsMessageClient {

    private ParameterService parameterService;

    private SmsMessageDao smsMessageDao;

    public String sendSms(SmsMessage smsMessage){
        HttpClient httpClient = new HttpClient();
        String result = null;

        try {
            String url = parameterService.getParameterByName(AppConst.SMS_URL);
            String account = parameterService.getParameterByName(AppConst.SMS_ACCOUNT);
            String password = parameterService.getParameterByName(AppConst.SMS_PASSWORD);
            String channel = parameterService.getParameterByName(AppConst.SMS_CHANNEL);
            String warrantyCode = parameterService.getParameterByName(AppConst.SMS_WARRANTYCODE);
            PostMethod postMethod = new PostMethod(url);
            postMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码

            NameValuePair[] data = {
                    new NameValuePair("account", account),
                    new NameValuePair("password", formatPwd(password, warrantyCode)),
                    new NameValuePair("mobile", smsMessage.getMobile()),
                    new NameValuePair("content", smsMessage.getContent()),
                    new NameValuePair("channel", channel),
                    new NameValuePair("smsid", smsMessage.getSmsId()),
                    new NameValuePair("sendType", "1") };
            postMethod.setRequestBody(data);
            int statusCode = httpClient.executeMethod(postMethod);
            Logger.info("调用短信接口返回statusCode：" + statusCode);

            result = postMethod.getResponseBodyAsString();
            if (statusCode == HttpStatus.SC_OK) {
                Logger.info("调用短信接口结果为：" + result);
            } else {
                Logger.info("调用短信接口失败：" + result);
            }
            postMethod.releaseConnection();

        } catch (Exception e) {
            e.printStackTrace();
            Logger.info(e.getMessage());
        }

        smsMessage.setReturnMsg(result);
        smsMessage.setUpdatedDatetime(DBHelper.getCurrentTime());
        smsMessageDao.updateSmsMessage(smsMessage);

        return result;
    }

    private static String formatPwd(String pwd, String warrantyCode){
        String pwdFormat = new MD5Helper().encrypt(pwd + warrantyCode);
        pwdFormat = pwdFormat.toLowerCase();
        String zero = "";
        for (int i = 0; i< 32 - pwdFormat.length(); i++){
            zero += "0";
        }
        return zero + pwdFormat;
    }


}
