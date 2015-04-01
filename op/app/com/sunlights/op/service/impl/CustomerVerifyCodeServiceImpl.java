package com.sunlights.op.service.impl;


import com.sunlights.common.vo.PageVo;
import com.sunlights.op.dal.CustomerVerifyCodeDao;
import com.sunlights.op.dal.impl.CustomerVerifyCodeDaoImpl;
import com.sunlights.op.service.CustomerVerifyCodeService;
import com.sunlights.op.vo.VerifyCodeVo;

import java.util.List;

/**
 * <p>Project: op</p>
 * <p>Title: CustomerVerifyCodeServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class CustomerVerifyCodeServiceImpl implements CustomerVerifyCodeService {

    private CustomerVerifyCodeDao customerVerifyCodeDao = new CustomerVerifyCodeDaoImpl();

    public List<VerifyCodeVo> findCustomerVerifyCodes(PageVo pageVo) {
//        List<VerifyCodeVo> verifyCodeVos = new ArrayList<VerifyCodeVo>();
        List<VerifyCodeVo> verifyCodes = customerVerifyCodeDao.findCustomerVerifyCodes(pageVo);

        return verifyCodes;
    }
}
