package com.sunlights.core.integration;

/**
 * <p>Project: fsp</p>
 * <p>Title: LoginClient.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public interface CommonClient {
    public String findCustomerIdByToken(String token);
    public String findIdCardNoByToken(String token);
}
