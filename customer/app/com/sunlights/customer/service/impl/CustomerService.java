package com.sunlights.customer.service.impl;

import com.sunlights.common.AppConst;
import com.sunlights.common.DictConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.ParameterConst;
import com.sunlights.common.service.ParameterService;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.utils.MD5Helper;
import com.sunlights.customer.dal.CustomerDao;
import com.sunlights.customer.dal.impl.CustomerDaoImpl;
import com.sunlights.customer.vo.CustomerVo;
import models.Customer;
import models.CustomerMsgSetting;
import models.CustomerSession;
import play.Logger;
import play.cache.Cache;
import play.mvc.Http;

import java.sql.Timestamp;
import java.text.MessageFormat;

/**
 * <p>Project: fsp</p>
 * <p>Title: CustomerFacadeImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */

public class CustomerService {

    private CustomerDao customerDao = new CustomerDaoImpl();
    private ParameterService parameterService = new ParameterService();

    public CustomerVo getCustomerVoByPhoneNo(String mobilePhoneNo, String deviceNo) {
        return customerDao.getCustomerVoByPhoneNo(mobilePhoneNo, deviceNo);
    }

    public CustomerVo getCustomerVoByIdCardNo(String idCardNo, String userName) {
        return customerDao.getCustomerVoByIdCardNo(idCardNo, userName);
    }

    public Customer getCustomerByMobile(String mobile) {
        return customerDao.getCustomerByMobile(mobile);
    }

    public Customer getCustomerByToken(String token) {
        CustomerSession customerSession = getCustomerSession(token);
        if (customerSession == null) return null;
        return getCustomerByCustomerId(customerSession.getCustomerId());
    }

    public Customer getCustomerByCustomerId(String customerId) {
        Customer customer = customerDao.getCustomerByCustomerId(customerId);
        return customer;
    }

    public CustomerSession validateCustomerSession(Http.Request request, Http.Session session, Http.Response response) {
        Http.Cookie cookie = request.cookie(AppConst.TOKEN);
        String token = cookie == null ? null : cookie.value();
        Logger.info("===============token:" + token);
        CustomerSession userSession = getCustomerSession(token);
        Logger.info("===============token-CustomerSession:" + userSession);
        if (userSession == null) {
            throw CommonUtil.getInstance().errorBusinessException(MsgCode.LOGIN_TIMEOUT);
        } else {
            sessionLoginSessionId(session, response, userSession);
        }
        return userSession;
    }

    /**
     * customer放入session
     *
     * @param customer
     * @return
     */
    public CustomerSession createCustomerSession(Customer customer, String clientAddress, String deviceNo) {
        Timestamp currentTime = DBHelper.getCurrentTime();
        CustomerSession customerSession = new CustomerSession();
        customerSession.setCustomerId(customer.getCustomerId());
        customerSession.setToken(new MD5Helper().encrypt(customer.getMobile() + deviceNo + currentTime));
        customerSession.setClientAddress(clientAddress);
        customerSession.setCreateTime(currentTime);
        customerSession.setUpdateTime(currentTime);
        customerSession.setDeviceNo(deviceNo);
        customerDao.saveCustomerSession(customerSession);

        cacheSession(customerSession.getToken(), customerSession);
        return customerSession;
    }

    public CustomerSession getCustomerSession(String token) {
        Logger.info("===============token:" + token);
        if (token == null) {
            return null;
        }
        // 先从缓存中获取，有则返回没有查询数据库
        CustomerSession customerSession = (CustomerSession) Cache.get(token);
        if (customerSession != null) {
            return customerSession;
        }
        // 再从数据库中获取，判断有效期
        Timestamp currentTime = DBHelper.getCurrentTime();
        int sessionTime = (int) parameterService.getParameterNumeric(ParameterConst.SESSION_EXPIRY);
        Timestamp nMin = DBHelper.beforeMinutes(currentTime, sessionTime);
        customerSession = customerDao.findCustomerSessionByToken(token, nMin);
        // 写入缓存
        if (customerSession != null) {
            cacheSession(customerSession.getToken(), customerSession);
        }
        Logger.info("===============token-CustomerSession:" + customerSession);
        return customerSession;
    }

