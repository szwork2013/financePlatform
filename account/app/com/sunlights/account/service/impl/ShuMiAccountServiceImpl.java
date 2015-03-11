package com.sunlights.account.service.impl;

import com.sunlights.account.dal.ShuMiAccountDao;
import com.sunlights.account.dal.impl.ShuMiAccountDaoImpl;
import com.sunlights.account.service.ShuMiAccountService;
import com.sunlights.account.vo.ShuMiAccountVo;
import com.sunlights.common.DictConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.exceptions.ConverterException;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.customer.service.impl.CustomerService;
import com.sunlights.customer.vo.CustomerVo;
import models.Customer;
import models.CustomerSession;
import models.ShuMiAccount;

import java.sql.Timestamp;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: ShuMiAccountServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class ShuMiAccountServiceImpl implements ShuMiAccountService {
    private ShuMiAccountDao shuMiAccountDao = new ShuMiAccountDaoImpl();
    private CustomerService customerService = new CustomerService();

    @Override
    public ShuMiAccount saveShuMiAccount(ShuMiAccountVo shuMiAccountVo, String token) throws ConverterException {
        Timestamp currentTime = DBHelper.getCurrentTime();
        Customer customer = updateCustomer(shuMiAccountVo, token);

        ShuMiAccount shuMiAccount = shuMiAccountDao.findShuMiAccountByCustomerId(customer.getCustomerId());
        if (shuMiAccount == null) {
            shuMiAccount = new ShuMiAccount();
            ConverterUtil.toEntity(shuMiAccount, shuMiAccountVo);
            shuMiAccount.setCreate_time(currentTime);
            shuMiAccount.setCustomerId(customer.getCustomerId());
            shuMiAccountDao.saveShuMiAccount(shuMiAccount);
        } else if (!shuMiAccount.getShumi_tokenKey().equals(shuMiAccountVo.getShumi_tokenKey())
                || !shuMiAccount.getShumi_tokenSecret().equals(shuMiAccountVo.getShumi_tokenSecret())) {

            shuMiAccount.setShumi_tokenKey(shuMiAccountVo.getShumi_tokenKey());
            shuMiAccount.setShumi_tokenSecret(shuMiAccountVo.getShumi_tokenSecret());
            shuMiAccount.setUpdate_time(currentTime);
            shuMiAccountDao.updateShuMiAccount(shuMiAccount);
        }

        CustomerSession customerSession = customerService.getCustomerSession(token);
        CustomerVo customerVo = customerService.getCustomerVoByPhoneNo(customer.getMobile(), customerSession.getDeviceNo());
        MessageUtil.getInstance().setMessage(new Message(MsgCode.SAVE_SHUMI_ACCOUNT_SUCCESS), customerVo);

        return shuMiAccount;
    }


    private Customer updateCustomer(ShuMiAccountVo shuMiAccountVo, String token) {
        Customer customer = customerService.getCustomerByToken(token);

        Timestamp currentTime = DBHelper.getCurrentTime();
        customer.setRealName(shuMiAccountVo.getShumi_realName());
        customer.setIdentityTyper(DictConst.CERTIFICATE_TYPE_1);
        customer.setIdentityNumber(shuMiAccountVo.getShumi_idNumber());
        customer.setEmail(shuMiAccountVo.getShumi_email());
        customer.setUpdateTime(currentTime);

        customerService.updateCustomer(customer);

        return customer;
    }
}
