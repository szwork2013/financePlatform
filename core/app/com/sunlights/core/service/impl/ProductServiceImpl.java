package com.sunlights.core.service.impl;

import com.google.common.collect.Lists;
import com.sunlights.common.*;
import com.sunlights.common.exceptions.BusinessRuntimeException;
import com.sunlights.common.utils.ArithUtil;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.core.dal.FundDao;
import com.sunlights.core.dal.impl.FundDaoImpl;
import com.sunlights.core.service.ProductService;
import com.sunlights.core.vo.ChartVo;
import com.sunlights.core.vo.FundVo;
import com.sunlights.core.vo.Point;
import com.sunlights.core.vo.ProductVo;
import models.*;
import org.apache.commons.lang3.StringUtils;
import play.Logger;
import play.cache.Cache;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Project: fsp</p>
 * <p>Title: ProductServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class ProductServiceImpl implements ProductService {

    public static final String CHART_TYPE = "1";
    private FundDao fundDao = new FundDaoImpl();

    @Override
    public List<ProductVo> findProductIndex(PageVo pageVo) {
        List<ProductVo> fundVos = fundDao.findProductIndex(pageVo);
        return fundVos;
    }

    @Override
    public List<FundVo> findFunds(PageVo pageVo) {
        this.convertPageVo(pageVo);
        List<FundVo> fundVos = fundDao.findFunds(pageVo);
        return fundVos;
    }

    private void convertPageVo(PageVo pageVo) {
        String category = (String) pageVo.get("EQS_category");
        FundCategory fundCategory = FundCategory.findFundCategoryBy(category);
        if (fundCategory == null) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.SEARCH_FAIL_FUND_CATEGORY_EMPTY));
        }
        if (FundCategory.MONETARY.equals(fundCategory)) {
            pageVo.put("EQI_isMonetary", 1);
            pageVo.put("EQI_isStf", 0);
        } else if (FundCategory.STF.equals(fundCategory)) {
            pageVo.put("EQI_isMonetary", 0);
            pageVo.put("EQI_isStf", 1);
        }

    }

    public Fund findFundByCode(String fundCode) {
        Fund fund = fundDao.findFundByCode(fundCode);
        return fund;
    }

    public FundNav findFundNavByCode(String fundCode) {
        return fundDao.findFundNavByCode(fundCode);
    }

    public FundHistory findFundHistoryByCode(String productCode) {
        FundHistory fundHistory = fundDao.findFundHistoryByCode(productCode);
        return fundHistory;
    }

    public FundCompany findFundCompanyById(String id) {
        return fundDao.findFundCompanyById(id);
    }

    @Override
    public void addProductPurchasedNum(String productCode) {
        fundDao.addProductPurchasedNum(productCode);
    }

    @Override
    public ProductVo findProductDetailBy(String productCode, String type) {
        ProductVo productVo = null;
        // 基金
        if (DictConst.FP_PRODUCT_TYPE_1.equals(type)) {
            productVo = fundDao.findFundDetailByCode(productCode);
        }
        // TODO P2P
        return productVo;
    }

    @Override
    public ChartVo findOneWeekProfitsByDays(String fundCode, int days) {

        List<FundProfitHistory> fundHistories = fundDao.findFundProfitHistoryByDays(fundCode, days);
        ChartVo chartVo = new ChartVo();
        chartVo.setChartName("七日年化走势");
        chartVo.setChartType("2");
        chartVo.setPrdCode(fundCode);
        if (!fundHistories.isEmpty()) {
            Code fundInfo = fundDao.findFundNameByFundCode(fundCode);
            chartVo.setPrdName(fundInfo.getValue());
        }
        for (FundProfitHistory fundHistory : fundHistories) {
            chartVo.getPoints().add(new Point(CommonUtil.dateToString(fundHistory.getDateTime(), CommonUtil.DATE_FORMAT_SHORT), ArithUtil.mul(fundHistory.getPercentSevenDays().doubleValue(), 100) + ""));
        }
        return chartVo;
    }

    @Override
    public ChartVo findMillionOfProfitsByDays(String fundCode, int days) {
        List<FundProfitHistory> fundHistories = fundDao.findFundProfitHistoryByDays(fundCode, days);
        ChartVo chartVo = new ChartVo();
        chartVo.setChartName("万份收益走势");
        chartVo.setChartType("1");
        chartVo.setPrdCode(fundCode);
        if (!fundHistories.isEmpty()) {
            Code fundInfo = fundDao.findFundNameByFundCode(fundCode);
            chartVo.setPrdName(fundInfo.getValue());
        }
        for (FundProfitHistory fundHistory : fundHistories) {
            chartVo.getPoints().add(new Point(CommonUtil.dateToString(fundHistory.getDateTime(), CommonUtil.DATE_FORMAT_SHORT), ArithUtil.bigUpScale4(fundHistory.getIncomePerTenThousand())));
        }
        return chartVo;
    }

    /**
     * 根据基金代码查询相应天数的万份收益和七日年华利率
     *
     * @param chartType
     * @param fundCode
     * @param days
     * @return
     */
    @Override
    public ChartVo findProfitHistoryByDays(String chartType, String fundCode, int days) {
        List<FundProfitHistory> fundHisLst = fundDao.findFundProfitHistoryByDays(fundCode, days);
        if (fundHisLst == null || fundHisLst.isEmpty()) {
            return null;
        }
        return getChartVo(chartType, fundCode, fundHisLst);
    }

    private ChartVo getChartVo(String chartType, String fundCode, List<FundProfitHistory> fundHisLst) {
        ChartVo chartVo = new ChartVo();
        Code fundInfo = fundDao.findFundNameByFundCode(fundCode);
        chartVo.setPrdName(fundInfo.getValue());
        chartVo.setChartName(CHART_TYPE.equals(chartType) ? "万份收益走势" : "七日年化走势");
        chartVo.setChartType(chartType);
        chartVo.setPrdCode(fundCode);
        setPoints(chartType, fundHisLst, chartVo);
        return chartVo;
    }

    private void setPoints(String chartType, List<FundProfitHistory> fundHisLst, ChartVo chartVo) {

        List<Point> points = chartVo.getPoints();
        for (FundProfitHistory fundHis : fundHisLst) {
            String date = CommonUtil.dateToString(fundHis.getDateTime(), CommonUtil.DATE_FORMAT_SHORT);

            if (CHART_TYPE.equals(chartType)) {
                points.add(new Point(date, ArithUtil.bigUpScale4(fundHis.getIncomePerTenThousand())));
            } else {
                points.add(new Point(date, ArithUtil.bigToScale2(fundHis.getPercentSevenDays().multiply(new BigDecimal(100)))));
            }
        }
        chartVo.setPoints(sortPoints(points));

    }

    private List<Point> sortPoints(List<Point> points) {
        List<Point> pointsTemp = new ArrayList<>();
        for (int i = points.size() - 1; i >= 0; i--) {
            pointsTemp.add(points.get(i));
        }
        return pointsTemp;
    }

    @Override
    public void reloadProductCache(boolean notReloadP2p){
        Cache.remove(AppConst.CACHE_PRODUCT_MONETARY_LIST);
        Cache.remove(AppConst.CACHE_PRODUCT_STF_LIST);
        Cache.remove(AppConst.CACHE_PRODUCT_INDEX);

        productMonetaryListLoad();
        productStfListLoad();
        productIndexListLoad();
    }

    @Override
    public List<ProductVo> findProductIndexFromCache(){
        List<ProductVo> list = (List) Cache.get(AppConst.CACHE_PRODUCT_INDEX);

        if (list == null || list.isEmpty()) {//加载
            list = productIndexListLoad();
        }

        return list;
    }

    @Override
    public List<FundVo> findProductListFromCache(PageVo pageVo){
        String category = (String) pageVo.get("EQS_category");
        FundCategory fundCategory = FundCategory.findFundCategoryBy(category);
        if (fundCategory == null) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.SEARCH_FAIL_FUND_CATEGORY_EMPTY));
        }

        List<FundVo> cacheList = Lists.newArrayList();
        if (FundCategory.MONETARY.equals(fundCategory)) {
            cacheList = (List) Cache.get(AppConst.CACHE_PRODUCT_MONETARY_LIST);
            if (cacheList == null || cacheList.isEmpty()) {
                cacheList = productMonetaryListLoad();
            }
        } else if (FundCategory.STF.equals(fundCategory)) {
            cacheList = (List) Cache.get(AppConst.CACHE_PRODUCT_STF_LIST);
            if (cacheList == null || cacheList.isEmpty()) {
                cacheList = productStfListLoad();
            }
        }

        List<FundVo> returnList = getProductReturnList(pageVo, cacheList);

        return returnList;
    }

    private List<FundVo> getProductReturnList(PageVo pageVo, List<FundVo> cacheList) {
        int index = pageVo.getIndex();
        int pageSize = pageVo.getPageSize();
        String type = (String)pageVo.get("EQS_productType");

        List<FundVo> returnList = Lists.newArrayList();
        List<FundVo> meetAllList = Lists.newArrayList();
        for (FundVo fundVo : cacheList) {
            if (StringUtils.isNotEmpty(type) && !type.equals(fundVo.getType())) {
                continue;
            }

            meetAllList.add(fundVo);
        }
        if (index >= meetAllList.size()) {
            return returnList;
        }
        if (index + pageSize > meetAllList.size()) {
            returnList = meetAllList.subList(index, meetAllList.size());
        }else{
            returnList = meetAllList.subList(index, index + pageSize);
        }

        pageVo.setCount(meetAllList.size());
        return returnList;
    }

    private List<FundVo> productMonetaryListLoad() {
        PageVo pageVo = new PageVo();
        pageVo.put("EQS_category", AppConst.FUND_CATEGORY_MONETARY);
        List<FundVo> monetaryList = findFunds(pageVo);
        Cache.set(AppConst.CACHE_PRODUCT_MONETARY_LIST, monetaryList);
        Logger.info(">>货币型产品列表缓存重新加载完成");

        return monetaryList;
    }
    private List<FundVo> productStfListLoad() {
        PageVo pageVo = new PageVo();
        pageVo.put("EQS_category", AppConst.FUND_CATEGORY_STF);
        List<FundVo> stfList = findFunds(pageVo);
        Cache.set(AppConst.CACHE_PRODUCT_STF_LIST, stfList);
        Logger.info(">>定期理财型产品列表缓存重新加载完成");
        return stfList;
    }
    private List<ProductVo> productIndexListLoad() {
        PageVo pageVo = new PageVo();
        List<ProductVo> list = findProductIndex(pageVo);
        Cache.set(AppConst.CACHE_PRODUCT_INDEX, list);
        Logger.info(">>产品首页缓存重新加载完成");
        return list;
    }

}
