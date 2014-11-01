package com.sunlights.account.dal.impl;

import com.sunlights.account.dal.BaseAccountDao;
import com.sunlights.account.models.BaseAccount;
import com.sunlights.common.dal.EntityBaseDao;

import org.springframework.stereotype.Repository;

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

}
