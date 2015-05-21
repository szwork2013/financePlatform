package com.sunlights.customer.service.impl;

import com.sunlights.common.AppConst;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.vo.PageVo;
import com.sunlights.customer.dal.MsgCenterDao;
import com.sunlights.customer.dal.MsgSettingDao;
import com.sunlights.customer.dal.impl.MsgCenterDaoImpl;
import com.sunlights.customer.dal.impl.MsgSettingDaoImpl;
import com.sunlights.customer.service.MsgCenterService;
import com.sunlights.customer.vo.MsgCenterDetailVo;
import com.sunlights.customer.vo.MsgCenterVo;
import models.CustomerMsgReadHistory;
import models.CustomerMsgSetting;
import play.cache.Cache;

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

    private MsgSettingDao msgSettingDao = new MsgSettingDaoImpl();
    private MsgCenterDao msgCenterDao = new MsgCenterDaoImpl();

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
        CustomerMsgReadHistory history = msgCenterDao.findMsgReadHistory(customerMsgReadHistory.getDeviceNo(), customerMsgReadHistory.getPushTxnId(), customerMsgReadHistory.getCustomerId());
        Timestamp currentTime = DBHelper.getCurrentTime();
        if (history == null) {//设备未读的  保存
            customerMsgReadHistory.setCreateTime(currentTime);
            customerMsgReadHistory.setReadTime(currentTime);
            msgCenterDao.createMsgReadHistory(customerMsgReadHistory);
        }
    }


    @Override
    public int countUnReadNum(String customerId, String deviceNo) {
        if (customerId != null) {
            return msgCenterDao.countUnReadNum(customerId, deviceNo);
        } else {
            return msgCenterDao.countUnReadNum(deviceNo);
        }
    }

    @Override
    public void enablePush(String registrationId, String deviceNo, String platform) {
        CustomerMsgSetting customerMsgSetting = msgSettingDao.findCustomerMsgSetting(registrationId, deviceNo);
        Timestamp currentTime = DBHelper.getCurrentTime();
        if (customerMsgSetting == null) {
            customerMsgSetting = new CustomerMsgSetting();
            customerMsgSetting.setRegistrationId(registrationId);
            customerMsgSetting.setDeviceNo(deviceNo);
            customerMsgSetting.setPushOpenStatus(AppConst.STATUS_VALID);
            customerMsgSetting.setPlatform(platform);
            customerMsgSetting.setCreateTime(currentTime);
            msgSettingDao.createCustomerMsgSetting(customerMsgSetting);
        }
    }

    @Override
    public void disablePush(String registrationId, String deviceNo) {
        Timestamp currentTime = DBHelper.getCurrentTime();
        CustomerMsgSetting customerMsgSetting = msgSettingDao.findCustomerMsgSetting(registrationId, deviceNo);
        if (customerMsgSetting != null) {
            customerMsgSetting.setUpdateTime(currentTime);
            customerMsgSetting.setPushOpenStatus(AppConst.STATUS_INVALID);
            msgSettingDao.updateCustomerMsgSetting(customerMsgSetting);
        }
        Cache.remove(AppConst.HEADER_REGISTRATION_ID + "_" + registrationId);
    }
}
