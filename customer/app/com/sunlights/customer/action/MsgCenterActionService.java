package com.sunlights.customer.action;

import com.google.common.collect.Lists;
import com.sunlights.common.AppConst;
import com.sunlights.common.DictConst;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.vo.MessageHeaderVo;
import com.sunlights.common.vo.PageVo;
import com.sunlights.common.vo.PushMessageVo;
import com.sunlights.customer.dal.CustomerDao;
import com.sunlights.customer.dal.MsgCenterDao;
import com.sunlights.customer.dal.impl.CustomerDaoImpl;
import com.sunlights.customer.dal.impl.MsgCenterDaoImpl;
import models.CustomerMsgPushTxn;
import play.Logger;
import play.Play;
import play.libs.Json;
import play.libs.ws.WS;

import java.text.MessageFormat;
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


    public void sendMsg(String routeActionMethod, List<MessageHeaderVo> messageHeaderVoList){
        for (MessageHeaderVo messageActivityVo : messageHeaderVoList) {
            String messageType = messageActivityVo.getMessageType();

            List<String> list = Lists.newArrayList();
            if (DictConst.PUSH_TYPE_2.equals(messageType)){//活动类
                list = getRuleCodeList(routeActionMethod, messageActivityVo);
            }
            for (String ruleCode : list) {
                Logger.info(MessageFormat.format("当前发送的消息规则编码：{0}", ruleCode));
                operationRuleCode(messageActivityVo, ruleCode);
            }
        }
    }

    private List<String> getRuleCodeList(String routeActionMethod, MessageHeaderVo messageActivityVo) {
        PageVo pageVo = new PageVo();
        pageVo.put("EQS_methodName", routeActionMethod);
        pageVo.put("EQS_messageType", messageActivityVo.getMessageType());
        pageVo.put("EQS_scene", messageActivityVo.getScene());
//        pageVo.put("EQL_activityId", messageRuleMapping.getActivityId());
        List<String> list = centerDao.findMessageRuleCodeList(pageVo);

        if (list.isEmpty()){
            Logger.info(">>未配置消息规则映射表");
        }

        return list;
    }


    private void operationRuleCode(MessageHeaderVo messageActivityVo, String ruleCode) {
        MsgCenterDao centerDao = new MsgCenterDaoImpl();
        PushMessageVo pushMessageVo = centerDao.findMessageRuleByCode(ruleCode);
        if (pushMessageVo == null) {
            Logger.info(MessageFormat.format(">>消息规则{0} 未配置！", ruleCode));
            return ;
        }

        Long id = pushMessageVo.getGroupId();
        if (id == null || id == 0) {//个人信息
            String pushInd = pushMessageVo.getPushInd();
            String smsInd = pushMessageVo.getSmsInd();

            String customerId = messageActivityVo.getCustomerId();
            List<String> params = messageActivityVo.getParams();

            pushMessageVo.setPersonalInd(AppConst.STATUS_VALID);

            if (AppConst.STATUS_VALID.equals(pushInd)) {//推送
                supplementMessage(pushMessageVo, messageActivityVo);
                sendPush(pushMessageVo, customerId, ruleCode);
            }
//                if (AppConst.STATUS_VALID.equals(smsInd)) {
//                    createMsgSmsTxn();
//                }

        }else{//针对某个群组操作
        }

    }

    /**
     *
     * @param pushMessageVo 规则信息
     * @param customerId 当前操作的客户号
     * @param ruleCode 规则编码
     */
    public void sendPush(PushMessageVo pushMessageVo, String customerId, String ruleCode) {

        CustomerMsgPushTxn customerMsgPushTxn = createCustomerMsgPushTxn(pushMessageVo, customerId);

        if (sendNow(pushMessageVo)) {//即时发送
            //更新为正在发送中
            customerMsgPushTxn.setPushStatus(DictConst.PUSH_STATUS_3);
            customerMsgPushTxn.setUpdateTime(DBHelper.getCurrentTime());
            centerDao.updateCustomerMsgPushTxn(customerMsgPushTxn);

            pushMessageVo.setPushTxnId(customerMsgPushTxn.getId());

            List<String> alias = getAliasList(pushMessageVo.getCustomerId());
            if (alias.isEmpty()) {
//                return ;TODO
            }
            pushMessageVo.setAliasList(alias);

            executePush(pushMessageVo);
        }

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
            Logger.info(MessageFormat.format("未查询到需要信息发送的接收者！当前客户号：{0}", customerId));
//          throw new BusinessRuntimeException("未查询到需要信息发送的接收者！");
        }
        return aliasList;
    }

    private CustomerMsgPushTxn createCustomerMsgPushTxn(PushMessageVo pushMessageVo, String customerId) {
        CustomerMsgPushTxn customerMsgPushTxn = new CustomerMsgPushTxn();
        customerMsgPushTxn.setMessageRuleId(pushMessageVo.getMessageRuleId());
        customerMsgPushTxn.setTitle(pushMessageVo.getTitle());
        customerMsgPushTxn.setContent(pushMessageVo.getContent());
        customerMsgPushTxn.setPushStatus(DictConst.PUSH_STATUS_2);
        customerMsgPushTxn.setCustomerId(customerId);
        customerMsgPushTxn.setCreateTime(DBHelper.getCurrentTime());
        centerDao.createCustomerMsgPushTxn(customerMsgPushTxn);
        return customerMsgPushTxn;
    }


    /**
     * 待发送信息补充占位符信息
     * @param pushMessageVo
     * @param messageActivityVo
     */
    private void supplementMessage(PushMessageVo pushMessageVo, MessageHeaderVo messageActivityVo){
        String customerId = messageActivityVo.getCustomerId();
        List<String> params = messageActivityVo.getParams();
        String messageType = messageActivityVo.getMessageType();

        if (DictConst.PUSH_TYPE_2.equals(messageType)) {//活动类
            pushMessageVo.setCustomerId(customerId);
            pushMessageVo.setContent(MessageFormat.format(pushMessageVo.getContent(), params.toArray()));
        }

    }
}
