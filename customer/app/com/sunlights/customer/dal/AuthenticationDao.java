package com.sunlights.customer.dal;

import com.sunlights.customer.vo.AuthenticationVo;
import models.Authentication;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: AutherticationDao.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public interface AuthenticationDao {
    
    public AuthenticationVo findAuthenticationVo(String userName);
    
    public Authentication findAuthentication(String userName, String password);

    public Authentication findAuthentication(String userName);

    public Authentication createAuthentication(Authentication authentication);

    public Authentication updateAuthentication(Authentication authentication);
}
