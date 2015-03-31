package com.sunlights.op.service.impl;

import com.sunlights.common.DictConst;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.dal.PageDao;
import com.sunlights.common.dal.impl.PageDaoImpl;
import com.sunlights.common.service.PageService;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.CustomerService;
import com.sunlights.op.vo.BankCardVo;
import com.sunlights.op.vo.CustomerVo;
import com.sunlights.op.vo.FundTradeVo;
import com.sunlights.op.vo.statistics.ReferrerDetailVo;
import models.Customer;
import models.MessageSmsTxn;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by Yuan on 2015/3/11.
 */
public class CustomerServiceImpl implements CustomerService {

	private EntityBaseDao entityBaseDao = new EntityBaseDao();
	private PageService pageService = new PageService();

	@Override
	public List<CustomerVo> findCustomersBy(PageVo pageVo) {
		StringBuilder xsql = new StringBuilder();

		xsql.append(" select new ");
		xsql.append(CustomerVo.class.getPackage().getName());
		xsql.append(" .CustomerVo(c) from Customer c");
		xsql.append(" where 1=1");
		xsql.append(" /~ and c.nickName like {nickName} ~/ ");
		xsql.append(" /~ and c.realName like {userName} ~/ ");
		xsql.append(" /~ and c.mobile like {mobilePhoneNo} ~/ ");
		xsql.append(" /~ and c.identityNumber like {idCardNo} ~/ ");
		xsql.append(" /~ and c.email like {email} ~/ ");
		xsql.append(" /~ and c.status = {locked} ~/ ");
		xsql.append(" /~ and c.customerId = {customerId} ~/ ");
		xsql.append(" /~ and c.mobile = {telephone} ~/ ");

		List<CustomerVo> customerVos = pageService.findXsqlBy(xsql.toString(), pageVo);
		return customerVos;
	}

	@Override
	public void unlock(Long customerId) {
		Customer customer = entityBaseDao.find(Customer.class, customerId);

		customer.setStatus(DictConst.CUSTOMER_STATUS_NARMAL);
		customer.setUpdateTime(new Date());

		entityBaseDao.update(customer);
	}

	@Override
	public MessageSmsTxn createMessageSmsTxn(MessageSmsTxn messageSmsTxn) {
		return entityBaseDao.create(messageSmsTxn);
	}

	@Override
	public List<BankCardVo> findBankCardsBy(PageVo pageVo) {
		StringBuffer xsql = new StringBuffer();
		xsql.append(" select new ");
		xsql.append(BankCardVo.class.getPackage().getName());
		xsql.append(" .BankCardVo(c) from BankCard c");
		xsql.append(" /~ and c.customerId = {customerId} ~/ ");
		List<BankCardVo> bankCardVos = pageService.findXsqlBy(xsql.toString(), pageVo);
		return bankCardVos;
	}

	@Override
	public List<ReferrerDetailVo> findReferrerDetailsBy(PageVo pageVo) {
		StringBuffer xsql = new StringBuffer();
		xsql.append(" SELECT DISTINCT ON (t.cust_id) t.cust_id,c.mobile,c.real_name,t.trade_time,t.trade_amount");
		xsql.append(" FROM t_trade t JOIN c_customer c ON c.customer_id = t.cust_id");
		xsql.append(" JOIN c_customer r ON c.recommend_phone = r.mobile");
		xsql.append(" WHERE t.type = '" + DictConst.TRADE_TYPE_1 + "'");
		xsql.append(" /~ and r.customerId = {customerId} ~/ ");
		xsql.append(" ORDER BY t.cust_id,t.trade_time");

		String countSql = "select count(1) from (" + xsql.toString() + ") as rs";
		Query query = entityBaseDao.createNativeQueryByMap(countSql, pageVo.getFilter());
		int count = Integer.valueOf(query.getSingleResult().toString());
		pageVo.setCount(count);

		query = entityBaseDao.createNativeQueryByMap(xsql.toString(), pageVo.getFilter());
		query.setFirstResult(pageVo.getIndex());
		query.setMaxResults(pageVo.getPageSize());
		List list = query.getResultList();

		String fields = "custId,mobile,realName,tradeDate,purchaseAmount";
		List<ReferrerDetailVo> referrerDetailVos = ConverterUtil.convert(fields, list, ReferrerDetailVo.class);

		return referrerDetailVos;
	}

	@Override
	public List<FundTradeVo> findFundTradeVos (PageVo pageVo) {
		StringBuffer fromSql = new StringBuffer();
		fromSql.append(" FROM t_trade t LEFT JOIN fundnav f ON t.product_code = f.fundcode");
		fromSql.append(" WHERE 1=1");
		fromSql.append(" /~ and t.cust_id = {customerId} ~/ ");

		String countSql = " select count(1)" + fromSql.toString();
		Query query = entityBaseDao.createNativeQueryByMap(countSql, pageVo.getFilter());
		int count = Integer.valueOf(query.getSingleResult().toString());
		pageVo.setCount(count);

		String querySql = " SELECT t.product_code,COALESCE(t.product_name, f.fundname) AS product_name,f.is_monetary" + fromSql.toString();
		query = entityBaseDao.createNativeQueryByMap(querySql, pageVo.getFilter());
		query.setFirstResult(pageVo.getIndex());
		query.setMaxResults(pageVo.getPageSize());
		List list = query.getResultList();

		String fields = "fundCode,fundName,fundCode";
		List<FundTradeVo> fundTradeVos = ConverterUtil.convert(fields, list, FundTradeVo.class);

		return fundTradeVos;
	}
}
