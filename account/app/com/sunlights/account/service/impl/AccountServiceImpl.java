package com.sunlights.account.service.impl;

import com.sunlights.account.AccountConstant;
import com.sunlights.account.dal.BaseAccountDao;
import com.sunlights.account.dal.impl.BaseAccountDaoImpl;
import com.sunlights.account.service.AccountService;
import com.sunlights.common.AppConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.exceptions.BusinessRuntimeException;
import com.sunlights.common.service.VerifyCodeService;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.utils.MD5Helper;
import com.sunlights.common.vo.Message;
import com.sunlights.customer.service.impl.CustomerService;
import com.sunlights.customer.vo.CustomerFormVo;
import models.BaseAccount;
import models.Customer;
import models.CustomerSession;
import play.Logger;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class AccountServiceImpl implements AccountService {
	
	private BaseAccountDao baseAccountDao = new BaseAccountDaoImpl();
    private CustomerService customerService = new CustomerService();
    private VerifyCodeService verifyCodeService = new VerifyCodeService();

	public boolean createBaseAccount(String custId, String tradePassword) {
		BaseAccount baseAccount = new BaseAccount();
		baseAccount.setCustId(custId);
		baseAccount.setTradePassword(tradePassword);
		baseAccount.setBaseAccountNo(new StringBuilder(custId).append(AccountConstant.BASE_ACCOUNT_HOUZUI).toString());
		baseAccount.setStatus(AccountConstant.BASE_ACCOUNT_NOMAL_STATUS);
		baseAccount.setBalance(BigDecimal.ZERO);
		baseAccount.setCreateTime(new Timestamp(System.currentTimeMillis()));
		baseAccountDao.saveBaseAccount(baseAccount);
		return true;
	}

	@Override
	public boolean registerSubAccount(String custId, String baseAccount, String prdCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void dealAccount(String custId, String prdCode, String tradeNo,
			String tradeType, BigDecimal amount) {
		// TODO Auto-generated method stub

	}

    public void resetTradePwdCertify(CustomerFormVo vo) {
        String mobilePhoneNo = vo.getMobilePhoneNo();
        String verifyCode = vo.getVerifyCode();
        String userName = vo.getUserName();
        String idCardNo = vo.getIdCardNo();
        String deviceNo = vo.getDeviceNo();

        Logger.info("=======userName:" + userName);
        Logger.info("=======mobilePhoneNo:" + mobilePhoneNo);
        CommonUtil.getInstance().validateParams(mobilePhoneNo, verifyCode);
        Customer customer = customerService.getCustomerByMobile(mobilePhoneNo);
        if (customer == null) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.PHONE_NUMBER_NOT_REGISTRY));
        }

        if (AppConst.ID_CARD.equals(customer.getIdentityTyper())) { //若为实名验证过的用户，判断真实姓名和身份证号是否和数据库中一致，
            if (!idCardNo.equals(customer.getIdentityNumber()) || !userName.equals(customer.getRealName())) {
                throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.NAME_OR_ID_ERROR));
            }
        }
    }

    /**
     * 交易密码设置/修改
     * @param customerFormVo
     * @param token
     * @return
     */
    public BaseAccount resetTradePwd(CustomerFormVo customerFormVo, String token){
        String mobilePhoneNo = customerFormVo.getMobilePhoneNo();
        String tradePassword = customerFormVo.getPassWord();
        String deviceNo = customerFormVo.getDeviceNo();

        CustomerSession customerSession = customerService.getCustomerSession(token);
        BaseAccount baseAccount = null;
        if (customerSession == null) {//为非法客户
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.LOGIN_TIMEOUT));
        }else{
            Timestamp currentTime = DBHelper.getCurrentTime();
            if (mobilePhoneNo == null || "".equals(mobilePhoneNo.trim())) {//为首次购买的未注册的 客户
                Customer customer = customerService.getCustomerByCustomerId(customerSession.getCustomerId());
                //登录密码为交易密码
                customer.setLoginPassWord(new MD5Helper().encrypt(tradePassword));
                customer.setUpdatedDatetime(currentTime);
                customerService.updateCustomer(customer);
            }
            //设置交易密码
            baseAccount = baseAccountDao.getBaseAccount(customerSession.getCustomerId());
            baseAccount.setTradePassword(new MD5Helper().encrypt(tradePassword));
            baseAccount.setUpdateTime(currentTime);
            baseAccountDao.updateBaseAccount(baseAccount);

        }

        return baseAccount;
    }

    @Override
    public void certifyAndResetTradePwd(CustomerFormVo customerFormVo, String token, String remoteAddress){
//        CustomerSession customerSession = verifyCodeService.certify(customerFormVo, token, remoteAddress);//TODO
//        resetTradePwd(customerFormVo, customerSession.getToken());
    }



}
