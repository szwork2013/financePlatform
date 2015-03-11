package com.sunlights.customer.vo;

import models.Authentication;
import models.Customer;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: AuthenticationVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class AuthenticationVo {
    private Authentication authentication;
    private Customer customer;
    private String password;

    public AuthenticationVo(Authentication authentication, Customer customer){
        this.authentication = authentication;
        this.customer = customer;
    }

    public Authentication getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
