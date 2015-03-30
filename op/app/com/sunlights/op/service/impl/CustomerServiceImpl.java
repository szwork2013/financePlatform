package com.sunlights.op.service.impl;

import com.sunlights.common.DictConst;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.service.PageService;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.CustomerService;
import com.sunlights.op.vo.CustomerVo;
import models.Customer;
import models.MessageSmsTxn;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/3/11.
 */
public class CustomerServiceImpl implements CustomerService {

	private EntityBaseDao entityBaseDao = new EntityBaseDao();

	private PageService pageService = new PageService();

	@Override
	public List<CustomerVo> findCustomersBy (PageVo pageVo) {
		StringBuilder xsql = new StringBuilder();

		xsql.append(" select new com.sunlights.op.vo.CustomerVo(c) from Customer c");
		xsql.append(" where 1=1");
		xsql.append(" /~ and c.nickName like {nickName} ~/ ");
		xsql.append(" /~ and c.realName like {userName} ~/ ");
		xsql.append(" /~ and c.mobile like {mobilePhoneNo} ~/ ");
		xsql.append(" /~ and c.identityNumber like {idCardNo} ~/ ");
		xsql.append(" /~ and c.email like {email} ~/ ");
		xsql.append(" /~ and c.status = {locked} ~/ ");
		xsql.append(" /~ and c.customerId = {customerId} ~/ ");

		List<CustomerVo> customerVos = pageService.findXsqlBy(xsql.toString(), pageVo);
		return customerVos;
	}

	@Override
	public void unlock (Long customerId) {
		Customer customer = entityBaseDao.find(Customer.class, customerId);


		customer.setStatus(DictConst.CUSTOMER_STATUS_NARMAL);
		customer.setUpdateTime(new Date());

		entityBaseDao.update(customer);
	}

	@Override
	public MessageSmsTxn createMessageSmsTxn (MessageSmsTxn messageSmsTxn) {
		return entityBaseDao.create(messageSmsTxn);
	}
}
