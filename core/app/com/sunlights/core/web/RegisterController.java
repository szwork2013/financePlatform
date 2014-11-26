package com.sunlights.core.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.sunlights.account.AccountConstant;
import com.sunlights.account.service.AccountService;
import com.sunlights.account.service.impl.AccountServiceImpl;
import com.sunlights.account.service.rewardrules.ObtainRewardFacade;
import com.sunlights.common.MsgCode;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.customer.service.LoginService;
import com.sunlights.customer.service.impl.CustomerService;
import com.sunlights.customer.service.impl.LoginServiceImpl;
import com.sunlights.customer.vo.CustomerFormVo;
import com.sunlights.customer.vo.CustomerVo;
import models.Customer;
import models.CustomerSession;
import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

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
public class RegisterController extends Controller {

    private Form<CustomerFormVo> customerForm = Form.form(CustomerFormVo.class);
    private LoginService loginService = new LoginServiceImpl();
    private AccountService accountService = new AccountServiceImpl();
    private CustomerService customerService = new CustomerService();

    private ObtainRewardFacade obtainRewardFacade = new ObtainRewardFacade();

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
     *
     * </pre>
     */
    public Result register() {
        Logger.info("==========register====================");
        CustomerFormVo customerFormVo = customerForm.bindFromRequest().get();
        Customer customer = loginService.register(customerFormVo);

        if (customer != null) {
            CustomerSession customerSession = customerService.createCustomerSession(customer, Controller.request().remoteAddress());
            customerService.sessionLoginSessionId(Controller.session(), Controller.response(), customerSession);
            accountService.createBaseAccount(customer.getCustomerId(), null);
            Message message = new Message(MsgCode.REGISTRY_SUCCESS);
            CustomerVo customerVo = customerService.getCustomerVoByPhoneNo(customer.getMobile(), customerFormVo.getDeviceNo());
            MessageUtil.getInstance().setMessage(message, customerVo);
            try {
                obtainRewardFacade.obtainReward(customerVo.getCustomerId(), AccountConstant.ACTIVITY_REGISTER_SCENE_CODE, null);
            } catch (Exception e) {
                Logger.error("获取积分失败");
            }
        }

        JsonNode json = MessageUtil.getInstance().toJson();

        Logger.info("==========register返回：" + json.toString());
        return Controller.ok(json);
    }
}
