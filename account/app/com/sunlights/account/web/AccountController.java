package com.sunlights.account.web;


import com.fasterxml.jackson.databind.JsonNode;
import com.sunlights.account.biz.AccountService;
import com.sunlights.common.AppConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.dal.impl.VerifyCodeService;
import com.sunlights.common.utils.msg.Message;
import com.sunlights.common.utils.msg.MessageUtil;
import com.sunlights.common.vo.CustomerVerifyCodeVo;
import com.sunlights.customer.service.impl.CustomerService;
import com.sunlights.customer.vo.CustomerFormVo;
import com.sunlights.customer.vo.CustomerVo;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

/**
 * <p>Project: fsp</p>
 * <p>Title: WebBaseAccountService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class AccountController {
    private Form<CustomerFormVo> customerForm = Form.form(CustomerFormVo.class);

    private AccountService accountService;

    private CustomerService customerService;


    private VerifyCodeService verifyCodeService;
    
    /**
     * 验证交易密码设置/修改
     * @return
     */
    public Result resetAccountPwdCertify(){
        Logger.info("=================resetTradePwdCertify==========");
        CustomerFormVo customerFormVo = customerForm.bindFromRequest().get();
        CustomerVerifyCodeVo customerVerifyCodeVo = accountService.resetTradePwdCertify(customerFormVo);
        if (verifyCodeService.validateVerifyCode(customerVerifyCodeVo)) {
            Message message = new Message(MsgCode.OPERATE_SUCCESS);
            MessageUtil.getInstance().addMessage(message);
        }
        JsonNode json = MessageUtil.getInstance().toJson();
        Logger.info("=================resetTradePwdCertify==========");
        return Controller.ok(json);
    }

    /**
     * 交易密码设置/修改
     * @return
     */
    public Result resetAccountPwd(){
        Logger.info("=================resetTradePwd==========");
        Http.Cookie cookie = Controller.request().cookie(AppConst.TOKEN);
        String token = cookie == null ? null : cookie.value();
        Logger.info("=========token:" + token);

        CustomerFormVo customerFormVo = customerForm.bindFromRequest().get();
        accountService.resetTradePwd(customerFormVo, token);

        CustomerVo customerVo = customerService.getCustomerVoByPhoneNo(customerFormVo.getMobilePhoneNo(), customerFormVo.getDeviceNo());
        MessageUtil.getInstance().addMessage(new Message(MsgCode.TRAD_PASSWORD_RESET_SUCCESS), customerVo);

        Logger.info("=================resetAccountPwd结束==========");

        return Controller.ok(MessageUtil.getInstance().toJson());
    }

    /**
     * 身份实名认证和设置交易密码
     * @return
     */
    public Result certifyAndResetTradePwd(){
        Logger.info("===========certifyAndResetTradePwd start=====");
        Http.Request request = Controller.request();
        Http.Cookie cookie = request.cookie(AppConst.TOKEN);
        String token = cookie == null ? null : cookie.value();
        Logger.info("=========token:" + token);
        CustomerFormVo customerFormVo = customerForm.bindFromRequest().get();

        customerService.certify(customerFormVo, token, request.remoteAddress());
        accountService.resetTradePwd(customerFormVo, token);

        CustomerVo customerVo = customerService.getCustomerVoByIdCardNo(customerFormVo.getIdCardNo(), customerFormVo.getUserName());
        MessageUtil.getInstance().addMessage(new Message(MsgCode.OPERATE_SUCCESS), customerVo);

        JsonNode json = MessageUtil.getInstance().toJson();
        Logger.info("===========certifyAndResetTradePwd end===" + json);
        return Controller.ok(json);
    }

}
