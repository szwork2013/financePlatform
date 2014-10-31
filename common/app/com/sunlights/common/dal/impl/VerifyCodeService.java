package com.sunlights.common.dal.impl;

import com.sunlights.common.AppConst;
import com.sunlights.common.IParameterConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.ParameterService;
import com.sunlights.common.dal.CustomerVerifyCodeDao;
import com.sunlights.common.models.CustomerVerifyCode;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.utils.msg.Message;
import com.sunlights.common.utils.msg.MessageUtil;
import com.sunlights.common.vo.CustomerVerifyCodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import play.Logger;

import java.sql.Timestamp;
import java.util.Random;

/**
 * Created by Administrator on 2014/10/30.
 */
@Service
public class VerifyCodeService {

    @Autowired
    private CustomerVerifyCodeDao customerVerifyCodeDao;
    @Autowired
    private ParameterService parameterService;
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
            long VERIFYCODE_EXPIRY = parameterService.getParameterNumeric(IParameterConst.VERIFYCODE_EXPIRY);//验证码在失效时间
            if (currentTimestamp.getTime() - preCustomerVerifyCode.getCreatedDatetime().getTime() <= VERIFYCODE_EXPIRY * AppConst.ONE_MINUTE) {
                verifyCode = preCustomerVerifyCode.getVerifyCode();
                Logger.info("===========verifyCode:" + verifyCode);
                return verifyCode;
            }else{
                preCustomerVerifyCode.setStatus(AppConst.VERIFY_CODE_STATUS_VALID);
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
    public boolean validateVerifyCode(CustomerVerifyCodeVo customerVerifyCodeVo){
        CustomerVerifyCode customerVerifyCode = customerVerifyCodeDao.findVerifyCodeByType(customerVerifyCodeVo.getMobile(), customerVerifyCodeVo.getVerifyType());
        if (customerVerifyCode == null) {
            MessageUtil.getInstance().addMessage(new Message(Message.SEVERITY_ERROR, MsgCode.CERTIFY_NONE));
            return false;
        }
        if (!customerVerifyCode.getVerifyCode().equals(customerVerifyCodeVo.getVerifyCode())) {
            MessageUtil.getInstance().addMessage(new Message(Message.SEVERITY_ERROR, MsgCode.CERTIFY_ERROR));
            return false;
        }
        Timestamp currentTime = DBHelper.getCurrentTime();
        long verifyCodeExpiry = parameterService.getParameterNumeric(IParameterConst.VERIFYCODE_EXPIRY);
        if (currentTime.getTime() - customerVerifyCode.getCreatedDatetime().getTime() >= verifyCodeExpiry * AppConst.ONE_MINUTE) {// 验证码有效时间超时
            customerVerifyCode.setStatus(AppConst.VERIFY_CODE_STATUS_VALID);
            customerVerifyCode.setUpdatedDatetime(currentTime);
            customerVerifyCodeDao.updateCustomerVerifyCode(customerVerifyCode);
            MessageUtil.getInstance().addMessage(new Message(Message.SEVERITY_ERROR, MsgCode.CERTIFY_TIMEOUT));
            return false;
        }
        if (!customerVerifyCode.getDeviceNo().equals(customerVerifyCodeVo.getDeviceNo())) {
            MessageUtil.getInstance().addMessage(new Message(Message.SEVERITY_ERROR, MsgCode.CERTIFY_DEVICE_NOT_MATCH));
            return false;
        }

        customerVerifyCode.setStatus(AppConst.VERIFY_CODE_STATUS_VALID);
        customerVerifyCode.setUpdatedDatetime(currentTime);
        customerVerifyCodeDao.updateCustomerVerifyCode(customerVerifyCode);

        return true;
    }
}
