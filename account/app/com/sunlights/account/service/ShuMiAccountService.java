package com.sunlights.account.service;

import com.sunlights.account.vo.ShuMiAccountVo;
import com.sunlights.common.exceptions.ConverterException;
import models.ShuMiAccount;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: ShuMiAccountService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public interface ShuMiAccountService {
    public ShuMiAccount saveShuMiAccount(ShuMiAccountVo shuMiAccountVo, String token) throws ConverterException;

}
