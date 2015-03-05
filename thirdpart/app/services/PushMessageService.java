package services;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.connection.HttpProxy;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.sunlights.common.*;
import com.sunlights.common.service.ParameterService;
import com.sunlights.common.utils.ArithUtil;
import com.sunlights.common.utils.ConfigUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.MessageVo;
import com.sunlights.common.vo.PushMessageVo;
import play.Logger;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.List;

/**
 * <p>Project: thirdpartyservice</p>
 * <p>Title: PushService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class PushMessageService {
    private ParameterService parameterService = new ParameterService();
    private static int maxLength = 1000;

    public MessageVo sendPush(PushMessageVo pushMessageVo){
        String appKey = parameterService.getParameterByName(ParameterConst.APP_KEY);
        String secretKey = parameterService.getParameterByName(ParameterConst.SECRET_KEY);
        Logger.info(">>sendPush>>> start");

        MessageVo messageVo = new MessageVo();

        int length = pushMessageVo.getRegistrationIdList().size();

        if (length <= maxLength) {
            messageVo = executePush(pushMessageVo, appKey, secretKey);
        } else{
            int num = Integer.valueOf(ArithUtil.bigUpScale0(new BigDecimal((double)length/ maxLength)).toString());
            for (int i = 1; i <= num; i++) {
                PushMessageVo vo = pushMessageVo;
                if (i * maxLength > length) {
                    vo.getRegistrationIdList().subList(i * maxLength, length);
                }else{
                    vo.getRegistrationIdList().subList((i - 1) * maxLength, i * maxLength);
                }
                messageVo = executePush(vo, appKey, secretKey);
            }
        }

        return messageVo;
    }

    private MessageVo executePush(PushMessageVo pushMessageVo, String appKey, String secretKey) {
        String resultMsg = null;
        String errorMsg = null;
        MessageVo messageVo = new MessageVo();
        JPushClient jPushClient = null;
        try {
            PushPayload pushPayload  = builderPushPayLoad(pushMessageVo);

            String proxyHost = ConfigUtil.getValueStr(ConfigUtil.proxy_host);
            if(proxyHost != null) {
                int proxyPort = ConfigUtil.getValueInt(ConfigUtil.proxy_port);
                HttpProxy httpProxy = new HttpProxy(proxyHost, proxyPort);
                jPushClient = new JPushClient(secretKey, appKey, 1, httpProxy);
            }else{
                jPushClient = new JPushClient(secretKey, appKey);
            }

            Logger.info("================sendPush begin：==========");
            Logger.debug(pushPayload.toString());
            PushResult result = jPushClient.sendPush(pushPayload.toString());
            resultMsg = result.toString();

            Logger.info("===============sendPush return：==========");
            Logger.debug(resultMsg);

            messageVo.setMessage(new Message(MsgCode.OPERATE_SUCCESS));
            messageVo.setValue(resultMsg);

        } catch (APIRequestException e) {
            Logger.error(">>出错了", e);
            Logger.info(MessageFormat.format(">> sendPush exception-->> personalInd：{0},messagePushTxnId：{1}", pushMessageVo.getPersonalInd(), pushMessageVo.getPushTxnId()));
            errorMsg = MessageFormat.format("异常信息code:{0},msg:{1}", e.getErrorCode(), e.getErrorMessage());
            Logger.error(">>出错详情：" , errorMsg);

            messageVo.setMessage(new Message(Severity.ERROR, e.getErrorCode() + "", e.getErrorMessage(), errorMsg));
            messageVo.setValue(errorMsg);
            e.printStackTrace();
        } catch (Exception e){
            Logger.error(">>出错了", e);
            Logger.info(MessageFormat.format(">> sendPush exception-->> personalInd：{0},messagePushTxnId：{1}", pushMessageVo.getPersonalInd(), pushMessageVo.getPushTxnId()));
            String detailMsg = e.getMessage();
            e.printStackTrace();
            if (detailMsg != null && e.getMessage().length() >= 200) {
                detailMsg = e.getMessage().substring(0, 200);
            }

            messageVo.setMessage(new Message(Severity.FATAL, "fatal", e.toString(), detailMsg));
            messageVo.setValue(detailMsg);
        }

        return messageVo;
    }

    private PushPayload builderPushPayLoad(PushMessageVo pushMessageVo) {
        PushPayload pushPayload = null;

        String platform = pushMessageVo.getPlatform(); //推送平台
        String msgPlatform = pushMessageVo.getCustomerPlatform();

        Logger.info(MessageFormat.format(">>推送配置中的推送平台：{0},当前registerId对应的推送平台：{1}", platform, msgPlatform));

        if (DictConst.PUSH_PLATFORM_IOS.equals(platform)) {
            pushPayload = builderIos(pushMessageVo);
        }else if(DictConst.PUSH_PLATFORM_ANDROID.equals(platform)){
            pushPayload = builderAndroid(pushMessageVo);
        }else{
            if (AppConst.PLATFORM_IOS.equals(msgPlatform)) {
                pushPayload = builderAndroid(pushMessageVo);
            }else{
                pushPayload = builderIos(pushMessageVo);
            }
        }
        return pushPayload;
    }

    private PushPayload builderAndroid(PushMessageVo pushMessageVo){
        String contentPush = pushMessageVo.getContentPush();
        String title = pushMessageVo.getTitle();
        List<String> aliasList = pushMessageVo.getAliasList();
        List<String> registrationIdList = pushMessageVo.getRegistrationIdList();

        PushPayload.Builder builder = PushPayload.newBuilder();

        builder.setPlatform(Platform.android());
        builder.setNotification(Notification.android(contentPush, title, null));

        if (registrationIdList.isEmpty()) {
            builder.setAudience(Audience.all());
        }else {
            builder.setAudience(Audience.registrationId(registrationIdList));//1000
        }
        boolean apns_production = ConfigUtil.getValueBoolean(ConfigUtil.apns_production);

        builder.setOptions(Options.newBuilder().setApnsProduction(apns_production).build());

        return builder.build();
    }
    private PushPayload builderAll(PushMessageVo pushMessageVo){
        String contentPush = pushMessageVo.getContentPush();
        String title = pushMessageVo.getTitle();

        PushPayload.Builder builder = PushPayload.newBuilder();

        builder.setPlatform(Platform.all());
        builder.setNotification(Notification.alert(contentPush));
        builder.setAudience(Audience.all());
        boolean apns_production = ConfigUtil.getValueBoolean(ConfigUtil.apns_production);
        builder.setOptions(Options.newBuilder().setApnsProduction(apns_production).build());

        return builder.build();
    }
    private PushPayload builderIos(PushMessageVo pushMessageVo){
        String contentPush = pushMessageVo.getContentPush();
        String title = pushMessageVo.getTitle();
        List<String> aliasList = pushMessageVo.getAliasList();
        List<String> registrationIdList = pushMessageVo.getRegistrationIdList();

        PushPayload.Builder builder = PushPayload.newBuilder();

        builder.setPlatform(Platform.ios());

        Logger.info("badge：" + pushMessageVo.getBadge());
        builder.setNotification(Notification.newBuilder().addPlatformNotification(IosNotification.newBuilder().setAlert(contentPush).setBadge(pushMessageVo.getBadge()).build()).build());

        if (registrationIdList.isEmpty()) {
            builder.setAudience(Audience.all());
        }else {
            builder.setAudience(Audience.registrationId(registrationIdList));//1000
        }

        boolean apns_production = ConfigUtil.getValueBoolean(ConfigUtil.apns_production);

        builder.setOptions(Options.newBuilder().setApnsProduction(apns_production).build());

        return builder.build();
    }





}
