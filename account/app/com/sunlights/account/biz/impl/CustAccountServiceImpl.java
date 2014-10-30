package com.sunlights.account.biz.impl;

import com.sunlights.account.AccountConstant;
import com.sunlights.account.biz.AccountService;
import com.sunlights.account.dal.BaseAccountDao;
import com.sunlights.account.models.BaseAccount;
import com.sunlights.common.AppConst;
import com.sunlights.common.CustomerVerifyCodeVo;
import com.sunlights.common.MsgCode;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.utils.DateUtils;
import com.sunlights.common.utils.MD5Helper;
import com.sunlights.customer.biz.impl.CustomerService;
import com.sunlights.customer.models.Customer;
import com.sunlights.customer.models.CustomerSession;
import com.sunlights.customer.vo.CustomerFormVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import play.Logger;

import java.math.BigDecimal;
import java.sql.Timestamp;


@Service("custAccountService")
public class CustAccountServiceImpl implements AccountService {
	
	@Autowired
	private BaseAccountDao baseAccountDao;
    @Autowired
    private CustomerService customerService;

	@Transactional
	@Override
	public boolean registerBaseAccount(String custId, String tradePassword) {
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
	public void tally(String custId, String prdCode, String tradeNo,
                      String tradeType, BigDecimal amount) {
		// TODO Auto-generated method stub

	}

    public CustomerVerifyCodeVo resetTradePwdCertify(CustomerFormVo vo) {
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
            throw CommonUtil.getInstance().errorBusinessException(MsgCode.PHONE_NUMBER_ALREADY_REGISTRY);
        }

        if (AppConst.ID_CARD.equals(customer.getIdentityTyper())) { //若为实名验证过的用户，判断真实姓名和身份证号是否和数据库中一致，
            if (!idCardNo.equals(customer.getIdentityNumber()) || !userName.equals(customer.getRealName())) {
                throw CommonUtil.getInstance().errorBusinessException(MsgCode.NAME_OR_ID_ERROR);
            }
        }

        CustomerVerifyCodeVo customerVerifyCodeVo = new CustomerVerifyCodeVo();
        customerVerifyCodeVo.setMobile(mobilePhoneNo);
        customerVerifyCodeVo.setVerifyType(AppConst.VERIFY_CODE_RESET_ACCOUNT);
        customerVerifyCodeVo.setDeviceNo(deviceNo);
        customerVerifyCodeVo.setVerifyCode(verifyCode);
        return customerVerifyCodeVo;
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
            throw CommonUtil.getInstance().errorBusinessException(MsgCode.LOGIN_TIMEOUT);
        }else{
            Timestamp currentTime = DateUtils.getCurrentTime();
            if (mobilePhoneNo == null || "".equals(mobilePhoneNo.trim())) {//为首次购买的未注册的 客户
                Customer customer = customerService.getCustomerByCustomerId(customerSession.getCustomerId());
                //登录密码为交易密码
                customer.setLoginPassWord(tradePassword);
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
}
