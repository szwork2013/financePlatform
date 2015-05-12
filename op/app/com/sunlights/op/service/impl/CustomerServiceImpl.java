package com.sunlights.op.service.impl;

import com.google.common.collect.Lists;
import com.sunlights.common.DictConst;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.service.CommonService;
import com.sunlights.common.service.PageService;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.customer.dal.HoldRewardDao;
import com.sunlights.customer.dal.RewardAccountDao;
import com.sunlights.customer.dal.RewardAccountDetailsDao;
import com.sunlights.customer.dal.impl.HoldRewardDaoImpl;
import com.sunlights.customer.dal.impl.RewardAccountDaoImpl;
import com.sunlights.customer.dal.impl.RewardAccountDetailsDaoImpl;
import com.sunlights.customer.service.HoldRewardService;
import com.sunlights.customer.service.RewardFlowService;
import com.sunlights.customer.service.impl.HoldRewardServiceImpl;
import com.sunlights.customer.service.impl.RewardFlowServiceImpl;
import com.sunlights.customer.vo.HoldRewardVo;
import com.sunlights.customer.vo.RewardFlowVo;
import com.sunlights.op.service.CustomerService;
import com.sunlights.op.vo.*;
import com.sunlights.op.vo.statistics.ReferrerDetailVo;
import models.*;

