package com.sunlights.customer.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.sunlights.common.AppConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.service.VerifyCodeService;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.MessageVo;
import com.sunlights.customer.models.Customer;
import com.sunlights.customer.models.CustomerSession;
import com.sunlights.customer.service.LoginService;
import com.sunlights.customer.service.impl.CustomerService;
import com.sunlights.customer.service.impl.LoginServiceImpl;
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
 * Created by Administrator on 2014/9/4.
 */
@Transactional
public class CustomerController extends Controller {
	private Form<CustomerFormVo> customerForm = Form.form(CustomerFormVo.class);

    private LoginService loginService = new LoginServiceImpl();

    private CustomerService customerService = new CustomerService();

    private VerifyCodeService verifyCodeService = new VerifyCodeService();

    /**
     * 查询手机号是否已注册
     * @return
     */
    public Result getCustomerByMobilePhoneNo() {
        Logger.info("===========getCustomerByMobilePhoneNo=====");
        CustomerFormVo customerFormVo = customerForm.bindFromRequest().get();

        String mobilePhoneNo = customerFormVo.getMobilePhoneNo();
        String deviceNo = customerFormVo.getDeviceNo();

        CustomerVo customerVo = customerService.getCustomerVoByPhoneNo(mobilePhoneNo, deviceNo);
        Message message = new Message(MsgCode.OPERATE_SUCCESS);
        if (customerVo == null) {
            message = new Message(MsgCode.PHONE_NUMBER_NOT_REGISTRY);
        }

        MessageVo<CustomerVo> messageVo = new MessageVo<>(message);
        return Controller.ok(messageVo.toJson());
    }

	/**
	 * 注册
	 * 
	 * @return
	 */
	public Result register() {
        Logger.info("==========register====================");
        CustomerFormVo customerFormVo = customerForm.bindFromRequest().get();
		Customer customer = loginService.register(customerFormVo);

        if (customer != null) {
            CustomerSession customerSession = customerService.createCustomerSession(customer, Controller.request().remoteAddress());
            customerService.sessionLoginSessionId(Controller.session(), Controller.response(), customerSession);
        }

        JsonNode json = MessageUtil.getInstance().toJson();

        Logger.info("==========register返回：" + json.toString());
		return Controller.ok(json);
	}

	/**
	 * 登录
	 * @return
	 */
	public Result login() {
		Logger.info("==========login====================");
		CustomerFormVo customerFormVo = customerForm.bindFromRequest().get();
        String mobilePhoneNo = customerFormVo.getMobilePhoneNo();
        String deviceNo = customerFormVo.getDeviceNo();

		Http.Cookie cookie = Controller.request().cookie(AppConst.TOKEN);
		String token = cookie == null ? null : cookie.value();
		
		CustomerSession customerSession = loginService.login(customerFormVo, token, Controller.request().remoteAddress());
		if (customerSession != null) {
            Message message = new Message(MsgCode.LOGIN_SUCCESS);
            CustomerVo customerVo = customerService.getCustomerVoByPhoneNo(mobilePhoneNo, deviceNo);
            MessageUtil.getInstance().setMessage(message, customerVo);
            customerService.sessionLoginSessionId(Controller.session(), Controller.response(), customerSession);
        }
		JsonNode json = MessageUtil.getInstance().toJson();
		Logger.info("==========login返回：" + json.toString());
		return Controller.ok(json);
	}

	/**
	 * 忘记密码验证码校对
	 * 
	 * @return
	 */
	public Result resetpwdCertify() {
        Logger.info("==============resetpwdCertify开始==========");
        CustomerFormVo customerFormVo = customerForm.bindFromRequest().get();
        if (loginService.resetpwdCertify(customerFormVo)) {
            MessageUtil.getInstance().setMessage(new Message(MsgCode.OPERATE_SUCCESS));
        }
        Logger.info("==============resetpwdCertify结束==========");
        return Controller.ok(MessageUtil.getInstance().toJson());
	}

	/**
	 * 重置密码
	 * @return
	 */
	public Result resetpwd() {
		Logger.info("================resetpwd==============");
		CustomerFormVo customerFormVo = customerForm.bindFromRequest().get();

        String mobilePhoneNo = customerFormVo.getMobilePhoneNo();
        String deviceNo = customerFormVo.getDeviceNo();
        String passWord = customerFormVo.getPassWord();

		Customer customer = loginService.resetpwd(mobilePhoneNo, passWord, deviceNo);

		Http.Cookie cookie = Controller.request().cookie(AppConst.TOKEN);
		String token = cookie == null ? null : cookie.value();
		CustomerSession userSession = customerService.getCustomerSession(token);
		if (userSession == null) {// 若为未登录操作重置密码，则自动登录
			Logger.info("===============重置密码之后自动登录===========");
			// 自动登录
			loginService.saveLoginHistory(customer, deviceNo);
			userSession = customerService.createCustomerSession(customer, Controller.request().remoteAddress());
		}
        customerService.sessionLoginSessionId(Controller.session(), Controller.response(), userSession);

        JsonNode json = MessageUtil.getInstance().toJson();
        Logger.info("================resetpwd返回信息：" + json.toString());
		return Controller.ok(json);
	}

