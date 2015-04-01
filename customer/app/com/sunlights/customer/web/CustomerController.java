package com.sunlights.customer.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import com.sunlights.common.AppConst;
import com.sunlights.common.DictConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
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
import com.sunlights.customer.vo.JudjeTokenVo;
import com.wordnik.swagger.annotations.*;
import models.Customer;
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
import java.util.Map;

import static play.data.Form.form;

/**
 * Created by Administrator on 2014/9/4.
 */
@Transactional
@Api(value = "/customer", description = "登录注册等服务")
public class CustomerController extends Controller {

    private Form<CustomerFormVo> customerForm = form(CustomerFormVo.class);
    private LoginService loginService = new LoginServiceImpl();
    private CustomerService customerService = new CustomerService();

    protected MessageUtil messageUtil = MessageUtil.getInstance();

    @ApiOperation(value = "查询手机号是否已注册",
            notes = "MessageVo 的value是CustomerVo对象", response = MessageVo.class, nickname = "getusermstr", httpMethod = "POST")
    @ApiImplicitParams({@ApiImplicitParam(name = "mobilePhoneNo", required = true, paramType = "form"),
            @ApiImplicitParam(name = "deviceNo", paramType = "form")})
    @ApiResponses(value = {@ApiResponse(code = 2100, message = "该手机号未注册"),
            @ApiResponse(code = 0000, message = "操作成功", response = CustomerVo.class)
    })
    public Result getCustomerByMobilePhoneNo() {
        Logger.debug(">>getCustomerByMobilePhoneNo params：" + Json.toJson(form().bindFromRequest().data()));
        CustomerFormVo customerFormVo = customerForm.bindFromRequest().get();

        String mobilePhoneNo = customerFormVo.getMobilePhoneNo();
        String deviceNo = customerFormVo.getDeviceNo();

        CustomerVo customerVo = customerService.getCustomerVoByPhoneNo(mobilePhoneNo, deviceNo);
        Message message = new Message(MsgCode.OPERATE_SUCCESS);
        if (customerVo == null) {
            message = new Message(MsgCode.PHONE_NUMBER_NOT_REGISTRY);
        }

        MessageVo<CustomerVo> messageVo = new MessageVo<CustomerVo>(message);
        messageVo.setValue(customerVo);
        Logger.debug(">>getCustomerByMobilePhoneNo return：" + messageVo.toJson());
        return Controller.ok(messageVo.toJson());
    }