import javax.persistence.Query;
import java.math.BigDecimal;
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

    private RewardAccountDao rewardAccountDao = new RewardAccountDaoImpl();
    private RewardAccountDetailsDao rewardAccountDetailsDao = new RewardAccountDetailsDaoImpl();
    private HoldRewardDao holdRewardDao = new HoldRewardDaoImpl();

    private com.sunlights.customer.service.impl.CustomerService customerService = new com.sunlights.customer.service.impl.CustomerService();



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
		xsql.append(" SELECT DISTINCT ON (t.cust_id) t.cust_id,c.mobile,c.real_name,c.create_time,t.trade_time,t.trade_amount");
		xsql.append(" FROM t_trade t JOIN c_customer c ON c.customer_id = t.cust_id");
		xsql.append(" JOIN c_customer r ON c.recommend_phone = r.mobile");
		xsql.append(" WHERE t.type = '" + DictConst.TRADE_TYPE_1 + "'");
		xsql.append(" /~ and r.customerId = {customerId} ~/ ");
		xsql.append(" /~ and c.recommend_phone = {telephone} ~/ ");
		xsql.append(" ORDER BY t.cust_id,t.trade_time");

		String countSql = "select count(1) from (" + xsql.toString() + ") as rs";
		Query query = entityBaseDao.createNativeQueryByMap(countSql, pageVo.getFilter());
		int count = Integer.valueOf(query.getSingleResult().toString());
		pageVo.setCount(count);

		query = entityBaseDao.createNativeQueryByMap(xsql.toString(), pageVo.getFilter());
		query.setFirstResult(pageVo.getIndex());
		query.setMaxResults(pageVo.getPageSize());
		List list = query.getResultList();

		String fields = "custId,mobile,realName,registrationDate,tradeDate,purchaseAmount";
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
		sql.append(" SELECT c.customer_id,c.real_name,c.mobile,c.status,c.identity_number,c.email,c.create_time,c.recommend_phone,u.real_name as referrer,l.login_time,b.account_create_time,b.bank_card_count,r.purchaser_count,r.purchaser_date");
		sql.append(" FROM c_customer c");
		sql.append(" LEFT JOIN c_customer u ON c.recommend_phone = u.mobile");
		sql.append(" LEFT JOIN (SELECT l.customer_id,MAX(l.login_time) AS login_time FROM c_login_history l GROUP BY l.customer_id) AS l ON c.customer_id = l.customer_id");
		sql.append(" LEFT JOIN (SELECT b.customer_id,MIN(b.create_time) AS account_create_time,COUNT(1) AS bank_card_count FROM c_bank_card b GROUP BY b.customer_id) AS b ON c.customer_id = b.customer_id");
		sql.append(" LEFT JOIN (SELECT c.recommend_phone,COUNT (DISTINCT( t.cust_id)) AS purchaser_count,min(t.trade_time) as purchaser_date FROM t_trade t JOIN c_customer c ON c.customer_id = t.cust_id WHERE t.type = 'FP.TRADE.TYPE.1' GROUP BY c.recommend_phone) AS r ON c.mobile = r.recommend_phone");
		sql.append(" WHERE c.mobile = ?1");

		List<Object[]> list = entityBaseDao.createNativeQuery(sql.toString(), mobile);

		String fields = "customerId,userName,mobilePhoneNo,status,idCardNo,email,registrationDate,referrerMobile,referrer,loginTime,accountCreateTime,bankCardCount,purchaserCount,purchaserDate";
		List<CustomerVo> customerVos = ConverterUtil.convert(fields, list, CustomerVo.class);
		if( customerVos.isEmpty() ) {
			return null;
		}
		CustomerVo customerVo = customerVos.get(0);
		customerVo.setStatus(new CommonService().findValueByCatPointKey(customerVo.getStatus()));
		return customerVo;
	}

    @Override
    public RewardStatisticVo findRewardByMobile(String mobile) {
        Customer customer = customerService.getCustomerByMobile(mobile);
        RewardStatisticVo rewardStatisticVo = new RewardStatisticVo();
        if(customer == null) {
            return rewardStatisticVo;
        }

        String customerId = customer.getCustomerId();
        String realName = customer.getRealName();


        rewardStatisticVo.setCustomerName(realName);
        rewardStatisticVo.setMobile(mobile);


        RewardAccountBalance rewardAccountBalance = rewardAccountDao.findRewardAccountByCustomerId(customerId);

        if(rewardAccountBalance == null) {
            return rewardStatisticVo;
        }


        rewardStatisticVo.setRewardAcctBalance(rewardAccountBalance.getRewardAccountBalance().toString());

        List<HoldReward> holdRewards = holdRewardDao.findListByCustIdAndRewardType(customerId, null, false);
        Long goldBeanNum = 0L;
        Long canExchangeBeans = 0L;
        Long alreadyExchangeBeans = 0L;
        BigDecimal redPacketNum = BigDecimal.valueOf(0);
        BigDecimal canExchangeRedPacketNum = BigDecimal.valueOf(0);
        BigDecimal alreadyEachangeredPacketNum = BigDecimal.valueOf(0);

        for(HoldReward holdReward : holdRewards) {
            if(RewardType.RewardTypeConstant.JINDOU.getType().equals(holdReward.getRewardType())) {
                goldBeanNum += holdReward.getGetReward();
                canExchangeBeans += holdReward.getHoldReward();
                alreadyExchangeBeans = holdReward.getGetReward() - holdReward.getHoldReward();
            }

            if(RewardType.RewardTypeConstant.REDPACKET.getType().equals(holdReward.getRewardType())) {
                redPacketNum = redPacketNum.add(holdReward.getGetMoney());
                canExchangeRedPacketNum = canExchangeRedPacketNum.add(holdReward.getHoldMoney());
                alreadyEachangeredPacketNum = alreadyEachangeredPacketNum.add(holdReward.getGetMoney().subtract(holdReward.getHoldMoney()));
            }
        }
        rewardStatisticVo.setGoldBeanNum(goldBeanNum);
        rewardStatisticVo.setCanExchangeBeans(canExchangeBeans);
        rewardStatisticVo.setAlreadyExchangeBeans(alreadyExchangeBeans);
        rewardStatisticVo.setRedPacketNum(redPacketNum.toString());
        rewardStatisticVo.setCanExchangeRedPacket(canExchangeRedPacketNum.toString());
        rewardStatisticVo.setAlreadyExchangeRedPacket(alreadyEachangeredPacketNum.toString());

        return rewardStatisticVo;
    }

    @Override
    public List<RewardItem> findRewardItemsByMobile(PageVo pageVo) {
        String mobile = ((Long)pageVo.get("EQS_telephone")).toString();
        Customer customer = customerService.getCustomerByMobile(mobile);
        List<RewardItem> rewardItems = Lists.newArrayList();
        if(customer == null) {
            return rewardItems;
        }

        String customerId= customer.getCustomerId();
        pageVo.getFilter().put("EQS_customerId", customerId);
        List<RewardAccountDetails> rewardAccountDetailsList = rewardAccountDetailsDao.getByPage(pageVo);


        RewardItem item = null;
        if(rewardAccountDetailsList != null) {
            for(RewardAccountDetails details : rewardAccountDetailsList) {
                item = new RewardItem();
                item.setScene(ActivityScene.ActivitySceneConstant.getDescByScene(details.getActivityType()));
                item.setRewardType(RewardType.RewardTypeConstant.getDescByScene(details.getRewardType()));
                item.setRewardTime(CommonUtil.dateToString(details.getCreateTime(), CommonUtil.DATE_FORMAT_SHORT));

                if(RewardAccountDetails.FundFlowType.INCOME.getType().equals(details.getFundFlowType())) {
                    item.setRewardNum(details.getIncomeExpendBalance().toString());
                }

                if(RewardAccountDetails.FundFlowType.EXPEND.getType().equals(details.getFundFlowType())) {
                    item.setExchangeMoney(details.getIncomeExpendBalance().toString());
                }

                rewardItems.add(item);
            }
        }
        return rewardItems;
    }
}
