package com.sunlights.core.dal.impl;

import com.sunlights.common.DictConst;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.service.PageService;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.core.dal.FundDao;
import com.sunlights.core.vo.FundDetailVo;
import com.sunlights.core.vo.FundVo;
import com.sunlights.core.vo.ProductVo;
import models.*;
import play.Logger;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * <p>Project: fsp</p>
 * <p>Title: FundDaoImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class FundDaoImpl extends EntityBaseDao implements FundDao {

    private PageService pageService = new PageService();

    @Override
    public Fund findFundByCode(String code) {
        List<Fund> funds = super.findBy(Fund.class, "fundCode", code);
        return funds.isEmpty() ? null : funds.get(0);
    }

    public FundNav findFundNavByCode(String code) {
        List<FundNav> funds = super.findBy(FundNav.class, "fundcode", code);
        return funds.isEmpty() ? null : funds.get(0);
    }

    @Override
    public FundDetailVo findFundDetailByCode(String code) {
        String sql = "SELECT"
                + "  pm.init_buyed_count,"
                + "  pm.one_month_buyed_count,"
                + "  pm.product_type,"
                + "  pm.recommend_type,"
                + "  pm.recommend_flag,"
                + "  f.fundcode,"
                + "  f.fundname,"
                + "  f.percent_seven_days,"
                + "  f.income_per_ten_thousand,"
                + "  f.purchase_limit_min,"
                + "  f.charge_rate_value,"
                + "  f.discount,"
                + "  f.purchase_state,"
                + "  f.is_monetary,"
                + "  f.is_stf,"
                + "  f.rapid_redeem,"
                + "  f.risk_level,"
                + "  f.lastest_total_asset,"
                + "  f.curr_date,"
                + "  fc.abbr_name,"
                + "  fa.establishment_date,"
                + "  fa.latest_hold_shares,"
                + "  fa.manager,"
                + "  fa.trustee_name"
                + "  FROM p_product_manage pm"
                + "  JOIN fundnav f ON pm.product_code = f.fundcode AND f.fundcode = ?1"
                + "  LEFT JOIN p_fund_company fc ON f.ia_guid = fc.fund_company_id"
                + "  LEFT JOIN fundarchiveex fa ON f.fundcode = fa.fund_code";

        List<Object[]> rows = createNativeQuery(sql, code);

        return rows.isEmpty() ? null : new FundDetailVo(rows.get(0));
    }

    @Override
    public FundHistory findFundHistoryByCode(String code) {
        List<FundHistory> funds = super.findBy(FundHistory.class, "fundCode", code);
        return funds.isEmpty() ? null : funds.get(0);

    }

    @Override
    public FundCompany findFundCompanyById(String id) {
        return findUniqueBy(FundCompany.class, "fundCompanyId", id);
    }

    @Override
    public void addProductPurchasedNum(String productCode) {
        String sql = "update p_product_manage set init_buyed_count = init_buyed_count + 1, one_month_buyed_count = one_month_buyed_count + 1  where product_code = ?1";
        Query query = em.createNativeQuery(sql, ProductManage.class);
        query.setParameter(1, productCode);
        query.executeUpdate();
    }


    @Override
    public List<FundProfitHistory> findFundProfitHistoryByDays(String fundCode, int days) {
        Logger.info("findFundProfitHistoryByDays start....");

        String jpql = "select fh from FundProfitHistory fh ,Code c where  fh.fundCode=c.code  and fh.fundCode = :fundCode order by fh.dateTime desc";

        Query query = super.createQuery(jpql);
        query.setParameter("fundCode", fundCode);
        Logger.info("days: " + days);
        if (days > 0) {
            query.setFirstResult(0);
            query.setMaxResults(days);
        }
        return query.getResultList();
    }

    @Override
    public Code findFundNameByFundCode(String fundCode) {
        List<Code> funds = super.findBy(Code.class, "code", fundCode);
        return funds.isEmpty() ? null : funds.get(0);
    }

    @Override
    public List<ProductVo> findProductIndex(PageVo pageVo) {
        String currentDate = CommonUtil.dateToString(new Date(), CommonUtil.DATE_FORMAT_LONG);
        StringBuilder xsql = new StringBuilder();
        xsql.append(" select new com.sunlights.core.vo.FundVo(f,pm)");
        xsql.append(" from FundNav f , ProductManage pm");
        xsql.append(" where f.fundcode = pm.productCode");
        xsql.append(" and pm.upBeginTime < '" + currentDate + "'");
        xsql.append(" and pm.downEndTime >= '" + currentDate + "'");
        xsql.append(" and pm.recommendType = '" + DictConst.FP_RECOMMEND_TYPE_1 + "'");
        xsql.append(" and pm.productStatus = '" + DictConst.FP_PRODUCT_MANAGE_STATUS_1 + "'");
        xsql.append(" order by pm.priorityLevel");

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
                " and pm.upBeginTime < '" + currentDate + "'" +
                " and pm.downEndTime >= '" + currentDate + "'" +
                "/~ and pm.productType = {productType} ~/" +
                "/~ and f.fundType = {fundType} ~/" +
                "/~ and f.isMonetary = {isMonetary} ~/" +
                "/~ and f.isStf = {isStf} ~/" +
                " order by pm.recommendType,pm.recommendFlag,pm.priorityLevel,f.percentSevenDays desc,f.fundcode";

        List<FundVo> fundVos = pageService.findXsqlBy(jpql, pageVo);
        return fundVos;
    }

}
