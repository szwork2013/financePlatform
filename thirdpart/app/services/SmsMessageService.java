package services;

import com.google.common.base.Strings;
import com.sunlights.common.AppConst;
import com.sunlights.common.ParameterConst;
import com.sunlights.common.service.ParameterService;
import com.sunlights.common.utils.ConfigUtil;
import com.sunlights.common.utils.MD5Helper;
import com.sunlights.common.vo.SmsMessageVo;
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
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */

public class SmsMessageService {

    private ParameterService parameterService = new ParameterService();

    public SmsMessageVo sendSms(SmsMessageVo smsMessageVo) {
        String result = executeSend(smsMessageVo);
        Logger.info(result);
        if ("0,成功".equals(result)) {
            smsMessageVo.setSuccessInd(AppConst.STATUS_VALID);
        } else {
            smsMessageVo.setSuccessInd(AppConst.STATUS_INVALID);
        }

        return smsMessageVo;
    }

    public String executeSend(SmsMessageVo smsMessageVo) {
        Logger.info("==============调用短信接口============");
        HttpClient httpClient = new HttpClient();

        ConfigUtil.setProxy(httpClient);

        String result = null;

        try {
            String url = parameterService.getParameterByName(ParameterConst.SMS_URL);
            String account = parameterService.getParameterByName(ParameterConst.SMS_ACCOUNT);
            String password = parameterService.getParameterByName(ParameterConst.SMS_PASSWORD);
            String channel = parameterService.getParameterByName(ParameterConst.SMS_CHANNEL);
            String warrantyCode = parameterService.getParameterByName(ParameterConst.SMS_WARRANTYCODE);

            PostMethod postMethod = new PostMethod(url);
            postMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码

            NameValuePair[] data = {
                    new NameValuePair("account", account),
                    new NameValuePair("password", formatPwd(password, warrantyCode)),
                    new NameValuePair("mobile", smsMessageVo.getMobile()),
                    new NameValuePair("content", smsMessageVo.getContent()),
                    new NameValuePair("channel", channel),
                    new NameValuePair("smsid", smsMessageVo.getSmsId()),
                    new NameValuePair("sendType", "1")};
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

        return result;
    }


    private String formatPwd(String pwd, String warrantyCode) {
        String pwdFormat = new MD5Helper().encrypt(pwd + warrantyCode).toLowerCase();
        return Strings.padStart(pwdFormat, 32, '0');
    }

}
