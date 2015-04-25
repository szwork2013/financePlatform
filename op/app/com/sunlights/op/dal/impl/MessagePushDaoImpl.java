package com.sunlights.op.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.dal.MessagePushDao;
import com.sunlights.op.vo.MessageRuleVo;
import models.Group;
import models.MessagePushConfig;
import models.MessagePushTxn;
import models.MessageRule;
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
                "ru.msg_center_ind,ru.push_ind,ru.message_push_config_id,ru.status,ru.stay_days,ru.group_id,ru.create_time,ru.update_time";

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
        String keys = "id,name,code,description,title,content,contentExt,contentSms,contentPush,smsInd,msgCenterInd,pushInd,messagePushConfigId,status,stayDays,groupId,createTime,updateTime,configRemarks,groupName";
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
    public MessageRule findMessageRule(String code){
        List<MessageRule> list = findBy(MessageRule.class, "code", code);
        return list.isEmpty() ? null : list.get(0);
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
    public List<String> findSendRuleCode(Long messageTxnId){
        String sql = "select mr.code" +
                "   from c_message_rule mr,c_message_push_txn mpt" +
                "  where mr.id = mpt.message_rule_id" +
                "    and mpt.id = :messageTxnId";
        Logger.info(sql);
        Query query = em.createNativeQuery(sql);
        query.setParameter("messageTxnId", messageTxnId);
        return query.getResultList();
    }

}
