package com.sunlights.customer.action;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import com.sunlights.common.AppConst;
import com.sunlights.common.DictConst;
import com.sunlights.common.service.ParameterService;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.vo.MessageHeaderVo;
import com.sunlights.common.vo.MessageVo;
import com.sunlights.common.vo.PushMessageVo;
import com.sunlights.customer.dal.CustomerDao;
import com.sunlights.customer.dal.MsgCenterDao;
import com.sunlights.customer.dal.impl.CustomerDaoImpl;
import com.sunlights.customer.dal.impl.MsgCenterDaoImpl;
import com.sunlights.customer.factory.ActivityServiceFactory;
import com.sunlights.customer.service.ActivityService;
import com.sunlights.common.vo.MsgSettingVo;
import models.*;
import play.Logger;
import play.Play;
import play.libs.F;
import play.libs.Json;
import play.libs.ws.WS;
import play.libs.ws.WSResponse;

import java.sql.Timestamp;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    private ActivityService activityService = ActivityServiceFactory.getActivityService();

    private final static String LOGIN = "login";
    private final static String LOGINBYGES = "loginByges";
    private final static String REGISTER = "register";


    public void sendMsg(String routeActionMethod, List<MessageHeaderVo> messageHeaderVoList){
        for (MessageHeaderVo messageActivityVo : messageHeaderVoList) {
            String messageType = messageActivityVo.getMessageType();
            String customerId = messageActivityVo.getCustomerId();
            String scene = messageActivityVo.getScene();

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
            String pushInd = pushMessageVo.getPushInd();
            String smsInd = pushMessageVo.getSmsInd();

            pushMessageVo.setPersonalInd(AppConst.STATUS_VALID);

            if (AppConst.STATUS_VALID.equals(smsInd)) {
                sendSms(pushMessageVo);
            }
            if (AppConst.STATUS_VALID.equals(pushInd)) {//推送
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

        CustomerMsgPushTxn customerMsgPushTxn = createCustomerMsgPushTxn(pushMessageVo);

        if (sendNow(pushMessageVo)) {//即时发送
            //更新为正在发送中
            customerMsgPushTxn.setPushStatus(DictConst.PUSH_STATUS_3);
            customerMsgPushTxn.setUpdateTime(DBHelper.getCurrentTime());
            centerDao.updateCustomerMsgPushTxn(customerMsgPushTxn);

            pushMessageVo.setPushTxnId(customerMsgPushTxn.getId());

//            List<String> alias = getAliasList(pushMessageVo.getCustomerId());
//            pushMessageVo.setAliasList(alias);

            List<MsgSettingVo> registrationIds = getRegistrationIdList(pushMessageVo.getCustomerId());

            if (registrationIds.isEmpty()) {
                Logger.debug(MessageFormat.format("未查询到需要信息发送的接收者！当前客户号：{0}", pushMessageVo.getCustomerId()));
                return ;
            }
            int badge = 0;
            List<String> registrationIdList = Lists.newArrayList();
            for (MsgSettingVo msgSettingVo : registrationIds) {
                registrationIdList.add(msgSettingVo.getRegistrationId());
                pushMessageVo.setRegistrationIdList(registrationIdList);
                if ("Y".equals(msgSettingVo.getLoginStatus())) {//login
                    badge = centerDao.countUnReadNum(pushMessageVo.getCustomerId(), msgSettingVo.getDeviceNo());
                }else{
                    badge = centerDao.countUnReadNum(msgSettingVo.getDeviceNo());
                }
                pushMessageVo.setBadge(badge);

                executePush(pushMessageVo);
            }
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
        String pushUrl = Play.application().configuration().getString("sms_url");
        try {
            F.Promise<MessageSmsTxn> messageSmsTxnPromise = WS.url(pushUrl).post(Json.toJson(messageSmsTxn)).map(new F.Function<WSResponse, MessageSmsTxn>() {
                @Override
                public MessageSmsTxn apply(WSResponse wsResponse) throws Throwable {
                    MessageSmsTxn messageSmsTxn = Json.fromJson(wsResponse.asJson(), MessageSmsTxn.class);
                    return messageSmsTxn;
                }
            });

            MessageSmsTxn resultMessageSmsTxn = messageSmsTxnPromise.get(10, TimeUnit.SECONDS);
            centerDao.createMessageSmsTxn(resultMessageSmsTxn);
        }catch (Exception e){
            centerDao.createMessageSmsTxn(messageSmsTxn);
            Logger.error(">>messageSmsTxn:" + Json.toJson(messageSmsTxn), e);
            e.printStackTrace();
        }
    }

    private boolean sendNow(PushMessageVo pushMessageVo){
        return AppConst.STATUS_INVALID.equals(pushMessageVo.getPushTimed());
    }

    private void executePush(PushMessageVo pushMessageVo) {
        String pushUrl = Play.application().configuration().getString("push_url");
        F.Promise<MessageVo> messageVoPromise = WS.url(pushUrl).post(Json.toJson(pushMessageVo)).map(new F.Function<WSResponse, MessageVo>() {
            @Override
            public MessageVo apply(WSResponse wsResponse) throws Throwable {
                MessageVo returnMsg = Json.fromJson(wsResponse.asJson(), MessageVo.class);
                return returnMsg;
            }
        });

        MessageVo messageVo = messageVoPromise.get(15, TimeUnit.SECONDS);
        int sendNum = pushMessageVo.getSendNum();
        int severity = messageVo.getMessage().getSeverity();
        if (severity != 0 && sendNum < 3){
            pushMessageVo.setSendNum(sendNum + 1);
            executePush(pushMessageVo);
        }else{
            updatePushTxn(pushMessageVo, (String)messageVo.getValue(), severity);
        }
    }

    private void updatePushTxn(PushMessageVo pushMessageVo, String result, int severity){
        Timestamp currentTime = DBHelper.getCurrentTime();
        int sendNum = pushMessageVo.getSendNum();

        if ("Y".equals(pushMessageVo.getPersonalInd())) {
            CustomerMsgPushTxn customerMsgPushTxn = centerDao.findCustomerMsgPushTxn(pushMessageVo.getPushTxnId());
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
            customerMsgPushTxn.setSendNum(sendNum);
            customerMsgPushTxn.setPushTime(currentTime);
            customerMsgPushTxn.setUpdateTime(currentTime);
            centerDao.updateCustomerMsgPushTxn(customerMsgPushTxn);
        }else{
            MessagePushTxn messagePushTxn = centerDao.findMessagePushTxn(pushMessageVo.getPushTxnId());
            if (severity == 0) {
                JsonNode jsonNode = Json.parse(result);
                String returnMsgId = jsonNode.get("msg_id").toString();
                String sendno = jsonNode.get("sendno").toString();
                messagePushTxn.setReturnMsgId(returnMsgId);
                messagePushTxn.setSendNo(sendno);
                messagePushTxn.setPushStatus(DictConst.PUSH_STATUS_4);
            }else{
                messagePushTxn.setReturnMsgId(result);
                messagePushTxn.setPushStatus(DictConst.PUSH_STATUS_5);
            }
            messagePushTxn.setSendNum(sendNum);
            messagePushTxn.setPushTime(currentTime);
            messagePushTxn.setUpdateTime(currentTime);
            centerDao.updateMessagePushTxn(messagePushTxn);
        }
    }

    private List getAliasList(String customerId) {
        List aliasList = Lists.newArrayList();
        if (customerId != null) {
            aliasList = customerDao.findAliasByCustomerId(customerId);
        }

        return aliasList;
    }

    private List getRegistrationIdList(String customerId) {
        List registrationIdList = Lists.newArrayList();
        if (customerId != null) {
            registrationIdList = customerDao.findRegistrationIdsByCustomerId(customerId);
        }

        return registrationIdList;
    }

    private CustomerMsgPushTxn createCustomerMsgPushTxn(PushMessageVo pushMessageVo) {
        CustomerMsgPushTxn customerMsgPushTxn = new CustomerMsgPushTxn();
        customerMsgPushTxn.setMessageRuleId(pushMessageVo.getMessageRuleId());
        customerMsgPushTxn.setTitle(pushMessageVo.getTitle());
        customerMsgPushTxn.setContent(pushMessageVo.getContent());
        customerMsgPushTxn.setPushStatus(DictConst.PUSH_STATUS_2);
        customerMsgPushTxn.setCustomerId(pushMessageVo.getCustomerId());
        customerMsgPushTxn.setCreateTime(DBHelper.getCurrentTime());
        centerDao.createCustomerMsgPushTxn(customerMsgPushTxn);
        return customerMsgPushTxn;
    }


    private MessageSmsTxn createMessageSmsTxn(PushMessageVo pushMessageVo, String mobilePhoneNo) {

        Timestamp currentTime = DBHelper.getCurrentTime();
        MessageSmsTxn messageSmsTxn = new MessageSmsTxn();
        messageSmsTxn.setMessageRuleId(pushMessageVo.getMessageRuleId());
        messageSmsTxn.setMobile(mobilePhoneNo);
        messageSmsTxn.setSmsId(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(DBHelper.getCurrentTime()));
        messageSmsTxn.setContent(pushMessageVo.getContent());
        messageSmsTxn.setTitle(pushMessageVo.getTitle());
        messageSmsTxn.setCreateTime(currentTime);

        return messageSmsTxn;
    }


}
