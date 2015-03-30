package com.sunlights.op.dal;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.vo.MessagePushConfigVo;

import java.util.List;

/**
 * Created by yanghong on 2014/11/27.
 */
public interface MessagePushConfigDao {

      public List<MessagePushConfigVo> findMessagePushConfig(PageVo pageVo);

      public void update(MessagePushConfigVo messagePushVo);

      public void save(MessagePushConfigVo messagePushVo);
//
//      public void saveMessPushTxn(MessagePushTxn messagePushTxn);
//
//
//      public List<MessagePushConfig> getMessPushConfigid();
//
//      public List<Group> getMessPushGroup();


}
