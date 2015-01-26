package com.sunlights.core.service.impl;

import com.sunlights.account.service.AccountService;
import com.sunlights.account.service.impl.AccountServiceImpl;
import com.sunlights.common.AppConst;
import com.sunlights.common.DictConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.core.integration.IdentityClient;
import com.sunlights.customer.service.impl.CustomerService;
import com.sunlights.customer.vo.CustomerFormVo;
import com.sunlights.customer.vo.CustomerVo;
import models.Customer;
import models.CustomerSession;

import java.sql.Timestamp;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: IdentityService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class IdentityService {
    private CustomerService customerService = new CustomerService();
    private AccountService accountService = new AccountServiceImpl();
    private IdentityClient identityClient = new IdentityClient();

    /**
     * 实名认证
     *
     * @param vo
     * @param token
     * @return
     */
    public CustomerSession certify(CustomerFormVo vo, String token, String remoteAddress) {
        String mobilePhoneNo = vo.getMobilePhoneNo();
        String userName = vo.getUserName();
        String idCardNo = vo.getIdCardNo();
        String deviceNo = vo.getDeviceNo();

        Customer customer = null;
        CustomerSession customerSession = customerService.getCustomerSession(token);
        if (customerSession != null) {//若为登录后再实名认证
            customer = customerService.getCustomerByCustomerId(customerSession.getCustomerId());
            if (DictConst.CERTIFICATE_TYPE_1.equals(customer.getIdentityTyper()) && customer.getIdentityNumber() != null) {
                //做过实名认证，取消后续动作重新进入，token还有效的，根据token查询当前客户操作到哪步
                CustomerVo customerVo = customerService.getCustomerVoByIdCardNo(customer.getIdentityNumber(), customer.getRealName());
                MessageUtil.getInstance().setMessage(new Message(MsgCode.OPERATE_SUCCESS), customerVo);
            } else {//注册后，登录后，再做实名认证的
                CommonUtil.getInstance().validateParams(userName, idCardNo, deviceNo);
                identityClient.identity(idCardNo, userName);  //真正调用实名认证

                customer.setRealName(userName);
                customer.setIdentityTyper(DictConst.CERTIFICATE_TYPE_1);
                customer.setIdentityNumber(idCardNo);
                customer.setUpdateTime(DBHelper.getCurrentTime());
                customerService.updateCustomer(customer);

                MessageUtil.getInstance().setMessage(new Message(MsgCode.CERTIFY_SUCCESS), customerService.getCustomerVoByPhoneNo(mobilePhoneNo, deviceNo));
            }
        } else {//未登录首次申购做实名认证
            CommonUtil.getInstance().validateParams(userName, idCardNo);
            //判断是否已做过实名认证

            CustomerVo customerVo = customerService.getCustomerVoByIdCardNo(idCardNo, userName);

            if (customerVo != null && AppConst.VALID_CERTIFY.equals(customerVo.getCertify())) {
                MessageUtil.getInstance().setMessage(new Message(MsgCode.OPERATE_SUCCESS), customerVo);
                customer = customerService.getCustomerByCustomerId(customerVo.getCustomerId());
            } else {
                identityClient.identity(idCardNo, userName);//真正调用实名认证
                customer = saveCustomer(mobilePhoneNo, userName, idCardNo, deviceNo);
                accountService.createBaseAccount(customer.getCustomerId(), null);

                if (customerVo == null) {
                    customerVo = new CustomerVo();
                }
                customerVo.setCertify("1");
                customerVo.setIdCardNo(idCardNo.substring(0, 6) + "******" + idCardNo.substring(14));
                customerVo.setUserName("*" + userName.substring(1));
                MessageUtil.getInstance().setMessage(new Message(MsgCode.CERTIFY_SUCCESS), customerVo);
            }

            customerSession = customerService.createCustomerSession(customer, remoteAddress, deviceNo);
        }

        return customerSession;
    }

    private Customer saveCustomer(String mobilePhoneNo, String realName, String idCardNo, String deviceNo) {
        Timestamp currentTime = DBHelper.getCurrentTime();
        Customer customer = new Customer();
        customer.setLoginId(mobilePhoneNo);
        customer.setMobile(mobilePhoneNo);
        customer.setRealName(realName);
        customer.setIdentityTyper(DictConst.CERTIFICATE_TYPE_1);
        customer.setIdentityNumber(idCardNo);
        customer.setCreateTime(currentTime);
        customer.setUpdateTime(currentTime);
        customer.setRegChannel(DictConst.CUSTOMER_CHANNEL_1);
        customer.setRegWay(DictConst.CUSTOMER_CHANNEL_1);
        customer.setCustomerType(DictConst.CUSTOMER_TYPE_2);
        customer.setProperty(DictConst.CUSTOMER_PROPERTY_1);
        customer.setDeviceNo(deviceNo);
        customer.setStatus(DictConst.CUSTOMER_STATUS_2);

        customerService.saveCustomer(customer);
        return customer;
    }
}
