package com.sunlights.customer.action;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.sunlights.common.AppConst;
import com.sunlights.common.DictConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.service.ParameterService;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.vo.*;
import com.sunlights.customer.dal.CustomerDao;
import com.sunlights.customer.dal.MsgCenterDao;
import com.sunlights.customer.dal.impl.CustomerDaoImpl;
import com.sunlights.customer.dal.impl.MsgCenterDaoImpl;
import com.sunlights.customer.factory.ActivityServiceFactory;
import com.sunlights.customer.service.ActivityService;
import models.Activity;
import models.Customer;
import models.CustomerMsgPushTxn;
import models.MessageSmsTxn;
import play.Logger;
import play.Play;
import play.libs.Json;
import services.PushMessageService;
import services.SmsMessageService;

import java.sql.Timestamp;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: MsgCenterActionService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class MsgCenterActionService {

    private MsgCenterDao centerDao = new MsgCenterDaoImpl();
    private CustomerDao customerDao = new CustomerDaoImpl();
    private ParameterService parameterService = new ParameterService();
    private SmsMessageService smsMessageService = new SmsMessageService();
    private PushMessageService pushMessageService = new PushMessageService();
    private ActivityService activityService = ActivityServiceFactory.getActivityService();


    private final static String LOGIN = "login";
    private final static String LOGINBYGES = "loginByges";
    private final static String REGISTER = "register";
    //    private final static String pushUrl = Play.application().configuration().getString("push_url");
    private final static String smsUrl = Play.application().configuration().getString("sms_url");


    public void sendMsg(String routeActionMethod, List<MessageHeaderVo> messageHeaderVoList){
        for (MessageHeaderVo messageActivityVo : messageHeaderVoList) {
            String messageType = messageActivityVo.getMessageType();
            String customerId = messageActivityVo.getCustomerId();
            String scene = messageActivityVo.getScene();

            if (customerId == null) {
                Logger.error(">>当前发送的消息传入参数customerId为空");
            }

            List<String> ruleCodeList = Lists.newArrayList();
            if (DictConst.PUSH_TYPE_4.equals(messageType)) {//注册登录提示类  信息
                if (LOGIN.equals(routeActionMethod) || LOGINBYGES.equals(routeActionMethod)) {
                    routeActionMethod = LOGIN;
                    ruleCodeList = findLoginUnRemindRuleCodeList(customerId, routeActionMethod);
                }else if(REGISTER.equals(routeActionMethod)) {//注册完成后自动登录
                    routeActionMethod = REGISTER;
                    ruleCodeList = findLoginUnRemindRuleCodeList(customerId, routeActionMethod);
                }
            }else{//活动类、交易类  信息
                ruleCodeList = getRuleCodeList(routeActionMethod, messageType, scene);
            }
            for (String ruleCode : ruleCodeList) {
                Logger.info(MessageFormat.format("当前发送的消息规则编码：{0}", ruleCode));
                operationRuleCode(messageActivityVo, ruleCode);
            }
        }
    }

    public void operationRuleCode(MessageHeaderVo messageActivityVo, String ruleCode) {
        PushMessageVo pushMessageVo = centerDao.findMessageRuleByCode(ruleCode);
        if (pushMessageVo == null) {
            Logger.info(MessageFormat.format(">>消息规则{0} 未配置！", ruleCode));
            return ;
        }
        pushMessageVo.setCustomerId(messageActivityVo.getCustomerId());
        pushMessageVo.setContent(MessageFormat.format(pushMessageVo.getContent(), messageActivityVo.getParams().toArray()));
        Long id = pushMessageVo.getGroupId();
        if (id == null || id == 0) {//个人信息
            Logger.info(">>开始组建个人待发送消息");
            String pushInd = pushMessageVo.getPushInd();
            String smsInd = pushMessageVo.getSmsInd();

            pushMessageVo.setPersonalInd(AppConst.STATUS_VALID);

            if (AppConst.STATUS_VALID.equals(smsInd)) {
                Logger.info(">>开始发送短信");
                pushMessageVo.setContentSms(MessageFormat.format(pushMessageVo.getContentSms(), messageActivityVo.getParams().toArray()));
                sendSms(pushMessageVo);
            }
            if (AppConst.STATUS_VALID.equals(pushInd) && AppConst.STATUS_INVALID.equals(pushMessageVo.getPushTimed())) {//即时推送
                Logger.info(">>开始推送");
                pushMessageVo.setContentPush(MessageFormat.format(pushMessageVo.getContentPush(), messageActivityVo.getParams().toArray()));
                sendPush(pushMessageVo);
            }
        }else{//针对某个群组操作
        }

    }

    private List<String> findLoginUnRemindRuleCodeList(String customerId, String methodNameStr){
        List<Activity> activityList = activityService.getCurrentValidActivities();
        if (activityList.isEmpty()) {
            return Lists.newArrayList();
        }
        List<Long> activityIdList = Lists.newArrayList();
        for (Activity activity : activityList) {
            activityIdList.add(activity.getId());
        }

        String activityIdStr = activityIdList.toString().replace("[","(").replace("]", ")");
        List<String> ruleCodeList = centerDao.findUnRemindRuleCodeList(customerId,activityIdStr, methodNameStr);
        return ruleCodeList;
    }

    private List<String> getRuleCodeList(String routeActionMethod, String messageType, String scene) {
        List<String> list = centerDao.findMessageRuleCodeList(routeActionMethod, messageType, scene);

        if (list.isEmpty()){
            Logger.info(">>未配置消息规则映射表");
        }

        return list;
    }

    /**
     *
     * @param pushMessageVo 规则信息
     */
    private void sendPush(PushMessageVo pushMessageVo) {
        String customerId = pushMessageVo.getCustomerId();

        List<MsgSettingVo> registrationIds = customerDao.findRegistrationIdsByCustomerId(customerId);

        if (registrationIds.isEmpty()) {
            Logger.debug(MessageFormat.format("未查询到需要信息发送的接收者！当前客户号：{0}", customerId));
            return ;
        }

        CustomerMsgPushTxn customerMsgPushTxn = createCustomerMsgPushTxn(pushMessageVo);
        Timestamp currentTime = DBHelper.getCurrentTime();
        customerMsgPushTxn.setPushTime(currentTime);
        customerMsgPushTxn.setUpdateTime(currentTime);
        centerDao.createCustomerMsgPushTxn(customerMsgPushTxn);

        int badge = 0;
        List<String> registrationIdList = Lists.newArrayList();
        for (MsgSettingVo msgSettingVo : registrationIds) {
            if (Strings.isNullOrEmpty(msgSettingVo.getRegistrationId())) {
                continue;
            }
            registrationIdList = Lists.newArrayList();
            registrationIdList.add(msgSettingVo.getRegistrationId());
            pushMessageVo.setRegistrationIdList(registrationIdList);
            if ("Y".equals(msgSettingVo.getLoginStatus())) {//login
                badge = centerDao.countUnReadNum(customerId, msgSettingVo.getDeviceNo());
            }else{
                badge = centerDao.countUnReadNum(msgSettingVo.getDeviceNo());
            }
            pushMessageVo.setBadge(badge);

            executePush(pushMessageVo);
        }


    }

    /**
     *
     * @param pushMessageVo 短信信息
     */
    private void sendSms(PushMessageVo pushMessageVo) {

        Customer customer = customerDao.getCustomerByCustomerId(pushMessageVo.getCustomerId());
        MessageSmsTxn messageSmsTxn = createMessageSmsTxn(pushMessageVo, customer.getMobile());

        executeSmsWS(messageSmsTxn);

    }

    public void executeSmsWS(MessageSmsTxn messageSmsTxn) {

        Logger.debug(">>待发送消息内容：" + Json.toJson(messageSmsTxn));
        try {
//            F.Promise<MessageSmsTxn> messageSmsTxnPromise = WS.url(smsUrl).post(Json.toJson(messageSmsTxn)).map(new F.Function<WSResponse, MessageSmsTxn>() {
//                @Override
//                public MessageSmsTxn apply(WSResponse wsResponse) throws Throwable {
//                    MessageSmsTxn messageSmsTxn = Json.fromJson(wsResponse.asJson(), MessageSmsTxn.class);
//                    return messageSmsTxn;
//                }
//            });
//            MessageSmsTxn resultMessageSmsTxn = messageSmsTxnPromise.get(10, TimeUnit.SECONDS);
            MessageSmsTxn resultMessageSmsTxn = smsMessageService.sendSms(messageSmsTxn);

            centerDao.createMessageSmsTxn(resultMessageSmsTxn);
        }catch (Exception e){
            centerDao.createMessageSmsTxn(messageSmsTxn);
            Logger.error(">>messageSmsTxn:" + Json.toJson(messageSmsTxn), e);
            e.printStackTrace();
        }
    }

    private MessageVo executePushWS(PushMessageVo pushMessageVo) {

        try {
//            F.Promise<MessageVo> messageVoPromise = WS.url(pushUrl).post(Json.toJson(pushMessageVo)).map(new F.Function<WSResponse, MessageVo>() {
//                @Override
//                public MessageVo apply(WSResponse wsResponse) throws Throwable {
//                    MessageVo returnMsg = Json.fromJson(wsResponse.asJson(), MessageVo.class);
//                    return returnMsg;
//                }
//            });
//
//            MessageVo messageVo = messageVoPromise.get(15, TimeUnit.SECONDS);

            return pushMessageService.sendPush(pushMessageVo);

        }catch (Exception e){
            Logger.error(">>CustomerMsgPushTxn:" + Json.toJson(pushMessageVo), e);
            e.printStackTrace();
        }

        return new MessageVo(new Message(MsgCode.OPERATE_FAILURE));
    }

    private void executePush(PushMessageVo pushMessageVo) {
        int sendNum = pushMessageVo.getSendNum();

        MessageVo messageVo = executePushWS(pushMessageVo);

        int severity = messageVo.getMessage().getSeverity();

//        if (severity != 0 && sendNum < 3) {
//            pushMessageVo.setSendNum(sendNum + 1);
//            executePush(pushMessageVo);
//        }else{
            createPushTxn(pushMessageVo, (String) messageVo.getValue(), severity);
//        }

    }

    private void createPushTxn(PushMessageVo pushMessageVo, String result, int severity){
        CustomerMsgPushTxn customerMsgPushTxn = createCustomerMsgPushTxn(pushMessageVo);

        if (severity == 0) {
            JsonNode jsonNode = Json.parse(result);
            String returnMsgId = jsonNode.get("msg_id").toString();
            String sendno = jsonNode.get("sendno").toString();
            customerMsgPushTxn.setReturnMsgId(returnMsgId);
            customerMsgPushTxn.setSendNo(sendno);
            customerMsgPushTxn.setPushStatus(DictConst.PUSH_STATUS_4);
        }else{
            customerMsgPushTxn.setPushStatus(DictConst.PUSH_STATUS_5);
            customerMsgPushTxn.setReturnMsgId(result);
        }
        Timestamp currentTime = DBHelper.getCurrentTime();
        customerMsgPushTxn.setSendNum(pushMessageVo.getSendNum());
        customerMsgPushTxn.setPushTime(currentTime);
        customerMsgPushTxn.setUpdateTime(currentTime);

//        centerDao.createCustomerMsgPushTxn(customerMsgPushTxn);

    }

    private CustomerMsgPushTxn createCustomerMsgPushTxn(PushMessageVo pushMessageVo) {
        CustomerMsgPushTxn customerMsgPushTxn = new CustomerMsgPushTxn();
        customerMsgPushTxn.setMessageRuleId(pushMessageVo.getMessageRuleId());
        customerMsgPushTxn.setTitle(pushMessageVo.getTitle());
        customerMsgPushTxn.setContent(pushMessageVo.getContent());
        customerMsgPushTxn.setPushStatus(DictConst.PUSH_STATUS_2);
        customerMsgPushTxn.setCustomerId(pushMessageVo.getCustomerId());
        customerMsgPushTxn.setCreateTime(DBHelper.getCurrentTime());

        return customerMsgPushTxn;
    }


    private MessageSmsTxn createMessageSmsTxn(PushMessageVo pushMessageVo, String mobilePhoneNo) {

        Timestamp currentTime = DBHelper.getCurrentTime();
        MessageSmsTxn messageSmsTxn = new MessageSmsTxn();
        messageSmsTxn.setMessageRuleId(pushMessageVo.getMessageRuleId());
        messageSmsTxn.setMobile(mobilePhoneNo);
        messageSmsTxn.setSmsId(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(DBHelper.getCurrentTime()));
        messageSmsTxn.setContent(pushMessageVo.getContentSms());
        messageSmsTxn.setTitle(pushMessageVo.getTitle());
        messageSmsTxn.setCreateTime(currentTime);

        return messageSmsTxn;
    }


}