	/**
	 * 退出
	 * @return
	 */
	public Result logout() {
        Logger.info("==========logout====================");

        Http.Cookie cookie = Controller.request().cookie(AppConst.TOKEN);
		String token = cookie == null ? null : cookie.value();
        CustomerFormVo customerFormVo = customerForm.bindFromRequest().get();
        String mobilePhoneNo = customerFormVo.getMobilePhoneNo();
        String deviceNo = customerFormVo.getDeviceNo();
		loginService.logout(mobilePhoneNo, deviceNo, token);

        MessageVo messageVo = new MessageVo(new Message(MsgCode.LOGOUT_SUCCESS));
		return Controller.ok(messageVo.toJson());
	}

	/**
	 * 手势登录
	 * @return
	 */
	public Result loginByges() {
		Logger.info("==========loginByGesture====================");
		CustomerFormVo customerFormVo = customerForm.bindFromRequest().get();
        String mobilePhoneNo = customerFormVo.getMobilePhoneNo();
        String deviceNo = customerFormVo.getDeviceNo();

 		Http.Cookie cookie = Controller.request().cookie(AppConst.TOKEN);
 		String token = cookie == null ? null : cookie.value();

 		CustomerSession customerSession = loginService.loginByGesture(customerFormVo, token, Controller.request().remoteAddress());

		if (customerSession != null) {
            Message message = new Message(MsgCode.LOGIN_SUCCESS);
            CustomerVo customerVo =  customerService.getCustomerVoByPhoneNo(mobilePhoneNo, deviceNo);
            MessageUtil.getInstance().setMessage(message, customerVo);
            customerService.sessionLoginSessionId(Controller.session(), Controller.response(), customerSession);
        }

		JsonNode json = MessageUtil.getInstance().toJson();
		Logger.info("==========loginByges返回：" + json.toString());
		
		return Controller.ok(json);
	}

	/**
	 * 密码确认
	 * @return
	 */
	public Result confirmPwd() {
	    CustomerFormVo customerFormVo = customerForm.bindFromRequest().get();
        String mobilePhoneNo = customerFormVo.getMobilePhoneNo();
        String passWord = customerFormVo.getPassWord();
		loginService.confirmPwd(mobilePhoneNo, passWord);


        Message message = new Message(MsgCode.OPERATE_SUCCESS);
        return Controller.ok(new MessageVo(message).toJson());
	}

	/**
	 * 保存手势密码
	 * @return
	 */
	public Result saveGesturePwd() {
        Logger.info("========saveGesturePwd================");
        customerService.validateCustomerSession(Controller.request(), Controller.session(), Controller.response());
        CustomerFormVo vo = customerForm.bindFromRequest().get();
        CustomerVo customerVo = loginService.saveGesturePwd(vo);
        MessageVo<CustomerVo> messageVo = new MessageVo<>(new Message(MsgCode.GESTURE_PASSWORD_SUCCESS));
        messageVo.setValue(customerVo);
        return Controller.ok(messageVo.toJson());
	}

    /**
     * 实名认证
     * @return
     */
    public Result certify(){
        Logger.info("=================ceritify=========");
        Http.Cookie cookie = Controller.request().cookie(AppConst.TOKEN);
        String token = cookie == null ? null : cookie.value();
        Logger.info("=========token:" + token);

        CustomerFormVo customerFormVo = customerForm.bindFromRequest().get();
        CustomerSession customerSession = customerService.certify(customerFormVo, token, Controller.request().remoteAddress());
        customerService.sessionLoginSessionId(Controller.session(), Controller.response(), customerSession);

        JsonNode json = MessageUtil.getInstance().toJson();
        Logger.info("=================ceritify返回==========" + json.toString());
        return Controller.ok(Json.toJson(json));
    }

    /**
     * <p>
     * Description: 获取验证码
     * </p>
     *
     * @return
     */
    public Result genVerificationCode() {
        Logger.info("===========genVerificationCode==================");
        CustomerFormVo vo = customerForm.bindFromRequest().get();

        String mobilePhoneNo = vo.getMobilePhoneNo();
        String verifyType = vo.getType();
        String deviceNo = vo.getDeviceNo();

        verifyCodeService.genVerificationCode(mobilePhoneNo, verifyType, deviceNo);

        Message message = new Message(MsgCode.OPERATE_SUCCESS);
        MessageVo messageVo = new MessageVo(message);
        return Controller.ok(Json.toJson(messageVo));
    }


}
