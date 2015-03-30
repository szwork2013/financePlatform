package com.sunlights.trade.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.trade.dal.TradeStatusChangeDao;
import com.sunlights.trade.vo.TradeStatusInfoVo;
import models.TradeStatusChange;

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
    public List<TradeStatusInfoVo> findTradeStatusChangeList(String tradeNo) {
        String tradeStatusSql = "select status_change_time,status_desc from t_trade_status_change_info where trade_no = :tradeNo order by create_time desc ";
        Query query = em.createNativeQuery(tradeStatusSql);
        query.setParameter("tradeNo", tradeNo);
        List list = query.getResultList();

        String keys = "timeInfo,desc";
        List<TradeStatusInfoVo> tradeStatusInfoVos = ConverterUtil.convert(keys, list, TradeStatusInfoVo.class);

        return tradeStatusInfoVos;
    }
}
