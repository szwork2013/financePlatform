package com.sunlights.account.dal.impl;

import com.sunlights.account.dal.CapitalDao;
import com.sunlights.account.vo.Capital4Product;
import com.sunlights.account.vo.CapitalFormVo;
import com.sunlights.account.vo.HoldCapitalVo;
import com.sunlights.common.AppConst;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.dal.PageDao;
import com.sunlights.common.dal.impl.PageDaoImpl;
import com.sunlights.common.utils.ArithUtil;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.vo.PageVo;
import models.HoldCapital;

import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 资产Dao实现
 *
 * @author tangweiqun 2014/10/22
 */

public class CapitalDaoImpl extends EntityBaseDao implements CapitalDao {
    private PageDao pageDao = new PageDaoImpl();

    private List<HoldCapital> getHoldCapitalList(PageVo pageVo){
        String sql = "select hc " +
                "from HoldCapital hc " +
                "where 1 = 1" +
                " /~ and hc.custId = {customerId} ~/" +
                " /~ and hc.status = {status} ~/" +
                " /~ and hc.productCode = {productCode} ~/" +
                " order by hc.createTime desc";
        List<HoldCapital> list = pageDao.findXsqlBy(sql, pageVo);

        return list;
    }

    @Override
    public List<Capital4Product> findHoldCapitalsByCustId(String customerId, PageVo pageVo) {
        pageVo.put("EQS_customerId", customerId);
        pageVo.put("EQS_status", AppConst.STATUS_VALID);
        List<HoldCapital> list = getHoldCapitalList(pageVo);

        List<Capital4Product> capital4ProductList = transCapital4Products(list);
        return capital4ProductList;
    }

    public List<HoldCapital> findHoldCapitalsByProductCode(String customerId, CapitalFormVo capitalFormVo) {
        PageVo pageVo = new PageVo();
        pageVo.put("EQS_customerId", customerId);
        pageVo.put("EQS_productCode", capitalFormVo.getPrdCode());
        pageVo.setIndex(capitalFormVo.getIndex());
        pageVo.setPageSize(capitalFormVo.getPageSize());

        List<HoldCapital> list = getHoldCapitalList(pageVo);
        return list;
    }

    @Override
    public HoldCapital findHoldCapital(String customerId, String productCode) {
        PageVo pageVo = new PageVo();
        pageVo.put("EQS_customerId", customerId);
        pageVo.put("EQS_productCode", productCode);
        pageVo.put("EQS_status", AppConst.STATUS_VALID);

        List<HoldCapital> list = getHoldCapitalList(pageVo);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    public HoldCapitalVo findCapitalProductDetail(String prdType, String productCode) {
        PageVo pageVo = new PageVo();
        pageVo.put("EQS_productCode", productCode);
        pageVo.put("EQS_status", AppConst.STATUS_VALID);

        HoldCapitalVo holdCapitalVo = transHoldCapitalVo(getHoldCapitalList(pageVo));

        if ("0".equals(prdType)) {//TODO
            String fundSql = "select f.million_of_profit,f.one_week_profit" +
                    " from p_fund_history f " +
                    " where f.fund_code = ?0 " +
                    " order by f.create_time desc" +
                    " limit 1 offset 0";
            Query fundQuery = em.createNativeQuery(fundSql);
            fundQuery.setParameter(0, productCode);

            List<Object[]> list = fundQuery.getResultList();
            for (Object[] row : list) {
                holdCapitalVo.setMillionOfProfit(row[0] == null ? null : row[0].toString());
                holdCapitalVo.setOneWeekProfit(row[1] == null ? null : row[1].toString());
            }
        }

        return holdCapitalVo;
    }
    private List<Capital4Product> transCapital4Products(List<HoldCapital> list) {
        List<Capital4Product> capital4ProductList = new ArrayList<Capital4Product>();
        for (HoldCapital holdCapital : list) {
            if (BigDecimal.ZERO.compareTo(holdCapital.getHoldCapital()) == 0) {
                continue;
            }
            
            Capital4Product capital4Product = new Capital4Product();

            capital4Product.setPrdCode(holdCapital.getProductCode());
            capital4Product.setPrdName(holdCapital.getProductName());
            capital4Product.setTotalProfit(ArithUtil.bigToScale2(holdCapital.getTotalProfit()));
            capital4Product.setMarketValue(ArithUtil.bigToScale2(holdCapital.getHoldCapital()));
            capital4Product.setPrdType(holdCapital.getProductType());
            capital4Product.setPrdTypeDesc("基金");//TODO
            capital4ProductList.add(capital4Product);
        }
        return capital4ProductList;
    }
    private HoldCapitalVo transHoldCapitalVo(List<HoldCapital> list) {
        HoldCapitalVo holdCapitalVo = new HoldCapitalVo();
        for (HoldCapital holdCapital : list) {
            holdCapitalVo.setPrdCode(holdCapital.getProductCode());
            holdCapitalVo.setPrdName(holdCapital.getProductName());
            holdCapitalVo.setMarketValue(ArithUtil.bigToScale2(holdCapital.getHoldCapital()));
            holdCapitalVo.setTotalProfit(ArithUtil.bigToScale2(holdCapital.getTotalProfit()));
            holdCapitalVo.setProfitLatestTime(CommonUtil.dateToString(holdCapital.getSettleDate(), CommonUtil.DATE_FORMAT_YYYY_MM_DD_HH_MM));
            holdCapitalVo.setHoldQuotient(holdCapital.getTradeAmount().toString());
        }
        return holdCapitalVo;
    }


    @Override
    public HoldCapital saveHoldCapital(HoldCapital holdCapital) {
        return create(holdCapital);
    }

    @Override
    public HoldCapital updateHoldCapital(HoldCapital holdCapital) {
        return update(holdCapital);
    }

}
