package com.sunlights.customer.dal.impl;

import com.google.common.base.Strings;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.customer.dal.CustomerDao;
import com.sunlights.customer.vo.CustomerVo;
import models.Customer;
import models.CustomerGesture;
import models.CustomerSession;
import play.Logger;

import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Project: fsp</p>
 * <p>Title: CustomerManageDaoImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */

public class CustomerDaoImpl extends EntityBaseDao implements CustomerDao {
    public String getCustomerIdSeq() {
        Query query = em.createNativeQuery("SELECT nextval('cust_seq')");
        String cust_seq = query.getSingleResult().toString();
        return Strings.padStart(cust_seq, 10, '0');
    }

    //Customer
    @Override
    public Customer getCustomerByMobile(String mobile) {
        List<Customer> list = findBy(Customer.class, "mobile", mobile);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public Customer getCustomerByCustomerId(String customerId) {
        List<Customer> list = findBy(Customer.class, "customerId", customerId);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public Customer updateCustomer(Customer customer) {
        return update(customer);
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return create(customer);
    }

    public CustomerVo getCustomerVoByPhoneNo(String mobilePhoneNo, String deviceNo) {
        String sql = "select c.mobile,c.real_name,c.nick_name,c.email,c.identity_number," +
                "case when EXISTS (select 1 from c_customer_gesture cg where cg.customer_id = c.customer_id and cg.status = 'Y' and cg.device_no = :deviceNo) THEN '1' ELSE '0' END as gestureOpened," +
                "case when c.identity_typer = 'I' and c.identity_number is not null THEN '1' ELSE '0' END as certify," +
                "case when a.trade_password is null THEN '0' ELSE '1' END as tradePwdFlag," +
                "(select count(1) from c_bank_card bc where bc.customer_id = c.customer_id) as bankCardCount " +
                ",c.customer_id " +
                "from   c_customer c,f_basic_account a " +
                "where  c.customer_id = a.cust_id " +
                "and  c.mobile = :mobilePhoneNo ";


        Query query = em.createNativeQuery(sql);
        query.setParameter("deviceNo", deviceNo);
        query.setParameter("mobilePhoneNo", mobilePhoneNo);

        List<Object[]> list = query.getResultList();

        CustomerVo customerInfoVo = transCustomerVo(list);

        return customerInfoVo;
    }

    public CustomerVo getCustomerVoByIdCardNo(String idCardNo, String userName) {
        String sql = " select c.mobile,c.real_name,c.nick_name,c.email,c.identity_number," +
                " 0 || '' as gestureOpened," +
                " case when c.identity_typer = 'I' and c.identity_number is not null THEN '1' ELSE '0' END as certify," +
                " case when a.trade_password is null THEN '0' ELSE '1' END as tradePwdFlag," +
                " (select count(1) from c_bank_card bc where bc.customer_id = c.customer_id) as bankCardCount, " +
                "  c.customer_id " +
                " from    c_customer c,f_basic_account a" +
                " where   c.real_name = :userName" +
                " and     c.identity_typer = 'I'" +
                " and     c.identity_number = :idCardNo";

        Logger.debug(sql);

        Query query = em.createNativeQuery(sql);
        query.setParameter("idCardNo", idCardNo);
        query.setParameter("userName", userName);
        List list = query.getResultList();

        CustomerVo customerVo = transCustomerVo(list);

        return customerVo;
    }




    private CustomerVo transCustomerVo(List<Object[]> list) {
        CustomerVo customerVo = CommonUtil.column2StringVo(list, CustomerVo.class);
        if (customerVo == null) {
            return null;
        }

        if (customerVo.getMobilePhoneNo() != null) {
            customerVo.setMobileDisplayNo(customerVo.getMobilePhoneNo().substring(0, 3) + "****" + customerVo.getMobilePhoneNo().substring(7));
        }
        if ("1".equals(customerVo.getCertify())) {
            customerVo.setIdCardNo(customerVo.getIdCardNo().substring(0, 6) + "******" + customerVo.getIdCardNo().substring(14));
            customerVo.setUserName("*" + customerVo.getUserName().substring(1));
        }
        return customerVo;
    }


    //CustomerSession
    public CustomerSession findCustomerSessionByToken(String token, Timestamp nMin) {
        StringBuffer sb = new StringBuffer();
        sb.append("select c FROM CustomerSession c ");
        sb.append("where c.status = 'Y' ");
        sb.append(" /~ and c.token = {token} ~/ ");
        sb.append(" /~ and c.updateTime >= {nMin} ~/ ");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("EQS_token", token);
        params.put("GED_nMin", new Date(nMin.getTime()));

        List<CustomerSession> list = findByMap(sb.toString(), params);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public CustomerSession saveCustomerSession(CustomerSession customerSession) {
        return create(customerSession);
    }

    @Override
    public CustomerSession updateCustomerSession(CustomerSession customerSession) {
        return update(customerSession);
    }

    public CustomerSession findCustomerSessionByCustomerId(String customerId, String deviceNo) {
        Query query = createNameQuery("findCSByCustomerId", customerId, deviceNo);
        List<CustomerSession> list = query.getResultList();
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }


    //CustomerGesture
    @Override
    public CustomerGesture saveCustomerGesture(CustomerGesture customerGesture) {
        return create(customerGesture);
    }

    @Override
    public CustomerGesture updateCustomerGesture(CustomerGesture customerGesture) {
        return update(customerGesture);
    }

    public CustomerGesture findCustomerGestureByDeviceNo(String customerId, String deviceNo) {
        Query query = createNameQuery("findCGByCustomerId", customerId, deviceNo);
        List<CustomerGesture> list = query.getResultList();
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }


}
