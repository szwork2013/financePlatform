package com.sunlights.op.dal.impl;

import com.sunlights.common.DictConst;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.common.vo.PushMessageVo;
import com.sunlights.op.dal.MessagePushDao;
import com.sunlights.op.vo.MessagePushSettingVo;
import com.sunlights.op.vo.MessageRuleVo;
import models.*;
import play.Logger;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by yanghong on 2014/12/14.
 */
public class MessagePushDaoImpl extends EntityBaseDao implements MessagePushDao {
    @Override
    public List<MessageRuleVo> findMessagePush(PageVo pageVo) {
        StringBuffer sb = new StringBuffer();

        String key = "ru.id,ru.name,ru.code,ru.description,ru.title,ru.content,ru.content_ext,ru.content_sms,ru.content_push,ru.sms_ind," +
                "ru.msg_center_ind,ru.push_ind,ru.message_push_config_id,ru.status,ru.stay_day_ind,ru.group_id,ru.create_time,ru.update_time";

        sb.append(" select " + key + ",");
        sb.append("  (select cf.remarks from c_message_push_config cf where cf.id = ru.message_push_config_id) as configRemarks,");
        sb.append( " (select gp.name from c_group gp where gp.id = ru.group_id) as groupName ");
        sb.append("  from c_message_rule ru ");
        sb.append( " where 1 = 1 ");
        sb.append( "  /~and ru.id = {id}~/ ");
        sb.append( "  /~and ru.name like {name}~/ ");
        sb.append( " /~and ru.code = {code} ~/ ");
        sb.append( " group by " + key);
        sb.append( " order by ru.create_time desc,ru.id desc");

        Query nativeQuery = createNativeQueryByMap(sb.toString(), pageVo.getFilter());
        int first = pageVo.getIndex();
        int pageSize = pageVo.getPageSize();
        if (first > 0) {
            nativeQuery.setFirstResult(first);
        }
        if (pageSize > 0) {
            nativeQuery.setMaxResults(pageSize);
        }
        List list = nativeQuery.getResultList();
        String keys = "id,name,code,description,title,content,contentExt,contentSms,contentPush,smsInd,msgCenterInd,pushInd,messagePushConfigId,status,stayDayInd,groupId,createTime,updateTime,configRemarks,groupName";
        List<MessageRuleVo> messageList = ConverterUtil.convert(keys, list, MessageRuleVo.class);

        StringBuffer countSql = new StringBuffer();
        countSql.append(" select count(1) from c_message_rule mr") ;
        countSql.append(" where 1 = 1 and mr.status='Y' ");
        countSql.append("  /~and mr.id = {id}~/ ");
        countSql.append("  /~and mr.name like {name}~/ ");
        countSql.append(" /~and mr.code = {code} ~/ ");
        Query countQuery = createNativeQueryByMap(countSql.toString(), pageVo.getFilter());
        pageVo.setCount(Integer.valueOf(countQuery.getSingleResult().toString()));

        return messageList;
    }


    @Override
    public void update(MessageRule messageRule) {
        super.update(messageRule);
    }

    @Override
    public void save(MessageRule messageRule) {
        super.create(messageRule);
    }

    @Override
    public MessagePushTxn updateMessPushTxn(MessagePushTxn messagePushTxn) {
        return super.update(messagePushTxn);
    }

    @Override
    public MessagePushTxn createMessPushTxn(MessagePushTxn messagePushTxn) {
        return create(messagePushTxn);
    }

    @Override
    public MessagePushTxn findMessagePushTxnById(Long messageTxnId){
        return findUniqueBy(MessagePushTxn.class, "id", messageTxnId);
    }

    @Override
    public List<MessagePushTxn> findMessagePushTxnByRuleId(Long messageRuleId){
        return findBy(MessagePushTxn.class, "messageRuleId", messageRuleId);
    }

    @Override
    public CustomerMsgPushTxn createCustomerMsgPushTxn(CustomerMsgPushTxn customerMsgPushTxn) {
        return create(customerMsgPushTxn);
    }

    @Override
    public List<MessagePushConfig> getMessPushConfigid() {
        return findAll(MessagePushConfig.class);
    }

    @Override
    public List<Group> getMessPushGroup() {
        return findAll(Group.class);
    }

    @Override
    public boolean checkMessPushConfig(Long configId) {
        List<MessagePushConfig> pushConfigList = findBy(MessagePushConfig.class, "id", configId);
        if (pushConfigList.isEmpty()) {
            return false;
        } else {
            MessagePushConfig msPush = pushConfigList.get(0);
            String N=msPush.getPushTimed();
            Logger.debug("N代表即时，该任务是:"+N);
            if("N".equalsIgnoreCase(N)){
                return true;
            }
            return false;
        }

    }

