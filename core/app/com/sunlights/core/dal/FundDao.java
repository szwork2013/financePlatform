package com.sunlights.core.dal;

import com.sunlights.common.vo.PageVo;
import com.sunlights.core.vo.FundDetailVo;
import com.sunlights.core.vo.FundVo;
import com.sunlights.core.vo.ProductVo;
import models.*;

import java.util.List;

/**
 * <p>Project: fsp</p>
 * <p>Title: FundDao.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public interface FundDao {
    public Fund findFundByCode(String code);

    public FundNav findFundNavByCode(String code);

    public FundDetailVo findFundDetailByCode(String code);

    public FundHistory findFundHistoryByCode(String code);

    public FundCompany findFundCompanyById(String id);

    public void addProductPurchasedNum(String productCode);

    public List<FundProfitHistory> findFundProfitHistoryByDays(String fundCode, int days);

    public Code findFundNameByFundCode(String fundCode);

    /**
     * 查询产品首页
     * @param pageVo
     * @return
     */
    public List<ProductVo> findProductIndex(PageVo pageVo);

    /**
     * 查询产品
     * @param pageVo
     * @return
     */
    public List<FundVo> findFunds(PageVo pageVo);
}
