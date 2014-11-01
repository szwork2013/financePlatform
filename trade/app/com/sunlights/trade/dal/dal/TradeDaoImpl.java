package com.sunlights.trade.dal.dal;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.trade.dal.TradeDao;
import com.sunlights.trade.vo.TradeInfoVo;

import javax.persistence.Query;
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

public class TradeDaoImpl extends EntityBaseDao implements TradeDao{
    @Override
    public List<TradeInfoVo> getTradeListByCustomerId(String customerId, String productType) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("productType", productType);
        
        Query query = entityManager.createQuery(com.sunlights.trade.dal.sqlmap.txt.tradeVo.render("TradeDao.getTradeListByCustomerId", params).body(), TradeInfoVo.class);
        query.setParameter("customerId", customerId);
        List<TradeInfoVo> list = query.getResultList();

        return list;
    }


}