    @Override
    public List<PushMessageVo> findPushMessage(Long messageTxnId) {
        String sql = "select pc.platform, mr.title, mr.content, mr.content_ext,mr.content_sms,mr.content_push, mpt.id,mpt.send_no, null||'' as customer_id, mpt.send_num, mpt.group_id, 'N'||'' AS personalInd" +
                    "   from c_message_push_config pc,c_message_rule mr,c_message_push_txn mpt" +
                    "  where pc.id = mr.message_push_config_id " +
                    "    and mr.id = mpt.message_rule_id" +
                    "    and mpt.id = :messageTxnId";
        Logger.info(sql);
        Query query = em.createNativeQuery(sql);
        query.setParameter("messageTxnId", messageTxnId);
        List<Object[]> list = query.getResultList();

        List<PushMessageVo> pushMessageVoList = getPushMessageVos(list);

        return pushMessageVoList;
    }

    @Override
    public List<PushMessageVo> findPushMessageList(PageVo pageVo) {
        String sql = "SELECT pc.platform, mr.title, mr.content, mr.content_ext,mr.content_sms,mr.content_push,  " +
                "        mpt.id,mpt.send_no, mpt.customer_id, mpt.send_num, mpt.group_id, mpt.personalInd   " +
                " FROM c_message_push_config pc, " +
                "      c_message_rule mr, " +
                "     (" + searchGroupPushTxn() + " UNION " + searchCustomerPushTxn() + ") mpt " +
                " WHERE pc.id = mr.message_push_config_id" +
                "   AND mr.id = mpt.message_rule_id" +
                "   AND mr.push_ind = 'Y'" +
                "   AND pc.push_timed = 'Y'" +
                "   AND pc.plan_begin_time <= CURRENT_DATE" +
                "   AND (pc.plan_end_time is null or pc.plan_end_time >= CURRENT_DATE)";
        Logger.debug(sql);

        Query query = em.createNativeQuery(sql);
        query.setFirstResult(pageVo.getIndex());
        query.setMaxResults(pageVo.getPageSize());
        query.setParameter("pushStatus", DictConst.PUSH_STATUS_2);
        List<Object[]> list = query.getResultList();

        List<PushMessageVo> pushMessageVoList = getPushMessageVos(list);

        return pushMessageVoList;
    }

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
    public List<MessagePushSettingVo> findCustomerMsgSettingList() {
        String sql = " SELECT distinct c.registration_id, c.device_no,c.customer_id,c.platform, " +
                     "        CASE WHEN ( " +
                     "              SELECT cs.status " +
                     "                FROM c_customer_session cs " +
                     "               WHERE cs.customer_id = c.customer_id " +
                     "                 AND cs.device_no = c.device_no " +
                     "            ORDER BY cs.create_time DESC " +
                     "               LIMIT 1 offset 0 " +
                     "        ) = 'Y' THEN 'Y' ELSE 'N' END AS loginStatus " +
                     "  FROM c_customer_msg_setting c " +
                     " WHERE c.push_open_status = 'Y' " +
                     "   and c.device_no is not null" +
                     "   and c.registration_id is not null";

        Query query = em.createNativeQuery(sql);
        String keys = "registrationId,deviceNo,customerId,platform,loginStatus";
        return ConverterUtil.convert(keys, query.getResultList(), MessagePushSettingVo.class);
    }

    @Override
    public List<MessagePushSettingVo> findCustomerMsgSettingListByGroupId(Long groupId) {
        String sql = " SELECT distinct c.registration_id, c.device_no,c.customer_id,c.platform, " +
                "        CASE WHEN ( " +
                "              SELECT cs.status " +
                "                FROM c_customer_session cs " +
                "               WHERE cs.customer_id = c.customer_id " +
                "                 AND cs.device_no = c.device_no " +
                "            ORDER BY cs.create_time DESC " +
                "               LIMIT 1 offset 0 " +
                "        ) = 'Y' THEN 'Y' ELSE 'N' END AS loginStatus " +
                "  FROM c_customer_msg_setting c,c_customer_group cg " +
                " WHERE c.push_open_status = 'Y' " +
                "   and cg.customer_id = c.customer_id" +
                "   and c.device_no is not null" +
                "   and c.registration_id is not null" +
                "   and cg.group_id = :groupId";

        Logger.info(sql);

        Query query = em.createNativeQuery(sql);
        query.setParameter("groupId", groupId);
        String keys = "registrationId,deviceNo,customerId,platform,loginStatus";
        return ConverterUtil.convert(keys, query.getResultList(), MessagePushSettingVo.class);
    }

