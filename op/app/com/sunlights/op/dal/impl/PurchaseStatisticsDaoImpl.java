package com.sunlights.op.dal.impl;

import com.sunlights.common.DictConst;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.dal.PurchaseStatisticsDao;
import com.sunlights.op.vo.PurchaseStatisticsVo;
import com.sunlights.op.vo.TradeSummaryVo;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by Yuan on 2015/3/26.
 */
public class PurchaseStatisticsDaoImpl extends EntityBaseDao implements PurchaseStatisticsDao {

    private static final String tradeSummaryVoProperties = "tradeDate,dayInAmount,inAmountTotal,dayOutAmount,outAmountTotal,registrationDate,registrationCount,registrationTotal,purchaseDate,inCustomerCount,totalInCustomerCount,outCustomerCount,totalOutCustomerCount";
    private static final StringBuffer fromTradeSummaryView = new StringBuffer();
    static {
        fromTradeSummaryView.append(" FROM total_trade_summary v");
        fromTradeSummaryView.append(" LEFT JOIN view_total_purchase_customer vp ON v.summary_date = vp.vw_purchase_date");
        fromTradeSummaryView.append(" LEFT JOIN view_total_registration_info vr ON v.summary_date = vr.vw_registration_date");
        fromTradeSummaryView.append(" WHERE 1=1");
        fromTradeSummaryView.append("  /~and v.summary_date >= {beginTime}~/ ");
        fromTradeSummaryView.append("  /~and v.summary_date <= {endTime}~/ ");
    }

    @Override
    public List<PurchaseStatisticsVo> findFirstPurchaseVos(PageVo pageVo) {
        Object beginTime = pageVo.get("GED_beginTime");
        if (beginTime != null) {
            pageVo.put("GED_beginTime", CommonUtil.stringToDateTime(beginTime.toString()));
        }
        Object endTime = pageVo.get("LTD_endTime");
        if (endTime != null) {
            pageVo.put("LTD_endTime", CommonUtil.stringToDateTime(endTime.toString()));
        }

        StringBuffer xsql = new StringBuffer();
        xsql.append(" SELECT DISTINCT ON (t.cust_id)v.cust_id,c.create_time,v.trade_amount,v.bank_name,c.mobile,c.real_name,c.recommend_phone,r.real_name as referrer_name");
        xsql.append(" FROM t_trade t");
        xsql.append(" JOIN c_customer c ONv.cust_id = c.customer_id");
        xsql.append(" LEFT JOIN c_customer r ON c.recommend_phone = r.mobile");
        xsql.append(" WHEREv.type = '").append(DictConst.TRADE_TYPE_1).append("'");
        xsql.append("  /~and c.mobile like {mobile}~/ ");
        xsql.append("  /~and c.create_time >= {beginTime}~/ ");
        xsql.append("  /~and c.create_time <= {endTime}~/ ");
        xsql.append(" ORDER BYv.cust_id,v.create_time");

        String countSql = "select count(1) from (" + xsql.toString() + ") as rs";
        Query query = createNativeQueryByMap(countSql, pageVo.getFilter());
        int count = Integer.valueOf(query.getSingleResult().toString());
        pageVo.setCount(count);

        String querySql = "select * from (" + xsql.toString() + ") as rs order by rs.create_time desc";
        query = createNativeQueryByMap(querySql, pageVo.getFilter());
        query.setFirstResult(pageVo.getIndex());
        query.setMaxResults(pageVo.getPageSize());
        List list = query.getResultList();

        String fields = "customerId,registrationDate,tradeAmount,bankName,mobile,name,referrerMobile,referrerName";
        List<PurchaseStatisticsVo> purchaseStatisticsVos = ConverterUtil.convert(fields, list, PurchaseStatisticsVo.class);

        return purchaseStatisticsVos;
    }

