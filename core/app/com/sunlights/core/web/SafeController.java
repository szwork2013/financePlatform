package com.sunlights.core.web;

import com.sunlights.common.dal.impl.VerifyCodeService;
import com.sunlights.customer.vo.CustomerFormVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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

@org.springframework.stereotype.Controller
@Transactional
public class SafeController {
    private static Form<CustomerFormVo> customerForm = Form.form(CustomerFormVo.class);

    @Autowired
    private VerifyCodeService verifyCodeService;



}
