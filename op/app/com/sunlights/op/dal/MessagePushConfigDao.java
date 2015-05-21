package com.sunlights.op.dal;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.vo.MessagePushConfigVo;
import models.MessagePushConfig;

import java.util.List;

/**
 * Created by yanghong on 2014/11/27.
 */
public interface MessagePushConfigDao {

      public List<MessagePushConfigVo> findMessagePushConfig(PageVo pageVo);

      public void update(MessagePushConfig messagePushConfig);

      public void save(MessagePushConfig messagePushConfig);

}
