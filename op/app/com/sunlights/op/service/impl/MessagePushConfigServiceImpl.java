package com.sunlights.op.service.impl;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.dal.MessagePushConfigDao;
import com.sunlights.op.dal.impl.MessagePushDaoConfigImpl;
import com.sunlights.op.service.MessagePushConfigService;
import com.sunlights.op.vo.MessagePushConfigVo;

import java.util.List;

/**
 * Created by Administrator on 2014/12/14.
 */
public class MessagePushConfigServiceImpl implements MessagePushConfigService {
    private MessagePushConfigDao messagePushDAO = new MessagePushDaoConfigImpl();
    @Override
    public List<MessagePushConfigVo> findMessagePushConfig(PageVo pageVo) {
        List<MessagePushConfigVo> messagePush=messagePushDAO.findMessagePushConfig(pageVo);
        return messagePush;
    }

    @Override
    public void update(MessagePushConfigVo messagePushVo) {
        messagePushDAO.update(messagePushVo);
    }

    @Override
    public void save(MessagePushConfigVo messagePushVo) {
        messagePushDAO.save(messagePushVo);
    }
//
//    @Override
//    public void saveMessPushTxn(MessagePushTxn messagePushTxn) {
//        messagePushDAO.saveMessPushTxn(messagePushTxn);
//    }
//
//    @Override
//    public List<MessagePushConfig> getMessPushConfigid() {
//        return messagePushDAO.getMessPushConfigid();
//    }
//
//    @Override
//    public List<Group> getMessPushGroup() {
//        return messagePushDAO.getMessPushGroup();
//    }


}
