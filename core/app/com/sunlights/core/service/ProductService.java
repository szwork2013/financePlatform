package com.sunlights.core.service;

import com.sunlights.common.vo.PageVo;
import com.sunlights.core.vo.ChartVo;
import com.sunlights.core.vo.FundVo;
import com.sunlights.core.vo.ProductVo;
import models.Fund;
import models.FundCompany;
import models.FundHistory;
import models.FundNav;

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

    public List<ProductVo> findProductIndex(PageVo pageVo);

    public List<FundVo> findFunds(PageVo pageVo);

    public ProductVo findProductDetailBy(String productCode, String type);

    public Fund findFundByCode(String productCode);

    public FundNav findFundNavByCode(String fundCode);

    public FundHistory findFundHistoryByCode(String productCode);

    public FundCompany findFundCompanyById(String id);

    public void addProductPurchasedNum(String productCode);

    public ChartVo findOneWeekProfitsByDays(String fundCode, int days);

    public ChartVo findMillionOfProfitsByDays(String fundCode, int days);

    /**
     * 根据基金代码查询相应天数的万份收益和七日年华利率
     *
     * @param chartType
     * @param fundCode
     * @param days
     * @return
     */
    public ChartVo findProfitHistoryByDays(String chartType, String fundCode, int days);

    /**
     * 刷新产品首页、列表缓存
     * @param notReloadP2p true不刷新p2p缓存  false反之
     */
    public void reloadProductCache(boolean notReloadP2p);

    /**
     * 从缓存中获取产品列表
     * @param pageVo
     * @return
     */
    public List<FundVo> findProductListFromCache(PageVo pageVo);

    /**
     * 从缓存中获取产品首页
     * @return
     */
    public List<ProductVo> findProductIndexFromCache();
}
