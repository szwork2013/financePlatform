package com.sunlights.customer.dal;

import com.sunlights.common.vo.PushMessageVo;
import models.CustomerMsgPushTxn;

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
    public PushMessageVo findMessageRuleByCode(String ruleCode);
    
    public CustomerMsgPushTxn createCustomerMsgPushTxn(CustomerMsgPushTxn customerMsgPushTxn);

    public CustomerMsgPushTxn updateCustomerMsgPushTxn(CustomerMsgPushTxn customerMsgPushTxn);
}
