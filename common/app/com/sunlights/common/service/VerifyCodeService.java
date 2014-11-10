package com.sunlights.common.service;

import com.sunlights.common.AppConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.dal.CustomerVerifyCodeDao;
import com.sunlights.common.dal.impl.CustomerVerifyCodeDaoImpl;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.vo.CustomerVerifyCodeVo;
import models.CustomerVerifyCode;
import play.Logger;

import java.sql.Timestamp;
import java.util.Random;

/**
 * Created by Administrator on 2014/10/30.
 */
public class VerifyCodeService {

    private CustomerVerifyCodeDao customerVerifyCodeDao = new CustomerVerifyCodeDaoImpl();
    private ParameterService parameterService = new ParameterService();
    
    /**
     * <P>Description: 获取验证码</p>
     * @return
     */
    public String genVerificationCode(String mobilePhoneNo, String type, String deviceNo) {
        CommonUtil.getInstance().validateParams(mobilePhoneNo, type);
        checkValidVerifyCode(type);


        String verifyCode = null;
        Timestamp currentTimestamp = DBHelper.getCurrentTime();
        CustomerVerifyCode preCustomerVerifyCode = customerVerifyCodeDao.findVerifyCodeByType(mobilePhoneNo, type);
        if (preCustomerVerifyCode != null) {
            //未失效返回以前的
            long VERIFYCODE_EXPIRY = parameterService.getParameterNumeric(AppConst.VERIFYCODE_EXPIRY);//验证码在失效时间
            if (currentTimestamp.getTime() - preCustomerVerifyCode.getCreatedDatetime().getTime() <= VERIFYCODE_EXPIRY * AppConst.ONE_MINUTE) {
                verifyCode = preCustomerVerifyCode.getVerifyCode();
                Logger.info("===========verifyCode:" + verifyCode);
                return verifyCode;
            }else{
                preCustomerVerifyCode.setStatus(AppConst.STATUS_INVALID);
                preCustomerVerifyCode.setUpdatedDatetime(currentTimestamp);
                customerVerifyCodeDao.updateCustomerVerifyCode(preCustomerVerifyCode);
            }
        }

        verifyCode = randomVerifyCode(6);
        CustomerVerifyCode newUserVefiryCode = new CustomerVerifyCode();
        newUserVefiryCode.setVerifyType(type);
        newUserVefiryCode.setMobile(mobilePhoneNo);
        newUserVefiryCode.setVerifyCode(verifyCode);
        newUserVefiryCode.setCreatedDatetime(currentTimestamp);
        newUserVefiryCode.setUpdatedDatetime(currentTimestamp);
        newUserVefiryCode.setDeviceNo(deviceNo);
        customerVerifyCodeDao.saveCustomerVerifyCode(newUserVefiryCode);

        Logger.info("===========mobilePhoneNo:" + mobilePhoneNo);
        Logger.info("===========verifyCode:" + verifyCode);

        return verifyCode;
    }

    private void checkValidVerifyCode(String type) {
        if (!AppConst.VALID_VERIFY_CODES.contains(type)){
            throw CommonUtil.getInstance().errorBusinessException(MsgCode.ACCESS_FAIL);
        }
    }


    private static String randomVerifyCode(int size) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < size; i++) {
            int num = random.nextInt(9);
            sb.append(num);
        }
        return sb.toString();
    }


    /**
     * 验证码验证
     * @return
     */
    public MsgCode validateVerifyCode(CustomerVerifyCodeVo customerVerifyCodeVo){
        CustomerVerifyCode customerVerifyCode = customerVerifyCodeDao.findVerifyCodeByType(customerVerifyCodeVo.getMobile(), customerVerifyCodeVo.getVerifyType());
        if (customerVerifyCode == null) {
            return MsgCode.CERTIFY_NONE;
        }
        if (!customerVerifyCode.getVerifyCode().equals(customerVerifyCodeVo.getVerifyCode())){
            return MsgCode.CERTIFY_ERROR;
        }

        Timestamp currentTime = DBHelper.getCurrentTime();
        long verifyCodeExpiry = parameterService.getParameterNumeric(AppConst.VERIFYCODE_EXPIRY);
        if (currentTime.getTime() - customerVerifyCode.getCreatedDatetime().getTime() >= verifyCodeExpiry * AppConst.ONE_MINUTE) {// 验证码有效时间超时
            customerVerifyCode.setStatus(AppConst.STATUS_INVALID);
            customerVerifyCode.setUpdatedDatetime(currentTime);
            customerVerifyCodeDao.updateCustomerVerifyCode(customerVerifyCode);
            return  MsgCode.CERTIFY_TIMEOUT;
        }
        if (!customerVerifyCode.getDeviceNo().equals(customerVerifyCodeVo.getDeviceNo())) {
            return MsgCode.CERTIFY_DEVICE_NOT_MATCH;
        }

        customerVerifyCode.setStatus(AppConst.STATUS_INVALID);
        customerVerifyCode.setUpdatedDatetime(currentTime);
        customerVerifyCodeDao.updateCustomerVerifyCode(customerVerifyCode);

        return MsgCode.OPERATE_SUCCESS;
    }
}
