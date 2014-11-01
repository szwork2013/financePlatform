package com.sunlights.core.web;

import com.sunlights.common.dal.impl.VerifyCodeService;
import com.sunlights.customer.vo.CustomerFormVo;
import play.data.Form;

/**
* <p>Project: fsp</p>
* <p>Title: SafeFacade.java</p>
* <p>Description: </p>
* <p>Copyright (c) 2014 Sunlights.cc</p>
* <p>All Rights Reserved.</p>
*
* @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
*/



public class SafeController {
    private static Form<CustomerFormVo> customerForm = Form.form(CustomerFormVo.class);


    private VerifyCodeService verifyCodeService;



}
