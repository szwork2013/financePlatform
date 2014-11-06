package com.sunlights.core.service;

import com.sunlights.common.vo.PageVo;
import com.sunlights.core.vo.ChartVo;
import com.sunlights.core.vo.FundVo;
import com.sunlights.core.vo.ProductVo;
import models.Fund;

import java.util.List;

/**
 * <p>Project: fsp</p>
 * <p>Title: ProductRecommendService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public interface ProductService {
    public List<ProductVo> findProductRecommends(PageVo pageVo);

    public List<FundVo> findFunds(PageVo pageVo);

    public ProductVo findProductDetailBy(String productCode, String type);

    public Fund findFundByCode(String productCode);

    public ChartVo findOneWeekProfitsByDays(String fundCode, int days);

    public ChartVo findMillionOfProfitsByDays(String fundCode, int days);
}
