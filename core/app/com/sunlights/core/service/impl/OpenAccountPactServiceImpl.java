package com.sunlights.core.service.impl;

import com.sunlights.core.dal.OpenAccountPactDao;
import models.OpenAccountPact;
import com.sunlights.core.service.OpenAccountPactService;
import com.sunlights.core.vo.AgreementVo;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>Project: fsp</p>
 * <p>Title: OpenAccountPactServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */

public class OpenAccountPactServiceImpl implements OpenAccountPactService {

    private OpenAccountPactDao openAccountPactDao;

    @Override
    public AgreementVo findAgreementVoByAgreementNo(String agreementNo) {
        if (StringUtils.isEmpty(agreementNo)) {
            return null;
        }
        OpenAccountPact openAccountPact = openAccountPactDao.findOpenAccountPactByAgreementNo(agreementNo);
        if (openAccountPact == null) {
            return null;
        }
        return new AgreementVo(openAccountPact);
    }
}
