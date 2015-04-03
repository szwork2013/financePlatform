package com.sunlights.trade.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.trade.dal.TradeStatusChangeDao;
import com.sunlights.trade.vo.TradeForecastDetailVo;
import models.TradeStatusChange;
import play.Logger;

import javax.persistence.Query;
import java.util.List;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: TradeStatusChangeDaoImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class TradeStatusChangeDaoImpl extends EntityBaseDao implements TradeStatusChangeDao {
    @Override
    public TradeStatusChange createTradeStatusChange(TradeStatusChange tradeStatusChange) {
        return create(tradeStatusChange);
    }

    @Override
    public TradeStatusChange updateTradeStatusChange(TradeStatusChange tradeStatusChange) {
        return update(tradeStatusChange);
    }

    @Override
    public List<TradeForecastDetailVo> findTradeStatusChangeList(String tradeNo, String productCode, String tradeType) {
        String tradeStatusSql = "select status_change_time,status_desc,finished_status " +
                                "  from t_trade_status_change_info " +
                                " where trade_no = :tradeNo " +
                                "   and product_code = :productCode " +
                                "   and trade_type = :tradeType " +
                                " order by trade_status";
        Logger.info(tradeStatusSql);
        Query query = em.createNativeQuery(tradeStatusSql);
        query.setParameter("tradeNo", tradeNo);
        query.setParameter("productCode", productCode);
        query.setParameter("tradeType", tradeType);
        List list = query.getResultList();

        String keys = "time,desc,completeInd";
        List<TradeForecastDetailVo> tradeStatusInfoVos = ConverterUtil.convert(keys, list, TradeForecastDetailVo.class);

        return tradeStatusInfoVos;
    }
}
