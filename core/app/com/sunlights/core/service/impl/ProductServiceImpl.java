package com.sunlights.core.service.impl;

import com.sunlights.common.page.PageService;
import com.sunlights.common.page.Pager;
import com.sunlights.common.utils.ArithUtil;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.core.models.Fund;
import com.sunlights.core.service.ProductService;
import com.sunlights.core.vo.FundVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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


    private PageService pageService;

    @Override
    public List<FundVo> findProductRecommends(Pager pager) {
        Date date = new Date();
        StringBuilder sql = new StringBuilder();
        sql.append(" select f.*");
        sql.append(" ,r.recommend_type");
        sql.append(" ,p.chi_name");
        sql.append(" from p_fund f");
        sql.append(" join p_product p on f.product_id = p.id and p.defunct_ind= 'N'");
        sql.append(" join p_product_recommend r on r.product_id = f.id");
        sql.append(" and r.recommend_type = '0'");
        sql.append(" and r.defunct_ind = 'N'");
        sql.append(" and r.begin_date <= '" + CommonUtil.dateToString(date, CommonUtil.PATTEN_DATE_FORMAT_DATETIME) + "'");
        sql.append(" and r.end_date > '" + CommonUtil.dateToString(date, CommonUtil.PATTEN_DATE_FORMAT_DATETIME) + "'");
        sql.append(" and r.temp_stop_date > '" + CommonUtil.dateToString(date, CommonUtil.PATTEN_DATE_FORMAT_DATETIME) + "'");
        sql.append(" where 1=1");
        sql.append(" and f.defunct_ind = 'N'");
        sql.append(" order by r.priority_level");
        List<FundVo> fundVos = new ArrayList<FundVo>();

        List<Object[]> rows = pageService.findNativeBy(sql.toString(), pager);
        FundVo fundVo = null;
        for (Object[] columns : rows) {
            fundVo = new FundVo();
            fundVo.id = columns[0] == null ? 0L : Long.valueOf("" + columns[0]);
            fundVo.fundCode = columns[6] == null ? null : (String) columns[6];
            BigDecimal oneWeekProfit = columns[13] == null ? new BigDecimal(0) : (BigDecimal) columns[13];
            fundVo.sevenDaysIncome = ArithUtil.mul(oneWeekProfit.doubleValue(), 100) + "%";//七日年化
            fundVo.sevenDaysIncomeNum = oneWeekProfit.toString();
            BigDecimal millionOfProfit = columns[10] == null ? new BigDecimal(0) : (BigDecimal) columns[10];
            fundVo.millionIncome = millionOfProfit.doubleValue() + "元";
            fundVo.type = columns[17] == null ? null : (String) columns[17];
            fundVo.name = columns[18] == null ? null : (String) columns[18];
            fundVos.add(fundVo);
        }

        pager.getList().addAll(fundVos);
        return fundVos;
    }

    @Override
    public List<FundVo> findFunds(Pager pager) {
        StringBuffer jpql = new StringBuffer();
        jpql.append(" select f from Fund f");
//        jpql.append(" join fetch f.product");

        List<Fund> funds = pageService.findBy(jpql.toString(), pager);
        List<FundVo> fundVos = new ArrayList<FundVo>();
        for (Fund fund : funds) {
            fundVos.add(new FundVo(fund));
        }
        pager.getList().addAll(fundVos);
        return fundVos;
    }


}
