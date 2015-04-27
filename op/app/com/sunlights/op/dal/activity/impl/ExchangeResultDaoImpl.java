package com.sunlights.op.dal.activity.impl;

import com.google.common.collect.Maps;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.dal.activity.ExchangeResultDao;
import com.sunlights.op.vo.activity.ExchangeBeanResultVo;
import com.sunlights.op.vo.activity.ExchangeResultVo;
import models.ExchangeResult;
import models.HoldReward;
import models.RewardFlow;
import play.Logger;

import javax.persistence.Query;
import java.util.*;

/**
 * Created by Administrator on 2014/12/11.
 */
public class ExchangeResultDaoImpl extends EntityBaseDao implements ExchangeResultDao {

	@Override
	public void updateStatus(Long id, Integer status) {
		String sql = "update F_EXCHANGE_RESULT set status = ? where id = ?";
		Map<String, Object> filterMap = Maps.newHashMapWithExpectedSize(5);

		filterMap.put("1", status);
		filterMap.put("2", id);

		createNativeQuery(sql, filterMap).executeUpdate();

	}

	@Override
	public void doUpdateHoldReward(HoldReward holdReward) {
		holdReward.setUpdateTime(new Date());
		super.update(holdReward);
	}

	@Override
	public RewardFlow saveRewardFlow(RewardFlow rewardFlow) {
		return create(rewardFlow);
	}

	@Override
	public RewardFlow findRewardFlowById(Long id) {
		List<RewardFlow> rewardFlowList = super.findBy(RewardFlow.class, "id", id);
		return rewardFlowList.isEmpty() ? null : rewardFlowList.get(0);
	}

	@Override
	public RewardFlow updateRewardFlow(RewardFlow rewardFlow) {
		return update(rewardFlow);
	}

	@Override
	public List<HoldReward> findHoldReward(String custId, String rewardType, String activityType, boolean isLock) {
		StringBuilder sb = new StringBuilder();
		String keys = "id,custId,activityType,rewardType,getReward,frozenReward,holdReward,getMoney,holdMoney,frozenMoney,createTime ";
		String columns = " a.id,  a.customer_Id,a.ACTIVITY_TYPE,a.REWARD_TYPE, a.GET_AMOUNT,a.FROZEN_REWARD,a.HOLD_REWARD,a.GET_MONEY,a.HOLD_MONEY, a.FROZEN_MONEY, a.CREATE_TIME ";
		sb.append("select ").append(columns).append("from F_REWARD_COUNT a ");
		sb.append(" where 1 = 1 ");
		sb.append("/~  and a.customer_Id  = {custId} ~/");
		sb.append("/~  and a.REWARD_TYPE  = {rewardType} ~/ ");
		sb.append("/~  and a.ACTIVITY_TYPE  = {activityType} ~/");
		if (isLock) {
			sb.append(" for update ");
		}

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("EQS_custId", custId);
		params.put("EQS_rewardType", rewardType);
		params.put("EQS_activityType", activityType);

		List<Object[]> resultRows = createNativeQueryByMap(sb.toString(), params).getResultList();
		List<HoldReward> holdRewards = ConverterUtil.convert(keys, resultRows, HoldReward.class);

		return holdRewards;
	}

	@Override
	public ExchangeResult findById(Long id) {
		List<ExchangeResult> exchangeResults = super.findBy(ExchangeResult.class, "id", id);
		if (exchangeResults == null || exchangeResults.isEmpty()) {
			return null;
		}
		return exchangeResults.get(0);
	}

	@Override
	public ExchangeResult updateExchangeResult(ExchangeResult exchangeResult) {
		return create(exchangeResult);
	}

	@Override
	public ExchangeResult createExchangeResult(ExchangeResult exchangeResult) {
		return update(exchangeResult);
	}

