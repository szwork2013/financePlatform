package com.sunlights.customer.dal.impl;

import com.google.common.collect.Lists;
import com.sunlights.common.DictConst;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.common.vo.PushMessageVo;
import com.sunlights.customer.dal.MsgCenterDao;
import com.sunlights.customer.vo.MsgCenterDetailVo;
import com.sunlights.customer.vo.MsgCenterVo;
import models.*;
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
public class MsgCenterDaoImpl extends EntityBaseDao implements MsgCenterDao {

    @Override
    public PushMessageVo findMessageRuleByCode(String ruleCode) {
        String sql = "SELECT pc.platform, pc.push_timed, mr.push_ind, mr.sms_ind, mr.msg_center_ind," +
                "        mr.id, mr.title, mr.content, mr.content_ext, mr.group_id,mr.content_sms,mr.content_push " +
                "  FROM c_message_push_config pc, " +
                "       c_message_rule mr " +
                " WHERE pc.id = mr.message_push_config_id" +
                "   AND mr.status = 'Y'" +
                "   AND pc.status = 'Y'" +
                "   AND mr.code = ?1";

        Logger.debug(sql);

        List<Object[]> list = createNativeQuery(sql, ruleCode);
        String keys = "platform,pushTimed,pushInd,smsInd,msgCenterInd,messageRuleId,title,content,contentExt,groupId,contentSms,contentPush";
        List<PushMessageVo> voList = ConverterUtil.convert(keys, list, PushMessageVo.class);
        return voList.isEmpty() ? null : voList.get(0);
    }

    @Override
    public String findMobileByCustomerId(String customerId) {
        return findUniqueBy(Customer.class, "customerId", customerId).getMobile();
    }

    @Override
    public List<String> findMobileListByGroupId(Long groupId) {
        String mobileSql = "select c.mobile from c_customer c,c_customer_group g where g.customer_id = c.customer_id and g.group_id = :groupId";
        Query query = em.createNativeQuery(mobileSql);
        query.setParameter("groupId", groupId);
        return query.getResultList();
    }

    @Override
    public List<String> findAllMobileList(){
        String mobileSql = "select mobile from c_customer";
        Query query = em.createNativeQuery(mobileSql);
        return query.getResultList();
    }

    @Override
    public MessageSmsTxn createMessageSmsTxn(MessageSmsTxn messageSmsTxn) {
        return create(messageSmsTxn);
    }

    @Override
    public MessageSmsTxn updateMessageSmsTxn(MessageSmsTxn messageSmsTxn) {
        return update(messageSmsTxn);
    }

