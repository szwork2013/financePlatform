package com.sunlights.customer.service.impl;

import com.sunlights.common.AppConst;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.vo.PageVo;
import com.sunlights.customer.dal.CustomerDao;
import com.sunlights.customer.dal.MsgCenterDao;
import com.sunlights.customer.dal.impl.CustomerDaoImpl;
import com.sunlights.customer.dal.impl.MsgCenterDaoImpl;
import com.sunlights.customer.service.MsgCenterService;
import com.sunlights.customer.vo.MsgCenterDetailVo;
import com.sunlights.customer.vo.MsgCenterVo;
import models.CustomerMsgReadHistory;
import models.CustomerMsgSetting;

import java.sql.Timestamp;
import java.util.List;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: MsgCenterServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class MsgCenterServiceImpl implements MsgCenterService {

    private CustomerDao customerDao = new CustomerDaoImpl();
    private MsgCenterDao msgCenterDao = new MsgCenterDaoImpl();
    private CustomerService customerService = new CustomerService();

    @Override
    public List<MsgCenterVo> findMsgCenterVoListWithLogin(PageVo pageVo) {
        return msgCenterDao.findMsgCenterVoListWithLogin(pageVo);
    }

    @Override
    public List<MsgCenterVo> findMsgCenterVoList(PageVo pageVo) {
        return msgCenterDao.findMsgCenterVoList(pageVo);
    }

    @Override
    public MsgCenterDetailVo findMsgCenterDetail(Long msgId, String sendType) {
        return msgCenterDao.findMsgCenterDetail(msgId, sendType);
    }

    @Override
    public void saveMsgReadHistory(CustomerMsgReadHistory customerMsgReadHistory) {
        CustomerMsgReadHistory history = msgCenterDao.findMsgReadHistoryByDeviceNo(customerMsgReadHistory.getDeviceNo(), customerMsgReadHistory.getPushTxnId());
        Timestamp currentTime = DBHelper.getCurrentTime();
        if (history == null) {//未读的  保存
            customerMsgReadHistory.setCreateTime(currentTime);
            customerMsgReadHistory.setReadTime(currentTime);
            msgCenterDao.createMsgReadHistory(customerMsgReadHistory);
        }else{//已读的
            if (history.getCustomerId() == null && customerMsgReadHistory.getCustomerId() != null) {//老记录是未登录已读 登录再读 则更新
                history.setCustomerId(customerMsgReadHistory.getCustomerId());
                history.setUpdateTime(currentTime);
                msgCenterDao.updateMsgReadHistory(history);
            }
        }
    }


    @Override
    public int countUnReadNum(String customerId, String deviceNo) {
        if (customerId != null) {
            return msgCenterDao.countUnReadNumWithLogin(customerId);
        }else{
            return msgCenterDao.countUnReadNum(deviceNo);
        }
    }

    @Override
    public void enablePush(String registrationId) {
        CustomerMsgSetting customerMsgSetting = customerDao.findCustomerMsgSetting(registrationId);
        Timestamp currentTime = DBHelper.getCurrentTime();
        if (customerMsgSetting == null) {
            customerMsgSetting = new CustomerMsgSetting();
            customerMsgSetting.setRegistrationId(registrationId);
            customerMsgSetting.setPushOpenStatus(AppConst.STATUS_VALID);
            customerMsgSetting.setCreateTime(currentTime);
            customerDao.createCustomerMsgSetting(customerMsgSetting);
        }
    }

    @Override
    public void disablePush(String registrationId) {
        Timestamp currentTime = DBHelper.getCurrentTime();
        CustomerMsgSetting customerMsgSetting = customerDao.findCustomerMsgSetting(registrationId);
        if (customerMsgSetting != null) {
            customerMsgSetting.setUpdateTime(currentTime);
            customerMsgSetting.setPushOpenStatus(AppConst.STATUS_INVALID);
            customerDao.updateCustomerMsgSetting(customerMsgSetting);
        }

        customerService.removeCache(AppConst.HEADER_REGISTRATION_ID + "_" + registrationId);
    }
}
