package com.sunlights.account.web;


import com.sunlights.account.service.CustAccountService;
import com.sunlights.account.service.impl.CustAccountServiceImpl;
import com.sunlights.common.AppConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.service.VerifyCodeService;
import com.sunlights.common.vo.CustomerVerifyCodeVo;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.MessageVo;
import com.sunlights.customer.service.impl.CustomerService;
import com.sunlights.customer.vo.CustomerFormVo;
import com.sunlights.customer.vo.CustomerVo;
import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.Map;


/**
 * <p>Project: fsp</p>
 * <p>Title: WebBaseAccountService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Transactional
public class BaseAccountController extends Controller {
    private Form<CustomerFormVo> customerFormVo = Form.form(CustomerFormVo.class);

    private CustAccountService custAccountService = new CustAccountServiceImpl();

    private CustomerService customerService = new CustomerService();

    private VerifyCodeService verifyCodeService = new VerifyCodeService();

    public Result createBaseAccount(){
        Map<String, String> params = Form.form().bindFromRequest().data();

        String custId = params.get("custId");
        String tradePassword = params.get("tradePassword");
        custAccountService.registerBaseAccount(custId, tradePassword);

        MessageVo<CustomerVo> messageVo = new MessageVo<>(new Message(MsgCode.TRAD_PASSWORD_RESET_SUCCESS));
        return ok(Json.toJson(messageVo));
    }

    /**
     * 验证交易密码设置/修改
     *
     * @return
     */
    public Result resetAccountPwdCertify() {
        Logger.info("=================resetTradePwdCertify==========");
        CustomerFormVo customerFormVo = this.customerFormVo.bindFromRequest().get();
        custAccountService.resetTradePwdCertify(customerFormVo);

        CustomerVerifyCodeVo customerVerifyCodeVo = new CustomerVerifyCodeVo();
        customerVerifyCodeVo.setMobile(customerFormVo.getMobilePhoneNo());
        customerVerifyCodeVo.setVerifyType(AppConst.VERIFY_CODE_RESET_ACCOUNT);
        customerVerifyCodeVo.setDeviceNo(customerFormVo.getDeviceNo());
        customerVerifyCodeVo.setVerifyCode(customerFormVo.getVerifyCode());
        MsgCode msgCode = verifyCodeService.validateVerifyCode(customerVerifyCodeVo);
        MessageVo messageVo = new MessageVo(new Message(msgCode));

        return ok(Json.toJson(messageVo));
    }

    /**
     * 交易密码设置/修改
     *
     * @return
     */
    public Result resetAccountPwd() {
        Logger.info("=================resetTradePwd==========");
        Http.Cookie cookie = Controller.request().cookie(AppConst.TOKEN);
        String token = cookie == null ? null : cookie.value();
        Logger.info("=========token:" + token);

        CustomerFormVo customerFormVo = this.customerFormVo.bindFromRequest().get();
        custAccountService.resetTradePwd(customerFormVo, token);

        CustomerVo customerVo = customerService.getCustomerVoByPhoneNo(customerFormVo.getMobilePhoneNo(), customerFormVo.getDeviceNo());
        MessageVo<CustomerVo> messageVo = new MessageVo<>(new Message(MsgCode.TRAD_PASSWORD_RESET_SUCCESS));
        messageVo.setValue(customerVo);

        return ok(Json.toJson(messageVo));
    }

    /**
     * 身份实名认证和设置交易密码
     *
     * @return
     */
    public Result certifyAndResetTradePwd() {
        Logger.info("===========certifyAndResetTradePwd start=====");
        Http.Request request = Controller.request();
        Http.Cookie cookie = request.cookie(AppConst.TOKEN);
        String token = cookie == null ? null : cookie.value();
        Logger.info("=========token:" + token);
        CustomerFormVo customerFormVo = this.customerFormVo.bindFromRequest().get();

        customerService.certify(customerFormVo, token, request.remoteAddress());//TODO
        custAccountService.resetTradePwd(customerFormVo, token);

        CustomerVo customerVo = customerService.getCustomerVoByIdCardNo(customerFormVo.getIdCardNo(), customerFormVo.getUserName());
        MessageVo<CustomerVo> messageVo = new MessageVo<>(new Message(MsgCode.OPERATE_SUCCESS));
        messageVo.setValue(customerVo);
        return ok(Json.toJson(messageVo));
    }

}
