package com.sunlights.op.service.impl;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.exceptions.BusinessRuntimeException;
import com.sunlights.common.exceptions.ConverterException;
import com.sunlights.common.service.PageService;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.FinancialPlannerService;
import com.sunlights.op.vo.FinancialPlannerCustomerVo;
import com.sunlights.op.vo.FinancialPlannerVo;
import models.FinancialPlanner;
import org.hibernate.SQLQuery;
import org.hibernate.annotations.QueryHints;
import org.hibernate.transform.Transformers;
import play.Logger;
import play.db.jpa.JPA;
import play.libs.Json;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Yuan on 2015/3/6.
 */
public class FinancialPlannerServiceImpl implements FinancialPlannerService {

    private EntityBaseDao entityBaseDao = new EntityBaseDao();

    private PageService pageService = new PageService();

    @Override
    public List<FinancialPlannerVo> findFinancialPlannersBy(PageVo pageVo) {
        StringBuilder xsql = new StringBuilder();

        xsql.append(" select new " + FinancialPlannerVo.class.getPackage().getName() + ".FinancialPlannerVo(u) from FinancialPlanner u");
        xsql.append(" where 1=1");
        xsql.append(" /~ and u.name like {name} ~/ ");
        xsql.append(" /~ and u.mobilePhone like {mobilePhone} ~/ ");
        xsql.append(" order by u.createTime desc");

        List<FinancialPlannerVo> financialPlannerVos = pageService.findXsqlBy(xsql.toString(), pageVo);
        return financialPlannerVos;
    }

    @Override
    public void save(FinancialPlannerVo financialPlannerVo) {

        hasFinancialPlanner(financialPlannerVo);
        FinancialPlanner financialPlanner = financialPlannerVo.convertToFinancialPlanner();
        entityBaseDao.update(financialPlanner);

    }

    @Override
    public List<FinancialPlannerCustomerVo> findPlannerCustomers(PageVo pageVo) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT fp.mobile_phone as \"mobilePhone\",fp.name,c.mobile,c.real_name as \"realName\",ts.total_amount as \"totalAmount\"");
        sql.append(" FROM c_financial_planner fp");
        sql.append(" JOIN c_customer c ON fp.mobile_phone = c.recommend_phone");
        sql.append(" LEFT JOIN");
        sql.append(" (SELECT t.cust_id AS cust_id,SUM(t.trade_amount) AS total_amount FROM t_trade t GROUP BY t.cust_id) ts");
        sql.append(" ON c.customer_id = ts.cust_id");
        sql.append(" where 1=1");
        sql.append(" /~ and fp.mobile_phone like {telephone} ~/ ");
        sql.append(" /~ and c.mobile like {mobile} ~/ ");

        String countSql = "select count(1) from (" + sql.toString() + ") as rs";
        Query countQuery = entityBaseDao.createNativeQueryByMap(countSql, pageVo.getFilter());
        int count = Integer.valueOf(countQuery.getSingleResult().toString());
        pageVo.setCount(count);

        Query nativeQuery = entityBaseDao.createNativeQueryByMap(sql.toString(), pageVo.getFilter());
        nativeQuery.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        nativeQuery.setFirstResult(pageVo.getIndex());
        nativeQuery.setMaxResults(pageVo.getPageSize());
        List<Map<String, Object>> resultList = nativeQuery.getResultList();

        List<FinancialPlannerCustomerVo> financialPlannerCustomerVos = new ArrayList<FinancialPlannerCustomerVo>();
        for (Map<String, Object> row : resultList) {
            try {
                FinancialPlannerCustomerVo financialPlannerCustomerVo = ConverterUtil.convertMap2Object(row, new FinancialPlannerCustomerVo());
                financialPlannerCustomerVos.add(financialPlannerCustomerVo);
            } catch (ConverterException e) {
                e.printStackTrace();
            }
        }
        return financialPlannerCustomerVos;
    }

    private boolean hasFinancialPlanner(FinancialPlannerVo financialPlannerVo) {
        StringBuffer jpql = new StringBuffer();
        jpql.append(" select u from FinancialPlanner u");
        jpql.append(" where u.mobilePhone = '" + financialPlannerVo.getMobilePhone().trim() + "'");
        if (financialPlannerVo.getId() != null) {
            jpql.append(" and u.id <> ").append(financialPlannerVo.getId());
        }
        List<FinancialPlanner> financialPlanners = entityBaseDao.find(jpql.toString());
        if (!financialPlanners.isEmpty()) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.PLANNER_PHONE_EXIST_ERROR));
        }
        return false;
    }

}
