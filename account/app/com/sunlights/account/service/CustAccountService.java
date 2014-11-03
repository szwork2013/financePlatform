package com.sunlights.account.service;

import models.BaseAccount;
import com.sunlights.customer.vo.CustomerFormVo;

import java.math.BigDecimal;

/**
 * 
 * @author tangweiqun 2014/10/23
 *
 */
public interface CustAccountService {
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

    /**
     * 修改交易密码验证
     * @param vo
     * @return
     */
    public void resetTradePwdCertify(CustomerFormVo vo);

    /**
     * 修改交易密码
     * @return
     */
    public BaseAccount resetTradePwd(CustomerFormVo customerFormVo, String token);

    /**
     * 实名验证和交易密码设置
     * @param customerFormVo
     * @param token
     * @param remoteAddress
     * @return
     */
    public void certifyAndResetTradePwd(CustomerFormVo customerFormVo, String token, String remoteAddress);
}
