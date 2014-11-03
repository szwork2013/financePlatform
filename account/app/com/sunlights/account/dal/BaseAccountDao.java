package com.sunlights.account.dal;

import com.sunlights.account.models.BaseAccount;
import com.sunlights.account.models.SubAccount;

import java.util.List;

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
    
    public List<SubAccount> findSubAccountList(String customerId);

}
