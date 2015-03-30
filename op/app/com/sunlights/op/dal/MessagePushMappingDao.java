package com.sunlights.op.dal;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.vo.MessagePushMappingVo;
import models.Activity;
import models.ActivityScene;

import java.util.List;

/**
 * Created by yanghong on 2014/11/27.
 */
public interface MessagePushMappingDao {

    public List<MessagePushMappingVo> findMessagePushMapping(PageVo pageVo);

    public void save(MessagePushMappingVo messagePushVo);

    public void update(MessagePushMappingVo messagePushVo);

    public List<ActivityScene> getScenes();

    public List<Activity> findActivityIdByScene(String scene);

    public void deleteById(Long id);




}
