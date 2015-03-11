package com.sunlights.account.dal.impl;

import com.sunlights.account.dal.CapitalDao;
import com.sunlights.account.vo.Capital4Product;
import com.sunlights.account.vo.CapitalVo;
import com.sunlights.common.AppConst;
import com.sunlights.common.DictConst;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.dal.PageDao;
import com.sunlights.common.dal.impl.PageDaoImpl;
import com.sunlights.common.utils.ArithUtil;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.vo.PageVo;
import models.HoldCapital;

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

    private List<HoldCapital> getHoldCapitalList(PageVo pageVo) {
        String sql = "select hc " +
                "from HoldCapital hc " +
                "where 1 = 1" +
                " /~ and hc.custId = {customerId} ~/" +
                " /~ and hc.status = {status} ~/" +
                " /~ and hc.productCode = {productCode} ~/" +
                " /~ and hc.holdCapitalType = {holdCapitalType} ~/" +
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

    @Override
    public List<CapitalVo> findCapitalProfitListByCustId(String customerId, PageVo pageVo) {
        pageVo.put("EQS_customerId", customerId);
        pageVo.put("EQS_holdCapitalType", DictConst.HOLDCAPITAL_TYPE_2);

        List<HoldCapital> list = getHoldCapitalList(pageVo);

        List<CapitalVo> capitalVoList = transCapitalVoList(list);
        return capitalVoList;
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

    private List<CapitalVo> transCapitalVoList(List<HoldCapital> list) {
        List<CapitalVo> capitalVoList = new ArrayList<CapitalVo>();
        for (HoldCapital holdCapital : list) {
            CapitalVo capitalVo = new CapitalVo();

            capitalVo.setPrdCode(holdCapital.getProductCode());
            capitalVo.setPrdName(holdCapital.getProductName());
            capitalVo.setYesterdayProfit(ArithUtil.bigToScale2(holdCapital.getYesterdayProfit()));
            capitalVo.setProfitLatestTime(CommonUtil.dateToString(holdCapital.getSettleDate(), CommonUtil.DATE_FORMAT_YYYY_MM_DD_HH_MM));
            capitalVoList.add(capitalVo);
        }
        return capitalVoList;
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
            capital4ProductList.add(capital4Product);
        }
        return capital4ProductList;
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
