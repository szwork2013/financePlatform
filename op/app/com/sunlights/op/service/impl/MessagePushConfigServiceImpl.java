package com.sunlights.op.service.impl;

import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.dal.MessagePushConfigDao;
import com.sunlights.op.dal.impl.MessagePushDaoConfigImpl;
import com.sunlights.op.service.MessagePushConfigService;
import com.sunlights.op.vo.MessagePushConfigVo;
import models.MessagePushConfig;

import java.util.List;

/**
 * Created by Administrator on 2014/12/14.
 */
public class MessagePushConfigServiceImpl implements MessagePushConfigService {
    private MessagePushConfigDao messagePushDao = new MessagePushDaoConfigImpl();
    @Override
    public List<MessagePushConfigVo> findMessagePushConfig(PageVo pageVo) {
        List<MessagePushConfigVo> messagePush = messagePushDao.findMessagePushConfig(pageVo);
        return messagePush;
    }

    @Override
    public void update(MessagePushConfigVo messagePushVo) {
        MessagePushConfig messageRule = messagePushVo.convertToMessageRuleConfig();

        messageRule.setUpdateTime(DBHelper.getCurrentTime());

        messagePushDao.update(messageRule);
    }

    @Override
    public void save(MessagePushConfigVo messagePushVo) {
        MessagePushConfig messageRule = messagePushVo.convertToMessageRuleConfig();

        messageRule.setCreateTime(DBHelper.getCurrentTime());

        messagePushDao.save(messageRule);
    }


}
