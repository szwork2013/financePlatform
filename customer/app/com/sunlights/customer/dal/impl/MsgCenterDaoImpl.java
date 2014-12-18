package com.sunlights.customer.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.customer.dal.MsgCenterDao;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.common.vo.PushMessageVo;
import models.CustomerMsgPushTxn;

import java.util.List;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: MsgCenterDaoImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class MsgCenterDaoImpl extends EntityBaseDao implements MsgCenterDao{
    @Override
    public PushMessageVo findMessageRuleByCode(String ruleCode) {
        String sql = "SELECT pc.platform, pc.push_timed, mr.push_ind, mr.sms_ind, mr.msg_center_ind," +
                    "        mr.id, mr.title, mr.content, mr.content_ext, mr.group_id " +
                    "  FROM c_message_push_config pc, " +
                    "       c_message_rule mr " +
                    " WHERE pc.id = mr.message_push_config_id" +
                    "   AND mr.status = 'Y'" +
                    "   AND pc.status = 'Y'" +
                    "   AND mr.code = ?1";
        List<Object[]> list = createNativeQuery(sql, ruleCode);
        String keys = "platform,pushTimed,pushInd,smsInd,msgCenterInd,messageRuleId,title,content,contentExt,groupId";
        List<PushMessageVo> voList = ConverterUtil.convert(keys, list, PushMessageVo.class);
        return voList.isEmpty() ? null : voList.get(0);
    }

    @Override
    public CustomerMsgPushTxn createCustomerMsgPushTxn(CustomerMsgPushTxn customerMsgPushTxn) {
        return create(customerMsgPushTxn);
    }

    @Override
    public CustomerMsgPushTxn updateCustomerMsgPushTxn(CustomerMsgPushTxn customerMsgPushTxn) {
        return update(customerMsgPushTxn);
    }


}
