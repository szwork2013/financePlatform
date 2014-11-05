package com.sunlights.account.dal.impl;

import com.sunlights.account.dal.CapitalDao;
import com.sunlights.account.vo.Capital4Product;
import com.sunlights.account.vo.CapitalFormVo;
import com.sunlights.account.vo.HoldCapitalVo;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.dal.PageDao;
import com.sunlights.common.dal.impl.PageDaoImpl;
import com.sunlights.common.utils.ArithUtil;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.vo.PageVo;
import models.HoldCapital;

import javax.persistence.Query;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 资产Dao实现
 * @author tangweiqun 2014/10/22
 *
 */

public class CapitalDaoImpl extends EntityBaseDao implements CapitalDao {
    private PageDao pageDao = new PageDaoImpl();
	
	@Override
	public List<Capital4Product> findHoldCapitalsByCustId(String custId, PageVo pageVo) {
        String sql = "select hc.product_code,hc.product_name,hc.total_profit,hc.hold_capital,hc.product_type " +
                    " FROM f_holdcapital hc " +
                    " where hc.cust_id = :custId " +
                    " and   hc.status = :status " +
                    " order by hc.create_time desc";
        Query query = em.createNativeQuery(sql);
        query.setParameter("custId", custId);
        query.setParameter("status", "N");
        query.setFirstResult(pageVo.getIndex());
        query.setMaxResults(pageVo.getPageSize());

        List<Object[]> list = query.getResultList();
        
        if (list == null || list.size() == 0) {
            return null;
        }

        countCapital4Product(custId, pageVo);

        List<Capital4Product> capital4ProductList = transCapital4Products(list);
        return capital4ProductList;
	}

    private List<Capital4Product> transCapital4Products(List<Object[]> list) {
        List<Capital4Product> capital4ProductList = new ArrayList<Capital4Product>();
        for (Object[] row : list) {
            Capital4Product capital4Product = new Capital4Product();

            capital4Product.setPrdCode(row[0] == null ? null : row[0].toString());
            capital4Product.setPrdName(row[1] == null ? null : row[1].toString());
            if (row[2] != null) {
                BigDecimal totalProfit = (BigDecimal)row[2];
                capital4Product.setTotalProfit(ArithUtil.bigToScale2(totalProfit));
            }
            if (row[3] != null) {
                BigDecimal marketValue = (BigDecimal)row[3];
                capital4Product.setMarketValue(ArithUtil.bigToScale2(marketValue));
            }
            capital4Product.setPrdType(row[4] == null ? null : row[4].toString());
//            capital4Product.setPrdTypeDesc();//TODO
            capital4ProductList.add(capital4Product);
        }
        return capital4ProductList;
    }

    private void countCapital4Product(String custId, PageVo pageVo) {
        String sql;
        Query query;
        sql = "select count(1) from f_holdcapital hc where hc.cust_id = ?0 and hc.status = ?1";
        query = em.createNativeQuery(sql);
        query.setParameter(0, custId);
        query.setParameter(1, "N");
        String count = query.getSingleResult().toString();
        pageVo.setCount(Integer.valueOf(count));
    }

    public HoldCapital findHoldCapitalsById(Long id){
        return super.find(HoldCapital.class, id);
    }

    public List<HoldCapital> findHoldCapitalsByProductCode(String customerId, CapitalFormVo capitalFormVo){
        Query query = em.createQuery("select c FROM HoldCapital c where c.custId = ?0 and c.productCode = ?1 order by c.createTime desc", HoldCapital.class);
        query.setParameter(0, customerId);
        query.setParameter(1, capitalFormVo.getPrdCode());
        query.setFirstResult(capitalFormVo.getIndex());
        query.setMaxResults(capitalFormVo.getPageSize());
        List<HoldCapital> list = query.getResultList();
        return list;
    }

    public HoldCapitalVo findCapitalProductDetail(String prdType, String prdCode){
        String sql = "select hc.product_code,hc.product_name,hc.hold_capital,hc.total_profit,hc.settle_date,hc.trade_amount" +
                    " from f_holdcapital hc " +
                    " where hc.product_code = ?0 " +
                    " and hc.status = ?1";
        Query holdCapitalSql = em.createNativeQuery(sql);
        holdCapitalSql.setParameter(0, prdCode);
        holdCapitalSql.setParameter(1, 'N');
        List<Object[]> list = holdCapitalSql.getResultList();
        HoldCapitalVo holdCapitalVo = transHoldCapitalVo(list);

        if ("0".equals(prdType)) {
            String fundSql = "select f.million_of_profit,f.one_week_profit" +
                            " from p_fund_history f " +
                            " where f.fund_code = ?0 " +
                            " order by f.create_time desc" +
                            " limit 1 offset 0";
            Query fundQuery = em.createNativeQuery(fundSql);
            fundQuery.setParameter(0, prdCode);

            list = fundQuery.getResultList();
            if (list != null && list.size() > 0) {
                for (Object[] row : list) {
                    holdCapitalVo.setMillionOfProfit(row[0] == null ? null : row[0].toString());
                    holdCapitalVo.setOneWeekProfit(row[1] == null ? null : row[1].toString());
                }
            }
        }

        return holdCapitalVo;
    }

    private HoldCapitalVo transHoldCapitalVo(List<Object[]> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        HoldCapitalVo holdCapitalVo = new HoldCapitalVo();
        for (Object[] row : list) {
            holdCapitalVo.setPrdCode(row[0] == null ? null : row[0].toString());
            holdCapitalVo.setPrdName(row[1] == null ? null : row[1].toString());
            if (row[2] != null) {
                BigDecimal marketValue = (BigDecimal)row[2];
                holdCapitalVo.setMarketValue(ArithUtil.bigToScale2(marketValue));
            }
            if (row[3] != null) {
                BigDecimal totalProfit = (BigDecimal)row[3];
                holdCapitalVo.setTotalProfit(ArithUtil.bigToScale2(totalProfit));
            }
            if (row[4] != null) {
                Timestamp profitLatestTime = (Timestamp)row[4];
                holdCapitalVo.setProfitLatestTime(CommonUtil.dateToString(profitLatestTime, CommonUtil.DATE_FORMAT_YYYY_MM_DD_HH_MM));
            }
            holdCapitalVo.setHoldQuotient(row[5] == null ? null : row[5].toString());

        }
        return holdCapitalVo;
    }


}