	@Override
	public List<ExchangeBeanResultVo> findExchangeBeanList(PageVo pageVo) {
        String selectKeys = "fer.id,fer.create_time,fer.phone,fer.amount,round(fer.exchanged_amount,2),fer.carrier_code,fer.status ,frf.reward_amt,c.mobile";
		String sql = " from f_exchange_result fer,c_customer c,f_reward_flow frf"
                + "  where fer.customer_id = c.customer_id"
				+ "    and frf.id = fer.reward_flow_id"
                + "    and frf.operator_type = '2'"
                + "    and frf.scene = 'EXC002'"
				+ "    and fer.exchange_scene = 'EXC002'"
                + "  /~ and fer.phone like {registerMobile} ~/ "
                + "  /~ and fer.status = {status} ~/ "
                + "  /~ and fer.create_time >= {beginTime} ~/ "
                + "  /~ and fer.create_time <= {endTime} ~/ ";

        String selectSql = "select " + selectKeys + sql + " order by fer.create_time desc ";

		Logger.debug(selectSql);

		Query query = createNativeQueryByMap(selectSql, pageVo.getFilter());
		query.setFirstResult(pageVo.getIndex());
		query.setMaxResults(pageVo.getPageSize());
		List list = query.getResultList();

		String countSql = " select count(1) as result " + sql;
		query = createNativeQueryByMap(countSql, pageVo.getFilter());
		int count = Integer.valueOf(query.getSingleResult().toString());

		pageVo.setCount(count);

		String keys = "id,exchangeTime,exchangeMobile,exchangeAmount,hasExchangeAmount,carrierCode,status,exchangeBean,registerMobile";
		return ConverterUtil.convert(keys, list, ExchangeBeanResultVo.class);

	}

	public int updateBatchResult(List<Long> exchangeResultIds) {
		StringBuffer jpql = new StringBuffer();
		jpql.append(" update ExchangeResult er set er.status = 3 where er.status = 1 and er.id in ?1");
		int i = createQuery(jpql.toString(), exchangeResultIds).executeUpdate();
		Logger.info("[update status success size is ]" + i);
		return i;
	}

	// ========================================================Yuan=============================================//

	@Override
	public List<ExchangeResultVo> findRedPacketExchangeBy(PageVo pageVo) {
		Object beginTime = pageVo.get("GED_beginTime");
		if (beginTime != null) {
			pageVo.put("GED_beginTime", CommonUtil.stringToDateTime(beginTime.toString()));
		}
		Object endTime = pageVo.get("LTD_endTime");
		if (endTime != null) {
			pageVo.put("LTD_endTime", CommonUtil.stringToDateTime(endTime.toString()));
		}

		StringBuffer xsql = new StringBuffer();

		xsql.append(" select count(1)");
		xsql.append(" from F_EXCHANGE_RESULT as a");
		xsql.append(" join F_EXCAHNGE_SCENE  s on a.EXCHANGE_SCENE = s.SCENE");
		xsql.append(" join c_customer c on a.customer_id = c.customer_id ");
		xsql.append(" where 1 = 1 ");
		xsql.append(" and s.scene = 'EXC001'");
		xsql.append("  /~and a.CUSTOMER_ID like {custId}~/ ");
		xsql.append("  /~and c.real_name like {realName}~/ ");
		xsql.append("  /~and a.STATUS = {status}~/ ");
		xsql.append("  /~and a.create_time >= {beginTime}~/ ");
		xsql.append("  /~and a.create_time <= {endTime}~/ ");
        xsql.append("  /~and a.phone like {mobile}~/ ");

		Query query = createNativeQueryByMap(xsql.toString(), pageVo.getFilter());
		int count = Integer.valueOf(query.getSingleResult().toString());
		pageVo.setCount(count);

		StringBuffer select = new StringBuffer();
		select.append("a.id,a.CUSTOMER_ID,a.EXCHANGE_SCENE,a.STATUS, a.PHONE,a.BANK_CODE, a.BANK_CARD_NO,a.AMOUNT,a.CREATE_TIME,s.title,a.bankname,c.real_name, c.mobile,a.EXCHANGED_AMOUNT,a.PAYMENT_RECEIPT_NO");
		String sql = xsql.toString().replace("count(1)", select.toString()) + " order by a.create_time desc, a.CUSTOMER_ID";

		query = createNativeQueryByMap(sql, pageVo.getFilter());
		query.setFirstResult(pageVo.getIndex());
		query.setMaxResults(pageVo.getPageSize());
		List list = query.getResultList();

		String keys = "id,custId,exchangeScene,status,phone,bankCode,bankCardNo,amount,createTime,exchangeSceneStr,bankName,realName,registerMobile,exchangedAmount,paymentReceiptNo";
		List<ExchangeResultVo> exchangeResultVos = ConverterUtil.convert(keys, list, ExchangeResultVo.class);

		Logger.debug("[export size is ]" + exchangeResultVos.size());

		return exchangeResultVos;
	}

	@Override
	public int exportSuccessfully(List<ExchangeResultVo> exchangeResultVos) {
		List<Long> ids = new ArrayList<Long>();
		for (ExchangeResultVo exchangeResultVo : exchangeResultVos) {
			ids.add(exchangeResultVo.getId());
		}
		return updateBatchResult(ids);
	}


	// ========================================================Yuan=============================================//
}
