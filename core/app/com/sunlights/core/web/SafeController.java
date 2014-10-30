package com.sunlights.core.web;

import com.sunlights.common.MsgCode;
import com.sunlights.common.ParameterService;
import com.sunlights.common.VerifyCodeService;
import com.sunlights.common.utils.msg.Message;
import com.sunlights.common.utils.msg.MessageUtil;
import com.sunlights.core.integration.CustomerClient;
import com.sunlights.customer.vo.CustomerFormVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

/**
* <p>Project: fsp</p>
* <p>Title: SafeFacade.java</p>
* <p>Description: </p>
* <p>Copyright (c) 2014 Sunlights.cc</p>
* <p>All Rights Reserved.</p>
*
* @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
*/

@org.springframework.stereotype.Controller
@Transactional
public class SafeController {
    private static Form<CustomerFormVo> customerForm = Form.form(CustomerFormVo.class);

    @Autowired
    private VerifyCodeService verifyCodeService;



}
