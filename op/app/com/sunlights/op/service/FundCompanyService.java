package com.sunlights.op.service;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.vo.FundCompanyVo;

import java.util.List;

/**
 * <p>Project: OperationPlatform</p>
 * <p>Title: FundCompanyService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public interface FundCompanyService {
    public List<FundCompanyVo> findFundCompaniesBy(PageVo pageVo);

    public void create(FundCompanyVo fundCompanyVo);

    public void update(FundCompanyVo fundCompanyVo);

    public void delete(FundCompanyVo fundCompanyVo);
}
