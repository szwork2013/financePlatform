package com.sunlights.account.dal;

import com.sunlights.account.models.BaseAccount;

/**
 * 基本账户DAO接口
 * 
 * @author tangweiqun 2014/10/23
 *
 */
public interface BaseAccountDao {
	
	public void saveBaseAccount(BaseAccount baseAccount);
    public void updateBaseAccount(BaseAccount baseAccount);
	public BaseAccount getBaseAccount(String custId);

}
