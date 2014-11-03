package com.sunlights.core.service.impl;

import com.sunlights.common.service.PageService;
import com.sunlights.common.utils.ArithUtil;
import com.sunlights.common.utils.CommonUtil;
import models.Fund;
import com.sunlights.common.vo.PageVo;
import com.sunlights.core.CodeConst;
import com.sunlights.core.dal.FundDao;
import com.sunlights.core.dal.impl.FundDaoImpl;
import com.sunlights.core.models.FundHistory;
import com.sunlights.core.service.ProductService;
import com.sunlights.core.vo.*;

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
public class ProductServiceImpl implements ProductService {

    private PageService pageService = new PageService();

    private FundDao fundDao = new FundDaoImpl();

    @Override
    public List<ProductVo> findProductRecommends(PageVo pageVo) {
        String currentDate = CommonUtil.dateToString(new Date(), CommonUtil.DATE_FORMAT_LONG);
        String sql = " select pr.recommend_type" +
                ",f.id" +
                ",f.chi_name_abbr" +
                ",f.fund_code" +
                ",f.one_week_profit" +
                ",f.million_of_profit" +
                ",f.min_apply_amount" +
                ",f.init_buyed_count" +
                ",f.invest_period" +
                " from p_product_recommend pr" +
                " left join p_fund f on pr.product_code = f.fund_code and pr.recommend_type = '" + CodeConst.PRODUCT_FUND + "'" +
                " join p_product_manage pm on f.fund_code = pm.product_code" +
                " and pm.product_status = '0'" +
                " and pm.begin_time < '" + currentDate + "'" +
                " and pm.end_date >= '" + currentDate + "'" +
                " where pr.begin_date < '" + currentDate + "'" +
                " and pr.end_date >= '" + currentDate + "'" +
                " and pr.temp_stop_date >= '" + currentDate + "'" +
                " and pr.recommend_flag = '0'" +
                " order by pr.priority_level";
        List<ProductVo> productVos = new ArrayList<ProductVo>();

        List<Object[]> rows = pageService.findNativeBy(sql, pageVo);
        for (Object[] columns : rows) {
            productVos.add(new FundVo(columns));
        }
        return productVos;
    }

    @Override
    public List<FundVo> findFunds(PageVo pageVo) {
        String currentDate = CommonUtil.dateToString(new Date(), CommonUtil.DATE_FORMAT_LONG);
        String jpql = " select new com.sunlights.core.vo.FundVo(f)" +
                " from ProductManage pm , Fund f" +
                " where pm.productCode = f.fundCode" +
                " and pm.productStatus = '0'" +
                " and pm.beginTime < '" + currentDate + "'" +
                " and pm.endDate >= '" + currentDate + "'";

        List<FundVo> fundVos = pageService.findBy(jpql, pageVo);
        return fundVos;
    }

    @Override
    public ProductVo findProductDetailBy(String productCode, String type) {
        ProductVo productVo = null;
        if (CodeConst.PRODUCT_FUND.equals(type)) {
            Fund fund = fundDao.findFundByCode(productCode);
            if (fund != null) {
                FundDetailVo fundDetailVo = new FundDetailVo(fund);
                productVo = fundDetailVo;
            }
        }
        return productVo;
    }

    @Override
    public ChartVo findOneWeekProfitsByDays(String fundCode, int days) {

        List<FundHistory> fundHistories = fundDao.findFundHistoriesByDays(fundCode, days);
        ChartVo chartVo = new ChartVo();
        chartVo.setChartName("七日年化走势");
        chartVo.setChartType("2");
        chartVo.setPrdCode(fundCode);
        if (!fundHistories.isEmpty()) {
            chartVo.setPrdName(fundHistories.get(0).getChiName());
        }
        for (FundHistory fundHistory : fundHistories) {
            chartVo.getPoints().add(new Point(CommonUtil.dateToString(fundHistory.getCreateTime(), CommonUtil.DATE_FORMAT_LONG), ArithUtil.mul(fundHistory.getOneWeekProfit().doubleValue(), 100) + ""));
        }
        return chartVo;
    }

    @Override
    public ChartVo findMillionOfProfitsByDays(String fundCode, int days) {
        List<FundHistory> fundHistories = fundDao.findFundHistoriesByDays(fundCode, days);
        ChartVo chartVo = new ChartVo();
        chartVo.setChartName("万份收益走势");
        chartVo.setChartType("1");
        chartVo.setPrdCode(fundCode);
        if (!fundHistories.isEmpty()) {
            chartVo.setPrdName(fundHistories.get(0).getChiName());
        }
        for (FundHistory fundHistory : fundHistories) {
            chartVo.getPoints().add(new Point(CommonUtil.dateToString(fundHistory.getCreateTime(), CommonUtil.DATE_FORMAT_LONG), fundHistory.getMillionOfProfit() + ""));
        }
        return chartVo;
    }


}
