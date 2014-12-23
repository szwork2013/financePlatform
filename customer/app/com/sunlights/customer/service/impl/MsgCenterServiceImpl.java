package com.sunlights.customer.service.impl;

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
    public void createMsgReadHistory(String customerId, Long msgId, String sendType) {
        Timestamp currentTime = DBHelper.getCurrentTime();
        CustomerMsgReadHistory customerMsgReadHistory = new CustomerMsgReadHistory();
        customerMsgReadHistory.setCustomerId(customerId);
        customerMsgReadHistory.setPushTxnId(msgId);
        customerMsgReadHistory.setSendType(sendType);
        customerMsgReadHistory.setCreateTime(currentTime);
        customerMsgReadHistory.setReadTime(currentTime);
        msgCenterDao.createMsgReadHistory(customerMsgReadHistory);
    }


    @Override
    public int countUnReadNum(String customerId) {
        return msgCenterDao.countUnReadNum(customerId);
    }

    @Override
    public CustomerMsgSetting saveCustomerMsgSetting(String customerId, String alias) {
//        List<String> aliasList = customerDao.findAliasByCustomerId(customerId);
//        if (aliasList.isEmpty()) {
//            customerDao.createCustomerMsgSetting()
//        }else{
//            if (!alias.equals(aliasList.get(0))) {
//                u
//            }
//        }
        return null;
    }
}
