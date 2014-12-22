package com.sunlights.customer.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.common.vo.PushMessageVo;
import com.sunlights.customer.dal.MsgCenterDao;
import models.CustomerMsgPushTxn;
import models.MessageRule;
import models.MessageSmsTxn;
import play.Logger;

import javax.persistence.Query;
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
    public MessageRule findMessageRuleSmsByCode(String ruleCode) {
        return findUniqueBy(MessageRule.class, "code" ,ruleCode);
    }

    @Override
    public MessageSmsTxn createMessageSmsTxn(MessageSmsTxn messageSmsTxn) {
        return create(messageSmsTxn);
    }

    @Override
    public CustomerMsgPushTxn createCustomerMsgPushTxn(CustomerMsgPushTxn customerMsgPushTxn) {
        return create(customerMsgPushTxn);
    }

    @Override
    public CustomerMsgPushTxn updateCustomerMsgPushTxn(CustomerMsgPushTxn customerMsgPushTxn) {
        return update(customerMsgPushTxn);
    }

    @Override
    public List<String> findMessageRuleCodeList(String methodName, String messageType, String scene) {
        String sql = "select mr.ruleCode " +
                "  from MessageRuleMapping mr " +
                " where mr.status = 'Y'" +
                " /~ and mr.methodName = {methodName} ~/" +
                " /~ and mr.messageType = {messageType} ~/" +
                " /~ and mr.scene = {scene} ~/" +
                " /~ and mr.activityId = {activityId} ~/";
        PageVo pageVo = new PageVo();
        pageVo.put("EQS_methodName", methodName);
        pageVo.put("EQS_messageType", messageType);
        pageVo.put("EQS_scene", scene);
        return findByMap(sql, pageVo.getFilter());
    }

    @Override
    public List<String> findUnRemindRuleCodeList(String customerId, String activityIdStr, String methodNameStr) {
        String hasSendMsg =
                "select ct.message_rule_id from c_customer_msg_push_txn ct where ct.customer_id = :customerId and mr.id = ct.message_rule_id" +
                " union "+
                " select st.message_rule_id from c_message_sms_txn st,c_customer c where st.mobile = c.mobile and c.customer_id = :customerId  and mr.id = st.message_rule_id";

        StringBuffer sb = new StringBuffer();
        sb.append("select distinct mr.code from c_message_rule_mapping mrm,c_message_rule mr")
         .append(" where mrm.rule_code = mr.code")
         .append(" and mrm.status = 'Y'")
         .append(" and mr.status = 'Y'");
        if ("register".equals(methodNameStr)) {
            sb.append(" and (mrm.method_name = 'login' or mrm.method_name = 'register')");
        }else if ("login".equals(methodNameStr)){
            sb.append(" and mrm.method_name = 'login'");
        }
        sb.append(" and mrm.activity_id in " + activityIdStr)
         .append(" and mr.id not in (" + hasSendMsg + ")");

        Logger.debug(sb.toString());
        Query query = em.createNativeQuery(sb.toString());
        query.setParameter("customerId", customerId);
        return query.getResultList();
    }

}
