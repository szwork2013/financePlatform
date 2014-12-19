package com.sunlights.customer.service.impl;

import com.sunlights.customer.dal.ExchangeResultDao;
import com.sunlights.customer.dal.impl.ExchangeResultDaoImpl;
import com.sunlights.customer.service.ExchangeResultService;
import models.ExchangeResult;

/**
 * Created by Administrator on 2014/12/3.
 */
public class ExchangeResultServiceImpl implements ExchangeResultService {
    private ExchangeResultDao exchangeResultDao = new ExchangeResultDaoImpl();

    @Override
    public void save(ExchangeResult exchangeResult) {

        exchangeResultDao.doInsert(exchangeResult);
    }
}
