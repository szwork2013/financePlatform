package com.sunlights.account.facade;

import java.math.BigDecimal;

/**
 * 客户理财账户管理
 * 		包括对客户的基本账户和子账户的管理：
 * 			1：账户的注册
 * 			2：账户信息的查询等操作
 * @author tangweiqun   2014/10/22
 *
 */
public interface CustAccountFacade {
	
	/**
	 * 客户注册基本账户
	 * 
	 * @param custId	客户号
	 * @param tradePassword	交易密码
	 * @return	true 表示注册成功    false表示注册失败
	 */
	public boolean registerBaseAccount(String custId, String tradePassword);
	
	/**
	 * 注册子账户
	 * @param custId	客户号
	 * @param baseAccount	该客户所属的基本账户
	 * @param prdCode	产品编码
	 * @return	true 表示注册成功    false表示注册失败
	 */
	public boolean registerSubAccount(String custId, String baseAccount, String prdCode);
	
	/**
	 * 记账
	 * @param custId	客户号
	 * @param prdCode	产品编码
	 * @param tradeNo	交易流水号
	 * @param tradeType	交易类型
	 * @param amount	变动金额
	 */
	public void dealAccount(String custId, String prdCode, String tradeNo, String tradeType, BigDecimal amount);
	
}