    @Override
    public CustomerMsgPushTxn findCustomerMsgPushTxn(Long customerMsgPushTxnId) {
        return find(CustomerMsgPushTxn.class, customerMsgPushTxnId);
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
    public MessagePushTxn findMessagePushTxn(Long messagePushTxnId) {
        return find(MessagePushTxn.class, messagePushTxnId);
    }

    @Override
    public void updateMessagePushTxn(MessagePushTxn messagePushTxn) {
        update(messagePushTxn);
    }

    @Override
    public void batchUpdateMsgPushTxn(String idStr) {
        String sql = " update c_message_push_txn " +
                "    set push_status = :pushStatus," +
                "        push_time = current_timestamp " +
                "  where id in ( select pt.id " +
                "                 from c_message_push_txn pt,c_message_rule mr " +
                "                where pt.message_rule_id = mr.id " +
                "                  and mr.id in " + idStr + ")";

        Query query = em.createNativeQuery(sql);
        query.setParameter("pushStatus", DictConst.PUSH_STATUS_4);
        query.executeUpdate();
        em.flush();
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
    public List<String> findUnRemindRuleCodeList(String customerId, String methodNameStr) {
        if ("register".equals(methodNameStr)) {
            methodNameStr = "'login','register'";
        }else{
            methodNameStr = "'" + methodNameStr + "'";
        }

        String sql = "select distinct mr.code " +
                " from c_message_rule_mapping mrm,c_message_rule mr,f_activity a" +
                " where mrm.rule_code = mr.code" +
                " and mrm.status = 'Y'" +
                " and mr.status = 'Y'" +
                " and mrm.method_name in (" + methodNameStr + ")" +
                " and mrm.activity_id = a.id" +
                " and a.status = 'N'  " +
                " and a.begin_time <= CURRENT_DATE " +
                " and a.end_time >= CURRENT_DATE " +
                " and mr.id not in (select ct.message_rule_id " +
                "                     from c_customer_msg_push_txn ct " +
                "                    where mr.id = ct.message_rule_id " +
                "                      and ct.customer_id = :customerId )";


        Logger.debug(sql);
        Query query = em.createNativeQuery(sql);
        query.setParameter("customerId", customerId);

        return query.getResultList();
    }

    @Override
    public List<MsgCenterVo> findMsgCenterVoListWithLogin(PageVo pageVo) {
        String customerId = (String) pageVo.get("customerId");
        String deviceNo = (String) pageVo.get("deviceNo");

        String sql = buildMsgCenterVoListWithLoginSql();
        Logger.debug(sql);

        Query query = em.createNativeQuery(sql);
        query.setParameter("customerId", customerId);
        query.setParameter("deviceNo", deviceNo);
        query.setFirstResult(pageVo.getIndex());
        query.setMaxResults(pageVo.getPageSize());
        List list = query.getResultList();
        String keys = "msgId,messageRuleId,title,summary,createTime,sendType,customerId,showTime,readInd";
        List<MsgCenterVo> msgCenterVoList = ConverterUtil.convert(keys, list, MsgCenterVo.class);

        String countSql = "SELECT count(1) FROM (" + buildMsgCenterVoListWithLoginSql() + ") AS T";
        query = em.createNativeQuery(countSql);
        query.setParameter("customerId", customerId);
        query.setParameter("deviceNo", deviceNo);
        pageVo.setCount(Integer.valueOf(query.getSingleResult().toString()));

        return msgCenterVoList;
    }

    private String buildMsgCenterVoListWithLoginSql() {
        String sql = " select ml.*, " +
                "         CASE WHEN ml.id IN (SELECT rh.push_txn_id FROM c_customer_msg_read_history rh WHERE rh.customer_id = :customerId " +
                "          or (rh.device_no = :deviceNo and rh.customer_id is null)) THEN 'Y' ELSE 'N' END AS read_ind " +
                "  FROM view_message_list ml " +
                " where (ml.customer_id IS NULL or ml.customer_id = :customerId)" +
                "   AND ml.show_time >= " + buildRegisterTime(true);
        return sql;
    }

    @Override
    public List<MsgCenterVo> findMsgCenterVoList(PageVo pageVo) {
        String sql = buildMsgCenterVoListSql();

        Query query = em.createNativeQuery(sql);
        query.setParameter("deviceNo", pageVo.get("deviceNo"));
        query.setFirstResult(pageVo.getIndex());
        query.setMaxResults(pageVo.getPageSize());
        Logger.debug(sql);
        List<Object[]> list = query.getResultList();
        String keys = "msgId,messageRuleId,title,summary,createTime,sendType,customerId,showTime,readInd";
        List<MsgCenterVo> msgCenterVoList = ConverterUtil.convert(keys, list, MsgCenterVo.class);

        String countSql = "select count(1) FROM (" + buildMsgCenterVoListSql() + ") AS T";
        query = em.createNativeQuery(countSql);
        query.setParameter("deviceNo", pageVo.get("deviceNo"));
        pageVo.setCount(Integer.valueOf(query.getSingleResult().toString()));

        return msgCenterVoList;
    }

    private String buildMsgCenterVoListSql() {
        String sql = "select ml.*,CASE WHEN ml.id IN (SELECT rh.push_txn_id FROM c_customer_msg_read_history rh WHERE rh.device_no = :deviceNo ) THEN 'Y' ELSE 'N' END AS read_ind " +
                "  FROM view_message_list ml  " +
                " where ml.customer_id IS NULL " +
                "   and ml.show_time >= " + buildRegisterTime(false);
        return sql;
    }

    private String buildRegisterTime(boolean isLogin) {
        String registerTime = null;
        if (isLogin) {//若登录 直接获取customerId查询注册时间
            registerTime = " (SELECT date_trunc('day',c.create_time)  FROM c_customer c WHERE  c.customer_id = :customerId ) ";
        } else {//未登录 通过deviceNo查询注册时间
            registerTime = " (select date_trunc('day',c.create_time) " +
                    "   from c_customer c,c_customer_msg_setting cms" +
                    "  where cms.device_no = :deviceNo " +
                    "    and cms.customer_id = c.customer_id" +
                    "    and cms.push_open_status = 'Y' " +
                    "    and cms.registration_id is not null limit 1 offset 0) ";
        }
        return registerTime;
    }

    @Override
    public int countUnReadNum(String customerId, String deviceNo) {
        String countSql = "SELECT count(1) FROM (" + buildMsgCenterVoListWithLoginSql() + ") AS T where read_ind = 'N'";

        Logger.debug(countSql);

        Query query = em.createNativeQuery(countSql);
        query.setParameter("customerId", customerId);
        query.setParameter("deviceNo", deviceNo);
        return Integer.valueOf(query.getSingleResult().toString());
    }

    @Override
    public int countUnReadNum(String deviceNo) {
        String countSql = "SELECT count(1) FROM (" + buildMsgCenterVoListSql() + ") AS T where read_ind = 'N'";

        Logger.debug(countSql);

        Query query = em.createNativeQuery(countSql);
        query.setParameter("deviceNo", deviceNo);
        return Integer.valueOf(query.getSingleResult().toString());
    }


    @Override
    public MsgCenterDetailVo findMsgCenterDetail(Long msgId, String sendType) {
        String sql = null;
        if (DictConst.SEND_TYPE_SMS.equals(sendType)) {
            sql = "select mst.title,mst.content,mst.create_time from c_message_sms_txn mst where mst.id = :msgId";
        } else if (DictConst.SEND_TYPE_PUSH_CUSTOMER.equals(sendType)) {
            sql = "select cmpt.title,cmpt.content,cmpt.create_time from c_customer_msg_push_txn cmpt where cmpt.id = :msgId";
        } else {
            sql = "select cpt.title,cpt.content,cpt.create_time from c_message_push_txn cpt where cpt.id = :msgId";
        }
        Query query = em.createNativeQuery(sql);
        query.setParameter("msgId", msgId);
        List<Object[]> list = query.getResultList();
        String keys = "title,content,createTime";
        List<MsgCenterDetailVo> msgCenterDetailVoList = ConverterUtil.convert(keys, list, MsgCenterDetailVo.class);
        return msgCenterDetailVoList.get(0);
    }

    @Override
    public void createMsgReadHistory(CustomerMsgReadHistory customerMsgReadHistory) {
        create(customerMsgReadHistory);
    }

    @Override
    public CustomerMsgReadHistory findMsgReadHistory(String deviceNo, Long msgId, String customerId) {
        List<CustomerMsgReadHistory> list = Lists.newArrayList();
        String sql = null;
        if (customerId == null) {
            sql = "select c from CustomerMsgReadHistory c where c.deviceNo = :deviceNo and c.pushTxnId = :msgId and c.customerId is null";
            Query query = em.createQuery(sql, CustomerMsgReadHistory.class);
            query.setParameter("deviceNo", deviceNo);
            query.setParameter("msgId", msgId);
            list = query.getResultList();
        } else {
            sql = "select c from CustomerMsgReadHistory c where c.deviceNo = :deviceNo and c.pushTxnId = :msgId and c.customerId = :customerId";
            Query query = em.createQuery(sql, CustomerMsgReadHistory.class);
            query.setParameter("deviceNo", deviceNo);
            query.setParameter("msgId", msgId);
            query.setParameter("customerId", customerId);
            list = query.getResultList();
        }

        return list.isEmpty() ? null : list.get(0);
    }



}
