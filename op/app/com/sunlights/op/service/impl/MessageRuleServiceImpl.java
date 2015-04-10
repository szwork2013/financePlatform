package com.sunlights.op.service.impl;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.sunlights.common.AppConst;
import com.sunlights.common.DictConst;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.vo.*;
import com.sunlights.op.dal.MessagePushDao;
import com.sunlights.op.dal.impl.MessagePushDaoImpl;
import com.sunlights.op.service.CustomerService;
import com.sunlights.op.service.MessageRuleService;
import com.sunlights.op.vo.CustomerVo;
import com.sunlights.op.vo.MessagePushSettingVo;
import com.sunlights.op.vo.MessageRuleVo;
import models.*;
import org.apache.commons.beanutils.BeanUtils;
import play.Logger;
import play.libs.Json;
import services.PushMessageService;
import services.SmsMessageService;

import java.sql.Timestamp;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Administrator on 2014/12/14.
 */
public class MessageRuleServiceImpl implements MessageRuleService {
    private MessagePushDao messagePushDAO = new MessagePushDaoImpl();
    private PushMessageService messagePushService = new PushMessageService();
    private SmsMessageService smsMessageService = new SmsMessageService();
    private CustomerService customerService = new CustomerServiceImpl();


    @Override
    public List<MessageRuleVo> findMessagePush(PageVo pageVo) {
        List<MessageRuleVo> messagePush=messagePushDAO.findMessagePush(pageVo);
        return messagePush;
    }

    @Override
    public void update(MessageRuleVo messagePushVo) {
        messagePushDAO.update(messagePushVo);
    }

    @Override
    public void save(MessageRuleVo messagePushVo) {
        messagePushDAO.save(messagePushVo);
    }

    @Override
    public MessagePushTxn saveMessPushTxn(MessagePushTxn messagePushTxn) {
        return messagePushDAO.saveMessPushTxn(messagePushTxn);
    }

    public MessagePushTxn findMessagePushTxnById(Long messageTxnId){
        return messagePushDAO.findMessagePushTxnById(messageTxnId);
    }

    @Override
    public List<MessagePushConfig> getMessPushConfig() {
        return messagePushDAO.getMessPushConfigid();
    }

    @Override
    public List<Group> getMessPushGroup() {
        return messagePushDAO.getMessPushGroup();
    }

    @Override
    public boolean checkMessPushConfig(Long configId) {
        return messagePushDAO.checkMessPushConfig(configId);
    }

    @Override
    public List<PushMessageVo> findPushMessage(Long messageTxnId) {
        List<PushMessageVo> pushMessageVoList = messagePushDAO.findPushMessage(messageTxnId);
        if (pushMessageVoList.isEmpty()){
            return pushMessageVoList;
        }

        List<PushMessageVo> returnList = Lists.newArrayList();

        buildPushMessageVoList(returnList, pushMessageVoList, null);

        return returnList;
    }

    @Override
    public List<PushMessageVo> findPushMessageList(PageVo pageVo) {
        List<PushMessageVo> returnList = Lists.newArrayList();

        List<PushMessageVo> pushMessageVoList = messagePushDAO.findPushMessageList(pageVo);
        buildPushMessageVoList(returnList, pushMessageVoList, null);

        return returnList;
    }

    private void buildPushMessageVoList(List<PushMessageVo> returnList, List<PushMessageVo> pushMessageVoList, String customerId) {
        List<MessagePushSettingVo> messagePushSettingVos = messagePushDAO.findCustomerMsgSettingList();
        Long groupId = null;
        for (PushMessageVo pushMessageVo : pushMessageVoList) {
            if (customerId != null) {
                List<MessagePushSettingVo> list = messagePushDAO.findCustomerMsgSettingListByCustomerId(customerId);
                transPushMessageVoList(returnList, pushMessageVo, list);
            }else{
                groupId = pushMessageVo.getGroupId();
                if (groupId != null && groupId != 0) {
                    List<MessagePushSettingVo> list =  messagePushDAO.findCustomerMsgSettingListByGroupId(groupId);
                    transPushMessageVoList(returnList, pushMessageVo, list);
                }else{
                    transPushMessageVoList(returnList, pushMessageVo, messagePushSettingVos);
                }
            }
        }
    }

