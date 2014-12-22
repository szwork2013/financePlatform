package com.sunlights.customer.dal;

import com.sunlights.common.vo.PushMessageVo;
import models.CustomerMsgPushTxn;
import models.MessageRule;
import models.MessageSmsTxn;

import java.util.List;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: MsgCenterDao.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public interface MsgCenterDao {
    /**
     * 推送
     * @param ruleCode
     * @return
     */
    public PushMessageVo findMessageRuleByCode(String ruleCode);

    public MessageRule findMessageRuleSmsByCode(String ruleCode);

    public MessageSmsTxn createMessageSmsTxn(MessageSmsTxn messageSmsTxn);
    
    public CustomerMsgPushTxn createCustomerMsgPushTxn(CustomerMsgPushTxn customerMsgPushTxn);

    public CustomerMsgPushTxn updateCustomerMsgPushTxn(CustomerMsgPushTxn customerMsgPushTxn);

    public List<String> findMessageRuleCodeList(String methodName, String messageType, String scene);

    /**
     * 查询 在有效时间范围内的 未提醒过的  活动提示
     * @return
     */
    public List<String> findUnRemindRuleCodeList(String customerId, String activityIdStr, String methodName);
}
