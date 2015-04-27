package com.sunlights.core.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import com.sunlights.account.service.AccountService;
import com.sunlights.account.service.impl.AccountServiceImpl;
import com.sunlights.common.AppConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.service.VerifyCodeService;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.MessageHeaderVo;
import com.sunlights.common.vo.MessageVo;
import com.sunlights.core.service.impl.IdentityService;
import com.sunlights.core.service.impl.SmsMessageService;
import com.sunlights.customer.action.MsgCenterAction;
import com.sunlights.customer.service.impl.CustomerService;
import com.sunlights.customer.vo.CustomerFormVo;
import com.sunlights.customer.vo.CustomerVo;
import models.CustomerSession;
import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.With;

import java.util.List;

import static play.data.Form.form;

/**
 * <p>Project: fsp</p>
 * <p>Title: SafeFacade.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */


@Transactional
public class SecurityController extends Controller {
    private static Form<CustomerFormVo> customerForm = Form.form(CustomerFormVo.class);

    private CustomerService customerService = new CustomerService();
    private AccountService accountService = new AccountServiceImpl();

    private VerifyCodeService verifyCodeService = new VerifyCodeService();
    private IdentityService identityService = new IdentityService();
    private SmsMessageService smsMessageService = new SmsMessageService();

    /**
     * <p>
     * Description: 获取验证码
     * </p>
     *
     * @return
     */
    @With(MsgCenterAction.class)
    public Result genVerificationCode() {
        Logger.info("===========genVerificationCode==================");
        Logger.debug(">>genVerificationCode params：" + Json.toJson(form().bindFromRequest().data()));
        CustomerFormVo vo = customerForm.bindFromRequest().get();

        String mobilePhoneNo = vo.getMobilePhoneNo();
        String verifyType = vo.getType();
        String deviceNo = vo.getDeviceNo();

        String verifyCode = verifyCodeService.genVerificationCode(mobilePhoneNo, verifyType, deviceNo);

        MessageHeaderVo messageHeaderVo = smsMessageService.buildSmsParams(mobilePhoneNo, verifyCode, verifyType);

        MessageUtil.getInstance().setMessage(new Message(MsgCode.OPERATE_SUCCESS));
        Controller.response().setHeader("Access-Control-Allow-Origin", "*");
        Logger.debug(">>genVerificationCode return：" + MessageUtil.getInstance().toJson());

        List<MessageHeaderVo> list = Lists.newArrayList();
        list.add(messageHeaderVo);
        Controller.response().setHeader(AppConst.HEADER_MSG, MessageUtil.getInstance().setMessageHeader(list));

        return Controller.ok(MessageUtil.getInstance().toJson());
    }

    /**
     * 实名认证
     *
     * @return
     */
    public Result certify() {
        Logger.info("=================ceritify=========");
        Http.Cookie cookie = Controller.request().cookie(AppConst.TOKEN);
        String token = cookie == null ? null : cookie.value();
        Logger.info("=========token:" + token);

        CustomerFormVo customerFormVo = customerForm.bindFromRequest().get();
        CustomerSession customerSession = identityService.certify(customerFormVo, token, Controller.request().remoteAddress());
        customerService.sessionLoginSessionId(Controller.session(), Controller.response(), customerSession);

        JsonNode json = MessageUtil.getInstance().toJson();
        Logger.info("=================ceritify返回==========" + json.toString());
        return Controller.ok(Json.toJson(json));
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
        CustomerFormVo customerFormVo = customerForm.bindFromRequest().get();

        identityService.certify(customerFormVo, token, request.remoteAddress());
        accountService.resetTradePwd(customerFormVo, token);

        CustomerVo customerVo = customerService.getCustomerVoByIdCardNo(customerFormVo.getIdCardNo(), customerFormVo.getUserName());
        MessageVo<CustomerVo> messageVo = new MessageVo<>(new Message(MsgCode.OPERATE_SUCCESS));
        messageVo.setValue(customerVo);
        return ok(Json.toJson(messageVo));
    }


}
