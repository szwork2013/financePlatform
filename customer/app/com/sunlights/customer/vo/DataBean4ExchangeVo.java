package com.sunlights.customer.vo;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: DataBean4ExchangeVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class DataBean4ExchangeVo implements Serializable {
    private String rate;//元：金豆=1：100
    private List<String> exchangeList = Lists.newArrayList();//可兑换金额

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public List<String> getExchangeList() {
        return exchangeList;
    }

    public void setExchangeList(List<String> exchangeList) {
        this.exchangeList = exchangeList;
    }


}
