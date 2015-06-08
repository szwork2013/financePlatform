package com.sunlights.core.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import com.sunlights.account.service.AccountService;
import com.sunlights.account.service.impl.AccountServiceImpl;
import com.sunlights.common.AppConst;
import com.sunlights.common.DictConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.MessageHeaderVo;
import com.sunlights.common.vo.MessageVo;
import com.sunlights.customer.action.MsgCenterAction;
import com.sunlights.customer.service.LoginService;
import com.sunlights.customer.service.impl.CustomerService;
import com.sunlights.customer.service.impl.LoginServiceImpl;
import com.sunlights.customer.vo.CustomerFormVo;
import com.sunlights.customer.vo.CustomerVo;
import com.wordnik.swagger.annotations.*;
import models.Customer;
import models.CustomerSession;
import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;

import java.util.List;

import static play.data.Form.form;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: RegisterController.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Transactional
@Api(value = "/core", description = "注册服务")
public class RegisterController extends Controller {

    private Form<CustomerFormVo> customerForm = Form.form(CustomerFormVo.class);
    private LoginService loginService = new LoginServiceImpl();
    private AccountService accountService = new AccountServiceImpl();
    private CustomerService customerService = new CustomerService();


    /**
     * 注册客户
     * <p/>
     * 请求URL： /customer/register
     * 请求方法： POST
     * <pre>
     * 输入参数：mobilePhoneNo、nickName 、passWord、idCardNo、verifyCode、deviceNo
     * 输出参数：
     * 成功：{"message":{"severity":0,"code":"0100","summary":"注册成功","detail":"","fileds":{}},"value":{"userName":null,"nickName":null,"mobilePhoneNo":"15811111175","mobileDisplayNo":"1581****1175","email":null,"gestureOpened":"0","certify":"0","idCardNo":null,"bankCardCount":"0","tradePwdFlag":"0"}}
     * 失败：{"message":{"severity":2,"code":"2103","summary":"验证码不正确","detail":"请重新输入","fileds":{}},"value":null}
     *
     * severity	code	summary	                detail
     * 0100	            注册成功
     * 2001	            访问失败	          参数为空
     * 2101	            该手机号已注册
     * 2103	            验证码不正确	      请重新输入
     * 2104	            验证码失效	         请重新获取
     * 2105	            未获取验证码	      请获取验证码
     * 2102	            验证码超过最大次数
     * C
     * </p>
     */
    @ApiOperation(value = "用户注册",
            notes = "MessageVo 的value是CustomerVo对象", response = MessageVo.class, nickname = "register", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobilePhoneNo", required = true, paramType = "form"),
            @ApiImplicitParam(name = "passWord", required = true, paramType = "form"),
            @ApiImplicitParam(name = "deviceNo", paramType = "form"),
            @ApiImplicitParam(name = "verifyCode", paramType = "form"),
            @ApiImplicitParam(name = "channel", required = true, paramType = "form", defaultValue = "0", allowableValues = "0,1,2")})
    @ApiResponses(value = {@ApiResponse(code = 0100, message = "注册成功", response = CustomerVo.class),
            @ApiResponse(code = 2001, message = "访问失败,参数为空"),
            @ApiResponse(code = 2101, message = "该手机号已注册"),
            @ApiResponse(code = 2103, message = "验证码不正确,请重新输入"),
            @ApiResponse(code = 2104, message = "验证码失效,请重新获取"),
            @ApiResponse(code = 2105, message = "未获取验证码,请获取验证码")
    })
    @With(MsgCenterAction.class)
    public Result register() {
        Logger.info("==========register====================");
        Logger.debug(">>register params：" + Json.toJson(form().bindFromRequest().data()));
        CustomerFormVo customerFormVo = customerForm.bindFromRequest().get();
        String deviceNo = customerFormVo.getDeviceNo();

        restChannelByAppPlatform(customerFormVo);
        Customer customer = loginService.register(customerFormVo);

        List<MessageHeaderVo> list = Lists.newArrayList();
        if (customer != null) {
            accountService.createBaseAccount(customer.getCustomerId(), null);
            CustomerSession customerSession = customerService.createCustomerSession(customer, Controller.request().remoteAddress(), deviceNo);
            customerService.sessionLoginSessionId(Controller.session(), Controller.response(), customerSession);

            Message message = new Message(MsgCode.REGISTRY_SUCCESS);
            CustomerVo customerVo = null;

            if (CommonUtil.fromApp(customerFormVo.getChannel())) {
                customerService.sessionPushRegId(request(), customerSession.getCustomerId(), deviceNo);
                customerVo = customerService.getCustomerVoByPhoneNo(customer.getMobile(), deviceNo);
            } else {
                customerVo = customerService.getCustomerVoByAuthenticationMobile(customerFormVo.getMobilePhoneNo());
            }

            MessageUtil.getInstance().setMessage(message, customerVo);

            MessageHeaderVo messageHeaderVo = new MessageHeaderVo(DictConst.PUSH_TYPE_4, null, customer.getCustomerId());
            list.add(messageHeaderVo);
        }

        Controller.response().setHeader("Access-Control-Allow-Origin", "*");
        Controller.response().setHeader(AppConst.HEADER_MSG, MessageUtil.getInstance().setMessageHeader(list));
        Logger.info("register end end>> " + DBHelper.getCurrentTime());
        JsonNode json = MessageUtil.getInstance().toJson();
        Logger.debug(">>register return：" + json.toString());
        return Controller.ok(json);
    }

    private void restChannelByAppPlatform(CustomerFormVo customerFormVo) {
        if(AppConst.CHANNEL_PC.equals(customerFormVo.getChannel()) || AppConst.CHANNEL_H5.equals(customerFormVo.getChannel())) {
            return;
        }
        String platform = CommonUtil.getCurrentPlatform(request());
        if (AppConst.PLATFORM_IOS.equals(platform)) {
            customerFormVo.setChannel(AppConst.CHANNEL_IOS);
        } else if((AppConst.PLATFORM_ANDROID.equals(platform))) {
            customerFormVo.setChannel(AppConst.CHANNEL_ANDROID);
        }

    }


}
