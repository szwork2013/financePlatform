package com.sunlights.customer.dal;

import com.sunlights.common.vo.MsgSettingVo;
import models.CustomerMsgSetting;

import java.util.List;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: MsgSettingDao.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public interface MsgSettingDao {

    public CustomerMsgSetting findCustomerMsgSetting(String registrationId, String deviceNo);

    public CustomerMsgSetting updateCustomerMsgSetting(CustomerMsgSetting customerMsgSetting);

    public CustomerMsgSetting createCustomerMsgSetting(CustomerMsgSetting customerMsgSetting);

    /**
     * 通过customerId查询 当前客户在所有手机registrationId 集合
     * @param
     * @return
     */
    public List<MsgSettingVo> findCustomerMsgSettingList();
    public List<MsgSettingVo> findCustomerMsgSettingListByGroupId(Long groupId);
    public List<MsgSettingVo> findCustomerMsgSettingListByCustomerId(String customerId);



}
