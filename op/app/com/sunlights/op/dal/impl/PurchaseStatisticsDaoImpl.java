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
		xsql.append(" SELECT DISTINCT ON (t.cust_id) t.cust_id,c.create_time,t.trade_amount,t.bank_name,c.mobile,c.real_name,c.recommend_phone,r.real_name as referrer_name");
		xsql.append(" FROM t_trade t");
		xsql.append(" JOIN c_customer c ON t.cust_id = c.customer_id");
		xsql.append(" LEFT JOIN c_customer r ON c.recommend_phone = r.mobile");
		xsql.append(" WHERE t.type = '").append(DictConst.TRADE_TYPE_1).append("'");
		xsql.append("  /~and c.mobile like {mobile}~/ ");
		xsql.append("  /~and c.create_time >= {beginTime}~/ ");
		xsql.append("  /~and c.create_time <= {endTime}~/ ");
		xsql.append(" ORDER BY t.cust_id,t.create_time");

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
		xsql.append(" SELECT t.cust_id FROM t_trade t WHERE t.type = '" + DictConst.TRADE_TYPE_1 + "'");
		xsql.append(" )");

		Query query = createNativeQueryByMap(xsql.toString(), pageVo.getFilter());
		int count = Integer.valueOf(query.getSingleResult().toString());
		pageVo.setCount(count);

		String querySql = xsql.append(" order by c.create_time desc").toString().replace("count(1)","c.customer_id,c.create_time,c.real_name,c.mobile,c.recommend_phone,r.real_name as referrerName");
		query = createNativeQueryByMap(querySql, pageVo.getFilter());
		query.setFirstResult(pageVo.getIndex());
		query.setMaxResults(pageVo.getPageSize());
		List list = query.getResultList();

		String fields = "customerId,registrationDate,name,mobile,referrerMobile,referrerName";
		List<PurchaseStatisticsVo> purchaseStatisticsVos = ConverterUtil.convert(fields, list, PurchaseStatisticsVo.class);

		return purchaseStatisticsVos;
	}

	@Override
	public List<TradeSummaryVo> findTradeSummaryVos (PageVo pageVo) {
		Object beginTime = pageVo.get("GED_beginTime");
		if (beginTime != null) {
			pageVo.put("GED_beginTime", CommonUtil.stringToDateTime(beginTime.toString()));
		}
		Object endTime = pageVo.get("LTD_endTime");
		if (endTime != null) {
			pageVo.put("LTD_endTime", CommonUtil.stringToDateTime(endTime.toString()));
		}

		StringBuffer select = new StringBuffer();
		select.append(" t.summary_date,t.day_in_amount,t.in_amount_total,t.day_out_amount,t.out_amount_total,");
		select.append(" t.registration_date,t.registration_count,COALESCE(t.registration_total,vr.vw_registration_total) AS registration_total,");
		select.append(" t.purchase_date,t.in_customer_count,COALESCE(t.total_in_customer_count,vp.vw_total_in_customer_count) AS total_in_customer_count,t.out_customer_count,COALESCE(t.total_out_customer_count,vp.vw_total_out_customer_count) AS total_out_customer_count");

		StringBuffer xsql = new StringBuffer();
		xsql.append(" SELECT count(1)");
		xsql.append(" FROM total_trade_summary t");
		xsql.append(" LEFT JOIN view_total_purchase_customer vp ON t.summary_date = vp.vw_purchase_date");
		xsql.append(" LEFT JOIN view_total_registration_info vr ON t.summary_date = vr.vw_registration_date");
		xsql.append(" WHERE 1=1");
		xsql.append("  /~and t.summary_date >= {beginTime}~/ ");
		xsql.append("  /~and t.summary_date <= {endTime}~/ ");

		Query query = createNativeQueryByMap(xsql.toString(), pageVo.getFilter());
		int count = Integer.valueOf(query.getSingleResult().toString());
		pageVo.setCount(count);

		String querySql = xsql.append(" order by t.trade_date desc").toString().replace("count(1)", select.toString());
		query = createNativeQueryByMap(querySql, pageVo.getFilter());
		query.setFirstResult(pageVo.getIndex());
		query.setMaxResults(pageVo.getPageSize());
		List list = query.getResultList();

		String fields = "tradeDate,dayInAmount,inAmountTotal,dayOutAmount,outAmountTotal,registrationDate,registrationCount,registrationTotal,purchaseDate,inCustomerCount,totalInCustomerCount,outCustomerCount,totalOutCustomerCount";
		List<TradeSummaryVo> tradeSummaryVos = ConverterUtil.convert(fields, list, TradeSummaryVo.class);
		return tradeSummaryVos;
	}
}
