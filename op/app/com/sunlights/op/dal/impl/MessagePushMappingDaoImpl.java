package com.sunlights.op.dal.impl;


import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.service.CommonService;
import com.sunlights.common.service.PageService;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.dal.MessagePushMappingDao;
import com.sunlights.op.vo.MessagePushMappingVo;
import models.Activity;
import models.ActivityScene;
import models.MessageRuleMapping;
import org.apache.commons.lang3.StringUtils;
import play.Logger;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by yanghong on 2014/12/23.
 */
public class MessagePushMappingDaoImpl extends EntityBaseDao implements MessagePushMappingDao {
    private PageService pageService = new PageService();

    public List<MessagePushMappingVo> findMessagePushMapping(PageVo pageVo) {
        StringBuilder sb = new StringBuilder();
        sb.append(" select mrp.id,mrp.method_name,mrp.rule_code,mrp.message_type,mrp.scene,mrp.activity_id,mrp.status,fs.sceneDesc,fa.title from c_Message_Rule_Mapping mrp");
        sb.append(" left join ")
            .append("(select fas.code as scene,fas.name as sceneDesc from F_ACTIVITY_SCENE fas ")
            .append("union ")
            .append(" select fes.scene,fes.title as sceneDesc from f_excahnge_scene fes) fs ")
            .append(" on mrp.scene = fs.scene");
        sb.append(" left join f_activity fa on mrp.activity_id = fa.id ");
        sb.append(" and  mrp.status = 'Y'");
        sb.append(" order by mrp.rule_code ");

        Logger.info(sb.toString());

        Query query = em.createNativeQuery(sb.toString());
        query.setFirstResult(pageVo.getIndex());
        query.setMaxResults(pageVo.getPageSize());
        List list = query.getResultList();
        
        String keys = "id,methodName,ruleCode,messageType,scene,activityId,status,sceneDesc,activityDesc";
        List<MessagePushMappingVo> messagelist = ConverterUtil.convert(keys, list, MessagePushMappingVo.class);
        CommonService commonService = new CommonService();
        for(MessagePushMappingVo pushConfigVo :  messagelist){
            pushConfigVo.setMessageTypeDes(commonService.findValueByCatPointKey(pushConfigVo.getMessageType()));
        }

        query = em.createQuery("select count(1) from MessageRuleMapping c where c.status = 'Y'");
        int count = Integer.valueOf(query.getSingleResult().toString());

        pageVo.setCount(count);

        Logger.debug("messagelist size= " + messagelist.size());

        return messagelist;
    }

    @Override
    public void save(MessagePushMappingVo messagePushVo) {
          MessageRuleMapping messageRuleMp= messagePushVo.convertToMessageRuleMapping();
          super.create(messageRuleMp);
    }

    public void update(MessagePushMappingVo messagePushVo){
        MessageRuleMapping messageRuleMapping= messagePushVo.convertToMessageRuleMapping();
        super.update(messageRuleMapping);
    }

    @Override
    public List<ActivityScene> getScenes() {
        return findAll(ActivityScene.class);
    }

    @Override
    public List<Activity> findActivityIdByScene(String scene) {
        if(StringUtils.isEmpty(scene)){
            return findAll(Activity.class);
        }else{
            return findBy(Activity.class, "scene", scene);
        }

    }

    @Override
    public void deleteById(Long id) {
        MessageRuleMapping messageRuleMap = super.find(MessageRuleMapping.class, id);
        super.delete(messageRuleMap);
    }
}
