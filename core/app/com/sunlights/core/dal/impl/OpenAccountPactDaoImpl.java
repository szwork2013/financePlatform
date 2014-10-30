package com.sunlights.core.dal.impl;

import com.sunlights.core.dal.OpenAccountPactDao;
import com.sunlights.core.models.OpenAccountPact;
import com.sunlights.common.dal.EntityBaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>Project: fsp</p>
 * <p>Title: OpenAccountPactDaoImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@Repository
public class OpenAccountPactDaoImpl extends EntityBaseDao implements OpenAccountPactDao {
    @Override
    public OpenAccountPact findOpenAccountPactByAgreementNo(String agreementNo) {
        StringBuffer jpql = new StringBuffer();
        jpql.append(" select o from OpenAccountPact o");
        jpql.append(" where o.status = 'Y'");
        jpql.append(" and o.agreementNo = '" + agreementNo + "'");
        List<OpenAccountPact> openAccountPacts = super.find(jpql.toString());
        if (!openAccountPacts.isEmpty()) {
            return openAccountPacts.get(0);
        }
        return null;
    }
}
