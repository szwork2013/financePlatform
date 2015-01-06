package com.sunlights.customer.action;

import com.google.common.collect.Lists;
import com.sunlights.common.AppConst;
import com.sunlights.common.DictConst;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.vo.MessageHeaderVo;
import com.sunlights.common.vo.PushMessageVo;
import com.sunlights.customer.dal.CustomerDao;
import com.sunlights.customer.dal.MsgCenterDao;
import com.sunlights.customer.dal.impl.CustomerDaoImpl;
import com.sunlights.customer.dal.impl.MsgCenterDaoImpl;
import com.sunlights.customer.factory.ActivityServiceFactory;
import com.sunlights.customer.service.ActivityService;
import com.sunlights.customer.service.impl.ActivityServiceImpl;
import models.Activity;
import models.Customer;
import models.CustomerMsgPushTxn;
import models.MessageSmsTxn;
import play.Logger;
import play.Play;
import play.libs.Json;
import play.libs.ws.WS;

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

            if (AppConst.STATUS_VALID.equals(pushInd)) {//推送
                sendPush(pushMessageVo);
            }
            if (AppConst.STATUS_VALID.equals(smsInd)) {
                sendSms(pushMessageVo);
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

        String activityIdStr = activityIdList.toString().replace("[","(").replace("]",")");
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
    public void sendPush(PushMessageVo pushMessageVo) {

        CustomerMsgPushTxn customerMsgPushTxn = createCustomerMsgPushTxn(pushMessageVo);

        if (sendNow(pushMessageVo)) {//即时发送
            //更新为正在发送中
            customerMsgPushTxn.setPushStatus(DictConst.PUSH_STATUS_3);
            customerMsgPushTxn.setUpdateTime(DBHelper.getCurrentTime());
            centerDao.updateCustomerMsgPushTxn(customerMsgPushTxn);

            pushMessageVo.setPushTxnId(customerMsgPushTxn.getId());

            List<String> alias = getAliasList(pushMessageVo.getCustomerId());
            if (alias.isEmpty()) {
                return ;
            }
            pushMessageVo.setAliasList(alias);

            executePush(pushMessageVo);
        }

    }

    /**
     *
     * @param pushMessageVo 短信信息
     */
    public void sendSms(PushMessageVo pushMessageVo) {

        Customer customer = customerDao.getCustomerByCustomerId(pushMessageVo.getCustomerId());
        MessageSmsTxn messageSmsTxn = createMessageSmsTxn(pushMessageVo, customer.getMobile());
        String smsUrl = Play.application().configuration().getString("sms_url");
        WS.url(smsUrl).post(Json.toJson(messageSmsTxn));

    }

    private boolean sendNow(PushMessageVo pushMessageVo){
        return AppConst.STATUS_INVALID.equals(pushMessageVo.getPushTimed());
    }

    private void executePush(PushMessageVo pushMessageVo) {
        List<PushMessageVo> list = Lists.newArrayList();
        list.add(pushMessageVo);

        String pushUrl = Play.application().configuration().getString("push_url");
        WS.url(pushUrl).post(Json.toJson(list));
    }

    private List getAliasList(String customerId) {
        List aliasList = Lists.newArrayList();
        if (customerId != null) {
            aliasList = customerDao.findAliasByCustomerId(customerId);
        }
        if (aliasList.isEmpty()) {
            Logger.error(MessageFormat.format("未查询到需要信息发送的接收者！当前客户号：{0}", customerId));
        }
        return aliasList;
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

        centerDao.createMessageSmsTxn(messageSmsTxn);

        return messageSmsTxn;
    }


}
