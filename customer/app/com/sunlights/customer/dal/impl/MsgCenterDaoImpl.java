package com.sunlights.customer.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.dal.PageDao;
import com.sunlights.common.dal.impl.PageDaoImpl;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.common.vo.PushMessageVo;
import com.sunlights.customer.dal.MsgCenterDao;
import models.CustomerMsgPushTxn;
import models.MessageRuleMapping;

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
    
    private PageDao pageDao = new PageDaoImpl();
    
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

    @Override
    public List<MessageRuleMapping> findMessageRuleMappingList(MessageRuleMapping messageRuleMapping) {
        String sql = "select mr " +
                "  from MessageRuleMapping mr " +
                " where mr.status = 'Y'" +
                " /~ and mr.methodName = {methodName} ~/" +
                " /~ and mr.messageType = {messageType} ~/" +
                " /~ and mr.scene = {scene} ~/" +
                " /~ and mr.activityId = {activityId} ~/";
        PageVo pageVo = new PageVo();
        pageVo.put("EQS_methodName", messageRuleMapping.getMethodName());
        pageVo.put("EQS_messageType", messageRuleMapping.getMessageType());
        pageVo.put("EQS_scene", messageRuleMapping.getScene());
        pageVo.put("EQL_activityId", messageRuleMapping.getActivityId());
        return pageDao.findXsqlBy(sql, pageVo);
    }
    @Override
    public List<String> findMessageRuleCodeList(PageVo pageVo) {
        String sql = "select mr.ruleCode " +
                "  from MessageRuleMapping mr " +
                " where mr.status = 'Y'" +
                " /~ and mr.methodName = {methodName} ~/" +
                " /~ and mr.messageType = {messageType} ~/" +
                " /~ and mr.scene = {scene} ~/" +
                " /~ and mr.activityId = {activityId} ~/";
        return findByMap(sql, pageVo.getFilter());
    }

}
