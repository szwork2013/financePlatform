package com.sunlights.account.web;

import com.sunlights.account.vo.ActivityParamter;
import com.sunlights.common.AppConst;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.customer.service.impl.CustomerService;
import models.CustomerSession;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;

/**
 * Created by tangweiqun on 2014/11/19.
 */
public class ActivityBaseController  extends Controller{
    private Form<ActivityParamter> activityParameterForm = Form.form(ActivityParamter.class);

    protected CustomerService customerService = new CustomerService();

    protected MessageUtil messageUtil = MessageUtil.getInstance();

    public ActivityParamter getActivityParamter() {
        ActivityParamter activityParamter = null;
        Http.RequestBody body = request().body();
        if (body.asJson() != null) {
            activityParamter = Json.fromJson(body.asJson(), ActivityParamter.class);
        }

        if (body.asFormUrlEncoded() != null) {
            activityParamter = activityParameterForm.bindFromRequest().get();
        }
        return activityParamter;
    }

    public String getToken() {
        customerService.validateCustomerSession(request(),session(),response());
        Http.Cookie cookie = request().cookie(AppConst.TOKEN);
        String token = cookie == null ? null : cookie.value();
        return token;
    }

    public CustomerSession getCustomerSession() {
        String token = getToken();
        CustomerSession customerSession = customerService.getCustomerSession(token);
        return customerSession;
    }
}
