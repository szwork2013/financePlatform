package com.sunlights.op.dal;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.vo.MessageRuleVo;
import models.Group;
import models.MessagePushConfig;
import models.MessagePushTxn;
import models.MessageRule;

import java.util.List;

/**
 * Created by yanghong on 2014/11/27.
 */
public interface MessagePushDao {

    public List<MessageRuleVo> findMessagePush(PageVo pageVo);

    public MessageRule findMessageRule(String code);
    public void update(MessageRule messageRule);
    public void save(MessageRule messageRule);

    public List<MessagePushTxn> findMessagePushTxnByRuleId(Long messageRuleId);
    public MessagePushTxn updateMessPushTxn(MessagePushTxn messagePushTxn);
    public MessagePushTxn createMessPushTxn(MessagePushTxn messagePushTxn);
    public MessagePushTxn findMessagePushTxnById(Long messageTxnId);


    public List<MessagePushConfig> getMessPushConfigid();

    public List<Group> getMessPushGroup();

    public boolean checkMessPushConfig(Long configId);

    public List<String> findSendRuleCode(Long messageTxnId);


}
