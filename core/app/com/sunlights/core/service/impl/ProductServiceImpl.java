package com.sunlights.core.service.impl;

import com.sunlights.common.DictConst;
import com.sunlights.common.service.PageService;
import com.sunlights.common.utils.ArithUtil;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.core.dal.FundDao;
import com.sunlights.core.dal.impl.FundDaoImpl;
import com.sunlights.core.service.ProductService;
import com.sunlights.core.vo.ChartVo;
import com.sunlights.core.vo.FundVo;
import com.sunlights.core.vo.Point;
import com.sunlights.core.vo.ProductVo;
import models.Fund;
import models.FundCompany;
import models.FundHistory;
import models.FundNavHistory;

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
public class ProductServiceImpl implements ProductService {

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

    public FundHistory findFundHistoryByCode(String productCode) {
        FundHistory fundHistory = fundDao.findFundHistoryByCode(productCode);
        return fundHistory;
    }

    public FundCompany findFundCompanyById(String id) {
        return fundDao.findFundCompanyById(id);
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

        List<FundNavHistory> fundHistories = fundDao.findFundNavHistoriesByDays(fundCode, days);
        ChartVo chartVo = new ChartVo();
        chartVo.setChartName("七日年化走势");
        chartVo.setChartType("2");
        chartVo.setPrdCode(fundCode);
        if (!fundHistories.isEmpty()) {
            chartVo.setPrdName(fundHistories.get(0).getFundname());
        }
        for (FundNavHistory fundHistory : fundHistories) {
            chartVo.getPoints().add(new Point(CommonUtil.dateToString(fundHistory.getCreateTime(), CommonUtil.DATE_FORMAT_SHORT),  ArithUtil.mul(fundHistory.getPercentSevenDays().doubleValue(), 100) + ""));
        }
        return chartVo;
    }

    @Override
    public ChartVo findMillionOfProfitsByDays(String fundCode, int days) {
        List<FundNavHistory> fundHistories = fundDao.findFundNavHistoriesByDays(fundCode, days);
        ChartVo chartVo = new ChartVo();
        chartVo.setChartName("万份收益走势");
        chartVo.setChartType("1");
        chartVo.setPrdCode(fundCode);
        if (!fundHistories.isEmpty()) {
            chartVo.setPrdName(fundHistories.get(0).getFundname());
        }
        for (FundNavHistory fundHistory : fundHistories) {
            chartVo.getPoints().add(new Point(CommonUtil.dateToString(fundHistory.getCreateTime(), CommonUtil.DATE_FORMAT_SHORT), ArithUtil.bigUpScale4(fundHistory.getIncomePerTenThousand()) + ""));
        }
        return chartVo;
    }


}
