package com.sunlights.op.service;

import com.sunlights.common.vo.MessageHeaderVo;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.vo.MessageRuleVo;
import models.Group;
import models.MessagePushConfig;
import models.MessagePushTxn;

import java.util.List;

/**
 * Created by tangweiqun on 2014/12、14.
 */
public interface MessageRuleService {


    /**
     * 查询消息推送规则
     *
     * @return
     */
    public List<MessageRuleVo> findMessagePush(PageVo pageVo);

    /**
     * 修改消息推送规则
     * @param messagePushVo
     */
    public void update(MessageRuleVo messagePushVo);

    /**
     * 保存消息推送规则
     * @param messagePushVo
     */
    public void save(MessageRuleVo messagePushVo);

    /**
     * 同步数据
     * @param messagePushVo
     */
    public MessagePushTxn saveMessPushTxn(MessageRuleVo messagePushVo);

    public MessagePushTxn findMessagePushTxnById(Long messageTxnId);


    public List<MessagePushConfig> getMessPushConfig();

    public List<Group> getMessPushGroup();

    public boolean checkMessPushConfig(Long configId);


    public List<MessageHeaderVo> findSendRuleCode(Long messageTxnId);


}
