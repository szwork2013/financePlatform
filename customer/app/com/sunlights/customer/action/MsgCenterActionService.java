package com.sunlights.customer.action;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.sunlights.common.AppConst;
import com.sunlights.common.DictConst;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.vo.MessageHeaderVo;
import com.sunlights.common.vo.MsgSettingVo;
import com.sunlights.common.vo.PushMessageVo;
import com.sunlights.common.vo.SmsMessageVo;
import com.sunlights.customer.dal.MsgCenterDao;
import com.sunlights.customer.dal.MsgSettingDao;
import com.sunlights.customer.dal.impl.MsgCenterDaoImpl;
import com.sunlights.customer.dal.impl.MsgSettingDaoImpl;
import models.CustomerMsgPushTxn;
import models.MessageSmsTxn;
import org.apache.commons.beanutils.BeanUtils;
import play.Logger;
import services.PushMessageService;
import services.SmsMessageService;

import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private MsgSettingDao msgSettingDao = new MsgSettingDaoImpl();
    private SmsMessageService smsMessageService = new SmsMessageService();
    private PushMessageService pushMessageService = new PushMessageService();

    private final static String LOGIN = "login";
    private final static String LOGINBYGES = "loginByges";
    private final static String verifyCode = "VERIFY_CODE";

    private final static String personalSendInd = "Y";//按customer为单位  个人 发送
    private final static String groupSendInd = "N";//群发

    private final static String android = "android";
    private final static String ios = "ios";


    public void msgCenterSendMsg(String routeActionMethod, List<MessageHeaderVo> messageHeaderVoList) {
        Logger.info(">> msgCenterSendMsg  start >> ");
        for (MessageHeaderVo messageHeaderVo : messageHeaderVoList) {
            String messageType = messageHeaderVo.getMessageType();
            if (DictConst.PUSH_TYPE_5.equals(messageType)){
                handSendMsg(messageHeaderVo);
            }else{
                autoSendMsg(routeActionMethod, messageHeaderVo);
            }
        }

        Logger.info(">> msgCenterSendMsg  end >> ");
    }

    private String autoSendMsg(String routeActionMethod, MessageHeaderVo messageHeaderVo) {
        Logger.info("=============autoSendMsg 自动发送消息 start======");

        String messageType = messageHeaderVo.getMessageType();
        String customerId = messageHeaderVo.getCustomerId();
        String scene = messageHeaderVo.getScene();
        List<String> ruleCodeList = Lists.newArrayList();
        if (DictConst.PUSH_TYPE_4.equals(messageType)) {//注册登录提示类  信息
            if (LOGINBYGES.equals(routeActionMethod)) {
                routeActionMethod = LOGIN;
            }
            ruleCodeList = findLoginUnRemindRuleCodeList(customerId, routeActionMethod);
        } else {//活动类、交易类  信息
            ruleCodeList = getRuleCodeList(routeActionMethod, messageType, scene);
        }
        Logger.info(">>ruleCodeList size:" + ruleCodeList.size());
        for (String ruleCode : ruleCodeList) {
            Logger.info(MessageFormat.format("当前发送的消息规则编码：{0}", ruleCode));
            messageHeaderVo.setRuleCode(ruleCode);
            operationRuleCode(messageHeaderVo);
        }

        Logger.info("=============autoSendMsg 自动发送消息 end======");

        return routeActionMethod;
    }

    private void handSendMsg(MessageHeaderVo messageHeaderVo){
        Logger.info("=============handSendMsg 手动发送消息 start======");

        Logger.info(MessageFormat.format("当前发送的消息规则编码：{0}", messageHeaderVo.getRuleCode()));
        operationRuleCode(messageHeaderVo);

        Logger.info("=============handSendMsg 手动发送消息 end=========");
    }

    private void operationRuleCode(MessageHeaderVo messageHeaderVo) {
        String ruleCode = messageHeaderVo.getRuleCode();
        String messageType = messageHeaderVo.getMessageType();

        PushMessageVo pushMessageVo = centerDao.findMessageRuleByCode(ruleCode);
        if (pushMessageVo == null) {
            Logger.info(MessageFormat.format(">>消息规则{0} 未配置！", ruleCode));
            return;
        }
        pushMessageVo.setCustomerId(messageHeaderVo.getCustomerId());
        if (AppConst.STATUS_VALID.equals(pushMessageVo.getMsgCenterInd())) {
            pushMessageVo.setContent(MessageFormat.format(pushMessageVo.getContent(), messageHeaderVo.getParams().toArray()));
        }

        Logger.info(">>开始组建个人待发送消息");

        if (DictConst.PUSH_TYPE_5.equals(messageType)) {
            pushMessageVo.setPersonalInd(groupSendInd);//
        }else{
            pushMessageVo.setPersonalInd(personalSendInd);//
        }

        if (AppConst.STATUS_VALID.equals(pushMessageVo.getSmsInd())) {
            Logger.info(">>开始发送短信");
            pushMessageVo.setContentSms(MessageFormat.format(pushMessageVo.getContentSms(), messageHeaderVo.getParams().toArray()));
            List<String> mobiles = Lists.newArrayList();
            if (verifyCode.equals(ruleCode)) {//获取验证码 传入mobile
                mobiles.add(messageHeaderVo.getMobile());
            }else{
                mobiles = getSendMobiles(pushMessageVo);
            }
            sendSms(pushMessageVo, mobiles);
        }

        if (AppConst.STATUS_VALID.equals(pushMessageVo.getPushInd()) && AppConst.STATUS_INVALID.equals(pushMessageVo.getPushTimed())) {//即时推送
            Logger.info(">>开始推送");
            pushMessageVo.setContentPush(MessageFormat.format(pushMessageVo.getContentPush(), messageHeaderVo.getParams().toArray()));
            sendPush(pushMessageVo);
        }
    }

    private List<String> findLoginUnRemindRuleCodeList(String customerId, String methodNameStr) {
        if (customerId == null) {
            return Lists.newArrayList();
        }
        return centerDao.findUnRemindRuleCodeList(customerId, methodNameStr);
    }

    private List<String> getRuleCodeList(String routeActionMethod, String messageType, String scene) {
        List<String> list = centerDao.findMessageRuleCodeList(routeActionMethod, messageType, scene);

        if (list.isEmpty()) {
            Logger.info(">>未配置消息规则映射表");
        }

        return list;
    }


    /**
     * @param pushMessageVo 规则信息
     */
    private void sendPush(PushMessageVo pushMessageVo) {
        if (personalSendInd.equals(pushMessageVo.getPersonalInd())) {
            if (pushMessageVo.getCustomerId() == null) {
                Logger.error(">>  个人发送消息未传入customerId");
                return ;
            }
            createCustomerMsgPushTxn(pushMessageVo);
        }

        List<Long> groupMsgPushList = Lists.newArrayList();
        List<PushMessageVo> pushMessageVoList = buildPushMsgSettingList(pushMessageVo);

        if (pushMessageVoList.isEmpty()) {
            Logger.info(MessageFormat.format("未查询到需要信息发送的接收设备！当前客户号：{0}", pushMessageVo.getCustomerId()));
            return;
        }

        for (PushMessageVo pushMsg : pushMessageVoList) {
            pushMessageService.sendPush(pushMsg);
            if (groupSendInd.equals(pushMsg.getPersonalInd())) {
                groupMsgPushList.add(pushMessageVo.getMessageRuleId());
            }
        }

        //推送状态更新状态为推送成功
        if (!groupMsgPushList.isEmpty()) {
            String groupMsgTxn = groupMsgPushList.toString().replace("[","(").replace("]",")");
            centerDao.batchUpdateMsgPushTxn(groupMsgTxn);
        }
    }


    private List<PushMessageVo> buildPushMsgSettingList(PushMessageVo ...pushMessageVoList) {
        List<MsgSettingVo> allMsgSettingVos = msgSettingDao.findCustomerMsgSettingList();
        List<PushMessageVo> returnList = Lists.newArrayList();
        List<PushMessageVo> regIdList = Lists.newArrayList();

        for (PushMessageVo pushMessageVo : pushMessageVoList) {
            if (pushMessageVo.getCustomerId() != null) {//发送给一个人
                List<MsgSettingVo> personalMsgSettingVos = msgSettingDao.findCustomerMsgSettingListByCustomerId(pushMessageVo.getCustomerId());
                regIdList = buildMsgRegistrationIdList(pushMessageVo, personalMsgSettingVos);
            } else if (pushMessageVo.getGroupId() == null || pushMessageVo.getGroupId() == 0) {//发送给所有人
                regIdList = buildMsgRegistrationIdList(pushMessageVo, allMsgSettingVos);
            } else{//发送给指定群组
                List<MsgSettingVo> groupMsgSettingVos =  msgSettingDao.findCustomerMsgSettingListByGroupId(pushMessageVo.getGroupId());
                regIdList = buildMsgRegistrationIdList(pushMessageVo, groupMsgSettingVos);
            }
            returnList.addAll(regIdList);
        }

        return returnList;
    }

    private List<PushMessageVo> buildMsgRegistrationIdList(PushMessageVo pushMessageVo, List<MsgSettingVo> msgSettingVoList) {
        List<PushMessageVo> returnList = Lists.newArrayList();
        int badge = 0;
        List<String> androidRegIdList = Lists.newArrayList();
        Map<Integer,PushMessageVo> groupByBadgeMap = new HashMap<>();
        PushMessageVo newPushMessageVo = null;

        for (MsgSettingVo msgSettingVo : msgSettingVoList) {
            if (Strings.isNullOrEmpty(msgSettingVo.getRegistrationId())) {
                continue;
            }
            if (ios.equals(msgSettingVo.getPlatform())) {//IOS 角标设置 按角标数分组群发
                try {
                    if ("Y".equals(msgSettingVo.getLoginStatus())) {
                        badge = centerDao.countUnReadNum(msgSettingVo.getCustomerId(), msgSettingVo.getDeviceNo());
                    }else{
                        badge = centerDao.countUnReadNum(msgSettingVo.getDeviceNo());
                    }

                    if (groupByBadgeMap.get(badge) != null) {
                        newPushMessageVo = groupByBadgeMap.get(badge);
                    }else{
                        newPushMessageVo = (PushMessageVo) BeanUtils.cloneBean(pushMessageVo);
                        newPushMessageVo.setBadge(badge);
                        newPushMessageVo.setCustomerPlatform(DictConst.PUSH_PLATFORM_IOS);
                    }
                    newPushMessageVo.getRegistrationIdList().add(msgSettingVo.getRegistrationId());
                    groupByBadgeMap.put(badge, newPushMessageVo);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Logger.info(MessageFormat.format(">>badge：{0}, deviceNo:{1}, registrationId:{2}", badge, msgSettingVo.getDeviceNo(), msgSettingVo.getRegistrationId()));
            }else if (android.equals(msgSettingVo.getPlatform())){//ANDROID无需设置角标  可群发
                androidRegIdList.add(msgSettingVo.getRegistrationId());
            }
        }

        try {
            returnList.addAll(groupByBadgeMap.values());

            if (!androidRegIdList.isEmpty()) {
                newPushMessageVo = (PushMessageVo) BeanUtils.cloneBean(pushMessageVo);
                newPushMessageVo.setRegistrationIdList(androidRegIdList);
                newPushMessageVo.setCustomerPlatform(DictConst.PUSH_PLATFORM_ANDROID);
                returnList.add(newPushMessageVo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnList;
    }

    private CustomerMsgPushTxn createCustomerMsgPushTxn(PushMessageVo pushMessageVo) {
        CustomerMsgPushTxn customerMsgPushTxn = new CustomerMsgPushTxn();
        customerMsgPushTxn.setMessageRuleId(pushMessageVo.getMessageRuleId());
        customerMsgPushTxn.setTitle(pushMessageVo.getTitle());
        customerMsgPushTxn.setContent(pushMessageVo.getContent());
        customerMsgPushTxn.setCustomerId(pushMessageVo.getCustomerId());

        Timestamp currentTime = DBHelper.getCurrentTime();
        customerMsgPushTxn.setPushTime(currentTime);
        customerMsgPushTxn.setCreateTime(currentTime);
        customerMsgPushTxn.setUpdateTime(currentTime);
        customerMsgPushTxn.setPushStatus(DictConst.PUSH_STATUS_4);
        centerDao.createCustomerMsgPushTxn(customerMsgPushTxn);

        return customerMsgPushTxn;
    }


    /**
     * @param pushMessageVo 短信信息
     */
    private void sendSms(PushMessageVo pushMessageVo, List<String> mobiles) {

        if (mobiles.isEmpty()) {
            Logger.error(">>未查询到需要发送的手机号码");
            return ;
        }

        String title = pushMessageVo.getTitle();
        Long ruleId = pushMessageVo.getMessageRuleId();

        SmsMessageVo smsMessageVo = new SmsMessageVo();
        smsMessageVo.setContent(pushMessageVo.getContentSms());
        smsMessageVo.setMobileList(mobiles);

        List<SmsMessageVo> smsMessageVoList = smsMessageService.sendSms(smsMessageVo);
        for (SmsMessageVo messageVo : smsMessageVoList){
            MessageSmsTxn messageSmsTxn = buildMessageSmsTxn(messageVo);
            messageSmsTxn.setTitle(title);
            messageSmsTxn.setMessageRuleId(ruleId);
            centerDao.createMessageSmsTxn(messageSmsTxn);
        }
    }

    private List<String> getSendMobiles(PushMessageVo pushMessageVo) {
        List<String> mobiles = Lists.newArrayList();

        if (pushMessageVo.getCustomerId() == null) {
            if (personalSendInd.equals(pushMessageVo.getPersonalInd())) {
                Logger.error(">>  个人发送消息未传入customerId");
                return mobiles;
            }

            if (pushMessageVo.getGroupId() != null && pushMessageVo.getGroupId() != 0){
                mobiles = centerDao.findMobileListByGroupId(pushMessageVo.getGroupId());
            }else{
                mobiles = centerDao.findAllMobileList();
            }
        }else{
            String mobile = centerDao.findMobileByCustomerId(pushMessageVo.getCustomerId());
            mobiles.add(mobile);
        }

        return mobiles;
    }

    private MessageSmsTxn buildMessageSmsTxn(SmsMessageVo messageVo) {

        Timestamp currentTime = DBHelper.getCurrentTime();
        MessageSmsTxn messageSmsTxn = new MessageSmsTxn();
        messageSmsTxn.setSmsId(messageVo.getSmsId());
        messageSmsTxn.setContent(messageVo.getContent());
        messageSmsTxn.setMobile(messageVo.getMobileList().toString().replace("[", "").replace("]", ""));
        messageSmsTxn.setSuccessInd(messageVo.getSuccessInd());
        messageSmsTxn.setSmsId(messageVo.getSmsId());
        messageSmsTxn.setCreateTime(currentTime);

        return messageSmsTxn;
    }



}
