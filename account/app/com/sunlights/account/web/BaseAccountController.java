package com.sunlights.account.web;


import com.sunlights.account.service.AccountService;
import com.sunlights.account.service.impl.AccountServiceImpl;
import com.sunlights.common.AppConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.service.VerifyCodeService;
import com.sunlights.common.utils.MessageUtil;
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

    private AccountService accountService = new AccountServiceImpl();

    private CustomerService customerService = new CustomerService();

    private VerifyCodeService verifyCodeService = new VerifyCodeService();

    /**
     * 验证交易密码设置/修改
     *
     * @return
     */
    public Result resetAccountPwdCertify() {
        Logger.info("=================resetTradePwdCertify==========");
        CustomerFormVo customerFormVo = this.customerFormVo.bindFromRequest().get();
        accountService.resetTradePwdCertify(customerFormVo);

        CustomerVerifyCodeVo customerVerifyCodeVo = new CustomerVerifyCodeVo();
        customerVerifyCodeVo.setMobile(customerFormVo.getMobilePhoneNo());
        customerVerifyCodeVo.setVerifyType(AppConst.VERIFY_CODE_RESET_ACCOUNT);
        customerVerifyCodeVo.setDeviceNo(customerFormVo.getDeviceNo());
        customerVerifyCodeVo.setVerifyCode(customerFormVo.getVerifyCode());
        boolean success = verifyCodeService.validateVerifyCode(customerVerifyCodeVo);
        if (success) {
            MessageUtil.getInstance().setMessage(new Message(MsgCode.OPERATE_SUCCESS));
        }

        return ok(MessageUtil.getInstance().toJson());
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
        accountService.resetTradePwd(customerFormVo, token);

        CustomerVo customerVo = customerService.getCustomerVoByPhoneNo(customerFormVo.getMobilePhoneNo(), customerFormVo.getDeviceNo());
        MessageVo<CustomerVo> messageVo = new MessageVo<>(new Message(MsgCode.TRAD_PASSWORD_RESET_SUCCESS));
        messageVo.setValue(customerVo);

        return ok(Json.toJson(messageVo));
    }


}
