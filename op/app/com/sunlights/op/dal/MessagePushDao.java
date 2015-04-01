package com.sunlights.op.dal;

import com.sunlights.common.vo.PageVo;
import com.sunlights.common.vo.PushMessageVo;
import com.sunlights.op.vo.MessagePushSettingVo;
import com.sunlights.op.vo.MessageRuleVo;
import models.CustomerMsgPushTxn;
import models.Group;
import models.MessagePushConfig;
import models.MessagePushTxn;

import java.util.List;

/**
 * Created by yanghong on 2014/11/27.
 */
public interface MessagePushDao {

    public List<MessageRuleVo> findMessagePush(PageVo pageVo);

    public void update(MessageRuleVo messagePushVo);

    public void save(MessageRuleVo messagePushVo);

    public MessagePushTxn saveMessPushTxn(MessagePushTxn messagePushTxn);
    public MessagePushTxn findMessagePushTxnById(Long messageTxnId);
    public CustomerMsgPushTxn createCustomerMsgPushTxn(CustomerMsgPushTxn customerMsgPushTxn);


    public List<MessagePushConfig> getMessPushConfigid();

    public List<Group> getMessPushGroup();

    public boolean checkMessPushConfig(Long configId);


    /**
     * 查询组装待发送的信息
     * @param messageTxnId
     * @return
     */
    public List<PushMessageVo> findPushMessage(Long messageTxnId);

    /**
     * 定时任务查询待推送信息列表
     * @return
     */
    public List<PushMessageVo> findPushMessageList(PageVo pageVo);

    public PushMessageVo findMessageRuleByCode(String ruleCode);

    public List<MessagePushSettingVo> findCustomerMsgSettingList();
    public List<MessagePushSettingVo> findCustomerMsgSettingListByGroupId(Long groupId);
    public List<MessagePushSettingVo> findCustomerMsgSettingListByCustomerId(String customerId);

    public int countUnReadNum(String customerId, String deviceNo);
    public int countUnReadNum(String deviceNo);

    /**
     * 批量更新c_customer_msg_push_txn
     * @param idStr 需要更新的c_customer_msg_push_txn.id字符串
     */
    public void batchUpdateCustomerMsgPushTxn(String idStr);

    /**
     * 批量更新 c_message_push_txn
     * @param idStr 需要更新的 c_message_push_txn.id字符串
     */
    public void batchUpdateMsgPushTxn(String idStr);


}