    public List<MessagePushSettingVo> findCustomerMsgSettingListByCustomerId(String customerId){
        String sql = " SELECT distinct c.registration_id, c.device_no,c.customer_id,c.platform, " +
                "        CASE WHEN ( " +
                "              SELECT cs.status " +
                "                FROM c_customer_session cs " +
                "               WHERE cs.customer_id = c.customer_id " +
                "                 AND cs.device_no = c.device_no " +
                "            ORDER BY cs.create_time DESC " +
                "               LIMIT 1 offset 0 " +
                "        ) = 'Y' THEN 'Y' ELSE 'N' END AS loginStatus " +
                "  FROM c_customer_msg_setting c " +
                " WHERE c.push_open_status = 'Y' " +
                "   and c.device_no is not null" +
                "   and c.registration_id is not null" +
                "   and c.customer_id = :customerId";

        Query query = em.createNativeQuery(sql);
        query.setParameter("customerId", customerId);
        String keys = "registrationId,deviceNo,customerId,platform,loginStatus";
        return ConverterUtil.convert(keys, query.getResultList(), MessagePushSettingVo.class);
    }


    private List<PushMessageVo> getPushMessageVos(List<Object[]> list) {
        String keys = "platform,title,content,contentExt,contentSms,contentPush,pushTxnId,sendNo,customerId,sendNum,groupId,personalInd";
        List<PushMessageVo> pushMessageVoList = ConverterUtil.convert(keys, list, PushMessageVo.class);

        return pushMessageVoList;
    }

    private String searchGroupPushTxn(){
        String sql = "SELECT pt.id, pt.message_rule_id, pt.send_no, NULL AS customer_id, pt.send_num, pt.group_id, 'N'||'' AS personalInd" +
                " FROM c_message_push_txn pt" +
                " WHERE pt.push_status = :pushStatus";
        return sql;
    }
    private String searchCustomerPushTxn(){
        String sql =  " SELECT cpt.id, cpt.message_rule_id, cpt.send_no, cpt.customer_id, cpt.send_num,NULL AS group_id, 'Y'||'' AS personalInd" +
                " FROM c_customer_msg_push_txn cpt" +
                " WHERE cpt.push_status = :pushStatus";
        return sql;
    }

    @Override
    public void batchUpdateCustomerMsgPushTxn(String idStr) {
        String sql = "update c_customer_msg_push_txn set push_status = :pushStatus where id in " + idStr;
        Query query = em.createNativeQuery(sql);
        query.setParameter("pushStatus", DictConst.PUSH_STATUS_4);
        query.executeUpdate();
        em.flush();
    }

    @Override
    public void batchUpdateMsgPushTxn(String idStr) {
        String sql = "update c_message_push_txn set push_status = :pushStatus where id in " + idStr;
        Query query = em.createNativeQuery(sql);
        query.setParameter("pushStatus", DictConst.PUSH_STATUS_4);
        query.executeUpdate();
        em.flush();
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
        String countSql =  "SELECT count(1) FROM (" + buildMsgCenterVoListSql() + ") AS T where read_ind = 'N'";

        Logger.debug(countSql);

        Query query = em.createNativeQuery(countSql);
        query.setParameter("deviceNo", deviceNo);
        return Integer.valueOf(query.getSingleResult().toString());
    }
    private String buildMsgCenterVoListSql(){
        String sql = "select ml.*,CASE WHEN ml.id IN (SELECT rh.push_txn_id FROM c_customer_msg_read_history rh WHERE rh.device_no = :deviceNo ) THEN 'Y' ELSE 'N' END AS read_ind " +
                "  FROM view_message_list ml  " +
                " where ml.customer_id IS NULL " +
                "   and ml.create_time >= " + buildRegisterTime(false);
        return sql;
    }
    private String buildMsgCenterVoListWithLoginSql(){
        String sql = " select ml.*, " +
                "         CASE WHEN ml.id IN (SELECT rh.push_txn_id FROM c_customer_msg_read_history rh WHERE rh.customer_id = :customerId " +
                "          or (rh.device_no = :deviceNo and rh.customer_id is null)) THEN 'Y' ELSE 'N' END AS read_ind " +
                "  FROM view_message_list ml " +
                " where (ml.customer_id IS NULL or ml.customer_id = :customerId)" +
                "   AND ml.create_time >= " + buildRegisterTime(true);
        return sql;
    }
    private String buildRegisterTime(boolean isLogin){
        String registerTime = null;
        if (isLogin) {
            registerTime = " (SELECT date_trunc('day',c.create_time) FROM c_customer c WHERE  c.customer_id = :customerId ) ";
        }else{
            registerTime = " (select date_trunc('day',c.create_time) " +
                    "   from c_customer c,c_customer_msg_setting cms" +
                    "  where cms.device_no = :deviceNo " +
                    "    and cms.customer_id = c.customer_id" +
                    "    and cms.push_open_status = 'Y' " +
                    "    and cms.registration_id is not null limit 1 offset 0) ";
        }
        return registerTime + " - (CASE WHEN stay_day_ind = 'Y' THEN interval '30 day' ELSE interval '0 day' END)";
    }
}
