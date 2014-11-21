package com.sunlights.account.web;

import com.sunlights.account.service.ShuMiAccountService;
import com.sunlights.account.service.impl.ShuMiAccountServiceImpl;
import com.sunlights.account.vo.ShuMiAccountVo;
import com.sunlights.common.AppConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.exceptions.ConverterException;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.customer.service.impl.CustomerService;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: ShumiAccountController.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Transactional
public class ShuMiAccountController extends Controller{

    private Form<ShuMiAccountVo> shuMiAccountVoForm = Form.form(ShuMiAccountVo.class);
    
    private ShuMiAccountService shuMiAccountService = new ShuMiAccountServiceImpl();
    private CustomerService customerService = new CustomerService();

    public Result saveShuMiAccount() {
        customerService.validateCustomerSession(request(), session(), response());

        String token = request().cookie(AppConst.TOKEN).value();
        ShuMiAccountVo shuMiAccountVo = shuMiAccountVoForm.bindFromRequest().get();

        try {
            shuMiAccountService.saveShuMiAccount(shuMiAccountVo, token);
        } catch (ConverterException e) {
            e.printStackTrace();
            MessageUtil.getInstance().setMessage(new Message(MsgCode.CONVERTER_FAIL));
        }

        return ok(MessageUtil.getInstance().toJson());
    }

}
