package com.sunlights.op.dal;


import com.sunlights.common.vo.PageVo;
import com.sunlights.op.vo.VerifyCodeVo;

import java.util.List;

/**
 * <p>Project: op</p>
 * <p>Title: CustomerVerifyCodeDao.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public interface CustomerVerifyCodeDao {
    public List<VerifyCodeVo> findCustomerVerifyCodes(PageVo pageVo);
}
