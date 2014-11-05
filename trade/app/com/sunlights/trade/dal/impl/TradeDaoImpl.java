package com.sunlights.trade.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.dal.PageDao;
import com.sunlights.common.dal.impl.PageDaoImpl;
import com.sunlights.common.utils.ArithUtil;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.trade.dal.TradeDao;
import com.sunlights.trade.vo.TradeFormVo;
import com.sunlights.trade.vo.TradeVo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Project: fsp</p>
 * <p>Title: TradeDaoImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class TradeDaoImpl extends EntityBaseDao implements TradeDao {

    private PageDao pageDao = new PageDaoImpl();

    @Override
    public List<TradeVo> getTradeListByCustomerId(String customerId, TradeFormVo tradeFormVo, PageVo pageVo) {
        String productCode = tradeFormVo.getPrdCode();
        String productType = tradeFormVo.getPrdType();

        Map<String, Object> filter = new HashMap<String, Object>();
        filter.put("0", customerId);

        String querySql = null;
        if (productCode == null) {
            querySql = "select t.product_name,t.trade_no,t.trade_time,t.trade_amount,t.trade_status,t.type,t.product_code" +
                    " from t_trade t " +
                    " where t.cust_id = ?0 " +
                    " order by t.trade_time desc";
        }else{
            querySql = "select t.product_name,t.trade_no,t.trade_time,t.trade_amount,t.trade_status,t.type,t.product_code" +
                    " from t_trade t " +
                    " where t.cust_id = ?0 " +
                    " and t.product_code = ?1" +
                    " order by t.trade_time desc";
            filter.put("1", productCode);

        }
        pageVo.setFilter(filter);
        List<Object[]> list = pageDao.findNativeBy(querySql, pageVo);

        return transTradeVo(list);
    }

    private List<TradeVo> transTradeVo(List<Object[]> list){
        if (list == null || list.size() == 0) {
            return null;
        }
        List<TradeVo> tradeVos = new ArrayList<TradeVo>();
        for (Object[] row : list) {
            TradeVo tradeVo = new TradeVo();
            tradeVo.setPrdName(row[0] == null ? null : row[0].toString());
            tradeVo.setTradeNo(row[1] == null ? null : row[1].toString());
            if (row[2] != null) {
                Timestamp tradeTime = (Timestamp)row[2];
                tradeVo.setTradeTime(CommonUtil.dateToString(tradeTime, CommonUtil.DATE_FORMAT_YYYY_MM_DD_HH_MM));
            }
            if (row[3] != null) {
                BigDecimal tradeAmount = (BigDecimal)row[3];
                tradeVo.setTradeAmount(ArithUtil.bigToScale2(tradeAmount.abs()));
            }
            tradeVo.setTradeStatus(row[4] == null ? null : row[4].toString());
            tradeVo.setTradeType(row[5] == null ? null : row[5].toString());
            tradeVo.setPrdCode(row[6] == null ? null : row[6].toString());

            tradeVos.add(tradeVo);
        }

        return tradeVos;
    }




}
