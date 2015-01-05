package com.sunlights.core.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.core.dal.FundDao;
import com.sunlights.core.vo.FundDetailVo;
import models.*;
import play.Logger;

import javax.persistence.Query;
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
    @Override
    public Fund findFundByCode(String code) {
        List<Fund> funds = super.findBy(Fund.class, "fundCode", code);
        return funds.isEmpty() ? null : funds.get(0);
    }

    public FundNav findFundNavByCode(String code){
        List<FundNav> funds = super.findBy(FundNav.class, "fundcode", code);
        return funds.isEmpty() ? null : funds.get(0);
    }

    @Override
    public FundDetailVo findFundDetailByCode(String code) {
		String sql ="SELECT"
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
        Logger.info("days: "+days);
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


}