    public void sessionLoginSessionId(Http.Session session, Http.Response response, CustomerSession customerSession) {
        if (session == null || response == null || customerSession == null) {
            return;
        }
        String key = AppConst.TOKEN;
        String token = customerSession.getToken();
        Logger.info("====sessionLoginSessionId====token:" + token);
        if (token != null) {
            session.put(key, token);
            int cookieExpiry = (int) parameterService.getParameterNumeric(ParameterConst.COOKIE_EXPIRY);
            response.setCookie(key, token, cookieExpiry * 60, "/");
        } else {
            session.remove(key);
            response.discardCookie(key);
            response.discardCookie(key, "/");
        }
    }

    /**
     * 缓存 设置 推送registrationId
     * @param request
     * @param customerId
     */
    public void sessionPushRegId(Http.Request request, String customerId, String deviceNo){
        String registrationId = request.getHeader(AppConst.HEADER_REGISTRATION_ID);
        String platform = CommonUtil.getCurrentPlatform(request);

        Logger.info(MessageFormat.format(">>sessionPushRegId：registrationId={0}, customerId={1}, deviceNo = {2}, platform = {3}", registrationId, customerId, deviceNo, platform));
        if (registrationId == null || customerId == null) {
            return ;
        }
        long cacheTime = parameterService.getParameterNumeric(ParameterConst.CACHE_EXPIRY);
        CustomerMsgSetting customerMsgSetting = null;
        String preCustomerId = (String)Cache.get(AppConst.HEADER_REGISTRATION_ID + "_" + registrationId);
        if (!customerId.equals(preCustomerId)) {
            customerMsgSetting = customerDao.findCustomerMsgSetting(registrationId, deviceNo);
            if (preCustomerId == null && customerMsgSetting != null && customerId.equals(customerMsgSetting.getCustomerId())) {//缓存超时失效查询数据库比对
                Cache.set(AppConst.HEADER_REGISTRATION_ID + "_" + registrationId, customerId, (int) cacheTime * 60);
                return ;
            }

            CustomerMsgSetting newCustomerMsgSetting = new CustomerMsgSetting();
            newCustomerMsgSetting.setRegistrationId(registrationId);
            newCustomerMsgSetting.setCustomerId(customerId);
            newCustomerMsgSetting.setDeviceNo(deviceNo);
            newCustomerMsgSetting.setPlatform(platform);
            resetRegistrationId(newCustomerMsgSetting, newCustomerMsgSetting);
        }

        Cache.set(AppConst.HEADER_REGISTRATION_ID + "_" + registrationId, customerId, (int) cacheTime * 60);
    }

    private void resetRegistrationId(CustomerMsgSetting preCustomerMsgSetting, CustomerMsgSetting newCustomerMsgSetting) {
        Timestamp currentTime = DBHelper.getCurrentTime();
        if (preCustomerMsgSetting != null) {
            preCustomerMsgSetting.setUpdateTime(currentTime);
            preCustomerMsgSetting.setPushOpenStatus(AppConst.STATUS_INVALID);
            customerDao.updateCustomerMsgSetting(preCustomerMsgSetting);
        }

        newCustomerMsgSetting.setPushOpenStatus(AppConst.STATUS_VALID);
        newCustomerMsgSetting.setCreateTime(currentTime);
        customerDao.createCustomerMsgSetting(newCustomerMsgSetting);
    }

    /**
     * 设置缓存
     *
     * @param customerSession
     */
    public void cacheSession(String key, CustomerSession customerSession) {
        long cacheTime = parameterService.getParameterNumeric(ParameterConst.CACHE_EXPIRY);
        Cache.set(key, customerSession, (int) cacheTime * 60);
    }

    public void removeCache(String key) {
        Cache.remove(key);
    }

    public Customer saveCustomer(Customer customer) {
        customer.setCustomerId(generateCustomerId(customer.getProperty()));
        customerDao.saveCustomer(customer);
        return customer;
    }

    public Customer updateCustomer(Customer customer) {
        return customerDao.updateCustomer(customer);
    }


    private String generateCustomerId(String property) {
        String customerId = CommonUtil.dateToString(DBHelper.getCurrentTime(), CommonUtil.YYYYMMDDHHMMSS);
        if (DictConst.CUSTOMER_PROPERTY_1.equals(property)) {
            customerId += "01";
        } else {
            customerId += "02";
        }
        customerId += customerDao.getCustomerIdSeq();
        return customerId;
    }


}
