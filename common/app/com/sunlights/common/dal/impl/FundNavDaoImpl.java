package com.sunlights.common.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.dal.FundNavDao;
import com.sunlights.common.utils.CommonUtil;
import models.FundNav;

import javax.persistence.Query;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by guxuelong on 2014/12/15.
 */
public class FundNavDaoImpl extends EntityBaseDao implements FundNavDao{
    private static final String START_TIME = " 00:00:00";
    private static final String END_TIME = " 23:59:59";
    private static final String QUERY_BY_UPDATE_DATE = "select t from FundNav t where  t.updateTime >= :dateTimeStart  and t.updateTime <= :dateTimeEnd";

    @Override
    public List<FundNav> queryByUpdateTime(String updateTime) {
        Query query = super.createQuery(QUERY_BY_UPDATE_DATE);
        setTradeTimePara(updateTime,query);
        return query.getResultList();
    }

    private void setTradeTimePara(String dateTime, Query query) {
        query.setParameter("dateTimeStart", getDate(dateTime, START_TIME));
        query.setParameter("dateTimeEnd", getDate(dateTime, END_TIME));
    }
    private Date getDate(String dateTime, String time) {
        try {
            return CommonUtil.stringToDate(dateTime + time, CommonUtil.DATE_FORMAT_LONG);
        } catch (ParseException e) {
            return null;
        }
    }
}
