package com.sunlights.customer.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.customer.dal.CustomerDao;
import models.Customer;
import models.CustomerGesture;
import models.CustomerSession;
import com.sunlights.customer.vo.CustomerInfoVo;

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

    public CustomerInfoVo getCustomerInfoVoByPhoneNo(String mobilePhoneNo, String deviceNo){
        Query query = em.createQuery(com.sunlights.customer.dal.sqlmap.txt.customerVo.render("CustomerDao.getCustomerInfoVoByPhoneNo", null).body(), CustomerInfoVo.class);
        query.setParameter("mobilePhoneNo", mobilePhoneNo);
        query.setParameter("deviceNo", deviceNo);
        List list = query.getResultList();
        if (list == null || list.size() == 0) {
            return null;
        }

        CustomerInfoVo customerInfoVo = (CustomerInfoVo)list.get(0);
        customerInfoVo.setMobilePhoneNo(mobilePhoneNo);
        customerInfoVo.setMobileDisplayNo(mobilePhoneNo.substring(0, 3) + "****" + mobilePhoneNo.substring(7));
        if ("1".equals(customerInfoVo.getCertify())) {
            customerInfoVo.setIdCardNo(customerInfoVo.getIdCardNo().substring(0, 6) + "******" + customerInfoVo.getIdCardNo().substring(14));
            customerInfoVo.setUserName("*" + customerInfoVo.getUserName().substring(1));
        }

        return customerInfoVo;
    }

    public CustomerInfoVo getCustomerInfoVoByIdCardNo(String idCardNo, String userName){
        Query query = em.createQuery(com.sunlights.customer.dal.sqlmap.txt.customerVo.render("CustomerDao.getCustomerInfoVoByIdCardNo", null).body(), CustomerInfoVo.class);
        query.setParameter("idCardNo", idCardNo);
        query.setParameter("userName", userName);
        List list = query.getResultList();
        if (list == null || list.size() == 0) {
            return null;
        }

        CustomerInfoVo customerInfoVo = (CustomerInfoVo)list.get(0);
        if (customerInfoVo.getMobilePhoneNo() != null) {
            customerInfoVo.setMobileDisplayNo(customerInfoVo.getMobilePhoneNo().substring(0, 3) + "****" + customerInfoVo.getMobilePhoneNo().substring(7));
        }
        if ("1".equals(customerInfoVo.getCertify())) {
            customerInfoVo.setIdCardNo(customerInfoVo.getIdCardNo().substring(0, 6) + "******" + customerInfoVo.getIdCardNo().substring(14));
            customerInfoVo.setUserName("*" + customerInfoVo.getUserName().substring(1));
        }

        return customerInfoVo;
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
