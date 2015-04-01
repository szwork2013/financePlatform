package com.sunlights.op.service;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.vo.MessagePushConfigVo;

import java.util.List;

/**
 * Created by tangweiqun on 2014/12、14.
 */
public interface MessagePushConfigService {


    /**
     * 查询消息推送规则
     *
     * @return
     */
    public List<MessagePushConfigVo> findMessagePushConfig(PageVo pageVo);

    /**
     * 修改消息推送规则
     * @param messagePushVo
     */
      public void update(MessagePushConfigVo messagePushVo);

    /**
     * 保存消息推送规则
     * @param messagePushVo
     */
      public void save(MessagePushConfigVo messagePushVo);

//    /**
//     * 同步数据
//     * @param messagePushTxn
//     */
//      public void saveMessPushTxn(MessagePushTxn messagePushTxn);
//
//
//      public List<MessagePushConfig> getMessPushConfigid();
//
//      public List<Group> getMessPushGroup();


}