    private void transPushMessageVoList(List<PushMessageVo> returnList, PushMessageVo pushMessageVo, List<MessagePushSettingVo> messagePushSettingVos) {
        int badge = 0;
        List<String> registrationIdList = null;

        for (MessagePushSettingVo messagePushSettingVo : messagePushSettingVos) {
            registrationIdList = Lists.newArrayList();
            if (Strings.isNullOrEmpty(messagePushSettingVo.getRegistrationId())) {
                continue;
            }
            if (messagePushSettingVo.getLoginStatus().equals("Y")) {
                badge = messagePushDAO.countUnReadNum(messagePushSettingVo.getCustomerId(), messagePushSettingVo.getDeviceNo());
            }else{
                badge = messagePushDAO.countUnReadNum(messagePushSettingVo.getDeviceNo());
            }
            Logger.info(MessageFormat.format(">>badge：{0}, deviceNo:{1}, registrationId:{2}", badge, messagePushSettingVo.getDeviceNo(), messagePushSettingVo.getRegistrationId()));
            try {
                PushMessageVo newPushMessageVo = (PushMessageVo) BeanUtils.cloneBean(pushMessageVo);
                newPushMessageVo.setBadge(badge + 1);
                registrationIdList.add(messagePushSettingVo.getRegistrationId());
                newPushMessageVo.setRegistrationIdList(registrationIdList);
                newPushMessageVo.setCustomerPlatform(messagePushSettingVo.getPlatform());
                returnList.add(newPushMessageVo);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void pushMessage(final List<PushMessageVo> list){
        Logger.info("=============推送开始轮循，待推送信息总数:" + list.size());

        List<Long> customerMsgPushList = Lists.newArrayList();
        List<Long> groupMsgPushList = Lists.newArrayList();

        for (PushMessageVo pushMessageVo : list) {
            MessageVo messageVo = messagePushService.sendPush(pushMessageVo);
            if (messageVo.getMessage().getSeverity() == 0) {
                if ("Y".equals(pushMessageVo.getPersonalInd())) {
                    customerMsgPushList.add(pushMessageVo.getPushTxnId());
                }else{
                    groupMsgPushList.add(pushMessageVo.getPushTxnId());
                }
            }
        }
        //更新状态为发送成功
        if (!customerMsgPushList.isEmpty()) {
            String customerMsgTxn = customerMsgPushList.toString().replace("[","(").replace("]",")");
            messagePushDAO.batchUpdateCustomerMsgPushTxn(customerMsgTxn);
        }
        if (!groupMsgPushList.isEmpty()) {
            String groupMsgTxn = groupMsgPushList.toString().replace("[","(").replace("]",")");
            messagePushDAO.batchUpdateMsgPushTxn(groupMsgTxn);
        }

        Logger.info("=============推送轮循完成=========");
    }


    public void pushMessagePersonal(MessageHeaderVo messageActivityVo, String ruleCode) {
        try {
            PushMessageVo pushVo = messagePushDAO.findMessageRuleByCode(ruleCode);
            if (pushVo == null) {
                Logger.info(MessageFormat.format(">>消息规则{0} 未配置！", ruleCode));
                return ;
            }

            List<PushMessageVo> returnList = Lists.newArrayList();
            String customerId = messageActivityVo.getCustomerId();

            List<PushMessageVo> pushMessageVoList = Lists.newArrayList();
            pushVo.setCustomerId(customerId);
            pushVo.setPersonalInd(AppConst.STATUS_VALID);
            pushVo.setContent(MessageFormat.format(pushVo.getContent(), messageActivityVo.getParams().toArray()));
            pushMessageVoList.add(pushVo);

            buildPushMessageVoList(returnList, pushMessageVoList, customerId);

            int sendNum = 0;//bug TODO 多次发送创建了多条消息

            for (PushMessageVo pushMessageVo : returnList) {
                String pushInd = pushMessageVo.getPushInd();
                String smsInd = pushMessageVo.getSmsInd();

                if (AppConst.STATUS_VALID.equals(smsInd)) {
                    Logger.info(">>开始发送短信");
                    pushMessageVo.setContentSms(MessageFormat.format(pushMessageVo.getContentSms(), messageActivityVo.getParams().toArray()));
                    PageVo pageVo = new PageVo();
                    pageVo.put("EQS_customerId", pushMessageVo.getCustomerId());
                    List<CustomerVo> customerVos = customerService.findCustomersBy(pageVo);
                    MessageSmsTxn messageSmsTxn = createMessageSmsTxn(pushMessageVo, customerVos.get(0).getMobilePhoneNo());

                    Logger.debug(">>待发送消息内容：" + Json.toJson(messageSmsTxn));
                    try {
                        SmsMessageVo smsMessageVo = new SmsMessageVo();
                        smsMessageVo.setContent(messageSmsTxn.getContent());
                        smsMessageVo.setMobile(messageSmsTxn.getMobile());
                        smsMessageVo.setSmsId(messageSmsTxn.getSmsId());

                        SmsMessageVo resultMessageSmsTxn = smsMessageService.sendSms(smsMessageVo);
                        messageSmsTxn.setSuccessInd(resultMessageSmsTxn.getSuccessInd());
                        if (sendNum == 0) {
                            customerService.createMessageSmsTxn(messageSmsTxn);
                        }
                    }catch (Exception e){
                        messageSmsTxn.setSuccessInd(AppConst.STATUS_INVALID);
                        if (sendNum == 0) {
                            customerService.createMessageSmsTxn(messageSmsTxn);
                        }
                        Logger.error(">>messageSmsTxn:" + Json.toJson(messageSmsTxn), e);
                        e.printStackTrace();
                    }
                if (AppConst.STATUS_VALID.equals(pushInd) && AppConst.STATUS_INVALID.equals(pushMessageVo.getPushTimed())) {//即时推送
                    Logger.info(">>开始推送");
                    pushMessageVo.setContentPush(MessageFormat.format(pushMessageVo.getContentPush(), messageActivityVo.getParams().toArray()));
                    if (sendNum == 0) {
                        createCustomerMsgPushTxn(pushMessageVo);
                    }

                    messagePushService.sendPush(pushMessageVo);
                }
            }
            sendNum++;
        }
        }catch (Exception ex) {
            ex.printStackTrace();
            Logger.error(">>pushMessagePersonal ruleCode：" + ruleCode, ex);
        }
    }

    private CustomerMsgPushTxn createCustomerMsgPushTxn(PushMessageVo pushMessageVo) {
        CustomerMsgPushTxn customerMsgPushTxn = new CustomerMsgPushTxn();
        customerMsgPushTxn.setMessageRuleId(pushMessageVo.getMessageRuleId());
        customerMsgPushTxn.setTitle(pushMessageVo.getTitle());
        customerMsgPushTxn.setContent(pushMessageVo.getContentPush());
        customerMsgPushTxn.setCustomerId(pushMessageVo.getCustomerId());

        Timestamp currentTime = DBHelper.getCurrentTime();
        customerMsgPushTxn.setCreateTime(currentTime);
        customerMsgPushTxn.setPushTime(currentTime);
        customerMsgPushTxn.setUpdateTime(currentTime);
        customerMsgPushTxn.setPushStatus(DictConst.PUSH_STATUS_4);
        messagePushDAO.createCustomerMsgPushTxn(customerMsgPushTxn);

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
