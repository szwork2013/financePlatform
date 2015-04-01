package com.sunlights.op.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.dal.PageDao;
import com.sunlights.common.dal.impl.PageDaoImpl;
import com.sunlights.common.service.CommonService;
import com.sunlights.common.utils.ArithUtil;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.dal.SettleAccountDao;
import com.sunlights.op.vo.SettleAccountVo;
import play.Logger;

import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Project: operationplatform</p>
 * <p>Title: SettleAccountDaoImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class SettleAccountDaoImpl extends EntityBaseDao implements SettleAccountDao {
    private PageDao pageDao = new PageDaoImpl();

    @Override
    public List<SettleAccountVo> findSettleAccountVos(PageVo pageVo) {
        String sql = "select " +
                " to_char(t.trade_time,'yyyy-MM-dd') as tradeTime," +
                " t.trade_no as tradeNo," +
                " t.product_code as productCode," +
                " t.trade_status as tradeStatus," +
                " t.pay_status as payStatus," +
                " t.type as tradeType," +
                " t.trade_amount as tradeAmount," +
                " fhh.fund_type as productType," +
                " (select pc.company_name from p_fund_company pc where fhh.ia_guid = pc.fund_company_id ) as companyName " +
                " from t_trade t, (select distinct fh.fundcode,fh.ia_guid,fh.fund_type from fundnav_history fh) as fhh " +
                " where t.product_code = fhh.fundcode " +
                " /~ and t.product_code = {productCode} ~/" +
                " /~ and t.trade_no = {tradeNo} ~/" +
                " /~ and t.trade_status = {tradeStatus} ~/" +
                " /~ and t.type = {tradeType} ~/" +
                " /~ and fhh.fund_type||'' = {productType} ~/" +
                " /~ and t.trade_time >= {beginTime} ~/" +
                " /~ and t.trade_time < {endTime} ~/";

        Logger.debug(sql);
        List<Object[]> list = getResultList(pageVo, sql);
        List<SettleAccountVo> settleAccountVoList = new ArrayList<SettleAccountVo>();

        if (!list.isEmpty()){
            String keys = "tradeTime,tradeNo,productCode,tradeStatus,payStatus,tradeType,tradeAmount,productType,companyName";
            settleAccountVoList = ConverterUtil.convert(keys, list, SettleAccountVo.class);
            i18n(settleAccountVoList);

            getAllCount(pageVo);
        }

        return settleAccountVoList;
    }

    @Override
    public List<SettleAccountVo> findSettleAccountStatisic(PageVo pageVo) {
        String sql = "select  " +
                " to_char(t.trade_time,'yyyy-MM-dd') as tradeTime, " +
                " t.product_code as productCode, " +
                " t.trade_status as tradeStatus," +
                " sum(t.trade_amount) as tradeAmount," +
                " fhh.fund_type as productType " +
                " fhh.fundname as productName, " +
                " from t_trade t, (select distinct fh.fundcode,fh.fund_type from fundnav_history fh) as fhh " +
                " where t.product_code = fhh.fundcode " +
                " /~ and t.trade_time >= {beginTime} ~/" +
                " /~ and t.trade_time < {endTime} ~/" +
                " group by tradeTime,productCode,tradeStatus,productType";
        Logger.debug(sql);
        List<Object[]> list = getResultList(pageVo, sql);
        List<SettleAccountVo> settleAccountVoList = new ArrayList<SettleAccountVo>();

        if (!list.isEmpty()){
            String keys = "tradeTime,productCode,tradeStatus,tradeAmount,productType,productName";
            settleAccountVoList = ConverterUtil.convert(keys, list, SettleAccountVo.class);
            i18n(settleAccountVoList);
            getAllCount(pageVo);
        }

        return settleAccountVoList;
    }

    private void i18n(List<SettleAccountVo> settleAccountVoList) {
        CommonService commonService = new CommonService();
        for (SettleAccountVo settleAccountVo : settleAccountVoList) {
            settleAccountVo.setTradeStatusDesc(commonService.findValueByCatPointKey(settleAccountVo.getTradeStatus()));
            settleAccountVo.setPayStatusDesc(commonService.findValueByCatPointKey(settleAccountVo.getPayStatus()));
            settleAccountVo.setTradeTypeDesc(commonService.findValueByCatPointKey(settleAccountVo.getTradeType()));
            if ("7".equals(settleAccountVo.getProductType())) {//TODO
                settleAccountVo.setProductTypeDesc("货币型");
            }else if ("6".equals(settleAccountVo.getProductType())) {
                settleAccountVo.setProductTypeDesc("创新型");
            }else if ("8".equals(settleAccountVo.getProductType())) {
                settleAccountVo.setProductTypeDesc("集合理财");
            }else if ("6".equals(settleAccountVo.getProductType())) {
                settleAccountVo.setProductTypeDesc("创新型");
            }else if ("2".equals(settleAccountVo.getProductType())) {
                settleAccountVo.setProductTypeDesc("基金");
            }else if ("0".equals(settleAccountVo.getProductType())) {
                settleAccountVo.setProductTypeDesc("短期理财");
            }
            settleAccountVo.setTradeAmount(ArithUtil.bigToScale2(new BigDecimal(settleAccountVo.getTradeAmount())));
        }
    }

    private void getAllCount(PageVo pageVo) {
        String countSql = " select count(1) " +
                " from t_trade t, (select distinct fh.fundcode from fundnav_history fh) as fhh" +
                " where t.product_code = fhh.fundcode " +
                " /~ and t.product_code = {fundCode} ~/" +
                " /~ and t.trade_no = {tradeNo} ~/" +
                " /~ and t.trade_status = {tradeStatus} ~/" +
                " /~ and t.type = {tradeType} ~/" +
                " /~ and fhh.fund_type||'' = {fundType} ~/" +
                " /~ and t.trade_time >= {beginTime} ~/" +
                " /~ and t.trade_time < {endTime} ~/";
        Query query = createNativeQueryByMap(countSql, pageVo.getFilter());
        Logger.info(query.getResultList().toString());
        int allCount = Integer.valueOf(query.getSingleResult().toString());
        pageVo.setCount(allCount);
    }

    private List getResultList(PageVo pageVo, String sql) {
        Query query = createNativeQueryByMap(sql, pageVo.getFilter());
        int index = pageVo.getIndex();
        int pageSize = pageVo.getPageSize();
        if (index > 0) {
            query.setFirstResult(index);
        }
        if (pageSize > 0) {
            query.setMaxResults(pageSize);
        }
        List list = query.getResultList();

        return list;
    }
}
