package com.sunlights.op.dal.impl;


import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.service.PageService;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.dal.CustomerVerifyCodeDao;
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
public class CustomerVerifyCodeDaoImpl extends EntityBaseDao implements CustomerVerifyCodeDao {
    private PageService pageService = new PageService();
    public List<VerifyCodeVo> findCustomerVerifyCodes(PageVo pageVo) {
        StringBuilder sb = new StringBuilder();
        sb.append(" select new com.sunlights.op.vo.VerifyCodeVo(c) from CustomerVerifyCode c");
        sb.append(" order by c.updateTime desc");
        List<VerifyCodeVo> verifylist = pageService.findXsqlBy(sb.toString(), pageVo);
        return verifylist;



//        return super.findAll(CustomerVerifyCode.class, "createTime", false);
    }
}
