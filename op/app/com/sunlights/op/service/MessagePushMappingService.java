package com.sunlights.op.service;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.vo.MessagePushMappingVo;
import models.Activity;
import models.ActivityScene;

import java.util.List;

/**
 * Created by tangweiqun on 2014/12、14.
 */
public interface MessagePushMappingService {

    /**
     * 查询消息推送规则
     *
     * @return
     */
    public List<MessagePushMappingVo> findMessagePushMapping(PageVo pageVo);

    /**
     * 保存
     * @param messagePushVo
     */
     public void save(MessagePushMappingVo messagePushVo);

     public void update(MessagePushMappingVo messagePushVo);

     public List<ActivityScene> getScenes();

     public List<Activity> findActivityIdByScene(String scene);

     public void deleteById(Long id);


}
