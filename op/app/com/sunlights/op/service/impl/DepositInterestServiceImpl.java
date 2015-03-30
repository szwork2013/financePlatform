package com.sunlights.op.service.impl;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.exceptions.BusinessRuntimeException;
import com.sunlights.common.service.PageService;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.DepositInterestService;
import com.sunlights.op.vo.DepositInterestVo;
import models.DepositInterest;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2014/12/16.
 */
public class DepositInterestServiceImpl extends EntityBaseDao implements DepositInterestService {

	private PageService pageService = new PageService();

	@Override
	public List<DepositInterestVo> findDepositInterests(PageVo pageVo) {

		StringBuilder xsql = new StringBuilder();

		xsql.append(" select new com.sunlights.op.vo.DepositInterestVo(di) from DepositInterest di");
		xsql.append(" where 1=1");
		xsql.append(" /~ and di.date > {beginDate} ~/ ");
		xsql.append(" /~ and di.date <= {endDate} ~/ ");
		xsql.append(" order by di.date desc");

		DateConverter dateConverter = new DateConverter(null);
		dateConverter.setPatterns(new String[] { "yyyy-MM-dd", "yyyy/MM/dd" });
		ConvertUtils.register(dateConverter, Date.class);

		List<DepositInterestVo> depositInterestVos = pageService.findXsqlBy(xsql.toString(), pageVo);
		return depositInterestVos;
	}

	@Override
	public void create(DepositInterestVo depositInterestVo) {
		if (hasDepositInterest(depositInterestVo)) {
			throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.DEPOSIT_INTEREST_EXIST_ERROR));
		}
		try {

			Date date = new Date();
			DepositInterest depositInterest = depositInterestVo.convertToDepositInterest();
			depositInterest.setCreateTime(date);
			depositInterest.setUpdateTime(date);
			create(depositInterest);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(DepositInterestVo depositInterestVo) {
		if (hasDepositInterest(depositInterestVo)) {
			throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.DEPOSIT_INTEREST_EXIST_ERROR));
		}
		try {
			Date date = new Date();
			DepositInterest depositInterest = depositInterestVo.convertToDepositInterest();
			depositInterest.setUpdateTime(date);
			update(depositInterest);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(DepositInterestVo depositInterestVo) {
		DepositInterest depositInterest = find(DepositInterest.class, depositInterestVo.getId());
		delete(depositInterest);
	}

	private boolean hasDepositInterest(DepositInterestVo depositInterestVo) {
		String sql = " SELECT * FROM p_deposit_interest pdi where (date (?1) - date (pdi.\"DATE\")) = 0";
		if (depositInterestVo.getId() != null) {
			sql = sql + " and pdi.id <> " + depositInterestVo.getId();
		}
		List<Object> result = super.createNativeQuery(sql, depositInterestVo.getDate());
		return !result.isEmpty();
	}
}
