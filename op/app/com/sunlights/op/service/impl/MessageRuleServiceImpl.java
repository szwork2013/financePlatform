package com.sunlights.op.service.impl;

import com.google.common.collect.Lists;
import com.sunlights.common.DictConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.exceptions.BusinessRuntimeException;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.MessageHeaderVo;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.dal.MessagePushDao;
import com.sunlights.op.dal.impl.MessagePushDaoImpl;
import com.sunlights.op.service.MessageRuleService;
import com.sunlights.op.vo.MessageRuleVo;
import models.Group;
import models.MessagePushConfig;
import models.MessagePushTxn;
import models.MessageRule;

import java.util.List;

/**
 * Created by Administrator on 2014/12/14.
 */
public class MessageRuleServiceImpl implements MessageRuleService {
    private MessagePushDao messagePushDao = new MessagePushDaoImpl();


    @Override
    public List<MessageRuleVo> findMessagePush(PageVo pageVo) {
        List<MessageRuleVo> messagePush = messagePushDao.findMessagePush(pageVo);
        return messagePush;
    }

    @Override
    public void update(MessageRuleVo messagePushVo) {
        MessageRule messageRule = messagePushDao.findMessageRule(messagePushVo.getCode());
        if (!messageRule.getId().equals(messagePushVo.getId())) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.MESSAGE_RULE_ERROR));
        }

        messageRule = messagePushVo.convertToMessageRule();
        messageRule.setUpdateTime(DBHelper.getCurrentTime());
        messagePushDao.update(messageRule);
    }

    @Override
    public void save(MessageRuleVo messagePushVo) {
        MessageRule messageRule = messagePushDao.findMessageRule(messagePushVo.getCode());
        if (messageRule != null) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.MESSAGE_RULE_ERROR));
        }

        messageRule = messagePushVo.convertToMessageRule();
        messageRule.setCreateTime(DBHelper.getCurrentTime());
        messagePushDao.save(messageRule);
    }

    @Override
    public MessagePushTxn saveMessPushTxn(MessageRuleVo messagePushVo) {
        boolean isUpdate = false;
        MessagePushTxn messagePushTxn = null;
        List<MessagePushTxn> messagePushTxnList = messagePushDao.findMessagePushTxnByRuleId(messagePushVo.getId());

        if (!messagePushTxnList.isEmpty()) {
            isUpdate = true;
            messagePushTxn = messagePushTxnList.get(0);
            messagePushTxn.setUpdateTime(DBHelper.getCurrentTime());
        }else{
            messagePushTxn = new MessagePushTxn();
            messagePushTxn.setCreateTime(DBHelper.getCurrentTime());
            messagePushTxn.setPushStatus(DictConst.PUSH_STATUS_2);//待推送
        }

        messagePushTxn.setMessageRuleId(messagePushVo.getId());
        messagePushTxn.setGroupId(messagePushVo.getGroupId() == null ? 0 : messagePushVo.getGroupId());
        messagePushTxn.setTitle(messagePushVo.getTitle());
        messagePushTxn.setContent(messagePushVo.getContent());

        if(isUpdate) {
            messagePushDao.updateMessPushTxn(messagePushTxn);
        }else{
            messagePushDao.createMessPushTxn(messagePushTxn);
        }

        return messagePushTxn;
    }

    public MessagePushTxn findMessagePushTxnById(Long messageTxnId){
        return messagePushDao.findMessagePushTxnById(messageTxnId);
    }

    @Override
    public List<MessagePushConfig> getMessPushConfig() {
        return messagePushDao.getMessPushConfigid();
    }

    @Override
    public List<Group> getMessPushGroup() {
        return messagePushDao.getMessPushGroup();
    }

    @Override
    public boolean checkMessPushConfig(Long configId) {
        return messagePushDao.checkMessPushConfig(configId);
    }

    @Override
    public List<MessageHeaderVo> findSendRuleCode(Long messageTxnId) {
        List<MessageHeaderVo> list = Lists.newArrayList();
        MessageHeaderVo messageHeaderVo = null;
        List<String> ruleCodeList = messagePushDao.findSendRuleCode(messageTxnId);
        for (String ruleCode : ruleCodeList) {
            messageHeaderVo = new MessageHeaderVo();
            messageHeaderVo.setRuleCode(ruleCode);
            messageHeaderVo.setMessageType(DictConst.PUSH_TYPE_5);
            list.add(messageHeaderVo);
        }

        return  list;
    }

}
