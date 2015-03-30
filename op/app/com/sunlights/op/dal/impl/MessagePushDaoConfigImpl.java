package com.sunlights.op.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.service.CommonService;
import com.sunlights.common.service.PageService;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.dal.MessagePushConfigDao;
import com.sunlights.op.vo.MessagePushConfigVo;
import models.MessagePushConfig;
import play.Logger;

import java.util.*;

/**
 * Created by yanghong on 2014/12/14.
 */
public class MessagePushDaoConfigImpl extends EntityBaseDao implements MessagePushConfigDao {
    private PageService pageService = new PageService();
    @Override
    public List<MessagePushConfigVo> findMessagePushConfig(PageVo pageVo) {
        StringBuilder sb = new StringBuilder();
        sb.append(" select new com.sunlights.op.vo.MessagePushConfigVo(c) from MessagePushConfig c");
        sb.append(" where 1 = 1 and c.status='Y' ");
        sb.append("  /~and c.id = {id}~/ ");
        sb.append("  /~and c.remarks like {remarks}~/ ");
        sb.append(" order by c.id ");


        List<MessagePushConfigVo> messagelist = pageService.findXsqlBy(sb.toString(), pageVo);
        List<MessagePushConfigVo> messagelist1 = new ArrayList<MessagePushConfigVo>();
        CommonService commonService = new CommonService();
        for(MessagePushConfigVo pushConfigVo :  messagelist){
            pushConfigVo.setPlatformdes(commonService.findValueByCatPointKey(pushConfigVo.getPlatform()));
            pushConfigVo.setPushtypedes(commonService.findValueByCatPointKey(pushConfigVo.getPushtype()));
            messagelist1.add(pushConfigVo);
        }

        Logger.debug("messagelist size= " + messagelist.size());

        return messagelist1;
    }

    @Override
    public void update(MessagePushConfigVo messagePushVo) {
        MessagePushConfig messageRule= messagePushVo.convertToMessageRuleConfig();
        Date planBeginTime=AddOneDate(messageRule.getPlanBeginTime());
        Date planEndTime=AddOneDate(messageRule.getPlanEndTime());
        messageRule.setPlanBeginTime(planBeginTime);
        messageRule.setPlanEndTime(planEndTime);
        super.update(messageRule);
    }

    @Override
    public void save(MessagePushConfigVo messagePushVo) {
        MessagePushConfig messageRule= messagePushVo.convertToMessageRuleConfig();
        Date planBeginTime=AddOneDate(messageRule.getPlanBeginTime());
        Date planEndTime=AddOneDate(messageRule.getPlanEndTime());
        messageRule.setPlanBeginTime(planBeginTime);
        messageRule.setPlanEndTime(planEndTime);
        super.create(messageRule);
    }

    public static Date AddOneDate(Date date){//时间往后推一天
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
        return calendar.getTime();//这个时间就是日期往后推一天的结果
    }
//
//    @Override
//    public void saveMessPushTxn(MessagePushTxn messagePushTxn) {
//        List<MessagePushTxn> messagePushTxnList = findBy(MessagePushTxn.class, "messageRuleId", messagePushTxn.getMessageRuleId());
//        if(messagePushTxnList.size()>0) {
//            messagePushTxn.setId(messagePushTxnList.get(0).getId());
//            super.update(messagePushTxn);
//        }else{
//            super.create(messagePushTxn);
//        }
//
//    }
//
//    @Override
//    public List<MessagePushConfig> getMessPushConfigid() {
//        return findAll(MessagePushConfig.class);
//    }
//
//    @Override
//    public List<Group> getMessPushGroup() {
//        return findAll(Group.class);
//    }


}
