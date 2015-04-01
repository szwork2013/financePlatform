package com.sunlights.op.service.impl;

import com.sunlights.common.DictConst;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.dal.PageDao;
import com.sunlights.common.dal.impl.PageDaoImpl;
import com.sunlights.common.service.PageService;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.customer.service.HoldRewardService;
import com.sunlights.customer.service.RewardFlowService;
import com.sunlights.customer.service.impl.HoldRewardServiceImpl;
import com.sunlights.customer.service.impl.RewardFlowServiceImpl;
import com.sunlights.customer.vo.HoldRewardVo;
import com.sunlights.customer.vo.RewardFlowVo;
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
	private HoldRewardService holdRewardService = new HoldRewardServiceImpl();
	private RewardFlowService rewardFlowService = new RewardFlowServiceImpl();

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
	public void saveCustomer(CustomerVo customerVo) {
		Customer customer = entityBaseDao.find(Customer.class, customerVo.getId());
		customer.setEmail(customerVo.getEmail());
		customer.setMobile(customerVo.getMobilePhoneNo());
		customer.setNickName(customerVo.getNickName());
		customer.setUpdateTime(new Date());
		entityBaseDao.update(customer);
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
		xsql.append(" .BankCardVo(c) from BankCard c , Customer u");
		xsql.append(" where c.customerId = u.customerId");
		xsql.append(" /~ and u.mobile = {telephone} ~/ ");
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
	public List<FundTradeVo> findFundTradeVos(PageVo pageVo) {
		StringBuffer fromSql = new StringBuffer();
		fromSql.append(" FROM t_trade t LEFT JOIN fundnav f ON t.product_code = f.fundcode join c_customer c on t.cust_id = c.customer_id");
		fromSql.append(" WHERE 1=1");
		fromSql.append(" /~ and u.mobile = {telephone} ~/ ");

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

	@Override
	public CustomerVo findBalanceByCustomer(CustomerVo customerVo) {
		HoldRewardVo totalReward = holdRewardService.getTotalReward(customerVo.getCustomerId());
		customerVo.setBalance(totalReward.getTotalCash());
		customerVo.setRedPacketAmount(totalReward.getRedPacket());
		customerVo.setGoldenBeanCount(totalReward.getTotalReward());
		return customerVo;
	}

	@Override
	public List<RewardFlowVo> findExchanges(PageVo pageVo) {
		return rewardFlowService.getMyFlowDetail(pageVo);
	}

	@Override
	public CustomerVo findCustomerByMobile(String mobile) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT c.customer_id,c.real_name,c.mobile,c.status,c.identity_number,c.email,c.create_time,l.login_time,b.account_create_time,b.bank_card_count,r.purchaser_count");
		sql.append(" FROM c_customer c");
		sql.append(" LEFT JOIN (SELECT l.customer_id,MAX(l.login_time) AS login_time FROM c_login_history l GROUP BY l.customer_id) AS l ON c.customer_id = l.customer_id");
		sql.append(" LEFT JOIN (SELECT b.customer_id,MIN(b.create_time) AS account_create_time,COUNT(1) AS bank_card_count FROM c_bank_card b GROUP BY b.customer_id) AS b ON c.customer_id = b.customer_id");
		sql.append(" LEFT JOIN (SELECT c.recommend_phone,COUNT (DISTINCT( t.cust_id)) AS purchaser_count FROM t_trade t JOIN c_customer c ON c.customer_id = t.cust_id WHERE t.type = 'FP.TRADE.TYPE.1' GROUP BY c.recommend_phone) AS r ON c.mobile = r.recommend_phone");
		sql.append(" WHERE c.mobile = ?1");

		List<Object[]> list = entityBaseDao.createNativeQuery(sql.toString(), mobile);

		String fields = "customerId,userName,mobilePhoneNo,status,idCardNo,email,registrationDate,loginTime,accountCreateTime,bankCardCount,purchaserCount";
		List<CustomerVo> customerVos = ConverterUtil.convert(fields, list, CustomerVo.class);

		return customerVos.isEmpty() ? null : customerVos.get(0);
	}
}
