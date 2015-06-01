package com.sunlights.op.service.impl;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.exceptions.BusinessRuntimeException;
import com.sunlights.common.service.PageService;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.FinancialPlannerService;
import com.sunlights.op.vo.FinancialPlannerVo;
import models.FinancialPlanner;

import java.util.List;

/**
 * Created by Yuan on 2015/3/6.
 */
public class FinancialPlannerServiceImpl implements FinancialPlannerService {

	private EntityBaseDao entityBaseDao = new EntityBaseDao();

	private PageService pageService = new PageService();

	@Override
	public List<FinancialPlannerVo> findFinancialPlannersBy(PageVo pageVo) {
		StringBuilder xsql = new StringBuilder();

		xsql.append(" select new " + FinancialPlannerVo.class.getPackage().getName() + ".FinancialPlannerVo(u) from FinancialPlanner u");
		xsql.append(" where 1=1");
		xsql.append(" /~ and u.name like {name} ~/ ");
		xsql.append(" /~ and u.mobilePhone like{mobilePhone} ~/ ");
		xsql.append(" order by u.createTime desc");

		List<FinancialPlannerVo> financialPlannerVos = pageService.findXsqlBy(xsql.toString(), pageVo);
		return financialPlannerVos;
	}

	@Override
	public void save(FinancialPlannerVo financialPlannerVo) {

		hasFinancialPlanner(financialPlannerVo);
		FinancialPlanner financialPlanner = financialPlannerVo.convertToFinancialPlanner();
		entityBaseDao.update(financialPlanner);

	}

	private boolean hasFinancialPlanner(FinancialPlannerVo financialPlannerVo) {
		StringBuffer jpql = new StringBuffer();
		jpql.append(" select u from FinancialPlanner u");
		jpql.append(" where u.mobilePhone = '" + financialPlannerVo.getMobilePhone().trim() + "'");
		if (financialPlannerVo.getId() != null) {
			jpql.append(" and u.id <> ").append(financialPlannerVo.getId());
		}
		List<FinancialPlanner> financialPlanners = entityBaseDao.find(jpql.toString());
		if(!financialPlanners.isEmpty()) {
			throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.PLANNER_PHONE_EXIST_ERROR));
		}
		return false;
	}

}
