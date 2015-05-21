package com.sunlights.customer.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.common.vo.MsgSettingVo;
import com.sunlights.customer.dal.MsgSettingDao;
import models.CustomerMsgSetting;
import play.Logger;

import javax.persistence.Query;
import java.util.List;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: MsgSettingDaoImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class MsgSettingDaoImpl extends EntityBaseDao implements MsgSettingDao{

    @Override
    public CustomerMsgSetting findCustomerMsgSetting(String registrationId, String deviceNo) {
        List<CustomerMsgSetting> list = createNameQuery("findSettingByRegIdAndDeviceNo", registrationId, deviceNo).getResultList();
        return list.isEmpty() ? null : list.get(0);
    }


    @Override
    public CustomerMsgSetting updateCustomerMsgSetting(CustomerMsgSetting customerMsgSetting) {
        return update(customerMsgSetting);
    }

    @Override
    public CustomerMsgSetting createCustomerMsgSetting(CustomerMsgSetting customerMsgSetting) {
        return create(customerMsgSetting);
    }

    @Override
    public List<MsgSettingVo> findCustomerMsgSettingList() {
        String sql = " SELECT " + buildCustomerMsgSettingKeys() +
                "  FROM c_customer_msg_setting c" +
                " WHERE c.push_open_status = 'Y' " +
                "   and c.device_no is not null" +
                "   and c.registration_id is not null";

        Query query = em.createNativeQuery(sql);
        return getMsgSettingVoList(query);
    }

    @Override
    public List<MsgSettingVo> findCustomerMsgSettingListByGroupId(Long groupId) {
        String sql = " SELECT " + buildCustomerMsgSettingKeys() +
                "  FROM c_customer_msg_setting c,c_customer_group cg" +
                " WHERE c.push_open_status = 'Y' " +
                "   and cg.customer_id = c.customer_id" +
                "   and c.device_no is not null" +
                "   and c.registration_id is not null" +
                "   and cg.group_id = :groupId";

        Logger.info(sql);

        Query query = em.createNativeQuery(sql);
        query.setParameter("groupId", groupId);
        return getMsgSettingVoList(query);
    }

    public List<MsgSettingVo> findCustomerMsgSettingListByCustomerId(String customerId){
        String sql = " SELECT " + buildCustomerMsgSettingKeys() +
                "  FROM c_customer_msg_setting c " +
                " WHERE c.push_open_status = 'Y' " +
                "   and c.device_no is not null" +
                "   and c.registration_id is not null" +
                "   and c.customer_id = :customerId";

        Query query = em.createNativeQuery(sql);
        query.setParameter("customerId", customerId);
        return getMsgSettingVoList(query);
    }

    private List<MsgSettingVo> getMsgSettingVoList(Query query) {
        String keys = "registrationId,deviceNo,customerId,platform,loginStatus";
        return ConverterUtil.convert(keys, query.getResultList(), MsgSettingVo.class);
    }

    private String buildCustomerMsgSettingKeys(){
        String keys = "distinct c.registration_id, c.device_no,c.customer_id,c.platform, " +
                "        CASE WHEN ( " +
                "              SELECT cs.status " +
                "                FROM c_customer_session cs " +
                "               WHERE cs.customer_id = c.customer_id " +
                "                 AND cs.device_no = c.device_no " +
                "            ORDER BY cs.create_time DESC " +
                "               LIMIT 1 offset 0 " +
                "        ) = 'Y' THEN 'Y' ELSE 'N' END AS loginStatus ";

        return keys;
    }
}
