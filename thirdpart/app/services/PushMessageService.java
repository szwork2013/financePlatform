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
import com.google.common.collect.Lists;
import com.sunlights.common.DictConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.ParameterConst;
import com.sunlights.common.Severity;
import com.sunlights.common.service.CommonService;
import com.sunlights.common.service.ParameterService;
import com.sunlights.common.utils.ConfigUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.MessageVo;
import com.sunlights.common.vo.PushMessageVo;
import org.apache.commons.beanutils.BeanUtils;
import play.Logger;

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
    private  CommonService commonService = new CommonService();
    private ParameterService parameterService = new ParameterService();
    private final static int maxLength = 1000;

    public MessageVo sendPush(PushMessageVo pushMessageVo) {
        String appKey = parameterService.getParameterByName(ParameterConst.APP_KEY);
        String secretKey = parameterService.getParameterByName(ParameterConst.SECRET_KEY);
        Logger.info(">>sendPush>>> start");

        List<PushMessageVo> pushMessageVoList = Lists.newArrayList();
        MessageVo messageVo = new MessageVo();

        List<String> registrationIdList = pushMessageVo.getRegistrationIdList();
        int length = registrationIdList.size();

        int i = 1;
        while(true){
            try {
                PushMessageVo vo = (PushMessageVo) BeanUtils.cloneBean(pushMessageVo);
                if (i * maxLength > length) {
                    vo.setRegistrationIdList(registrationIdList.subList((i - 1) * maxLength, length));
                    pushMessageVoList.add(vo);
                    break;
                } else {
                    vo.setRegistrationIdList(registrationIdList.subList((i-1) * maxLength, i * maxLength));
                    pushMessageVoList.add(vo);
                }
                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        HttpProxy httpProxy = null;
        String proxyHost = ConfigUtil.getValueStr(ConfigUtil.proxy_host);
        if (proxyHost != null) {
            int proxyPort = ConfigUtil.getValueInt(ConfigUtil.proxy_port);
            httpProxy = new HttpProxy(proxyHost, proxyPort);
        }

        for (PushMessageVo tempVo : pushMessageVoList) {
            messageVo = executePush(tempVo, appKey, secretKey, httpProxy);
        }

        return messageVo;
    }

    private MessageVo executePush(PushMessageVo pushMessageVo, String appKey, String secretKey, HttpProxy httpProxy) {
        String resultMsg = null;
        String errorMsg = null;
        MessageVo messageVo = new MessageVo();
        JPushClient jPushClient = null;
        try {
            PushPayload pushPayload = builderPushPayLoad(pushMessageVo);

            if (httpProxy != null) {
                jPushClient = new JPushClient(secretKey, appKey, 1, httpProxy);
            } else {
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
            Logger.error(">>出错详情：", errorMsg);

            messageVo.setMessage(new Message(Severity.ERROR, e.getErrorCode() + "", e.getErrorMessage(), errorMsg));
            messageVo.setValue(errorMsg);
            e.printStackTrace();
        } catch (Exception e) {
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

        Logger.info(MessageFormat.format(">>推送配置中的推送平台：{0},当前registerId对应的推送平台：{1}", commonService.findValueByCatPointKey(platform), commonService.findValueByCatPointKey(msgPlatform)));

        if (DictConst.PUSH_PLATFORM_IOS.equals(platform)) {
            pushPayload = builderIos(pushMessageVo);
        } else if (DictConst.PUSH_PLATFORM_ANDROID.equals(platform)) {
            pushPayload = builderAndroid(pushMessageVo);
        } else {
            if (DictConst.PUSH_PLATFORM_IOS.equals(msgPlatform)) {
                pushPayload = builderIos(pushMessageVo);
            } else {
                pushPayload = builderAndroid(pushMessageVo);
            }
        }
        return pushPayload;
    }

    private PushPayload builderAndroid(PushMessageVo pushMessageVo) {
        String contentPush = pushMessageVo.getContentPush();
        String title = pushMessageVo.getTitle();
        List<String> registrationIdList = pushMessageVo.getRegistrationIdList();

        PushPayload.Builder builder = PushPayload.newBuilder();

        builder.setPlatform(Platform.android());
        builder.setNotification(Notification.android(contentPush, title, null));

        if (registrationIdList.isEmpty()) {
            builder.setAudience(Audience.all());
        } else {
            builder.setAudience(Audience.registrationId(registrationIdList));//1000
        }
        boolean apns_production = ConfigUtil.getValueBoolean(ConfigUtil.apns_production);

        builder.setOptions(Options.newBuilder().setApnsProduction(apns_production).build());

        return builder.build();
    }

    private PushPayload builderIos(PushMessageVo pushMessageVo) {
        String contentPush = pushMessageVo.getContentPush();
        List<String> registrationIdList = pushMessageVo.getRegistrationIdList();

        PushPayload.Builder builder = PushPayload.newBuilder();

        builder.setPlatform(Platform.ios());

        Logger.info("badge：" + pushMessageVo.getBadge());
        builder.setNotification(Notification.newBuilder().addPlatformNotification(IosNotification.newBuilder().setAlert(contentPush).setBadge(pushMessageVo.getBadge()).build()).build());

        if (registrationIdList.isEmpty()) {
            builder.setAudience(Audience.all());
        } else {
            builder.setAudience(Audience.registrationId(registrationIdList));//1000
        }

        boolean apns_production = ConfigUtil.getValueBoolean(ConfigUtil.apns_production);

        builder.setOptions(Options.newBuilder().setApnsProduction(apns_production).build());

        return builder.build();
    }


}
