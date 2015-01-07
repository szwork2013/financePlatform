package com.sunlights.customer.service.impl;

import com.sunlights.common.*;
import com.sunlights.common.exceptions.BusinessRuntimeException;
import com.sunlights.common.service.ParameterService;
import com.sunlights.common.service.VerifyCodeService;
import com.sunlights.common.utils.*;
import com.sunlights.common.vo.CustomerVerifyCodeVo;
import com.sunlights.common.vo.Message;
import com.sunlights.customer.dal.CustomerDao;
import com.sunlights.customer.dal.LoginDao;
import com.sunlights.customer.dal.impl.CustomerDaoImpl;
import com.sunlights.customer.dal.impl.LoginDaoImpl;
import com.sunlights.customer.service.LoginService;
import com.sunlights.customer.vo.CustomerFormVo;
import com.sunlights.customer.vo.CustomerVo;
import models.Customer;
import models.CustomerGesture;
import models.CustomerSession;
import models.LoginHistory;
import play.Logger;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * @author Administrator
 * 
 */

public class LoginServiceImpl implements LoginService {


    private LoginDao loginDao = new LoginDaoImpl();

    private ParameterService parameterService=new ParameterService();

    private CustomerDao customerDao=new CustomerDaoImpl();

    private CustomerService customerService=new CustomerService();


    private VerifyCodeService verifyCodeService=new VerifyCodeService();

	/**
	 * 登录
	 */
	public CustomerSession login(CustomerFormVo vo, String token, String remoteAddress) {
		String mobilePhoneNo = vo.getMobilePhoneNo();
		String passWord = vo.getPassWord();
		String deviceNo = vo.getDeviceNo();
		//
        CommonUtil.getInstance().validateParams(mobilePhoneNo, passWord, deviceNo);
        Customer customer = getCustomerByMobilePhoneNo(mobilePhoneNo);
        if (customer == null) {
        	throw CommonUtil.getInstance().errorBusinessException(MsgCode.PHONE_NUMBER_NOT_REGISTRY);
        }
        //若为已登录状态跳出，（如：手机后台激活）
        CustomerSession customerSession = customerService.getCustomerSession(token);
        if (customerSession != null) {
            if (new MD5Helper().encrypt(passWord).equals(customer.getLoginPassWord())) {
                saveLoginHistory(customer, deviceNo);
                return customerSession;
            }
        }
        validateLoginTime(customer, deviceNo);
        //
        if (!customer.getLoginPassWord().equals(new MD5Helper().encrypt(passWord))) {
            saveLoginFail(customer, deviceNo, false);
            return null;
        }

        saveLoginHistory(customer, deviceNo);
        customerSession = customerService.createCustomerSession(customer, remoteAddress);

        return customerSession;
	}

