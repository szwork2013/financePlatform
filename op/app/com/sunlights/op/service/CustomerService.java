package com.sunlights.op.service;

import com.sunlights.common.vo.PageVo;
import com.sunlights.customer.vo.RewardFlowVo;
import com.sunlights.op.vo.BankCardVo;
import com.sunlights.op.vo.CustomerVo;
import com.sunlights.op.vo.FundTradeVo;
import com.sunlights.op.vo.statistics.ReferrerDetailVo;
import models.MessageSmsTxn;

import java.util.List;

/**
 * <p>Project: OperationPlatform</p>
 * <p>Title: CustomerService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public interface CustomerService {

	public List<CustomerVo> findCustomersBy(PageVo pageVo);

	public void unlock(Long customerId);

	public MessageSmsTxn createMessageSmsTxn(MessageSmsTxn messageSmsTxn);

	public List<BankCardVo> findBankCardsBy(PageVo pageVo);

	public List<ReferrerDetailVo> findReferrerDetailsBy(PageVo pageVo);

	public List<FundTradeVo> findFundTradeVos(PageVo pageVo);

	public CustomerVo findBalanceByCustomer(CustomerVo customerVo);

	public List<RewardFlowVo> findExchanges(PageVo pageVo);
}
