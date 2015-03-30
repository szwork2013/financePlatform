package com.sunlights.op.service.impl;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.dal.MessagePushMappingDao;
import com.sunlights.op.dal.impl.MessagePushMappingDaoImpl;
import com.sunlights.op.service.MessagePushMappingService;
import com.sunlights.op.vo.MessagePushMappingVo;
import models.Activity;
import models.ActivityScene;

import java.util.List;

/**
 * Created by Administrator on 2014/12/14.
 */
public class MessagePushMappingServiceImpl implements MessagePushMappingService {
    private MessagePushMappingDao messagePushDAO = new MessagePushMappingDaoImpl();
    @Override
    public List<MessagePushMappingVo> findMessagePushMapping(PageVo pageVo) {
        List<MessagePushMappingVo> messagePush=messagePushDAO.findMessagePushMapping(pageVo);
        return messagePush;
    }

    @Override
    public void save(MessagePushMappingVo messagePushVo) {
        messagePushDAO.save(messagePushVo);
    }

    public void update(MessagePushMappingVo messagePushVo){
        messagePushDAO.update(messagePushVo);
    }

    @Override
    public List<ActivityScene> getScenes() {
        return messagePushDAO.getScenes();
    }

    @Override
    public List<Activity> findActivityIdByScene(String scene) {
        return messagePushDAO.findActivityIdByScene(scene);
    }

    @Override
    public void deleteById(Long id) {
        messagePushDAO.deleteById(id);
    }


}
