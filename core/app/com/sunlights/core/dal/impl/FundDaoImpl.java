package com.sunlights.core.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.core.dal.FundDao;
import com.sunlights.core.vo.FundDetailVo;
import com.sunlights.core.vo.FundVo;
import models.Fund;
import models.FundHistory;

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

    @Override
    public FundDetailVo findFundDetailByCode(String code) {
        StringBuffer jpql = new StringBuffer();
        jpql.append(" select new com.sunlights.core.vo.FundDetailVo(f,pr,pm,fc)");
        jpql.append(" from ProductManage pm , Fund f ,ProductRecommend pr, FundCompany fc");
        jpql.append(" where f.fundCode = pr.productCode");
        jpql.append(" and f.fundCode = pm.productCode");
        jpql.append(" and f.fundCompanyId = fc.fundCompanyId");
        jpql.append(" and f.fundCode = ?1");

        FundDetailVo fundDetailVo = super.findUnique(jpql.toString(), code);
        return fundDetailVo;
    }

    @Override
    public FundHistory findFundHistoryByCode(String code) {
        List<FundHistory> funds = super.findBy(FundHistory.class, "fundCode", code);
        return funds.isEmpty() ? null : funds.get(0);
    }

    @Override
    public List<FundHistory> findFundHistoriesByDays(String fundCode, int days) {

        String jpql = " select fh from FundHistory fh where fh.fundCode = '" + fundCode + "' order by fh.createTime desc";

        Query query = super.createQuery(jpql);
        if (days > 0) {
            query.setMaxResults(days);
        }
        return query.getResultList();
    }
}
