package com.sunlights.core.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.core.dal.FundDao;
import com.sunlights.core.vo.FundDetailVo;
import models.Fund;
import models.FundCompany;
import models.FundHistory;
import models.FundNavHistory;

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
        jpql.append(" select new com.sunlights.core.vo.FundDetailVo(f,pm,fc)");
        jpql.append(" from ProductManage pm , FundNav f , FundCompany fc");
        jpql.append(" where f.fundcode = pm.productCode");
        jpql.append(" and f.iaGuid = fc.fundCompanyId");
        jpql.append(" and f.fundcode = ?1");

        FundDetailVo fundDetailVo = super.findUnique(jpql.toString(), code);
        return fundDetailVo;
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
    public List<FundHistory> findFundHistoriesByDays(String fundCode, int days) {

        String jpql = " select fh from FundHistory fh where fh.fundCode = '" + fundCode + "' order by fh.createTime desc";

        Query query = super.createQuery(jpql);
        if (days > 0) {
            query.setMaxResults(days);
        }
        return query.getResultList();
    }

    @Override
    public List<FundNavHistory> findFundNavHistoriesByDays(String fundCode, int days) {
        String jpql = " select fh from FundNavHistory fh where fh.fundcode = '" + fundCode + "' order by fh.createTime desc";
        List<FundNavHistory> fundNavHistories = super.find(jpql);
        return fundNavHistories;
    }


}
