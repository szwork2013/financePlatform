package com.sunlights.op.dal.impl;

import com.sunlights.common.DictConst;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.dal.ReferrerDao;
import com.sunlights.op.vo.statistics.ReferrerDetailVo;
import com.sunlights.op.vo.statistics.ReferrerVo;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/1/14.
 */
public class ReferrerDaoImpl extends EntityBaseDao implements ReferrerDao {

	@Override
	public List<ReferrerVo> findReferrers(PageVo pageVo) {
		StringBuffer xsql = new StringBuffer();

		xsql.append(" SELECT COUNT(c.recommend_phone) AS REFERRER_TOTALITY,COUNT(t.cust_id) AS PURCHASER_COUNT,SUM(t.trade_amount),c.recommend_phone,r.real_name");
		xsql.append(" FROM c_customer c");
		xsql.append(" LEFT JOIN (");
		xsql.append(" SELECT SUM(t.trade_amount) AS trade_amount,t.cust_id AS cust_id FROM t_trade t WHERE t.type = '")
				.append(DictConst.TRADE_TYPE_1).append("' GROUP BY t.cust_id");
		xsql.append(" ) AS t");
		xsql.append(" ON t.cust_id = c.customer_id");
		xsql.append(" JOIN c_customer r ON c.recommend_phone = r.mobile");
		xsql.append(" WHERE 1=1");
		xsql.append(" /~ AND r.mobile LIKE {mobile} ~/ ");
		xsql.append(" /~ AND c.create_time > {beginTime} ~/ ");
		xsql.append(" /~ AND c.create_time <= {endTime} ~/ ");
		xsql.append(" GROUP BY c.recommend_phone,r.real_name");

		String countSql = "select count(1) from (" + xsql.toString() + ") as rs";

		Object beginTime = pageVo.get("GED_beginTime");
		if (beginTime != null) {
			Date date = CommonUtil.stringToDateTime(beginTime.toString());
			pageVo.put("GED_beginTime", date);

		}
		Object endTime = pageVo.get("LTD_endTime");
		if (endTime != null) {
			Date date = CommonUtil.stringToDateTime(endTime.toString());
			pageVo.put("LTD_endTime", date);
		}

		Query query = createNativeQueryByMap(countSql, pageVo.getFilter());
		int count = Integer.valueOf(query.getSingleResult().toString());
		pageVo.setCount(count);

		query = createNativeQueryByMap(xsql.toString(), pageVo.getFilter());
		query.setFirstResult(pageVo.getIndex());
		query.setMaxResults(pageVo.getPageSize());
		List list = query.getResultList();

		String fields = "referrerTotality,purchaserCount,recommendedPurchaseAmount,mobile,name";
		List<ReferrerVo> referrerVos = ConverterUtil.convert(fields, list, ReferrerVo.class);

		return referrerVos;
	}

	@Override
	public List<ReferrerDetailVo> findReferrerDetails(PageVo pageVo) {
		StringBuffer xsql = new StringBuffer();
		xsql.append(" SELECT DISTINCT c.mobile,c.real_name,c.create_Time,t.trade_amount");
		xsql.append(" FROM c_customer c");
		xsql.append(" LEFT JOIN (");
		xsql.append(" SELECT SUM(t.trade_amount) AS trade_amount,t.cust_id AS cust_id FROM t_trade t WHERE t.type = '")
				.append(DictConst.TRADE_TYPE_1).append("' GROUP BY t.cust_id");
		xsql.append(" ) AS t");
		xsql.append(" ON t.cust_id = c.customer_id");
		xsql.append(" WHERE 1=1");
		xsql.append(" /~ AND c.recommend_phone = {mobile} ~/ ");

		Object tradeAmount = pageVo.get("EQS_tradeAmount");
		if (tradeAmount != null && StringUtils.isNotBlank(tradeAmount.toString())) {
			xsql.append(" AND t.trade_amount ").append(tradeAmount);
		}

		String countSql = "select count(1) from (" + xsql.toString() + ") as rs";
		Query query = createNativeQueryByMap(countSql, pageVo.getFilter());
		int count = Integer.valueOf(query.getSingleResult().toString());
		pageVo.setCount(count);

		query = createNativeQueryByMap(xsql.append(" order by c.create_Time desc").toString(), pageVo.getFilter());
		query.setFirstResult(pageVo.getIndex());
		query.setMaxResults(pageVo.getPageSize());
		List list = query.getResultList();

		String fields = "mobile,realName,registrationDate,purchaseAmount";
		List<ReferrerDetailVo> referrerDetailVos = ConverterUtil.convert(fields, list, ReferrerDetailVo.class);

		return referrerDetailVos;
	}

}
