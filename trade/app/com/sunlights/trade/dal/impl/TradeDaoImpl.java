package com.sunlights.trade.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.trade.dal.TradeDao;
import models.Trade;

import javax.persistence.Query;
import java.util.List;

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
    @Override
    public List<Trade> getTradeListByCustomerId(String customerId, String productCode) {
        Query query = null;
        if (productCode == null) {
            String querySql = "select t from Trade t where t.custId = ?0 order by t.tradeTime desc";
            query = em.createQuery(querySql, Trade.class);
            query.setParameter(0, customerId);
        }else{
            String querySql = "select t from Trade t where t.custId = ?0 and t.productCode = ?1 order by t.tradeTime desc";
            query = em.createQuery(querySql, Trade.class);
            query.setParameter(0, customerId);
            query.setParameter(1, productCode);
        }

        List<Trade> list = query.getResultList();

        return list;
    }


}
