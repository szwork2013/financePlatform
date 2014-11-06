package com.sunlights.customer.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.customer.dal.CustomerDao;
import com.sunlights.customer.vo.CustomerVo;
import models.Customer;
import models.CustomerGesture;
import models.CustomerSession;
import play.Logger;

import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.List;

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
    public String getCustomerIdSeq(){
        Query query = em.createNativeQuery("SELECT nextval('cust_seq')");
        String cust_seq = query.getSingleResult().toString();
        String zero = "";
        for (int i = 0; i < 10 - cust_seq.length(); i++){
            zero += "0";
        }
        return zero + cust_seq;
    }

    //Customer
    @Override
    public Customer getCustomerByMobile(String mobile) {
        List<Customer> list = findBy(Customer.class, "mobile", mobile);
        if (list != null && list.size() != 0) {
            return list.get(0);
        }
        return null;
    }

    public Customer getCustomerByCustomerId(String customerId) {
        List<Customer> list = findBy(Customer.class, "customerId", customerId);
        if (list != null && list.size() != 0) {
            return list.get(0);
        }
        return null;
    }

    public Customer updateCustomer(Customer customer){
        return update(customer);
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return create(customer);
    }

    public CustomerVo getCustomerVoByPhoneNo(String mobilePhoneNo, String deviceNo){
        String sql = "select c.mobile,c.real_name,c.nick_name,c.email,c.identity_number," +
                "case when EXISTS (select 1 from c_customer_gesture cg where cg.customer_id = c.customer_id and cg.status = 'N' and cg.device_no = :deviceNo) THEN '1' ELSE '0' END as gestureOpened," +
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

    public CustomerVo getCustomerVoByIdCardNo(String idCardNo, String userName){
        String sql = " select c.mobile,c.real_name,c.nick_name,c.email,c.identity_number," +
                " 0 || '' as gestureOpened," +
                " case when c.identity_typer = 'I' and c.identity_number is not null THEN '1' ELSE '0' END as certify," +
                " case when a.trade_password is null THEN '0' ELSE '1' END as tradePwdFlag," +
                " (select count(1) from c_bank_card bc where bc.customer_id = c.customer_id) as bankCardCount, " +
                "  c.customer_id" +
                "   from    c_customer c,f_basic_account a" +
                "   where   c.real_name = :userName" +
                "   and     c.identity_typer = 'I'" +
                "   and     c.identity_number = :idCardNo";

        Logger.info(sql);
        Query query = em.createNativeQuery(sql);
        query.setParameter("idCardNo", idCardNo);
        query.setParameter("userName", userName);
        List list = query.getResultList();

        CustomerVo customerVo = transCustomerVo(list);

        return customerVo;
    }

    private CustomerVo transCustomerVo(List<Object[]> list) {
        if (list == null || list.size() == 0) {
            return null;
        }

        CustomerVo customerVo = new CustomerVo();
        for (Object[] column : list) {
            customerVo.setMobilePhoneNo(column[0] == null ? null : column[0].toString());
            customerVo.setUserName(column[1] == null ? null : column[1].toString());
            customerVo.setNickName(column[2] == null ? null : column[2].toString());
            customerVo.setEmail(column[3] == null ? null : column[3].toString());
            customerVo.setIdCardNo(column[4] == null ? null : column[4].toString());
            customerVo.setGestureOpened(column[5] == null ? null : column[5].toString());
            customerVo.setCertify(column[6] == null ? null : column[6].toString());
            customerVo.setTradePwdFlag(column[7] == null ? null : column[7].toString());
            customerVo.setBankCardCount(column[8] == null ? null : column[8].toString());
            customerVo.setCustomerId(column[9] == null ? null : column[9].toString());
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
    public CustomerSession findCustomerSessionByToken(String token, Timestamp nMin){
        StringBuilder sb = new StringBuilder();
        sb.append("select c FROM CustomerSession c ");
        sb.append("where c.status = 'N' and c.token = :token");
        if (nMin != null) {
            sb.append(" and c.updatedDatetime >= :nMin");
        }
        Query query = em.createQuery(sb.toString(), CustomerSession.class);
        query.setParameter("token", token);
        if (nMin != null) {
            query.setParameter("nMin", nMin);
        }
        List<CustomerSession> list = query.getResultList();
        if (list != null && list.size() != 0) {
            return list.get(0);
        }
        return null;
    }
    public CustomerSession saveCustomerSession(CustomerSession customerSession) {
        return create(customerSession);
    }

    @Override
    public CustomerSession updateCustomerSession(CustomerSession customerSession) {
        return update(customerSession);
    }

    public CustomerSession findCustomerSessionByCustomer(String customerId, String deviceNo){
        Query query = em.createNativeQuery("select c.* FROM c_customer_session c where c.customer_id = ?0 and c.deviceNo = ?1 and c.status = 'N' order by create_datetime desc", CustomerSession.class);
        query.setParameter(0, customerId);
        query.setParameter(1, deviceNo);
        List<CustomerSession> list = query.getResultList();
        if (list != null && list.size() != 0) {
            return list.get(0);
        }
        return null;
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
    public CustomerGesture findCustomerGestureByDeviceNo(String customerId, String deviceNo){
        Query query = em.createNativeQuery("select cg.* FROM c_customer_gesture cg where cg.customer_id = ?0 and cg.device_no = ?1 and cg.status = 'N'", CustomerGesture.class);
        query.setParameter(0, customerId);
        query.setParameter(1, deviceNo);
        List<CustomerGesture> list = query.getResultList();
        if (list != null && list.size() != 0) {
            return list.get(0);
        }
        return null;
    }


}