	/**
	 * 手势登录
	 * 
	 * @return
	 */
	public CustomerSession loginByGesture(CustomerFormVo vo, String token, String clientAddress) {
        String mobilePhoneNo = vo.getMobilePhoneNo();
        String gesturePassWord = vo.getGesturePassWord();
        String deviceNo = vo.getDeviceNo();

        CommonUtil.getInstance().validateParams(mobilePhoneNo, gesturePassWord, deviceNo);
        Customer customer = getCustomerByMobilePhoneNo(mobilePhoneNo);
        if (customer == null) {
            throw CommonUtil.getInstance().errorBusinessException(MsgCode.PHONE_NUMBER_NOT_REGISTRY);
        }
        CustomerGesture customerGesture = customerDao.findCustomerGestureByDeviceNo(customer.getCustomerId(), deviceNo);
        if (customerGesture == null) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.GESTURE_NONE));
        }

        //若为登录状态
        CustomerSession customerSession = customerService.getCustomerSession(token);
        if (customerSession != null) {
            //并且此次手势密码输入正确  直接返回（用于手势解屏）
            if (new MD5Helper().encrypt(gesturePassWord).equals(customerGesture.getGesturePassword())) {
                saveLoginHistory(customer, deviceNo);
            	return customerSession;
            }
        }else{
            validateLoginTime(customer, deviceNo);//验证暂时锁定状态是否已过
        }
        //
        if (!new MD5Helper().encrypt(gesturePassWord).equals(customerGesture.getGesturePassword())) {
            LoginHistory loginHistory = saveLoginFail(customer, deviceNo, true);
        	long PWD_MAX = parameterService.getParameterNumeric(ParameterConst.PWD_MAX);// 登录失败的最大次数
    		if (loginHistory.getLogNum() % PWD_MAX == 0) {// 此次为PWD_MAX * n次    手势删除，若为登录状态则登出
    			customerGesture.setStatus(AppConst.STATUS_INVALID);
    			customerGesture.setUpdateTime(DBHelper.getCurrentTime());
                customerDao.updateCustomerGesture(customerGesture);
    			logout(customer.getMobile(), deviceNo, token);
    		}
    		return null;
        }

        saveLoginHistory(customer, deviceNo);
        customerSession = customerService.createCustomerSession(customer, clientAddress);

        return customerSession;
	}


	/**
	 * 注册
	 * @return
	 */
	public Customer register(CustomerFormVo vo) {
		String mobilePhoneNo = vo.getMobilePhoneNo();
		String passWord = vo.getPassWord();
		String verifyCode = vo.getVerifyCode();
		String deviceNo = vo.getDeviceNo();

        Logger.info("====nickName:" + vo.getNickName());
        Logger.info("=============mobilePhoneNo:" + mobilePhoneNo);
        Logger.info("=============deviceNo:" + deviceNo);
        Logger.info("=============recommendPhone:" + vo.getRecommendPhone());
        CommonUtil.getInstance().validateParams(mobilePhoneNo, passWord);
        
        Customer oldUserMstr = getCustomerByMobilePhoneNo(mobilePhoneNo);
        if (oldUserMstr != null) {
            throw CommonUtil.getInstance().errorBusinessException(MsgCode.PHONE_NUMBER_ALREADY_REGISTRY);
        }
        CustomerVerifyCodeVo customerVerifyCodeVo = new CustomerVerifyCodeVo();
        customerVerifyCodeVo.setMobile(mobilePhoneNo);
        customerVerifyCodeVo.setVerifyType(AppConst.VERIFY_CODE_REGISTER);
        customerVerifyCodeVo.setDeviceNo(deviceNo);
        customerVerifyCodeVo.setVerifyCode(verifyCode);
        boolean success = verifyCodeService.validateVerifyCode(customerVerifyCodeVo);

        if (!success) {
            return null;
        }

        Customer customer = saveCustomer(vo);

        saveLoginHistory(customer, deviceNo);

        return customer;
	}

    private Customer saveCustomer(CustomerFormVo vo) {
        String mobilePhoneNo = vo.getMobilePhoneNo();
        String passWord = vo.getPassWord();
        String nickName = vo.getNickName();
        String deviceNo = vo.getDeviceNo();
        String recommendPhone = vo.getRecommendPhone();

        Timestamp currentTime = DBHelper.getCurrentTime();
        Customer customer = new Customer();
        customer.setLoginId(mobilePhoneNo);
        customer.setNickName(nickName);
        customer.setMobile(mobilePhoneNo);
        customer.setLoginPassWord(new MD5Helper().encrypt(passWord));
        customer.setRegChannel(DictConst.CUSTOMER_CHANNEL_1);
        customer.setRegWay(DictConst.CUSTOMER_CHANNEL_1);
        customer.setCustomerType(DictConst.CUSTOMER_TYPE_2);
        customer.setProperty(DictConst.CUSTOMER_PROPERTY_1);
        customer.setDeviceNo(deviceNo);
        customer.setStatus(DictConst.CUSTOMER_STATUS_2);
        customer.setRecommendPhone(recommendPhone);
        customer.setCreateTime(currentTime);
        customer.setUpdateTime(currentTime);
        customerService.saveCustomer(customer);
        return customer;
    }

    /**
	 * 忘记密码验证码校对
	 * 
	 * @return
	 */
	public boolean resetpwdCertify(CustomerFormVo vo) {
        String mobilePhoneNo = vo.getMobilePhoneNo();
        String verifyCode = vo.getVerifyCode();
        String userName = vo.getUserName();
        String idCardNo = vo.getIdCardNo();
        String deviceNo = vo.getDeviceNo();

        CommonUtil.getInstance().validateParams(mobilePhoneNo);
		Customer customer = getCustomerByMobilePhoneNo(mobilePhoneNo);
		if (customer == null) {
			throw CommonUtil.getInstance().errorBusinessException(MsgCode.PHONE_NUMBER_NOT_REGISTRY);
		}

        if (DictConst.CERTIFICATE_TYPE_1.equals(customer.getIdentityTyper())) { //若为实名验证过的用户，判断真实姓名和身份证号是否和数据库中一致，
            if (!idCardNo.equals(customer.getIdentityNumber()) || !userName.equals(customer.getRealName())) {
                throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.NAME_OR_ID_ERROR));
            }
        }
        CustomerVerifyCodeVo customerVerifyCodeVo = new CustomerVerifyCodeVo();
        customerVerifyCodeVo.setMobile(mobilePhoneNo);
        customerVerifyCodeVo.setVerifyType(AppConst.VERIFY_CODE_RESETPWD);
        customerVerifyCodeVo.setDeviceNo(deviceNo);
        customerVerifyCodeVo.setVerifyCode(verifyCode);
        boolean success = verifyCodeService.validateVerifyCode(customerVerifyCodeVo);

        return success;
	}
	/**
	 * 重置密码
	 * @return
	 */
	public Customer resetPwd(String mobilePhoneNo, String passWord, String deviceNo) {
        CommonUtil.getInstance().validateParams(mobilePhoneNo, passWord);
		Customer customer = getCustomerByMobilePhoneNo(mobilePhoneNo);
		if (customer == null) {
			throw CommonUtil.getInstance().errorBusinessException(MsgCode.PHONE_NUMBER_NOT_REGISTRY);
		}
		Timestamp currentTime = DBHelper.getCurrentTime();
        customer.setLoginPassWord(new MD5Helper().encrypt(passWord));
        customer.setUpdateTime(currentTime);
        customerService.updateCustomer(customer);

        Message message = new Message(MsgCode.PASSWORD_CHANGE_SUCCESS);
        CustomerVo customerVoByPhoneNo = customerService.getCustomerVoByPhoneNo(mobilePhoneNo, deviceNo);
        MessageUtil.getInstance().setMessage(message, customerVoByPhoneNo);

        return customer;
	}
    /**
     * 退出
     * @param mobilePhoneNo
     * @param deviceNo
     * @param token
     */
	public void logout(String mobilePhoneNo, String deviceNo, String token) {
        Customer customer = getCustomerByMobilePhoneNo(mobilePhoneNo);
        if (customer == null) {
            throw CommonUtil.getInstance().errorBusinessException(MsgCode.PHONE_NUMBER_NOT_REGISTRY);
        }
        CustomerSession customerSession = null;
        if (token != null) {
            customerSession = customerService.getCustomerSession(token);
        }else{
            customerSession = customerDao.findCustomerSessionByCustomerId(customer.getCustomerId(), deviceNo);
        }
        Timestamp currentTime = DBHelper.getCurrentTime();
        if (customerSession != null) {//清除
            customerService.removeCache(token);
            customerSession.setStatus(AppConst.STATUS_INVALID);
            customerSession.setUpdateTime(currentTime);
            customerDao.updateCustomerSession(customerSession);
        }
        LoginHistory loginHistory = loginDao.findByLoginCustomer(customer.getCustomerId(), deviceNo);
        if(loginHistory != null){
            loginHistory.setLogoutTime(currentTime);
            loginHistory.setUpdateTime(currentTime);
            loginDao.updateLoginHistory(loginHistory);
        }

	}
	
	/**
	 * 密码确认
	 * 
	 * @return
	 */
	public void confirmPwd(String mobilePhoneNo, String password) {
        CommonUtil.getInstance().validateParams(mobilePhoneNo, password);
		Customer userMstr = getCustomerByMobilePhoneNo(mobilePhoneNo);
		if (userMstr == null) {
			throw CommonUtil.getInstance().errorBusinessException(MsgCode.PHONE_NUMBER_NOT_REGISTRY);
		}
		if (!new MD5Helper().encrypt(password).equals(userMstr.getLoginPassWord())) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.PASSWORD_CONFIRM_ERROR));
		}
	}
	
	/**
	 * 保存手势密码
	 * 
	 * @return
	 */
	public CustomerVo saveGesturePwd(CustomerFormVo vo) {
        String mobilePhoneNo = vo.getMobilePhoneNo();
        String gesturePassWord = vo.getGesturePassWord();
        String gestureOpened = vo.getGestureOpened();
        String deviceNo = vo.getDeviceNo();

        Customer customer = checkFormVo(mobilePhoneNo, gesturePassWord, gestureOpened);
		Timestamp currentTime = DBHelper.getCurrentTime();
		CustomerGesture customerGesture = customerDao.findCustomerGestureByDeviceNo(customer.getCustomerId(), deviceNo);
		if (customerGesture != null && !AppConst.VALID_CERTIFY.equals(gestureOpened)) {// 关闭手势密码
            closeGesture(currentTime, customerGesture);
		} else if (AppConst.VALID_CERTIFY.equals(gestureOpened)) {// 开启
            openGesture(gesturePassWord, deviceNo, customer, currentTime);
		}

        CustomerVo customerVoByPhoneNo = customerService.getCustomerVoByPhoneNo(mobilePhoneNo, deviceNo);
        return customerVoByPhoneNo;
	}

    private Customer checkFormVo(String mobilePhoneNo, String gesturePassWord, String gestureOpened) {
        if (mobilePhoneNo == null
                || gestureOpened == null
                || (AppConst.VALID_CERTIFY.equals(gestureOpened) && gesturePassWord == null)) {
            throw CommonUtil.getInstance().errorBusinessException(MsgCode.ACCESS_FAIL);
        }
        Customer customer = getCustomerByMobilePhoneNo(mobilePhoneNo);
        if (customer == null) {
            throw CommonUtil.getInstance().errorBusinessException(MsgCode.PHONE_NUMBER_NOT_REGISTRY);
        }
        return customer;
    }

    private void closeGesture(Timestamp currentTime, CustomerGesture customerGesture) {
        customerGesture.setUpdateTime(currentTime);
        customerGesture.setStatus(AppConst.STATUS_INVALID);
        customerDao.updateCustomerGesture(customerGesture);
    }

    private void openGesture(String gesturePassWord, String deviceNo, Customer customer, Timestamp currentTime) {
        CustomerGesture customerGesture = new CustomerGesture();
        customerGesture.setGesturePassword(new MD5Helper().encrypt(gesturePassWord));
        customerGesture.setCreateTime(currentTime);
        customerGesture.setUpdateTime(currentTime);
        customerGesture.setDeviceNo(deviceNo);
        customerGesture.setCustomerId(customer.getCustomerId());
        customerDao.saveCustomerGesture(customerGesture);
    }

    public void saveLoginHistory(Customer customer, String deviceNo){
        Timestamp currentTime = DBHelper.getCurrentTime();
        LoginHistory loginHistory = new LoginHistory();
        loginHistory.setCustomerId(customer.getCustomerId());
        loginHistory.setDeviceNo(deviceNo);
        loginHistory.setPwdInd(AppConst.STATUS_VALID);
        loginHistory.setGestureInd(AppConst.STATUS_VALID);
        loginHistory.setSocialInd(AppConst.STATUS_VALID);
        loginHistory.setSuccessInd(AppConst.STATUS_VALID);
        loginHistory.setLoginTime(currentTime);
        loginHistory.setCreateTime(currentTime);
        loginHistory.setUpdateTime(currentTime);
        loginHistory.setLogNum(0);
        loginDao.saveLoginHistory(loginHistory);
    }


    /**
     * 用户被暂时锁定的时间 = 倍数(向上取整) * RELIEVE_SUSLOCK_PERIOD(每次的停留时间)
     * @param oldTimes
     * @param PWD_MAX
     * @return
     */
    private long getTotalMinute(long oldTimes, long PWD_MAX){
        long RELIEVE_SUSLOCK_PERIOD = parameterService.getParameterNumeric(ParameterConst.RELIEVE_SUSLOCK_PERIOD);
        BigDecimal failTimes = ArithUtil.bigUpScale0(new BigDecimal((double) oldTimes / PWD_MAX));
        long times = (long)Math.pow(2, failTimes.subtract(BigDecimal.ONE).doubleValue());
        long returnTime = times * RELIEVE_SUSLOCK_PERIOD;
        return returnTime;
    }

    /**
     * 用户查询
     * @return
     */
    private Customer getCustomerByMobilePhoneNo(String  mobilePhoneNo) {
        Customer customer = customerService.getCustomerByMobile(mobilePhoneNo);
        return customer;
    }
    /**
     * 验证暂时锁定状态、时间是否已过
     * @param customer
     * @param deviceNo
     */
    private void validateLoginTime(Customer customer, String deviceNo){
        LoginHistory oldHistory = loginDao.findByPwd(customer.getCustomerId(), deviceNo);
        if (oldHistory != null) {
            Timestamp currentTime = DBHelper.getCurrentTime();
            long logNum = oldHistory.getLogNum();
            long PWD_MAX = parameterService.getParameterNumeric(ParameterConst.PWD_MAX);
            if (logNum != 0 && logNum % PWD_MAX == 0) {//上一次为PWD_MAX * n次
                long balanceMin = getTotalMinute(logNum, PWD_MAX) * AppConst.ONE_MINUTE - (currentTime.getTime() - oldHistory.getCreateTime().getTime());
                if (balanceMin > 0) {
                    BigDecimal min = ArithUtil.bigUpScale0(new BigDecimal((double) balanceMin / AppConst.ONE_MINUTE));
                    throw CommonUtil.getInstance().errorBusinessException(MsgCode.PASSWORD_ERROR_OVER_COUNT, min);
                }
            }
        }
    }

    private LoginHistory saveLoginFail(Customer customer, String deviceNo, boolean isGestureLogin){
        Timestamp currentTime = DBHelper.getCurrentTime();
        long oldNum = 0;
        long PWD_MAX = parameterService.getParameterNumeric(ParameterConst.PWD_MAX);//登录失败的最大次数
        LoginHistory oldHistory = null;
        if (isGestureLogin) {
            oldHistory = loginDao.findByGesturePwd(customer.getCustomerId(), deviceNo);
        }else{
            oldHistory = loginDao.findByPwd(customer.getCustomerId(), deviceNo);
        }

        if (oldHistory != null) {
            oldNum = oldHistory.getLogNum();
            if (!AppConst.STATUS_VALID.equals(oldHistory.getSuccessInd())) {// 成功为0，以下重计算失败的次数
                //登录失败但没到最大次数，隔LOGIN_PERIOD时间恢复重新计数登录次数
                long LOGIN_PERIOD = parameterService.getParameterNumeric(ParameterConst.LOGIN_PERIOD);
                if (oldNum < PWD_MAX && (currentTime.getTime() - oldHistory.getCreateTime().getTime() > LOGIN_PERIOD * AppConst.ONE_MINUTE)) {
                    oldNum = 0;
                }
            }
        }

        LoginHistory loginHistory = new LoginHistory();
        loginHistory.setCustomerId(customer.getCustomerId());
        loginHistory.setDeviceNo(deviceNo);
        if (isGestureLogin) {
            loginHistory.setGestureInd(AppConst.STATUS_VALID);
        }else{
            loginHistory.setPwdInd(AppConst.STATUS_VALID);
        }
        loginHistory.setLogNum(oldNum + 1);
        loginHistory.setSuccessInd(AppConst.STATUS_INVALID);
        loginHistory.setLoginTime(currentTime);
        loginHistory.setCreateTime(currentTime);
        loginHistory.setUpdateTime(currentTime);
        loginDao.saveLoginHistory(loginHistory);

        addFailMessage(loginHistory, isGestureLogin);

        return loginHistory;
    }

    private void addFailMessage(LoginHistory loginHistory, boolean isGestureLogin){
        long PWD_MAX = parameterService.getParameterNumeric(ParameterConst.PWD_MAX);//登录失败的最大次数
        Message message = null;
        if (isGestureLogin){
            if (loginHistory.getLogNum() % PWD_MAX != 0) {
                long times = PWD_MAX - loginHistory.getLogNum() % PWD_MAX;
                message = new Message(Severity.ERROR, MsgCode.GESTURE_PASSWORD_ERROR, times);
            } else {// 此次为PWD_MAX * n次    做清除操作，若为登录状态则登出
                message = new Message(Severity.ERROR, MsgCode.GESTURE_LOGIN_ERROR_OVER_COUNT);
            }
        }else{
            if (loginHistory.getLogNum() % PWD_MAX != 0) {
                long times = PWD_MAX - loginHistory.getLogNum() % PWD_MAX;
                message = new Message(Severity.ERROR, MsgCode.PASSWORD_ERROR, times);
            } else {// 此次为PWD_MAX * n次
                message = new Message(Severity.ERROR, MsgCode.PASSWORD_ERROR_OVER_COUNT, getTotalMinute(loginHistory.getLogNum(), PWD_MAX));
            }
        }

        MessageUtil.getInstance().setMessage(message);
    }

}
