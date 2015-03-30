package com.sunlights.op.service.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.service.PageService;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.FundManageService;
import com.sunlights.op.vo.FundManageVo;
import models.Fund;
import models.FundNav;

import java.util.Date;
import java.util.List;

/**
 * <p>Project: OperationPlatform</p>
 * <p>Title: FundManageServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class FundManageServiceImpl extends EntityBaseDao implements FundManageService {
    private PageService pageService = new PageService();

    @Override
    public List<FundManageVo> findFundsBy(PageVo pageVo) {
        StringBuilder xsql = new StringBuilder();

        xsql.append(" select new com.sunlights.op.vo.FundManageVo(f,c)");
        xsql.append(" from FundNav f , FundCompany c");
        xsql.append(" where f.iaGuid = c.fundCompanyId");
        xsql.append(" /~ and f.fundcode like {fundcode} ~/ ");
        xsql.append(" /~ and f.fundname like {fundname} ~/ ");
        xsql.append(" /~ and f.fundType = {fundType} ~/ ");
        xsql.append(" /~ and c.companyName like {companyName} ~/ ");
        List<FundManageVo> fundManageVos = pageService.findXsqlBy(xsql.toString(), pageVo);
        return fundManageVos;
    }

    @Override
    public void create(FundManageVo fundManageVo) {
        FundNav fund = fundManageVo.convertToFund();
        fund.setCreateTime(new Date());
        fund.setUpdateTime(new Date());
        super.create(fund);
    }

    @Override
    public void update(FundManageVo fundManageVo) {
        FundNav fund = fundManageVo.convertToFund();
        fund.setUpdateTime(new Date());
        super.update(fund);
    }

    @Override
    public void delete(FundManageVo fundManageVo) {
        Fund fund = super.find(Fund.class, fundManageVo.getFundcode());
        super.delete(fund);
    }

}