    /**
     * <pre>
     * 请求URL： /customer/login
     * 请求方法： POST
     * 输入参数：mobilePhoneNo、passWord、deviceNo(设备号)
     *
     * 输出参数：
     * 成功：{"message":{"severity":0,"code":"0101","summary":"登录成功","detail":"登录成功","fileds":{}},"value":{"userName":null,"nickName":null,"mobilePhoneNo":"15811111175","mobileDisplayNo":"1581****1175","email":null,"gestureOpened":"0","certify":"0","idCardNo":null,"bankCardCount":"0","tradePwdFlag":"0"}}
     * 失败：{"message":{"severity":2,"code":"2107","summary":"密码错误","detail":"剩余次数4","fileds":{}},"value":null}
     * ---------------------------------------------------------------
     * <b>severity	code	                summary	detail</b>
     * 0101	    登录成功
     * 2001	    访问失败	            参数为空
     * 2002	    登录超时	            请重新登录
     * 2100	    该手机号未注册
     * 2106	    密码错误次数过多	    约xx分后再试
     * 2107	    密码错误	            剩余次数xx
     * ---------------------------------------------------------------
     * </pre>
     *
     * @return
     */
    @ApiOperation(value = "登录",
            notes = "MessageVo 的value是CustomerVo对象", response = MessageVo.class, nickname = "login", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobilePhoneNo", required = true, paramType = "form"),
            @ApiImplicitParam(name = "passWord", required = true, paramType = "form"),
            @ApiImplicitParam(name = "channel", value = "若为1时表示pc端登录,deviceNo可不填；反之为app登录，deviceNo必填", required = true, paramType = "form"),
            @ApiImplicitParam(name = "deviceNo", value = "设备号", paramType = "form")
    })
    @ApiResponses(value = {@ApiResponse(code = 2001, message = "访问失败 参数为空"),
            @ApiResponse(code = 2002, message = "登录超时 请重新登录"),
            @ApiResponse(code = 2100, message = "该手机号未注册"),
            @ApiResponse(code = 2106, message = "密码错误次数过多 约xx分后再试"),
            @ApiResponse(code = 2107, message = "密码错误 剩余次数xx"),
            @ApiResponse(code = 0101, message = "登录成功", response = CustomerVo.class)
    })
    @With(MsgCenterAction.class)
    public Result login() {
        Logger.info("==========login====================");

        Map<String, String> params = form().bindFromRequest().data();
        Logger.debug(">>login params:" + Json.toJson(params));

        CustomerFormVo customerFormVo = customerForm.bindFromRequest().get();
        String mobilePhoneNo = customerFormVo.getMobilePhoneNo();
        String deviceNo = customerFormVo.getDeviceNo();

        final Http.Request request = Controller.request();
        Http.Cookie cookie = request.cookie(AppConst.TOKEN);
        String token = cookie == null ? null : cookie.value();
        List<MessageHeaderVo> list = Lists.newArrayList();

        CustomerSession customerSession = loginService.login(customerFormVo, token, request.remoteAddress());
        if (customerSession != null){
            customerService.sessionLoginSessionId(Controller.session(), Controller.response(), customerSession);
            if (!AppConst.CHANNEL_PC.equals(customerFormVo.getChannel())) {
                Message message = new Message(MsgCode.LOGIN_SUCCESS);
                CustomerVo customerVo = customerService.getCustomerVoByPhoneNo(mobilePhoneNo, deviceNo);
                MessageUtil.getInstance().setMessage(message, customerVo);
                customerService.sessionPushRegId(request(), customerSession.getCustomerId(), customerFormVo.getDeviceNo());
                MessageHeaderVo messageHeaderVo = new MessageHeaderVo(DictConst.PUSH_TYPE_4, null, customerSession.getCustomerId());
                list.add(messageHeaderVo);
                response().setHeader(AppConst.HEADER_MSG, MessageUtil.getInstance().setMessageHeader(list));
            } else if (AppConst.CHANNEL_PC.equals(customerFormVo.getChannel())) {
                Message message = new Message(MsgCode.LOGIN_SUCCESS);
                //CustomerVo customerVo = customerService.getCustomerVoByPhoneNo(mobilePhoneNo, deviceNo);
                MessageUtil.getInstance().setMessage(message);
            }
        }

        JsonNode json = MessageUtil.getInstance().toJson();
        Logger.info(">>login返回：" + json.toString());
        return Controller.ok(json);
    }

    /**
     * 忘记密码验证码校对
     *
     * @return
     */
    public Result resetpwdCertify() {
        Logger.info("==============resetpwdCertify开始==========");
        Map<String, String> params = form().bindFromRequest().data();
        Logger.debug(">>resetpwdCertify params：" + Json.toJson(params));

        CustomerFormVo customerFormVo = customerForm.bindFromRequest().get();
        if (loginService.resetpwdCertify(customerFormVo)) {
            MessageUtil.getInstance().setMessage(new Message(MsgCode.OPERATE_SUCCESS));
        }
        Logger.info("==============resetpwdCertify结束==========");
        Logger.debug(">>login返回：" + MessageUtil.getInstance().toJson());
        return Controller.ok(MessageUtil.getInstance().toJson());
    }

    /**
     * 重置密码
     *
     * @return
     */
    public Result resetpwd() {
        Logger.info("================resetpwd==============");
        Map<String, String> params = form().bindFromRequest().data();
        Logger.debug(">>resetpwd params：" + Json.toJson(params));

        CustomerFormVo customerFormVo = customerForm.bindFromRequest().get();

        Customer customer = loginService.resetPwd(customerFormVo);

        if (!AppConst.CHANNEL_PC.equals(customerFormVo.getChannel())) {

            Http.Cookie cookie = Controller.request().cookie(AppConst.TOKEN);
            String token = cookie == null ? null : cookie.value();
            CustomerSession userSession = customerService.getCustomerSession(token);
            if (userSession == null) {// 若为未登录操作重置密码，则自动登录
                Logger.info("===============重置密码之后自动登录===========");
                // 自动登录
                loginService.saveLoginHistory(customer, customerFormVo);
                userSession = customerService.createCustomerSession(customer, Controller.request().remoteAddress(), customerFormVo.getDeviceNo());
                customerService.sessionPushRegId(request(), userSession.getCustomerId(), customerFormVo.getDeviceNo());
            }
            customerService.sessionLoginSessionId(Controller.session(), Controller.response(), userSession);
        }

        JsonNode json = MessageUtil.getInstance().toJson();
        Logger.info("================resetpwd返回信息：" + json.toString());
        return Controller.ok(json);
    }

    /**
     * 退出
     *
     * @return
     */
    public Result logout() {
        Logger.info("==========logout====================");
        Logger.debug(">>logout params：" + Json.toJson(form().bindFromRequest().data()));
        Http.Cookie cookie = Controller.request().cookie(AppConst.TOKEN);
        String token = cookie == null ? null : cookie.value();

        Logger.info(">>token：" + token);
        CustomerFormVo customerFormVo = customerForm.bindFromRequest().get();
        String mobilePhoneNo = customerFormVo.getMobilePhoneNo();
        String deviceNo = customerFormVo.getDeviceNo();
        loginService.logout(mobilePhoneNo, deviceNo, token);

        MessageVo messageVo = new MessageVo(new Message(MsgCode.LOGOUT_SUCCESS));
        Logger.info(">>logout return： " + messageVo.toJson());
        return Controller.ok(messageVo.toJson());
    }

    /**
     * 手势登录
     *
     * @return
     */
    @With(MsgCenterAction.class)
    public Result loginByges() {
        Logger.info("==========loginByGesture====================");
        Map<String, String> params = form().bindFromRequest().data();
        Logger.debug(">>loginByges params：" + Json.toJson(params));

        CustomerFormVo customerFormVo = customerForm.bindFromRequest().get();
        String mobilePhoneNo = customerFormVo.getMobilePhoneNo();
        String deviceNo = customerFormVo.getDeviceNo();

        Http.Cookie cookie = Controller.request().cookie(AppConst.TOKEN);
        String token = cookie == null ? null : cookie.value();
        List<MessageHeaderVo> list = Lists.newArrayList();

        CustomerSession customerSession = loginService.loginByGesture(customerFormVo, token, Controller.request().remoteAddress());

        if (customerSession != null) {
            Message message = new Message(MsgCode.LOGIN_SUCCESS);
            CustomerVo customerVo = customerService.getCustomerVoByPhoneNo(mobilePhoneNo, deviceNo);
            MessageUtil.getInstance().setMessage(message, customerVo);
            customerService.sessionLoginSessionId(Controller.session(), Controller.response(), customerSession);
            customerService.sessionPushRegId(request(), customerSession.getCustomerId(), customerFormVo.getDeviceNo());
            MessageHeaderVo messageHeaderVo = new MessageHeaderVo(DictConst.PUSH_TYPE_4, null, customerSession.getCustomerId());
            list.add(messageHeaderVo);
        }

        JsonNode json = MessageUtil.getInstance().toJson();
        Logger.info("==========loginByges返回：" + json.toString());


        response().setHeader(AppConst.HEADER_MSG, MessageUtil.getInstance().setMessageHeader(list));

        return Controller.ok(json);
    }

    /**
     * 密码确认
     *
     * @return
     */
    public Result confirmPwd() {
        Map<String, String> params = form().bindFromRequest().data();
        Logger.debug(">>confirmPwd params：" + Json.toJson(params));

        CustomerFormVo customerFormVo = customerForm.bindFromRequest().get();
        String mobilePhoneNo = customerFormVo.getMobilePhoneNo();
        String passWord = customerFormVo.getPassWord();
        loginService.confirmPwd(mobilePhoneNo, passWord);

        Message message = new Message(MsgCode.OPERATE_SUCCESS);
        JsonNode jsonNode = MessageUtil.getInstance().msgToJson(message);
        Logger.info("==========confirmPwd return：" + jsonNode.toString());
        return Controller.ok(jsonNode);
    }

    /**
     * 保存手势密码
     *
     * @return
     */
    public Result saveGesturePwd() {
        Logger.info("========saveGesturePwd================");
        Logger.debug(">>saveGesturePwd params：" + Json.toJson(form().bindFromRequest().data()));
        customerService.validateCustomerSession(Controller.request(), Controller.session(), Controller.response());
        CustomerFormVo vo = customerForm.bindFromRequest().get();
        CustomerVo customerVo = loginService.saveGesturePwd(vo);
        MessageVo<CustomerVo> messageVo = new MessageVo<>(new Message(MsgCode.GESTURE_PASSWORD_SUCCESS));
        messageVo.setValue(customerVo);
        Logger.debug(">>> saveGesturePwd return： " + messageVo.toJson());
        return Controller.ok(messageVo.toJson());
    }

    public Result getToken() {
        Http.Cookie cookie = request().cookie(AppConst.TOKEN);
        String token = cookie == null ? null : cookie.value();
        try {
            customerService.validateCustomerSession(request(), session(), response());
        } catch (Exception e) {
            Logger.error("还没登陆");
            token = null;
        }
        Logger.debug("token = " + token);
        JudjeTokenVo judjeTokenVo = new JudjeTokenVo();
        if (token == null) {
            judjeTokenVo.setExistToken(false);
        } else {
            judjeTokenVo.setExistToken(true);
        }
        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), judjeTokenVo);
        Controller.response().setHeader("Access-Control-Allow-Origin", "*");
        JsonNode jsonNode = messageUtil.toJson();
        Logger.debug("jsonNode = " + jsonNode);
        return ok(jsonNode);
    }


    /**
     * 获取注册人数(提供给活动中的h5展示的，不是真正的注册用户数
     *
     *
     * @return
     */
    @ApiOperation(value = "获取注册人数(提供给活动中的h5展示的，不是真正的注册用户数",
            notes = "MessageVo 的value是CustomerVo对象", response = MessageVo.class, nickname = "getRegisterNumbers4Activity", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobilePhoneNo", required = true, paramType = "form"),
            @ApiImplicitParam(name = "passWord", required = true, paramType = "form"),
            @ApiImplicitParam(name = "channel", required = true, paramType = "form")
    })
    @ApiResponses(value = {@ApiResponse(code = 2001, message = "访问失败 参数为空"),
            @ApiResponse(code = 2002, message = "登录超时 请重新登录"),
            @ApiResponse(code = 2100, message = "该手机号未注册"),
            @ApiResponse(code = 2106, message = "密码错误次数过多 约xx分后再试"),
            @ApiResponse(code = 2107, message = "密码错误 剩余次数xx"),
            @ApiResponse(code = 0101, message = "登录成功", response = CustomerVo.class)
    })
    public Result getRegisterNumbers4Activity() {

        return ok(messageUtil.toJson());
    }

}