    @Override
    public List<PurchaseStatisticsVo> findUnPurchaseVos(PageVo pageVo) {
        Object beginTime = pageVo.get("GED_beginTime");
        if (beginTime != null) {
            pageVo.put("GED_beginTime", CommonUtil.stringToDateTime(beginTime.toString()));
        }
        Object endTime = pageVo.get("LTD_endTime");
        if (endTime != null) {
            pageVo.put("LTD_endTime", CommonUtil.stringToDateTime(endTime.toString()));
        }
        StringBuffer xsql = new StringBuffer();
        xsql.append(" SELECT count(1) FROM c_customer c");
        xsql.append(" LEFT JOIN c_customer r ON c.recommend_phone = r.mobile");
        xsql.append(" WHERE 1=1");
        xsql.append("  /~and c.mobile like {mobile}~/ ");
        xsql.append("  /~and c.create_time >= {beginTime}~/ ");
        xsql.append("  /~and c.create_time <= {endTime}~/ ");
        xsql.append(" AND c.customer_id NOT IN");
        xsql.append(" (");
        xsql.append(" SELECTv.cust_id FROM t_trade t WHEREv.type = '" + DictConst.TRADE_TYPE_1 + "'");
        xsql.append(" )");

        Query query = createNativeQueryByMap(xsql.toString(), pageVo.getFilter());
        int count = Integer.valueOf(query.getSingleResult().toString());
        pageVo.setCount(count);

        String querySql = xsql.append(" order by c.create_time desc").toString()
                .replace("count(1)", "c.customer_id,c.create_time,c.real_name,c.mobile,c.recommend_phone,r.real_name as referrerName");
        query = createNativeQueryByMap(querySql, pageVo.getFilter());
        query.setFirstResult(pageVo.getIndex());
        query.setMaxResults(pageVo.getPageSize());
        List list = query.getResultList();

        String fields = "customerId,registrationDate,name,mobile,referrerMobile,referrerName";
        List<PurchaseStatisticsVo> purchaseStatisticsVos = ConverterUtil.convert(fields, list, PurchaseStatisticsVo.class);

        return purchaseStatisticsVos;
    }

    @Override
    public List<TradeSummaryVo> findTradeSummaryVos(PageVo pageVo) {
        Object beginTime = pageVo.get("GED_beginTime");
        if (beginTime != null) {
            pageVo.put("GED_beginTime", CommonUtil.stringToDateTime(beginTime.toString()));
        }
        Object endTime = pageVo.get("LTD_endTime");
        if (endTime != null) {
            pageVo.put("LTD_endTime", CommonUtil.stringToDateTime(endTime.toString()));
        }

        setTradeSummaryCount(pageVo, fromTradeSummaryView);
        List list = findTradeSummaryViewData(pageVo, fromTradeSummaryView);

        List<TradeSummaryVo> tradeSummaryVos = ConverterUtil.convert(tradeSummaryVoProperties, list, TradeSummaryVo.class);
        return tradeSummaryVos;
    }

    private void setTradeSummaryCount(PageVo pageVo, StringBuffer fromTradeSummaryView) {
        StringBuffer tradeSummaryCount = new StringBuffer();
        tradeSummaryCount.append(" SELECT count(1)");
        Query query = createNativeQueryByMap(tradeSummaryCount.append(fromTradeSummaryView).toString(), pageVo.getFilter());
        int count = Integer.valueOf(query.getSingleResult().toString());
        pageVo.setCount(count);
    }

    private List findTradeSummaryViewData(PageVo pageVo, StringBuffer fromTradeSummaryView) {
        Query query;
        StringBuffer selectColumsFromTradeSummaryView = new StringBuffer();
        selectColumsFromTradeSummaryView.append("select v.summary_date,v.day_in_amount,v.in_amount_total,v.day_out_amount,v.out_amount_total,");
        selectColumsFromTradeSummaryView.append("v.registration_date,v.registration_count,COALESCE(t.registration_total,vr.vw_registration_total) AS registration_total,");
        selectColumsFromTradeSummaryView.append("v.purchase_date,v.in_customer_count,COALESCE(t.total_in_customer_count,vp.vw_total_in_customer_count) AS total_in_customer_count,");
        selectColumsFromTradeSummaryView.append("t.out_customer_count,COALESCE(t.total_out_customer_count,vp.vw_total_out_customer_count) AS total_out_customer_count");
        String querySql = selectColumsFromTradeSummaryView.append(fromTradeSummaryView).append(" order by v.trade_date desc").toString();
        query = createNativeQueryByMap(querySql, pageVo.getFilter());
        query.setFirstResult(pageVo.getIndex());
        query.setMaxResults(pageVo.getPageSize());
        return query.getResultList();
    }
}
