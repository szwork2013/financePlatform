package com.sunlights.account.service.impl;

import com.sunlights.account.AccountConstant;
import com.sunlights.account.dal.AccountDao;
import com.sunlights.account.dal.impl.AccountDaoImpl;
import com.sunlights.account.service.AccountService;
import com.sunlights.account.vo.AcctChangeFlowVo;
import com.sunlights.common.AppConst;
import com.sunlights.common.DictConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.exceptions.BusinessRuntimeException;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.utils.MD5Helper;
import com.sunlights.common.vo.Message;
import com.sunlights.customer.service.impl.CustomerService;
import com.sunlights.customer.vo.CustomerFormVo;
import models.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class AccountServiceImpl implements AccountService {
	
	private AccountDao accountDao = new AccountDaoImpl();
    private CustomerService customerService = new CustomerService();

	public boolean createBaseAccount(String custId, String tradePassword) {
		BaseAccount baseAccount = new BaseAccount();
		baseAccount.setCustId(custId);
		baseAccount.setTradePassword(tradePassword);
		baseAccount.setBaseAccountNo(new StringBuilder(custId).append(AccountConstant.BASE_ACCOUNT_HOUZUI).toString());
		baseAccount.setStatus(AccountConstant.BASE_ACCOUNT_NOMAL_STATUS);
		baseAccount.setBalance(BigDecimal.ZERO);
		baseAccount.setCreateTime(new Timestamp(System.currentTimeMillis()));
		accountDao.saveBaseAccount(baseAccount);
		return true;
	}



	@Override
	public void createSubAccount(String custId, String fundCompanyId, String prdType) {
        //与基金公司开户
//        if (fundCompanyId != null && DictConst.FP_PRODUCT_TYPE_1.equals(prdType)) {
//            boolean isExist = accountDao.findFundAgreementExist(custId, fundCompanyId);
//            if (isExist){
//                return ;
//            }
//            accountDao.saveFundAgreement(custId, fundCompanyId);
//        }
        Timestamp currentTime = DBHelper.getCurrentTime();

        //创建子帐号
        BaseAccount baseAccount = accountDao.getBaseAccount(custId);
        SubAccount subAccount = new SubAccount();
        subAccount.setCustId(custId);
        subAccount.setBasicAccount(baseAccount.getBaseAccountNo());
        SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
        subAccount.setSubAccount(sdf.format(currentTime));
        subAccount.setStatus("0");
        subAccount.setCreateTime(currentTime);
        subAccount.setUpdateTime(currentTime);
        accountDao.saveSubAccount(subAccount);

        //查询是否设置产品账户关系
        boolean prdAccountConfigExist = accountDao.findPrdAccountConfigExist(prdType, subAccount.getSubAccount());
        if (!prdAccountConfigExist) {
            accountDao.savePrdAccountConfig(subAccount.getSubAccount(), prdType);
        }
	}

	@Override
	public void dealAccount(AcctChangeFlowVo acctChangeFlowVo) {
        String customerId = acctChangeFlowVo.getCustomerId();
        String prdCode = acctChangeFlowVo.getPrdCode();
        String tradeNo = acctChangeFlowVo.getTradeNo();
        String tradeType = acctChangeFlowVo.getTradeType();
        BigDecimal amount = acctChangeFlowVo.getAmount();

        AcctChangFlow acctChangFlow = new AcctChangFlow();
        acctChangFlow.setCustomerId(customerId);
        acctChangFlow.setProductCode(prdCode);
        acctChangFlow.setTradeNo(tradeNo);
        if (DictConst.TRADE_TYPE_1.equals(tradeType)) {
            acctChangFlow.setSubjectNo(AppConst.SUBJECT_PURCHASE);
            acctChangFlow.setAmount(amount);
        }else if (DictConst.TRADE_TYPE_2.equals(tradeType)){
            acctChangFlow.setAmount(amount.negate());
            acctChangFlow.setSubjectNo(AppConst.SUBJECT_REDEEM);
        }

        acctChangFlow.setCreateTime(DBHelper.getCurrentTime());
        accountDao.saveAcctChangFlow(acctChangFlow);
    }


    public void resetTradePwdCertify(CustomerFormVo vo) {
        String mobilePhoneNo = vo.getMobilePhoneNo();
        String verifyCode = vo.getVerifyCode();
        String userName = vo.getUserName();
        String idCardNo = vo.getIdCardNo();

        CommonUtil.getInstance().validateParams(mobilePhoneNo, verifyCode);
        Customer customer = customerService.getCustomerByMobile(mobilePhoneNo);
        if (customer == null) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.PHONE_NUMBER_NOT_REGISTRY));
        }

        if (DictConst.CERTIFICATE_TYPE_1.equals(customer.getIdentityTyper())) { //若为实名验证过的用户，判断真实姓名和身份证号是否和数据库中一致，
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
                customer.setUpdateTime(currentTime);
                customerService.updateCustomer(customer);
            }
            //设置交易密码
            baseAccount = accountDao.getBaseAccount(customerSession.getCustomerId());
            baseAccount.setTradePassword(new MD5Helper().encrypt(tradePassword));
            baseAccount.setUpdateTime(currentTime);
            accountDao.updateBaseAccount(baseAccount);

        }

        return baseAccount;
    }




}
