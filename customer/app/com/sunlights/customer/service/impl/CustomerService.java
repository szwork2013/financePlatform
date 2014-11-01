package com.sunlights.customer.service.impl;

import com.sunlights.common.AppConst;
import com.sunlights.common.IParameterConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.service.ParameterService;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.utils.MD5Helper;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.customer.dal.CustomerDao;
import com.sunlights.customer.dal.impl.CustomerDaoImpl;
import com.sunlights.customer.models.Customer;
import com.sunlights.customer.models.CustomerSession;
import com.sunlights.customer.vo.CustomerFormVo;
import com.sunlights.customer.vo.CustomerInfoVo;
import com.sunlights.customer.vo.CustomerVo;
import play.Logger;
import play.cache.Cache;
import play.mvc.Http;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

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


    private IdentityService identityService = new IdentityService();

    public CustomerInfoVo getCustomerInfoVoByPhoneNo(String mobilePhoneNo, String deviceNo) {
        return customerDao.getCustomerInfoVoByPhoneNo(mobilePhoneNo, deviceNo);
    }
    public CustomerInfoVo getCustomerInfoVoByIdCardNo(String idCardNo, String userName){
        return customerDao.getCustomerInfoVoByIdCardNo(idCardNo, userName);
    }
    public CustomerVo getCustomerVoByPhoneNo(String mobilePhoneNo, String deviceNo){
        CustomerInfoVo customerInfoVo = getCustomerInfoVoByPhoneNo(mobilePhoneNo, deviceNo);
        if (customerInfoVo != null) {
            return customerInfoVo.getCustomerVo();
        }
        return null;
    }
    public CustomerVo getCustomerVoByIdCardNo(String idCardNo, String userName){
        CustomerInfoVo customerInfoVo = getCustomerInfoVoByIdCardNo(idCardNo, userName);
        if (customerInfoVo != null) {
            return customerInfoVo.getCustomerVo();
        }
        return null;
    }


    public Customer getCustomerByMobile(String mobile) {
        return customerDao.getCustomerByMobile(mobile);
    }

    public CustomerSession findCurrentSession(Http.Request request) {
        Http.Cookie cookie = request.cookie(AppConst.TOKEN);
        if (cookie == null) return null;
        String token = cookie.value();
        return getCustomerSession(token);
    }

    public void validateCustomerSession(Http.Request request, Http.Session session, Http.Response response){
        Http.Cookie cookie = request.cookie(AppConst.TOKEN);
        String token = cookie == null ? null : cookie.value();
        Logger.info("===============token:" + token);
        CustomerSession userSession = getCustomerSession(token);
        Logger.info("===============token-CustomerSession:" + userSession);
        if (userSession == null) {
            throw CommonUtil.getInstance().errorBusinessException(MsgCode.LOGIN_TIMEOUT);
        }else{
            sessionLoginSessionId(session, response, userSession);
        }
    }

    /**
     * customer放入session
     *
     * @param customer
     * @return
     */
    public CustomerSession createCustomerSession(Customer customer, String clientAddress) {
        Timestamp currentTime = DBHelper.getCurrentTime();
        CustomerSession customerSession = new CustomerSession();
        customerSession.setCustomerId(customer.getCustomerId());
        customerSession.setToken(new MD5Helper().encrypt(customer.getMobile() + customer.getDeviceNo() + currentTime));
        customerSession.setClientAddress(clientAddress);
        customerSession.setCreatedDatetime(currentTime);
        customerSession.setUpdatedDatetime(currentTime);
        customerSession.setDeviceNo(customer.getDeviceNo());
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
        int sessionTime = (int) parameterService.getParameterNumeric(IParameterConst.SESSION_EXPIRY);
        Timestamp nMin = DBHelper.beforeMinutes(currentTime, sessionTime);
        customerSession = customerDao.findCustomerSessionByToken(token, nMin);
        // 写入缓存
        if (customerSession != null) {
            cacheSession(customerSession.getToken(), customerSession);
        }
        Logger.info("===============token-CustomerSession:" + customerSession);
        return customerSession;
    }

    public void sessionLoginSessionId(Http.Session session, Http.Response response,CustomerSession customerSession) {
        if (session == null || response == null || customerSession == null) {
            return ;
        }
        String key = AppConst.TOKEN;
        String token = customerSession.getToken();
        Logger.info("====sessionLoginSessionId====token:" + token);
        if (token != null) {
            session.put(key, token);
            int cookieExpiry = (int) parameterService.getParameterNumeric(IParameterConst.COOKIE_EXPIRY);
            response.setCookie(key, token, cookieExpiry * 60, "/");
        } else {
            session.remove(key);
            response.discardCookie(key);
            response.discardCookie(key, "/");
        }
    }


    /**
     * 设置缓存
     *
     * @param customerSession
     */
    public void cacheSession(String key, CustomerSession customerSession) {
        long cacheTime = parameterService.getParameterNumeric(IParameterConst.CACHE_EXPIRY);
        Cache.set(key, customerSession, (int) cacheTime * 60);
    }

    public void removeCache(String key) {
        Cache.remove(key);
    }

    public Customer getCustomerByToken(String token){
        CustomerSession customerSession = getCustomerSession(token);
        return getCustomerByCustomerId(customerSession.getCustomerId());
    }

    public Customer getCustomerByCustomerId(String customerId) {
        Customer customer = customerDao.getCustomerByCustomerId(customerId);
        return customer;
    }
    public Customer saveCustomer(Customer customer){
        customer.setCustomerId(generateCustomerId(customer.getProperty()));
        customerDao.saveCustomer(customer);
        return customer;
    }
    public Customer updateCustomer(Customer customer){
        return customerDao.updateCustomer(customer);
    }



    private String generateCustomerId(String property) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String customerId = formatter.format(DBHelper.getCurrentTime());
        if (AppConst.CUSTOMER_BUYER.equals(property)) {
            customerId += "01";
        }else{
            customerId += "02";
        }
        customerId += customerDao.getCustomerIdSeq();
        return customerId;
    }

    /**
     * 实名认证
     * @param vo
     * @param token
     * @return
     */
    public CustomerSession certify(CustomerFormVo vo, String token, String remoteAddress) {
        String mobilePhoneNo = vo.getMobilePhoneNo();
        String userName = vo.getUserName();
        String idCardNo = vo.getIdCardNo();
        String deviceNo = vo.getDeviceNo();

        Logger.info("==userName:" + userName);
        Logger.info("==idCardNo:" + idCardNo);
        Logger.info("==token:" + token);

        Customer customer = null;
        CustomerSession customerSession = getCustomerSession(token);
        if (customerSession != null) {//若为登录后再实名认证
            customer = getCustomerByCustomerId(customerSession.getCustomerId());
            if (mobilePhoneNo == null || "".equals(mobilePhoneNo.trim())) {//做过实名认证，取消后续动作重新进入，token还有效的，
                // 根据token查询当前客户操作到哪步
                CustomerVo customerVo = getCustomerVoByIdCardNo(customer.getIdentityNumber(), customer.getRealName());
                MessageUtil.getInstance().setMessage(new Message(MsgCode.OPERATE_SUCCESS), customerVo);
            }else{//注册后，登录后，再做实名认证的
                CommonUtil.getInstance().validateParams(userName, idCardNo, deviceNo);
                identityService.identity(idCardNo, userName);  //真正调用实名认证

                customer.setRealName(userName);
                customer.setIdentityTyper(AppConst.ID_CARD);
                customer.setIdentityNumber(idCardNo);
                customer.setUpdatedDatetime(DBHelper.getCurrentTime());
                updateCustomer(customer);

                MessageUtil.getInstance().setMessage(new Message(MsgCode.CERTIFY_SUCCESS),
                        getCustomerVoByPhoneNo(mobilePhoneNo, deviceNo));
            }
        }else{//未登录首次申购做实名认证
            CommonUtil.getInstance().validateParams(userName, idCardNo);
            //判断是否已做过实名认证

            CustomerInfoVo customerInfoVo = getCustomerInfoVoByIdCardNo(idCardNo, userName);

            if (customerInfoVo != null && AppConst.VALID_CERTIFY.equals(customerInfoVo.getCertify())) {
                MessageUtil.getInstance().setMessage(new Message(MsgCode.OPERATE_SUCCESS), customerInfoVo.getCustomerVo());
                customer = getCustomerByCustomerId(customerInfoVo.getCustomerId());
            }else{
                identityService.identity(idCardNo, userName);//真正调用实名认证
                customer = saveCustomer(mobilePhoneNo, userName, idCardNo, deviceNo);

                CustomerVo customerVo = null;
                if (customerInfoVo != null) {
                    customerVo = customerInfoVo.getCustomerVo();
                }
                if (customerVo == null) {
                    customerVo = new CustomerVo();
                }
                customerVo.setCertify("1");
                customerVo.setIdCardNo(idCardNo.substring(0, 6) + "******" + idCardNo.substring(14));
                customerVo.setUserName("*" + userName.substring(1));
                MessageUtil.getInstance().setMessage(new Message(MsgCode.CERTIFY_SUCCESS), customerVo);
            }

            customerSession = createCustomerSession(customer, remoteAddress);
        }

        return customerSession;
    }

    private Customer saveCustomer(String mobilePhoneNo, String realName,String idCardNo, String deviceNo){
        Timestamp currentTime = DBHelper.getCurrentTime();
        Customer customer = new Customer();
        customer.setLoginId(mobilePhoneNo);
        customer.setMobile(mobilePhoneNo);
        customer.setRealName(realName);
        customer.setIdentityTyper(AppConst.ID_CARD);
        customer.setIdentityNumber(idCardNo);
        customer.setRegChannel(AppConst.REGISTER_CHANNEL_MOBILE);
        customer.setRegWay(AppConst.REGISTER_CHANNEL_MOBILE);
        customer.setCustomerType(AppConst.CUSTOMER_TYPE_PERSON);
        customer.setProperty(AppConst.CUSTOMER_BUYER);
        customer.setDeviceNo(deviceNo);
        customer.setStatus(AppConst.CUSTOMER_STATUS_NORMAL);
        customer.setCreatedDatetime(currentTime);
        customer.setUpdatedDatetime(currentTime);
        saveCustomer(customer);
        return customer;
    }
}
