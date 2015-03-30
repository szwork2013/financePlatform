package com.sunlights.op.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.dal.PageDao;
import com.sunlights.common.dal.impl.PageDaoImpl;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.dal.SmsMessageDao;
import com.sunlights.op.vo.SmsMessageVo;
import play.Logger;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Project: operationplatform</p>
 * <p>Title: SmsMessageDaoImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class SmsMessageDaoImpl extends EntityBaseDao implements SmsMessageDao {

    private PageDao pageDao = new PageDaoImpl();

    @Override
    public List<SmsMessageVo> findSmsMessageVos(PageVo pageVo) {
        List<SmsMessageVo> smsMessageVos = new ArrayList<>();

        String sql = "select to_char(sms.createTime,'yyyy-MM-dd') as sendTime," +
                "count(1) as count," +
                "sms.successInd as sendStatus " +
                "from MessageSmsTxn sms " +
                "where 1 = 1 " +
                "/~ and sms.successInd = {sendStatus} ~/" +
                "/~ and sms.createTime >= {beginTime} ~/" +
                "/~ and sms.createTime < {endTime} ~/" +
                "group by to_char(sms.createTime,'yyyy-MM-dd'),sms.successInd " +
                "order by to_char(sms.createTime,'yyyy-MM-dd')";

        List<Object[]> list = getResultList(pageVo, sql);
        if (!list.isEmpty()){
            smsMessageVos = ConverterUtil.convert("sendTime,count,sendStatus", list, SmsMessageVo.class);
            String countSql = "select count(1) " +
                    "from MessageSmsTxn sms " +
                    "where 1 = 1 " +
                    "/~ and sms.successInd = {sendStatus} ~/" +
                    "/~ and sms.createTime >= {beginTime} ~/" +
                    "/~ and sms.createTime < {endTime} ~/";
            Query query = createQueryByMap(countSql, pageVo.getFilter());
            Logger.info(query.getResultList().toString());
            int allCount = Integer.valueOf(query.getSingleResult().toString());
            pageVo.setCount(allCount);
        }

        return smsMessageVos;
    }

    private List getResultList(PageVo pageVo, String sql) {
        Query query = createQueryByMap(sql, pageVo.getFilter());
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
