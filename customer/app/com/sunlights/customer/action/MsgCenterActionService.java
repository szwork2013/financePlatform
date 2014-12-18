package com.sunlights.customer.action;

import com.google.common.collect.Lists;
import com.sunlights.common.AppConst;
import com.sunlights.common.DictConst;
import com.sunlights.common.MsgCenterRuleConst;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.vo.PushMessageVo;
import com.sunlights.customer.dal.CustomerDao;
import com.sunlights.customer.dal.MsgCenterDao;
import com.sunlights.customer.dal.impl.CustomerDaoImpl;
import com.sunlights.customer.dal.impl.MsgCenterDaoImpl;
import models.CustomerMsgPushTxn;
import play.Logger;
import play.Play;
import play.libs.Json;
import play.libs.ws.WS;

import java.text.MessageFormat;
import java.util.List;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: MsgCenterActionService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class MsgCenterActionService {

    private MsgCenterDao centerDao = new MsgCenterDaoImpl();
    private CustomerDao customerDao = new CustomerDaoImpl();


    public void sendPush(PushMessageVo pushMessageVo, String customerId, String ruleCode) {
        supplementMessage(pushMessageVo, ruleCode);
        CustomerMsgPushTxn customerMsgPushTxn = createCustomerMsgPushTxn(pushMessageVo, customerId);

        if (sendNow(pushMessageVo)) {//即时发送
            //更新为正在发送中
            customerMsgPushTxn.setPushStatus(DictConst.PUSH_STATUS_3);
            customerMsgPushTxn.setUpdateTime(DBHelper.getCurrentTime());
            centerDao.updateCustomerMsgPushTxn(customerMsgPushTxn);

            pushMessageVo.setPushTxnId(customerMsgPushTxn.getId());
            pushMessageVo.setCustomerId(customerId);
            pushMessageVo.setPersonalInd(AppConst.STATUS_VALID);
            pushMessageVo.setAliasList(getAliasList(pushMessageVo));

            executePush(pushMessageVo);
        }

    }

    private boolean sendNow(PushMessageVo pushMessageVo){
        return AppConst.STATUS_INVALID.equals(pushMessageVo.getPushTimed());
    }

    private void executePush(PushMessageVo pushMessageVo) {
        List<PushMessageVo> list = Lists.newArrayList();
        list.add(pushMessageVo);

        String pushUrl = Play.application().configuration().getString("push_url");
        WS.url(pushUrl).post(Json.toJson(list));
    }

    private List getAliasList(PushMessageVo pushMessageVo) {
        List aliasList = customerDao.findAliasByCustomerId(pushMessageVo.getCustomerId());
        if (aliasList.isEmpty()) {
            Logger.info("未查询到需要信息发送的接收者！");
//          throw new BusinessRuntimeException("未查询到需要信息发送的接收者！");
        }
        return aliasList;
    }

    private CustomerMsgPushTxn createCustomerMsgPushTxn(PushMessageVo pushMessageVo, String customerId) {
        CustomerMsgPushTxn customerMsgPushTxn = new CustomerMsgPushTxn();
        customerMsgPushTxn.setMessageRuleId(pushMessageVo.getMessageRuleId());
        customerMsgPushTxn.setTitle(pushMessageVo.getTitle());
        customerMsgPushTxn.setContent(pushMessageVo.getContent());
        customerMsgPushTxn.setPushStatus(DictConst.PUSH_STATUS_2);
        customerMsgPushTxn.setCustomerId(customerId);
        customerMsgPushTxn.setCreateTime(DBHelper.getCurrentTime());
        centerDao.createCustomerMsgPushTxn(customerMsgPushTxn);
        return customerMsgPushTxn;
    }


    private void supplementMessage(PushMessageVo pushMessageVo, String ruleCode){
        if (MsgCenterRuleConst.REGISTER_BEAN.equals(ruleCode)) {
            pushMessageVo.setContent(MessageFormat.format(pushMessageVo.getContent(), pushMessageVo.getContentExt()));
            //TODO
        }
    }
}
