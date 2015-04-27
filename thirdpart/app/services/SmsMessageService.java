package services;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.sunlights.common.AppConst;
import com.sunlights.common.ParameterConst;
import com.sunlights.common.service.ParameterService;
import com.sunlights.common.utils.ConfigUtil;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.utils.MD5Helper;
import com.sunlights.common.vo.SmsMessageVo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import play.Logger;
import play.libs.Json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

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
    private final static int maxLength = 500;

    public List<SmsMessageVo> sendSms(SmsMessageVo smsMessageVo) {
        List<String> mobileList = smsMessageVo.getMobileList();
        int length = mobileList.size();

        List<SmsMessageVo> smsMessageVoList = Lists.newArrayList();

        int i = 1;
        while(true){
            try {
                SmsMessageVo vo = (SmsMessageVo) BeanUtils.cloneBean(smsMessageVo);
                if (i * maxLength > length) {
                    vo.setMobileList(mobileList.subList((i-1) * maxLength, length));
                    smsMessageVoList.add(vo);
                    break;
                } else {
                    vo.setMobileList(mobileList.subList((i-1) * maxLength, i * maxLength));
                    smsMessageVoList.add(vo);
                }
                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        buildSend(smsMessageVoList);

        return smsMessageVoList;
    }

    private void buildSend(List<SmsMessageVo> smsMessageVoList) {
        Logger.info("==============调用短信接口============");
        HttpClient httpClient = new HttpClient();
        ConfigUtil.setProxy(httpClient);

        try {
            String url = parameterService.getParameterByName(ParameterConst.SMS_URL);
            String account = parameterService.getParameterByName(ParameterConst.SMS_ACCOUNT);
            String password = parameterService.getParameterByName(ParameterConst.SMS_PASSWORD);
            String channel = parameterService.getParameterByName(ParameterConst.SMS_CHANNEL);
            String warrantyCode = parameterService.getParameterByName(ParameterConst.SMS_WARRANTYCODE);
            String formatPwd = formatPwd(password, warrantyCode);

            PostMethod postMethod = new PostMethod(url);
            postMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码

            for (SmsMessageVo smsMessageVo : smsMessageVoList){
                postSms(smsMessageVo, httpClient, account, formatPwd, channel, postMethod);
            }

            postMethod.releaseConnection();

        } catch (Exception e) {
            e.printStackTrace();
            Logger.info(e.getMessage());
        }

    }

    private void postSms(SmsMessageVo smsMessageVo, HttpClient httpClient, String account, String formatPwd, String channel, PostMethod postMethod) throws IOException {
        Logger.debug("smsMessage:" + Json.toJson(smsMessageVo));
        String mobiles = smsMessageVo.getMobileList().toString().replace("[", "").replace("]", "");
        String smsId = getSmsId();
        NameValuePair[] data = {
                new NameValuePair("account", account),
                new NameValuePair("password", formatPwd),
                new NameValuePair("mobile", mobiles),
                new NameValuePair("content", smsMessageVo.getContent()),
                new NameValuePair("channel", channel),
                new NameValuePair("smsid", smsId),
                new NameValuePair("sendType", "1")};
        postMethod.setRequestBody(data);
        int statusCode = httpClient.executeMethod(postMethod);
        Logger.info("调用短信接口返回statusCode：" + statusCode);

        String result = postMethod.getResponseBodyAsString();
        if (statusCode == HttpStatus.SC_OK) {
            Logger.info("调用短信接口结果为：" + result);
        } else {
            Logger.info("调用短信接口失败：" + result);
        }

        if ("0,成功".equals(result)) {
            smsMessageVo.setSuccessInd(AppConst.STATUS_VALID);
        } else {
            smsMessageVo.setSuccessInd(AppConst.STATUS_INVALID);
        }
        smsMessageVo.setSmsId(smsId);
    }

    private String getSmsId(){
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(DBHelper.getCurrentTime()) + new Random().nextInt(99);
    }

    private String formatPwd(String pwd, String warrantyCode) {
        String pwdFormat = new MD5Helper().encrypt(pwd + warrantyCode).toLowerCase();
        return Strings.padStart(pwdFormat, 32, '0');
    }

}
