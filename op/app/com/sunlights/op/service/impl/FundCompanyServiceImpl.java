package com.sunlights.op.service.impl;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.exceptions.BusinessRuntimeException;
import com.sunlights.common.service.PageService;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.FundCompanyService;
import com.sunlights.op.vo.FundCompanyVo;
import models.FundCompany;

import java.util.List;

/**
 * <p>Project: OperationPlatform</p>
 * <p>Title: FundCompanyServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class FundCompanyServiceImpl implements FundCompanyService {

	private EntityBaseDao entityBaseDao = new EntityBaseDao();

    private PageService pageService = new PageService();

    @Override
    public List<FundCompanyVo> findFundCompaniesBy(PageVo pageVo) {
        StringBuilder xsql = new StringBuilder();

        xsql.append(" select new com.sunlights.op.vo.FundCompanyVo(f) from FundCompany f");
        xsql.append(" where 1=1");
        xsql.append(" /~ and f.companyName like {companyName} ~/ ");
        xsql.append(" /~ and f.pinYinCode like {pinYinCode} ~/ ");

        List<FundCompanyVo> fundCompanyVos = pageService.findXsqlBy(xsql.toString(), pageVo);
        return fundCompanyVos;
    }

    @Override
    public void create(FundCompanyVo fundCompanyVo) {
        if (hasFundCompany(fundCompanyVo)) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.FUND_COMPANY_EXIST_ERROR));
        }
        FundCompany fundCompany = fundCompanyVo.convertToFundCompany();
		entityBaseDao.create(fundCompany);

    }

    @Override
    public void update(FundCompanyVo fundCompanyVo) {
        if (hasFundCompany(fundCompanyVo)) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.FUND_COMPANY_EXIST_ERROR));
        }
        FundCompany fundCompany = fundCompanyVo.convertToFundCompany();
		entityBaseDao.update(fundCompany);
    }

    @Override
    public void delete(FundCompanyVo fundCompanyVo) {
        FundCompany fundCompany = entityBaseDao.find(FundCompany.class, fundCompanyVo.getId());
		entityBaseDao.delete(fundCompany);
    }

    private boolean hasFundCompany(FundCompanyVo fundCompanyVo) {
        StringBuffer jpql = new StringBuffer();
        jpql.append(" select f from FundCompany f");
        jpql.append(" where f.fundCompanyId = '" + fundCompanyVo.getFundCompanyId().trim() + "'");
        if (fundCompanyVo.getId() != null) {
            jpql.append(" and f.id <> ").append(fundCompanyVo.getId());
        }
        List<FundCompany> fundCompanies = entityBaseDao.find(jpql.toString());
        return !fundCompanies.isEmpty();
    }
}
