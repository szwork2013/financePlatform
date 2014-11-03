package com.sunlights.account.dal.impl;

import com.sunlights.account.dal.BaseAccountDao;
import models.BaseAccount;
import models.SubAccount;
import com.sunlights.common.dal.EntityBaseDao;

import java.util.List;

public class BaseAccountDaoImpl extends EntityBaseDao implements BaseAccountDao {

	@Override
	public void saveBaseAccount(BaseAccount baseAccount) {
		super.create(baseAccount);
	}

    @Override
    public void updateBaseAccount(BaseAccount baseAccount) {
        update(baseAccount);
    }

    @Override
	public BaseAccount getBaseAccount(String custId) {
		return super.findUniqueBy(BaseAccount.class, "custId", custId);
	}

    public List<SubAccount> findSubAccountList(String customerId){
        return super.findBy(SubAccount.class, "custId", customerId);
    }

}
