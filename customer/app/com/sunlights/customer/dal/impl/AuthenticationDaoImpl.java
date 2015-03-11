package com.sunlights.customer.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.customer.dal.AuthenticationDao;
import com.sunlights.customer.vo.AuthenticationVo;
import models.Authentication;

import javax.persistence.Query;
import java.util.List;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: AutherticationDaoImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class AuthenticationDaoImpl extends EntityBaseDao implements AuthenticationDao {
    @Override
    public AuthenticationVo findAuthenticationVo(String userName) {
        String authenticationVoQuery = "select new com.sunlights.customer.vo.AuthenticationVo(a,c) from Authentication a,Customer c where c.authenticationId = a.id and a.userName = :userName";
        Query query = em.createQuery(authenticationVoQuery, AuthenticationVo.class);
        query.setParameter("userName", userName);
        List<AuthenticationVo> list = query.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Authentication findAuthentication(String userName, String password) {
        Query query = em.createNamedQuery("findAuthentication", Authentication.class);
        query.setParameter("userName", userName);
        query.setParameter("password", password);
        List<Authentication> list = query.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Authentication findAuthentication(String userName) {
        List<Authentication> list = super.findBy(Authentication.class, "userName", userName);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Authentication createAuthentication(Authentication authentication) {
        return create(authentication);
    }

    @Override
    public Authentication updateAuthentication(Authentication authentication) {
        return update(authentication);
    }
}
