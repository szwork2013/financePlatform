package services;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.service.ParameterService;
import com.sunlights.common.utils.ArithUtil;
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
//        String appKey = parameterService.getParameterByName(ParameterConst.APP_KEY);
//        String secretKey = parameterService.getParameterByName(ParameterConst.SECRET_KEY);

        String appKey = "b5763dd71f67ef2da3e08fa2";
        String secretKey = "d5bc1fca3c38f30e212a5b85";

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
        try {
            JPushClient jPushClient = new JPushClient(secretKey, appKey);
            PushPayload pushPayload  = builderPushPayLoad(pushMessageVo);

            Logger.info("================sendPush begin：==========");
            Logger.debug(pushPayload.toString());
            PushResult result = jPushClient.sendPush(pushPayload.toString());
            resultMsg = result.toString();

            Logger.info("===============sendPush return：==========");
            Logger.debug(resultMsg);


            messageVo.setMessage(new Message(MsgCode.OPERATE_SUCCESS));
            messageVo.setValue(resultMsg);
//            MessageUtil.getInstance().setMessage(new Message(MsgCode.OPERATE_SUCCESS), resultMsg);

        } catch (APIRequestException e) {
            Logger.info("================sendPush exception personalInd = " + pushMessageVo.getPersonalInd());
            Logger.info("================sendPush exception messagePushTxnId = " + pushMessageVo.getPushTxnId());
            errorMsg = MessageFormat.format("异常信息code:{0},msg:{1}", e.getErrorCode(), e.getErrorMessage());
            Logger.error(">>出错咯：" , errorMsg);

            messageVo.setMessage(new Message(Severity.ERROR, e.getErrorCode() + "", e.getErrorMessage(), errorMsg));
            messageVo.setValue(errorMsg);
//            MessageUtil.getInstance().setMessage(new Message(Severity.ERROR, e.getErrorCode() + "", e.getErrorMessage(), errorMsg), errorMsg);
            e.printStackTrace();
        } catch (Exception e){
            Logger.info("================sendPush exception personalInd = " + pushMessageVo.getPersonalInd());
            Logger.info("================sendPush exception messagePushTxnId = " + pushMessageVo.getPushTxnId());
            String detailMsg = e.getMessage();
            if (e.getMessage().length() >= 200) {
                detailMsg = e.getMessage().substring(0, 200);
            }
//            MessageUtil.getInstance().setMessage(new Message(Severity.FATAL, "fatal", e.toString(), detailMsg), detailMsg);
            e.printStackTrace();

            messageVo.setMessage(new Message(Severity.FATAL, "fatal", e.toString(), detailMsg));
            messageVo.setValue(detailMsg);
        }

        return messageVo;
    }

    private PushPayload builderPushPayLoad(PushMessageVo pushMessageVo) {
        PushPayload pushPayload = null;

        String platform = pushMessageVo.getPlatform(); //推送平台
        Logger.info(">>推送平台：" + platform);
//        if (DictConst.PUSH_PLATFORM_ALL.equals(platform)){
//            pushPayload = builderAll(pushMessageVo);
//        }else if (DictConst.PUSH_PLATFORM_ANDROID.equals(platform)) {
//            pushPayload = builderAndroid(pushMessageVo);
//        }else if (DictConst.PUSH_PLATFORM_IOS.equals(platform)) {
            pushPayload = builderIos(pushMessageVo);
//        }
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
        builder.setOptions(Options.newBuilder().build());

        return builder.build();
    }
    private PushPayload builderAll(PushMessageVo pushMessageVo){
        String contentPush = pushMessageVo.getContentPush();
        String title = pushMessageVo.getTitle();

        PushPayload.Builder builder = PushPayload.newBuilder();

        builder.setPlatform(Platform.all());
        builder.setNotification(Notification.alert(contentPush));
        builder.setAudience(Audience.all());
        builder.setOptions(Options.newBuilder().build());

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
        builder.setOptions(Options.newBuilder().build());

        return builder.build();
    }





}
