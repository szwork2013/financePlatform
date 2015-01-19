package com.sunlights.customer.dal;

import com.sunlights.common.vo.PageVo;
import com.sunlights.common.vo.PushMessageVo;
import com.sunlights.customer.vo.MsgCenterDetailVo;
import com.sunlights.customer.vo.MsgCenterVo;
import models.*;

import java.util.List;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: MsgCenterDao.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public interface MsgCenterDao {
    /**
     * 推送
     * @param ruleCode
     * @return
     */
    public PushMessageVo findMessageRuleByCode(String ruleCode);

    public MessageRule findMessageRuleSmsByCode(String ruleCode);

    public MessageSmsTxn createMessageSmsTxn(MessageSmsTxn messageSmsTxn);
    public MessageSmsTxn updateMessageSmsTxn(MessageSmsTxn messageSmsTxn);

    public CustomerMsgPushTxn findCustomerMsgPushTxn(Long customerMsgPushTxnId);
    public CustomerMsgPushTxn createCustomerMsgPushTxn(CustomerMsgPushTxn customerMsgPushTxn);
    public CustomerMsgPushTxn updateCustomerMsgPushTxn(CustomerMsgPushTxn customerMsgPushTxn);

    public MessagePushTxn findMessagePushTxn(Long messagePushTxnId);
    public void updateMessagePushTxn(MessagePushTxn messagePushTxn);


    /**
     * 根据配置信息查询需要发送的消息编码集合
     * @param methodName 方法名
     * @param messageType 消息类型
     * @param scene 场景编码
     * @return
     */
    public List<String> findMessageRuleCodeList(String methodName, String messageType, String scene);

    /**
     * 查询 在有效时间范围内的 未提醒过的  活动提示
     * @return
     */
    public List<String> findUnRemindRuleCodeList(String customerId, String activityIdStr, String methodName);


    /**
     * 登录后消息中心分页查询
     * @param pageVo
     * @return
     */
    public List<MsgCenterVo> findMsgCenterVoListWithLogin(PageVo pageVo);
    public List<MsgCenterVo> findMsgCenterVoList(PageVo pageVo);

    public MsgCenterDetailVo findMsgCenterDetail(Long msgId, String sendType);


    public void createMsgReadHistory(CustomerMsgReadHistory customerMsgReadHistory);

    /**
     * 设备号、消息id,customerId 查询 此消息是否已读
     * @param deviceNo
     * @param msgId
     * @param customerId
     * @return
     */
    public CustomerMsgReadHistory findMsgReadHistory(String deviceNo, Long msgId, String customerId);
    public void updateMsgReadHistory(CustomerMsgReadHistory customerMsgReadHistory);

    /**
     * 未读数量记录查询---登录
     * @return
     */
    public int countUnReadNum(String customerId, String deviceNo);
    /**
     * 未读数量记录查询---未登录
     * @return
     */
    public int countUnReadNum(String deviceNo);


}
