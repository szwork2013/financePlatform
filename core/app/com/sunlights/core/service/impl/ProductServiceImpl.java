package com.sunlights.core.service.impl;

import com.sunlights.common.DictConst;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.service.PageService;
import com.sunlights.common.utils.ArithUtil;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.utils.PropertyFilter;
import com.sunlights.common.vo.PageVo;
import com.sunlights.core.dal.FundDao;
import com.sunlights.core.dal.impl.FundDaoImpl;
import com.sunlights.core.service.ProductService;
import com.sunlights.core.vo.ChartVo;
import com.sunlights.core.vo.FundVo;
import com.sunlights.core.vo.Point;
import com.sunlights.core.vo.ProductVo;
import models.*;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
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
public class ProductServiceImpl extends EntityBaseDao implements ProductService {

    public static final String CHART_TYPE = "1";
    private PageService pageService = new PageService();

    private FundDao fundDao = new FundDaoImpl();

    @Override
    public List<ProductVo> findProductIndex(PageVo pageVo) {
        String currentDate = CommonUtil.dateToString(new Date(), CommonUtil.DATE_FORMAT_LONG);
        StringBuilder xsql = new StringBuilder();
        xsql.append(" select new com.sunlights.core.vo.FundVo(f,pm)");
        xsql.append(" from FundNav f , ProductManage pm");
        xsql.append(" where f.fundcode = pm.productCode");
        xsql.append(" and pm.upBeginTime < '" + currentDate + "'");
        xsql.append(" and pm.downEndTime >= '" + currentDate + "'");
        xsql.append(" and pm.recommendFlag = '" + DictConst.FP_RECOMMEND_FLAG_1 + "'");
        xsql.append(" and pm.productStatus = '" + DictConst.FP_PRODUCT_MANAGE_STATUS_1 + "'");
        xsql.append(" order by pm.priorityLevel desc");

        List<ProductVo> fundVos = pageService.findXsqlBy(xsql.toString(), pageVo);
        return fundVos;
    }

    @Override
    public List<FundVo> findFunds(PageVo pageVo) {
        String currentDate = CommonUtil.dateToString(new Date(), CommonUtil.DATE_FORMAT_LONG);
        String jpql = " select new com.sunlights.core.vo.FundVo(f,pm)" +
                " from FundNav f , ProductManage pm" +
                " where f.fundcode = pm.productCode" +
                " and pm.productStatus = '" + DictConst.FP_PRODUCT_MANAGE_STATUS_1 + "'" +
                " and pm.productType = :productType" +
                " and pm.upBeginTime < '" + currentDate + "'" +
                " and pm.downEndTime >= '" + currentDate + "'" +
                " and f.fundType = :fundType" +
                " order by pm.recommendType,pm.priorityLevel desc";

        List<FundVo> fundVos = pageService.findBy(jpql, pageVo);
        return fundVos;
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

    @Override
    public void createProductAttention(List<ProductVo> productVos, String customerId) {
        Date date = new Date();

        for (ProductVo productVo : productVos) {
            ProductAttention productAttention = findProductAttentionBy(customerId, productVo.getCode());
            if (productAttention == null) {
                productAttention = new ProductAttention();
            }
            productAttention.setCreateTime(date);
            productAttention.setCustomerId(customerId);
            productAttention.setProductCode(productVo.getCode());
            productAttention.setProductType(productVo.getType());
            super.update(productAttention);
        }
    }

    private ProductAttention findProductAttentionBy(String customerId, String productCode) {
        List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
        PropertyFilter customerIdFilter = new PropertyFilter("EQS_customerId", customerId);
        PropertyFilter productCodeFilter = new PropertyFilter("EQS_productCode", productCode);
        filters.add(customerIdFilter);
        filters.add(productCodeFilter);
        List<ProductAttention> productAttentions = super.find(ProductAttention.class, filters);
        return productAttentions.isEmpty() ? null : productAttentions.get(0);
    }

    @Override
    public void cancelProductAttention(ProductVo productVo, String customerId) {
        ProductAttention productAttention = findProductAttentionBy(customerId, productVo.getCode());
        super.delete(productAttention);
    }

    @Override
    public List<ProductVo> findProductAttentions(PageVo pageVo, String customerId) {
        String currentDate = CommonUtil.dateToString(new Date(), CommonUtil.DATE_FORMAT_LONG);
        StringBuffer jpql = new StringBuffer();
        jpql.append(" select new com.sunlights.core.vo.FundVo(f,pm)");
        jpql.append(" from FundNav f , ProductManage pm, ProductAttention pa");
        jpql.append(" where f.fundcode = pm.productCode");
        jpql.append(" and pm.upBeginTime < '" + currentDate + "'");
        jpql.append(" and pm.downEndTime >= '" + currentDate + "'");
        jpql.append(" and pm.productStatus = '" + DictConst.FP_PRODUCT_MANAGE_STATUS_1 + "'");
        jpql.append(" and f.fundcode = pa.productCode and pa.productType = '" + DictConst.FP_PRODUCT_TYPE_1 + "'");
        jpql.append(" order by pm.productType,pm.recommendType,pa.createTime desc");

        List<ProductVo> productVos = pageService.findXsqlBy(jpql.toString(), pageVo);
        return productVos;
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
                points.add(new Point(date, ArithUtil.bigUpScale4(fundHis.getIncomePerTenThousand()) + ""));
            } else {
                points.add(new Point(date, ArithUtil.mul(fundHis.getPercentSevenDays().doubleValue(), 100) + ""));
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

}
